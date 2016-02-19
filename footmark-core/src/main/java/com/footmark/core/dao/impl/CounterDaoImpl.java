package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.CounterDao;
import com.footmark.core.entity.Counter;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class CounterDaoImpl extends GenericDaoDefault<Counter> implements CounterDao {

}
