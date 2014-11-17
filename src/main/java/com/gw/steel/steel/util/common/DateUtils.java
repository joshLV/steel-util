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
     
    /**
     * 使用预设格式提取字符串日期
     * @param strDate 日期字符串
     * @return
     * @throws ParseException 
     */
    public static Date parse(String strDate) throws ParseException {
        return parse(strDate, DEFAULT_FORMAT);
    }
     
    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     * @throws ParseException 
     */
    public static Date parse(String strDate, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(strDate);
    }
}