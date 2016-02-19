package com.footmark.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.footmark.core.dao.CoordinateDao;
import com.footmark.core.entity.Coordinate;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class CoordinateDaoImpl extends GenericDaoDefault<Coordinate> implements CoordinateDao {

}
