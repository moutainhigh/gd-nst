package cn.gdeng.nst.util.web.api;

import java.math.BigDecimal;

public class DistanceCalculationUtil {

    private static final double EARTH_RADIUS = 6378.138;// 地球半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        BigDecimal result = new BigDecimal(s);
        s = result.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return s;
    }

    public static void main(String[] args) {
        System.out.println(GetDistance(11, 23, 12, 23));
    }
    
}
