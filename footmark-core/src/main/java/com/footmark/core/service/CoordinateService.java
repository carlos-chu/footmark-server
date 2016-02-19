package com.footmark.core.service;

import java.util.List;

import com.footmark.core.biz.util.CoordinateScopeUtil.CoordinateScope;
import com.footmark.core.entity.Coordinate;

/**
 * @Description: 坐标服务接口
 * @author carlos.chu
 * @date 2015年8月30日
 */
public interface CoordinateService {

    /**
     * 根据范围查询坐标集合
     * 
     * @param CoordinateScope
     * @return
     */
    List<Coordinate> queryByScope(double latitude, double longitude, CoordinateScope scope);
}
