package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.UserSettingDao;
import com.footmark.core.entity.UserSetting;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class UserSettingDaoImpl extends GenericDaoDefault<UserSetting> implements UserSettingDao {

}
