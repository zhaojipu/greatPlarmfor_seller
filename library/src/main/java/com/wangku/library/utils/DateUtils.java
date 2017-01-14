package com.wangku.library.utils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 内容摘要：日期工具类
 */
public class DateUtils {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_DATE_CN = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat DISPLAY_FORMAT	 = new SimpleDateFormat("MM/dd HH:mm");
	public static final SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat("HH:mm");

	private DateUtils() {}

	/**
	 * 传入自1970的毫秒数，得到的日期格式字符串
	 *
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */
	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	public static String getTime(long timeInMillis) {
		return DEFAULT_DATE_FORMAT.format(new Date(timeInMillis));
	}

	/**
	 * get current time in milliseconds
	 *
	 * @return
	 */
	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 *
	 * @return
	 */
	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * 以友好的方式展示时间
	 *
	 * @param date
	 * @return
	 */
	public static String getFriendlyTime(Date date) {
		if (date == null) {
			return "时间转换异常";
		}
		String friendlyTime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = DATE_FORMAT_DATE.format(cal.getTime());
		String paramDate = DATE_FORMAT_DATE.format(date);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
			if (hour == 0) {
				friendlyTime = Math.max((cal.getTimeInMillis()
						- date.getTime()) / 60000, 1)+ "分钟前";
			} else {
				friendlyTime = hour + "小时前";
			}

			return friendlyTime;
		}

		long lt = date.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		int currentMinutes = cal.get(Calendar.MINUTE);
		int currentHours = cal.get(Calendar.HOUR);
		String minutes = currentMinutes >= 10 ? currentMinutes + "" : "0" + currentMinutes;
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
			if (hour == 0) {
				friendlyTime = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) +
						"分钟前";
			} else {
				friendlyTime = hour + "小时前";
			}
		} else if (days == 1) {
			friendlyTime = MessageFormat.format("昨天 {0}:{1}", currentHours, minutes);
		} else if (days == 2) {
			friendlyTime = MessageFormat.format("前天 {0}:{1}", currentHours, minutes);
		} else if (days > 2) {
			friendlyTime = DATE_FORMAT_DATE_CN.format(date);
		}
		return friendlyTime;
	}
}
