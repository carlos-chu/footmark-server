package com.footmark.core.biz;

import java.util.List;

/**
 * @Description: 坐标计算接口
 * @author carlos.chu
 * @date 2015年8月30日
 */
public interface CoordinateBiz {

    /**
     * 根据距离计算在范围内的坐标
     * 
     * @param distance
     *            距离，米级别
     * @param longitude
     *            经度
     * @param latitude
     *            纬度
     * @return List<Long>
     */
    List<Long> calculateCoordinate(double longitude, double latitude, double distance);
}
