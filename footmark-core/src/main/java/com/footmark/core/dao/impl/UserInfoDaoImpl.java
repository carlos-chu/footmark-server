package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.UserInfoDao;
import com.footmark.core.entity.UserInfo;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class UserInfoDaoImpl extends GenericDaoDefault<UserInfo> implements UserInfoDao {

}
