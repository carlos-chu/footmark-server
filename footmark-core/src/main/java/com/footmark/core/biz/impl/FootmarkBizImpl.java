package com.footmark.core.biz.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.SystemUtil;
import com.footmark.core.biz.CoordinateBiz;
import com.footmark.core.biz.CounterBiz;
import com.footmark.core.biz.FootmarkBiz;
import com.footmark.core.biz.context.pull.DefaultPullProvider;
import com.footmark.core.biz.context.pull.PullWrapper;
import com.footmark.core.biz.context.pull.PullWrapperImpl.UserPull;
import com.footmark.core.biz.util.CoordinateScopeUtil;
import com.footmark.core.biz.util.TimeUtil;
import com.footmark.core.dao.impl.CoordinateDaoImpl;
import com.footmark.core.dao.impl.MultiMediaDaoImpl;
import com.footmark.core.dao.impl.SourceMultiDaoImpl;
import com.footmark.core.dao.impl.UserFootmarkDaoImpl;
import com.footmark.core.dto.CreateFootmarkDto;
import com.footmark.core.dto.FootmarkDto;
import com.footmark.core.dto.FootmarkPullDto;
import com.footmark.core.entity.Coordinate;
import com.footmark.core.entity.Counter;
import com.footmark.core.entity.Footmark;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.core.enums.CounterSourceTypeEnum;
import com.footmark.core.enums.SourceMultiTypeEnum;
import com.footmark.core.service.MultiMediaService;
import com.footmark.core.service.UserFootmarkService;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.vo.FootmarkVo;
import com.footmark.core.vo.MultiMediaVo;

/**
 * @Description: 脚印业务实现
 * @author carlos.chu
 * @date 2015年8月21日
 */
@Service
public class FootmarkBizImpl implements FootmarkBiz {

    @Autowired
    private UserFootmarkDaoImpl userFootmarkDao;
    @Autowired
    private CoordinateDaoImpl coordinateDao;
    @Autowired
    private MultiMediaDaoImpl multiMediaDao;
    @Autowired
    private SourceMultiDaoImpl sourceMultiDao;
    @Autowired
    private PullWrapper pullWrapper;
    @Autowired
    private CoordinateBiz coordinateBiz;
    @Autowired
    private UserFootmarkService userFootmarkService;
    @Autowired
    private UserInfoService userService;
    @Autowired
    private CounterBiz counterBiz;
    @Autowired
    private MultiMediaService multiMediaService;

    @Transactional
    public Long create(CreateFootmarkDto dto) {
        UserInfo userInfo = userService.queryByUserId(dto.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        // 1.拆分出并创建坐标
        long coordinateId = populateCoordinate(dto);
        // 2.创建userFootmark
        dto.setCoordinateId(coordinateId);
        dto.setGender(userInfo.getGender());
        userFootmarkDao.add(dto);
        return dto.getId();
    }

    @Override
    public List<FootmarkVo> pull(FootmarkPullDto dto) {
        final UserInfo userInfo = userService.queryByUserId(dto.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        return pullWrapper.pull(dto, new DefaultPullProvider(), new UserPull<FootmarkVo>() {
            // user pull logic...
            public List<FootmarkVo> pull(FootmarkPullDto dto, Map<String, Object> settingMap) {
                int from = SystemUtil.getDBFrom(dto.getPage(), dto.getSize());
                int to = dto.getSize();
                List<Long> coordinateIds = coordinateBiz.calculateCoordinate(Double.valueOf(dto.getLongitude()),
                        Double.valueOf(dto.getLatitude()),
                        Double.valueOf(String.valueOf(settingMap.get(UserSetting.SCOPE))));
                List<FootmarkDto> footmarks = userFootmarkService.queryByScope(userInfo.getGender(),
                        AccepterCategoryEnum.toEnum((Integer) settingMap.get(UserSetting.GENDER_CATEGORY)),
                        coordinateIds, from, to);
                return buildResult(dto, footmarks);
            }
        });
    }

    /**
     * 创建脚印VO
     * 
     * @param dto
     * @param footmarkDtos
     * @return
     */
    private List<FootmarkVo> buildResult(FootmarkPullDto dto, List<FootmarkDto> footmarkDtos) {
        if (footmarkDtos == null) {
            return null;
        }
        List<FootmarkVo> footmarkVos = new ArrayList<FootmarkVo>();
        for (FootmarkDto footmarkDto : footmarkDtos) {
            FootmarkVo footmarkVo = populateFootmarkVo(footmarkDto);
            double distance = CoordinateScopeUtil.distance(Double.valueOf(dto.getLongitude()),
                    Double.valueOf(dto.getLatitude()), Double.parseDouble(footmarkDto.getLongitude()),
                    Double.parseDouble(footmarkDto.getLatitude()));
            String distanceStr = String.valueOf(distance);
            String distanceInt = distanceStr.substring(0, distanceStr.lastIndexOf("."));
            footmarkVo.setDistance(distanceInt);
            Counter counter = counterBiz.queryLiked(dto.getUserId(), footmarkDto.getId(),
                    CounterSourceTypeEnum.FOOTMARK);
            if (counter != null) {
                footmarkVo.setLikeId(counter.getId());
            }
            // 查询多媒体
            List<MultiMediaVo> multiMedias = multiMediaService.queryBySource(footmarkDto.getId(),
                    SourceMultiTypeEnum.FOOTMARK);
            footmarkVo.setMultiMedias(multiMedias);
            footmarkVos.add(footmarkVo);
        }
        // 对返回结果根据距离排序
        Collections.sort(footmarkVos);
        return footmarkVos;
    }

    private FootmarkVo populateFootmarkVo(FootmarkDto footmarkDto) {
        UserInfo userInfo = userService.queryByUserId(footmarkDto.getUserId());
        FootmarkVo footmarkVo = new FootmarkVo();
        footmarkVo.setUserId(footmarkDto.getUserId());
        footmarkVo.setFootmarkId(footmarkDto.getId());
        footmarkVo.setContent(footmarkDto.getContent());
        footmarkVo.setUserName(userInfo.getName());
        footmarkVo.setAvatar(userInfo.getAvatar());
        footmarkVo.setGender(userInfo.getGender().getCode());
        footmarkVo.setCreateTime(TimeUtil.coverToColloquialLanguage(footmarkDto.getCreateTime()));
        return footmarkVo;
    }

    /**
     * 创建coordinate
     * 
     * @param dto
     * @return coordinateId
     */
    private long populateCoordinate(CreateFootmarkDto dto) {
        Coordinate coordinate = new Coordinate();
        coordinate.setHeight(dto.getHeight());
        coordinate.setLatitude(dto.getLatitude());
        coordinate.setLongitude(dto.getLongitude());
        coordinate.setLocation(dto.getLocation());
        coordinateDao.add(coordinate);
        return coordinate.getId();
    }

    @Override
    public List<FootmarkVo> queryByUid(Long uid, int page, int size) {
        List<FootmarkDto> footmarkDtos = userFootmarkService.queryByUid(uid, page, size);
        if (footmarkDtos == null) {
            return null;
        }
        List<FootmarkVo> footmarkVos = new ArrayList<>();
        for (FootmarkDto footmarkDto : footmarkDtos) {
            FootmarkVo footmarkVo = populateFootmarkVo(footmarkDto);
            Counter counter = counterBiz.queryLiked(uid, footmarkDto.getId(), CounterSourceTypeEnum.FOOTMARK);
            if (counter != null) {
                footmarkVo.setLikeId(counter.getId());
            }
            // 查询多媒体
            List<MultiMediaVo> multiMedias = multiMediaService.queryBySource(footmarkDto.getId(),
                    SourceMultiTypeEnum.FOOTMARK);
            footmarkVo.setMultiMedias(multiMedias);
            footmarkVos.add(footmarkVo);
        }
        return footmarkVos;
    }

    @Override
    public FootmarkVo queryById(Long id) {
        Footmark footmark = userFootmarkDao.get(id);
        if (footmark == null) {
            throw new BusinessException(ExceptionEnum.DATA_ISEMPTY_ERR);
        }
        UserInfo user = userService.queryByUserId(footmark.getUserId());
        FootmarkVo footmarkVo = new FootmarkVo();
        footmarkVo.setUserId(footmark.getUserId());
        footmarkVo.setFootmarkId(footmark.getId());
        footmarkVo.setContent(footmark.getContent());
        footmarkVo.setUserName(user.getName());
        footmarkVo.setAvatar(user.getAvatar());
        footmarkVo.setGender(user.getGender().getCode());
        footmarkVo.setCreateTime(TimeUtil.coverToColloquialLanguage(footmark.getCreateTime()));
        return footmarkVo;
    }
}
