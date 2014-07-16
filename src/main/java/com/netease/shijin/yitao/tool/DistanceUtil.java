package com.netease.shijin.yitao.tool;

public class DistanceUtil {
    
    private static final double PI = 3.14159265358979323; // 圆周率
    private static final double R = 6371229; // 地球的半径

    /**
     * 计算两个点之间的距离
     * @param longi1
     * @param lati1
     * @param longi2
     * @param lati2
     * @return
     */
    public static double getDistance(double longi1, double lati1, double longi2, double lati2) {
        double x, y, distance;
        x = (longi2 - longi1) * PI * R / 180 * Math.cos(((lati1 + lati2) / 2) * PI / 180);
        y = (lati2 - lati1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    }

    /**
     * 计算同一纬度上相差距离distance的两点之间的经度差
     * @param longi
     * @param lati
     * @param distance
     * @return
     */
    public static double getLongi(double longi, double lati, double distance) {
        double a = (180 * distance) / (PI * R * Math.cos(lati * PI / 180));
        return a;
    }

    /**
     * 计算同一经度上相差距离distance的两点之间的纬度差
     * @param longi
     * @param lati
     * @param distance
     * @return
     */
    public static double getLati(double longi, double lati, double distance) {
        double a = (180 * distance) / (PI * R);
        return a;
    }

}
