package com.baolong.boot.common.utils;

import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间转换工具类
 *
 * @author Baolong 2024/6/6 0:14
 * @version 1.0
 * @since 1.8
 */
public class DateUtils {
	private DateUtils() {
	}

	private static final String DEFAULT_ZONE = "GMT+08:00";

	/**
	 * 获取当前时间戳，东八区（GMT+08:00）
	 *
	 * @return Date 时间
	 */
	public static Date currentTimeStamp() {
		return currentTimeStamp(DEFAULT_ZONE);
	}

	/**
	 * 获取当前时间戳，指定时区
	 *
	 * @param zone 时区
	 * @return Date 时间
	 */
	public static Date currentTimeStamp(String zone) {
		return Calendar.getInstance(TimeZone.getTimeZone(zone)).getTime();
	}

	/**
	 * 时间转字符串，格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 时间
	 * @return String 时间字符串
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 时间转字符串，指定格式
	 *
	 * @param date   时间
	 * @param format 格式：yyyy-MM-dd | yyyy-MM-dd HH:mm:ss | yyyy年MM月dd日 HH时mm分ss秒 ...
	 * @return String 时间字符串
	 */
	public static String dateToString(Date date, String format) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE));
		return df.format(date);
	}

	/**
	 * 字符串转时间，指定格式（时间字符串和字符串格式必须对应）
	 *
	 * @param dateStr 时间字符串 2024-06-08 10:46:17
	 * @param format  字符串格式 yyyy-MM-dd HH:mm:ss
	 * @return Date 时间
	 */
	@SneakyThrows
	public static Date stringToDate(String dateStr, String format) {
		DateFormat df = new SimpleDateFormat(format);
		df.setTimeZone(TimeZone.getTimeZone(DEFAULT_ZONE));
		return df.parse(dateStr);
	}

	/**
	 * 时间转毫秒，从1970年开始
	 *
	 * @param date 时间
	 * @return long 毫秒
	 */
	public static long toMillisecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 毫秒转时间
	 *
	 * @param ms 毫秒
	 * @return Date 时间
	 */
	public static Date toDate(long ms) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(ms);
		return c.getTime();
	}

	/**
	 * 操作时间加秒/减秒，正数加负数减
	 *
	 * @param date   时间
	 * @param second 秒
	 * @return Date 时间
	 */
	public static Date operaSecond(Date date, int second) {
		return opera(date, Calendar.SECOND, second);
	}

	/**
	 * 操作时间加分钟/减分钟，正数加负数减
	 *
	 * @param date   时间
	 * @param minute 分钟
	 * @return Date 时间
	 */
	public static Date operaMinute(Date date, int minute) {
		return opera(date, Calendar.MINUTE, minute);
	}

	/**
	 * 操作时间加小时/减小时，正数加负数减
	 *
	 * @param date 时间
	 * @param hour 小时
	 * @return Date 时间
	 */
	public static Date operaHour(Date date, int hour) {
		return opera(date, Calendar.HOUR, hour);
	}

	/**
	 * 操作时间加天/减天，正数加负数减
	 *
	 * @param date 时间
	 * @param day  天
	 * @return Date 时间
	 */
	public static Date operaDay(Date date, int day) {
		return opera(date, Calendar.DATE, day);
	}

	/**
	 * 操作时间加月/减月，正数加负数减
	 *
	 * @param date  时间
	 * @param month 月
	 * @return Date 时间
	 */
	public static Date operaMonth(Date date, int month) {
		return opera(date, Calendar.MONTH, month);
	}

	/**
	 * 操作时间加月/减月，正数加负数减
	 *
	 * @param date 时间
	 * @param year 年
	 * @return Date 时间
	 */
	public static Date operaYear(Date date, int year) {
		return opera(date, Calendar.YEAR, year);
	}

	/**
	 * 操作时间加/减，正数加负数减
	 *
	 * @param date 时间
	 * @param type 类型
	 * @param num  数量
	 * @return Date 时间
	 */
	public static Date opera(Date date, int type, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, num);
		return c.getTime();
	}

	/**
	 * 判断时间是否在某时间段
	 *
	 * @param startDate  开始时间
	 * @param targetDate 目标时间
	 * @param endDate    结束时间
	 * @return boolean true 是，false 否
	 */
	public static boolean judgeBetweenDate(Date startDate, Date targetDate, Date endDate) {
		return judgeBetweenDate(startDate.getTime(), targetDate.getTime(), endDate.getTime());
	}

	/**
	 * 判断时间是否在某时间段
	 *
	 * @param startTime  开始时间，毫秒
	 * @param targetTime 目标时间，毫秒
	 * @param endTime    结束时间，毫秒
	 * @return boolean true 是，false 否
	 */
	public static boolean judgeBetweenDate(long startTime, long targetTime, long endTime) {
		return startTime <= targetTime && targetTime <= endTime;
	}

	/**
	 * 计算时间相差的毫秒
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差毫秒
	 */
	public static long calcMillisecond(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.MILLISECOND);
	}

	/**
	 * 计算时间相差的秒
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差秒
	 */
	public static long calcSecond(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.SECOND);
	}

	/**
	 * 计算时间相差的分钟
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差分钟
	 */
	public static long calcMinute(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.MINUTE);
	}

	/**
	 * 计算时间相差的时
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差时
	 */
	public static long calcHour(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.HOUR);
	}

	/**
	 * 计算时间相差的天
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差天
	 */
	public static long calcDay(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.DATE);
	}

	/**
	 * 计算时间相差的月
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差月
	 */
	public static long calcMonth(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.MONTH);
	}

	/**
	 * 计算时间相差的年
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return long 相差年
	 */
	public static long calcYear(Date startDate, Date endDate) {
		return calc(startDate, endDate, Calendar.YEAR);
	}

	/**
	 * 计算时间相差的时间
	 *
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @param calendar  时间类型，规则为 Calendar.MILLISECOND、Calendar.SECOND、Calendar.MINUTE、Calendar.HOUR、Calendar.DATE、Calendar.MONTH、Calendar.YEAR
	 * @return long 相差时间值
	 */
	public static long calc(Date startDate, Date endDate, int calendar) {
		long value = 0;
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		if (calendar == Calendar.MILLISECOND) {
			value = startTime - endTime;
		}
		if (calendar == Calendar.SECOND) {
			value = (startTime - endTime) / 1000;
		}
		if (calendar == Calendar.MINUTE) {
			value = (startTime - endTime) / (60 * 1000);
		}
		if (calendar == Calendar.HOUR) {
			value = (startTime - endTime) / (60 * 60 * 1000);
		}
		if (calendar == Calendar.DATE) {
			value = (startTime - endTime) / (24 * 60 * 60 * 1000);
		}
		if (calendar == Calendar.MONTH || calendar == Calendar.YEAR) {
			Calendar end = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			end.setTime(endDate);
			start.setTime(startDate);
			//相差的月数
			value = (end.get(Calendar.MONTH) + ((end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12) - start.get(Calendar.MONTH));

			if (calendar == Calendar.YEAR) {
				value = value / 12;
			}
		}
		return Math.abs(value);
	}

	/**
	 * 获取时间毫秒
	 *
	 * @param date 时间
	 * @return int 毫秒
	 */
	public static int partMillisecond(Date date) {
		return part(date, Calendar.MILLISECOND);
	}

	/**
	 * 获取时间秒
	 *
	 * @param date 时间
	 * @return int 秒
	 */
	public static int partSecond(Date date) {
		return part(date, Calendar.SECOND);
	}

	/**
	 * 获取时间分钟
	 *
	 * @param date 时间
	 * @return int 分钟
	 */
	public static int partMinute(Date date) {
		return part(date, Calendar.MINUTE);
	}

	/**
	 * 获取时间小时
	 *
	 * @param date 时间
	 * @return int 小时
	 */
	public static int partHour(Date date) {
		return part(date, Calendar.HOUR);
	}

	/**
	 * 获取时间日/号
	 *
	 * @param date 时间
	 * @return int 日/号
	 */
	public static int partDate(Date date) {
		return part(date, Calendar.DATE);
	}

	/**
	 * 获取时间月
	 *
	 * @param date 时间
	 * @return int 月
	 */
	public static int partMonth(Date date) {
		return part(date, Calendar.MONTH) + 1;
	}

	/**
	 * 获取时间年
	 *
	 * @param date 时间
	 * @return int 年
	 */
	public static int partYear(Date date) {
		return part(date, Calendar.YEAR);
	}

	/**
	 * 获取时间某部分
	 *
	 * @param date     时间
	 * @param calendar 时间类型，规则为 Calendar.MILLISECOND、Calendar.SECOND、Calendar.MINUTE、Calendar.HOUR、Calendar.DATE、Calendar.MONTH、Calendar.YEAR
	 * @return int
	 */
	public static int part(Date date, int calendar) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(calendar);
	}

	/**
	 * 获取时间是星期几
	 *
	 * @param date 时间
	 * @return int 星期几
	 */
	public static int getWeek(Date date) {
		return (part(date, Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : part(date, Calendar.DAY_OF_WEEK) - 1;
	}

	/**
	 * 获取时间月份最大一天
	 *
	 * @param date 时间
	 * @return int 日期
	 */
	public static int monthMaxDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.set(partYear(date), partMonth(date) - 1, 1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
