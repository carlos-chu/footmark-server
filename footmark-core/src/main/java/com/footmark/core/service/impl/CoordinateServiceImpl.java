package com.footmark.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.footmark.core.biz.util.CoordinateScopeUtil.CoordinateScope;
import com.footmark.core.dao.impl.CoordinateDaoImpl;
import com.footmark.core.entity.Coordinate;
import com.footmark.core.service.CoordinateService;

@Component
public class CoordinateServiceImpl implements CoordinateService {

    private static final String QUERY_BY_SCOPE = "queryByScope";
    @Autowired
    private CoordinateDaoImpl coordinateDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<Coordinate> queryByScope(double latitude, double longitude, CoordinateScope scope) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("latitude", latitude);
        paramMap.put("longitude", longitude);
        paramMap.put("minLat", scope.minLatitude);
        paramMap.put("maxLat", scope.maxLatitude);
        paramMap.put("minLon", scope.minLongitude);
        paramMap.put("maxLon", scope.maxLongitude);
        return coordinateDao.query(QUERY_BY_SCOPE, paramMap);
    }

}
