package com.tianzhixing.oms.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author tangzhi
 */
public class CalendarUtil {

    /**
     * 日期之前
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date, Date date2) {
        return null != date && null != date2 && date.before(date2);
    }

    /**
     * 日期之后
     *
     * @param date
     * @param date2
     * @return
     */
    public static boolean isAfter(Date date, Date date2) {
        return null != date && null != date2 && date.after(date2);
    }

    /**
     * 日期之中
     *
     * @param date
     * @param date2
     * @param date3
     * @return
     */
    public static boolean isBetween(Date date, Date date2, Date date3) {
        return null != date && isAfter(date, date2) && isBefore(date, date3);
    }

    /**
     * 某一个日期n月相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForMonth(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, n);
        return c.getTime();
    }

    /**
     * 某一个日期n天相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForDay(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }

    /**
     * 某一个日期n天相对应某一天 n 为负值表示向前 n 为正值表示向后
     */
    public static Date calDateForYear(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, n);
        return c.getTime();
    }

    /**
     * 取某一个时间相对某一时间n小时向前或向后的时间
     */
    public static Date calDateForHour(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, n);
        return c.getTime();
    }

    /**
     * 取某一时间相对某一时间n分钟向前或向后的时间
     */
    public static Date calDateForMin(Date date, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, n);
        return c.getTime();
    }

    /**
     * 当前月份最大天数
     */
    public static int currentMonthMaxDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 计算日期天数差值
     *
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {
        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        // 设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        // 得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;
        return days;
    }

    /**
     * 获取传入日期的开始时间 00:00:00
     */
    public static final Date calBeginTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        return calst.getTime();
    }

    /**
     * 获取传入日期的开始时间 00:00:00
     */
    public static final Date calBeginHourTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        return calst.getTime();
    }

    /**
     * 获取传入日期的结束时间 00:00
     */
    public static final Date calEndTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.HOUR_OF_DAY, 23);
        calst.set(Calendar.MINUTE, 59);
        calst.set(Calendar.SECOND, 59);
        return calst.getTime();
    }

    /**
     * 获取传入日期的结束时间 00:00
     */
    public static final Date calEndHourTimeForThisTime(Date date) {
        Calendar calst = Calendar.getInstance();
        calst.setTime(date);
        calst.set(Calendar.MINUTE, 59);
        calst.set(Calendar.SECOND, 59);
        return calst.getTime();
    }

    /**
     * 将日期转化为字符串。 字符串格式("yyyy-MM-dd HH:mm:ss")。
     */
    public static String dateTime2String(Date date) {
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期转化为字符串
     */
    public static String dateToString(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern) || date == null) return null;
        String str = "";
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            str = formater.format(date);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 将字符串转化为日期。 字符串格式("YYYY-MM-DD")。
     * 例如："2012-07-01"或者"2012-7-1"或者"2012-7-01"或者"2012-07-1"是等价的。
     */
    public static Date stringToDate(String str, String pattern) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(str)) return null;
        Date dateTime = null;
        try {
            SimpleDateFormat formater = new SimpleDateFormat(pattern);
            dateTime = formater.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 日期精确到日
     *
     * @param date
     * @return
     */
    public static String dayWithYearAndMonth(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        return formater.format(date);
    }

    /**
     * 日期精确到日
     *
     * @param date
     * @return
     */
    public static String dayWithYearAndMonth(long date) {
        return dayWithYearAndMonth(new Date(date));
    }

    /**
     * 当前年
     *
     * @param date
     * @return
     */
    public static String year(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.YEAR));
    }

    /**
     * 当前月
     *
     * @param date
     * @return
     */
    public static String month(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.MONTH) + 1);
    }

    /**
     * 当前小时
     *
     * @param date
     * @return
     */
    public static String hour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * 当前天
     *
     * @param date
     * @return
     */
    public static String day(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 当前小时
     *
     * @param date
     * @return
     */
    public static String hour(long date) {
        return hour(new Date(date));
    }


    /**
     * 当前分钟
     *
     * @param date
     * @return
     */
    public static String min(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return String.valueOf(c.get(Calendar.MINUTE));
    }

    /**
     * 当前分钟
     *
     * @param date
     * @return
     */
    public static String min(long date) {
        return min(new Date(date));
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date calDate = calDateForMin(date, -1470);
        System.out.println(f.format(calDate));
    }

}