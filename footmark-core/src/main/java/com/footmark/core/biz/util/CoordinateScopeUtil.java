package com.footmark.core.biz.util;

/**
 * @Description: 坐标范围工具类
 * @author carlos.chu
 * @date 2015年8月31日
 */
public class CoordinateScopeUtil {

    private final static double jl_jd = 102834.74258026089786013677476285;// 每经度单位米;
    private final static double jl_wd = 111712.69150641055729984301412873;// 每纬度单位米;

    private final static double degree = (24901 * 1609) / 360.0;
    private final static double dpmLat = 1 / degree;

    /**
     * 计算中心经纬度与目标经纬度的距离（米）
     * 
     * @param centerLon
     *            中心精度
     * @param centerLan
     *            中心纬度
     * @param targetLon
     *            需要计算的精度
     * @param targetLan
     *            需要计算的纬度
     * @return 米
     */
    public static double distance(double centerLon, double centerLat, double targetLon, double targetLat) {

        double b = Math.abs((centerLat - targetLat) * jl_jd);
        double a = Math.abs((centerLon - targetLon) * jl_wd);
        return Math.sqrt((a * a + b * b));
    }

    public static CoordinateScope generatorScope(double longitude, double latitude, double distance) {
        return new CoordinateScopeUtil().new CoordinateScope(longitude, latitude, distance);
    }

    /** 坐标四方范围类 */
    public class CoordinateScope {
        public double minLatitude, maxLatitude, minLongitude, maxLongitude;

        public CoordinateScope(double longitude, double latitude, double distance) {
            calculateScope(longitude, latitude, distance);
        }

        /**
         * 生成以中心点为中心的四方形经纬度
         * 
         * @param lat
         *            纬度
         * @param lon
         *            精度
         * @param raidus
         *            半径（以米为单位）
         * @return
         */
        private void calculateScope(double longitude, double latitude, double distance) {
            double raidusMile = distance;
            double radiusLat = dpmLat * raidusMile;
            minLatitude = latitude - radiusLat;
            maxLatitude = latitude + radiusLat;

            double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
            double dpmLng = 1 / mpdLng;
            double radiusLng = dpmLng * raidusMile;
            minLongitude = longitude - radiusLng;
            maxLongitude = longitude + radiusLng;
        }
    }

}
