package com.juziwl.commonlibrary.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/06/28
 * @description 时间工具类
 */
public class TimeUtils {

    /**
     * 线程安全的时间格式化
     */
    public static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    };

    public static ThreadLocal<SimpleDateFormat> MMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        }
    };

    public static ThreadLocal<SimpleDateFormat> HHMM = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm", Locale.CHINA);
        }
    };

    public static String getCurrentTime() {
        return YYYYMMDDHHMMSS.get().format(new Date());
    }

    public static String formatTime(String time) {
        Calendar currentTime = Calendar.getInstance();
        Calendar needFormatTime = Calendar.getInstance();
        try {
            needFormatTime.setTime(YYYYMMDDHHMMSS.get().parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (currentTime.get(Calendar.YEAR) != needFormatTime.get(Calendar.YEAR)) {
            return time;
        }
        if (currentTime.get(Calendar.MONTH) != needFormatTime.get(Calendar.MONTH)) {
            return MMDDHHMM.get().format(needFormatTime.getTime());
        }
        int delta = currentTime.get(Calendar.DAY_OF_MONTH) - needFormatTime.get(Calendar.DAY_OF_MONTH);
        if (delta > 2) {
            return MMDDHHMM.get().format(needFormatTime.getTime());
        }
        if (delta == 2) {
            return "前天" + HHMM.get().format(needFormatTime.getTime());
        }
        if (delta == 1) {
            return "昨日" + HHMM.get().format(needFormatTime.getTime());
        }
        if (currentTime.get(Calendar.HOUR_OF_DAY) - needFormatTime.get(Calendar.HOUR_OF_DAY) > 0) {
            return "今日" + HHMM.get().format(needFormatTime.getTime());
        }
        delta = currentTime.get(Calendar.MINUTE) - needFormatTime.get(Calendar.MINUTE);
        if (delta >= 30 && delta < 60) {
            return "30分钟前";
        }
        if (delta > 5 && delta < 30) {
            return "5分钟前";
        } else {
            return "刚刚";
        }
    }
}
