package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.core.dao.impl.UserInfoDaoImpl;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final String QUERY_BY_MOBILENO = "queryByMobileNo";
    private static final String QUERY_BY_IDS = "queryByIds";
    private static final String QUERY_BY_MOBILENO_AND_PSW = "queryByMobileNoAndPsw";

    @Autowired
    UserInfoDaoImpl userInfoDao;

    @Override
    public UserInfo queryByMobileNo(String mobileNo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("telNum", mobileNo);
        return (UserInfo) userInfoDao.queryOne(QUERY_BY_MOBILENO, paramMap);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        try {
            userInfoDao.add(userInfo);
        } catch (DuplicateKeyException e) {
            logger.warn("创建用户唯一索引异常，返回查询，userInfo:{}", userInfo, e);
            throw new BusinessException(ExceptionEnum.MOBILENO_EXITED_ERR);
        }
    }

    @Override
    public UserInfo queryByUserId(long userId) {
        return (UserInfo) userInfoDao.get(userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserInfo> queryByIds(List<Long> ids) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (ids != null && ids.size() > 0) {
            paramMap.put("ids", ids);
            return userInfoDao.query(QUERY_BY_IDS, paramMap);
        }
        return null;
    }

    @Override
    public UserInfo queryByMobileNoAndPsw(String mobileNo, String password) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("telNum", mobileNo);
        paramMap.put("password", password);
        return (UserInfo) userInfoDao.queryOne(QUERY_BY_MOBILENO_AND_PSW, paramMap);
    }

}
