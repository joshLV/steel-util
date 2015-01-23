package com.gw.steel.steel.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * @author Administrator
 *
 */
public class DateUtils {

    public final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, DEFAULT_FORMAT);
    }

    public static Date parse(String strDate, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

}