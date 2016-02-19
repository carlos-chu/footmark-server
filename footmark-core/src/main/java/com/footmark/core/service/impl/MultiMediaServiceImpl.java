package com.footmark.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.core.dao.impl.MultiMediaDaoImpl;
import com.footmark.core.dao.impl.SourceMultiDaoImpl;
import com.footmark.core.entity.SourceMulti;
import com.footmark.core.enums.SourceMultiTypeEnum;
import com.footmark.core.service.MultiMediaService;
import com.footmark.core.vo.MultiMediaVo;

/**
 * @Description: 多媒体服务实现
 * @author carlos.chu
 * @date 2015年9月10日
 */
@Service
public class MultiMediaServiceImpl implements MultiMediaService {

    private static final String QUERY_FOR_MEDIA = "queryForMedia";
    private static final String QUERY_BY_IDS = "queryByIds";
    @Autowired
    private SourceMultiDaoImpl sourceMultiDao;
    @Autowired
    private MultiMediaDaoImpl multiMediaDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<MultiMediaVo> queryBySource(long sourceId, SourceMultiTypeEnum sourceType) {
        Map<String, Object> sourceParamMap = new HashMap<String, Object>();
        sourceParamMap.put("sourceId", sourceId);
        sourceParamMap.put("sourceType", sourceType);
        List<SourceMulti> sourceMultis = sourceMultiDao.query(QUERY_FOR_MEDIA, sourceParamMap);
        if(sourceMultis != null && sourceMultis.size() > 0) {
            List<Long> multiMediaIds = new ArrayList<Long>();
            for (SourceMulti sm : sourceMultis) {
                multiMediaIds.add(sm.getMultiId());
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("ids", multiMediaIds);
            return multiMediaDao.query(QUERY_BY_IDS, paramMap);
        }
        return null;
    }

}
