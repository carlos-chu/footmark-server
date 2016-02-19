package com.footmark.core.biz.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 时间工具类
 * @author carlos.chu
 * @date 2015年9月10日
 */
public class TimeUtil {

    /**
     * 把过去的时间转化为口语，如刚刚，N分钟前，1小时前，N天前，N月前，N年前
     * 
     * @param oldDate
     * @return
     */
    public static String coverToColloquialLanguage(Date oldDate) {
        Date now = new Date();
        long nowTime = now.getTime();
        long oldDateTime = oldDate.getTime();
        long diff = nowTime - oldDateTime;
        return cover(diff);
    }

    private static String cover(long diff) {
        long sec = diff / 1000;
        if (sec <= 10 * 60) { // 十分钟之内
            return "刚刚";
        }
        if (sec > 10 * 60 && sec <= 60 * 60) { // 一小时之内
            return sec / 60 + "分钟前";
        }
        if (sec > 60 * 60 && sec <= 60 * 60 * 24) { // 24小时内
            return sec / 60 / 60 + "小时前";
        }
        if (sec > 60 * 60 * 24 && sec <= 60 * 60 * 24 * 30) { // 一个月之内
            return sec / 60 / 60 / 24 + "天前";
        }
        if (sec > 60 * 60 * 24 * 30 && sec <= 60 * 60 * 24 * 30 * 12) { // 一年之内
            return sec / 60 / 60 / 24 / 30 + "个月前";
        }
        if (sec > 60 * 60 * 24 * 30 * 12) { // 几年之内
            return sec / 60 / 60 / 24 / 30 / 12 + "年前";
        }
        return null;
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2015, 4, 10, 16, 1, 20);
        System.out.println(coverToColloquialLanguage(c.getTime()));
    }
}
