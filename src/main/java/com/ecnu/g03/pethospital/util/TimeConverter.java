package com.ecnu.g03.pethospital.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Shen Lei
 * @date 2021/4/24 11:21
 */
public class TimeConverter {

    public static String DBTimeToUI(String time) {
        SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dbSdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        SimpleDateFormat uiSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        uiSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            Date dbTime = dbSdf.parse(time);
            return uiSdf.format(dbTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String UITimeToDB(String time) {
        SimpleDateFormat dbSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dbSdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        SimpleDateFormat uiSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        uiSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            Date uiTime = uiSdf.parse(time);
            return dbSdf.format(uiTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
