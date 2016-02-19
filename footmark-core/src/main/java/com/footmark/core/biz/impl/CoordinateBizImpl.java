package com.footmark.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.core.biz.CoordinateBiz;
import com.footmark.core.biz.util.CoordinateScopeUtil;
import com.footmark.core.biz.util.CoordinateScopeUtil.CoordinateScope;
import com.footmark.core.entity.Coordinate;
import com.footmark.core.service.impl.CoordinateServiceImpl;

/**
 * @Description: 计算坐标实现
 * @author carlos.chu
 * @date 2015年8月30日
 */
@Service
public class CoordinateBizImpl implements CoordinateBiz {

    @Autowired
    private CoordinateServiceImpl coordinateService;

    @Override
    public List<Long> calculateCoordinate(double longitude, double latitude, double distance) {
        CoordinateScope coorScope = CoordinateScopeUtil.generatorScope(longitude, latitude, distance);
        List<Coordinate> scopeCoordinates = coordinateService.queryByScope(longitude, latitude, coorScope);
        return buildResult(scopeCoordinates);
    }

    /**
     * 对集合按照距离进行排序
     * 
     * @param scopeCoordinates
     */
    private List<Long> buildResult(List<Coordinate> scopeCoordinates) {
        List<Long> coordinateIds = new ArrayList<Long>();
        for (Coordinate c : scopeCoordinates) {
            coordinateIds.add(c.getId());
        }
        return coordinateIds;
    }
}
