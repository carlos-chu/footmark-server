package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.footmark.core.dao.impl.PushInfoDaoImpl;
import com.footmark.core.entity.PushInfo;
import com.footmark.core.service.PushInfoService;

@Service
public class PushInfoServiceImpl implements PushInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PushInfoDaoImpl pushInfoDao;

    @Override
    public void add(PushInfo pushInfo) {
        try {
            pushInfoDao.add(pushInfo);
        } catch (DuplicateKeyException e) {
            logger.error("已存在相同的记录原始pushInfo:{}", pushInfo);
        }
    }

    @Override
    public void updateByUserId(PushInfo pushInfo) {
        pushInfoDao.update("updateByUid", pushInfo);
    }

    @Override
    public PushInfo queryByUid(Long uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", uid);
        return (PushInfo) pushInfoDao.queryOne("queryByUid", params);
    }

}
