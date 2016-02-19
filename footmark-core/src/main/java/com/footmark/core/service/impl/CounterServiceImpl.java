package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.dao.impl.CounterDaoImpl;
import com.footmark.core.entity.Counter;
import com.footmark.core.enums.CounterSourceTypeEnum;
import com.footmark.core.enums.CounterTypeEnum;
import com.footmark.core.service.CounterService;

@Component
public class CounterServiceImpl implements CounterService {

    private static final String QUERY_IS_COUNTING = "queryIsCounting";
    private static final String QUERY_HAS_COUNTED = "queryHasCounted";
    private static final String UPDATE_STATUS = "updateStatus";
    @Autowired
    private CounterDaoImpl counterDao;

    @Override
    public Counter isCounting(long userId, long sourceId, CounterSourceTypeEnum sourceType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("sourceId", sourceId);
        paramMap.put("sourceType", sourceType);
        paramMap.put("counterType", CounterTypeEnum.LOVE);
        Counter counter = (Counter) counterDao.queryOne(QUERY_IS_COUNTING, paramMap);
        return counter;
    }

    @Override
    public void updateStatus(long id, StatusEnum status) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("status", status);
        counterDao.update(UPDATE_STATUS, paramMap);
    }

    @Override
    public Counter hasCounted(long userId, long sourceId, CounterSourceTypeEnum sourceType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("sourceId", sourceId);
        paramMap.put("sourceType", sourceType);
        paramMap.put("counterType", CounterTypeEnum.LOVE);
        Counter counter = (Counter) counterDao.queryOne(QUERY_HAS_COUNTED, paramMap);
        return counter;
    }
}
