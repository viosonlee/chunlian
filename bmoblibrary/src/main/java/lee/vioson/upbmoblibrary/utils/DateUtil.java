package lee.vioson.upbmoblibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author:李烽
 * Date:2016-05-27
 * FIXME
 * Todo
 */
public class DateUtil {

    // 将字符串转为时间戳
    public static long getMillTimeFromYYYY_MM_DD_HH_MM(String user_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        long l = 0;
        try {
            Date d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        return l;
    }

    // 将字符串转为时间戳
    public static long getMillTimeFromYYYY_MM_DD_HH_MM_SS(String user_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long l = 0;
        try {
            Date d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        return l;
    }  // 将字符串转为时间戳

    public static long getMillTimeFromYYYY_MM_DD(String user_time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        long l = 0;
        try {
            Date d = sdf.parse(user_time);
            l = d.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        return l;
    }


    // 格式分
    public static String getMinute(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(time);

    }

    // 格式分
    public static String getMinute() {
        return getMinute(System.currentTimeMillis());
    }

}
