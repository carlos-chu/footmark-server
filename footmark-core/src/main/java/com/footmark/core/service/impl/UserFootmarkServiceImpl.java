package com.footmark.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.core.dao.impl.UserFootmarkDaoImpl;
import com.footmark.core.dto.FootmarkDto;
import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.core.enums.FootmarkStatusEnum;
import com.footmark.core.enums.GenderEnum;
import com.footmark.core.service.UserFootmarkService;

/**
 * @Description: 用户脚印服务
 * @author carlos.chu
 * @date 2015年9月10日
 */
@Service
public class UserFootmarkServiceImpl implements UserFootmarkService {

    private static final String QUERY_BY_SCOPE = "queryByScope";
    private static final String QUERY_BY_USERID = "queryByUserId";
    @Autowired
    private UserFootmarkDaoImpl userFootmarkDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<FootmarkDto> queryByScope(GenderEnum selfGender, AccepterCategoryEnum settingGender,
            List<Long> coordinates, int from, int to) {
        if (coordinates == null || coordinates.size() <= 0) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<AccepterCategoryEnum> allowGenders = new ArrayList<>();
        if (settingGender == AccepterCategoryEnum.ALL) {
            allowGenders.add(AccepterCategoryEnum.toEnum(GenderEnum.FAMALE.getCode()));
            allowGenders.add(AccepterCategoryEnum.toEnum(GenderEnum.MALE.getCode()));
        } else {
            allowGenders.add(AccepterCategoryEnum.toEnum(selfGender.getCode()));
        }
        paramMap.put("allowGenders", allowGenders);
        paramMap.put("accepterCategory", settingGender);
        paramMap.put("status", FootmarkStatusEnum.NORMAL);
        paramMap.put("coordinates", coordinates);
        paramMap.put("from", from);
        paramMap.put("to", to);
        return userFootmarkDao.query(QUERY_BY_SCOPE, paramMap);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FootmarkDto> queryByUid(Long uid, int page, int size) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", uid);
        return userFootmarkDao.query(QUERY_BY_USERID, params);
    }
}
