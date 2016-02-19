package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.UserFootmarkDao;
import com.footmark.core.entity.Footmark;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class UserFootmarkDaoImpl extends GenericDaoDefault<Footmark> implements UserFootmarkDao {

}
