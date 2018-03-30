package com.bola.nwcl.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYYMM = "yyyyMM";
	public final static String YYYY_MM = "yyyy-MM";
	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public final static String YYYYMMDDHHMM = "yyyyMMddHHmm";
	public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 将字符串时间改成Date类型
	 */
	public static Date strToDate(String dateStr, String format) {

		Date date = null;

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 将Date时间转成字符串
	 */
	public static String DateToStr(String format, Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate) {

		Date l_startDate = DateUtils.strToDate(p_startDate,DateUtils.YYYY_MM_DD);
		Date l_endDate = DateUtils.strToDate(p_endDate,DateUtils.YYYY_MM_DD);
		long l_startTime = l_startDate.getTime();
		long l_endTime = l_endDate.getTime();
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 获取2个Date型日期的分钟数差值
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 分钟数差值
	 */
	public static long getMinutesOfTowDiffDate(Date p_startDate, Date p_endDate) {
		long l_startTime = p_startDate.getTime();
		long l_endTime = p_endDate.getTime();
		long betweenMinutes = (long) ((l_endTime - l_startTime) / (1000 * 60));
		return betweenMinutes;
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(Date l_startDate, Date l_endDate) {

		long l_startTime = l_startDate.getTime();
		long l_endTime = l_endDate.getTime();
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 给出日期添加一段时间后的日期
	 * 
	 * @param dateStr
	 * @param plus
	 * @return
	 */
	public static String getPlusDays(String format, String dateStr, long plus) {

		Date date = DateUtils.strToDate(format, dateStr);
		long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

		return DateUtils.DateToStr(format, new Date(time));
	}

	/**
	 * 给出日期添加一段时间后的日期
	 */
	public static String getPlusDays(String format, Date date, long plus) {

		long time = date.getTime() + plus * 24 * 60 * 60 * 1000;

		return DateUtils.DateToStr(format, new Date(time));
	}
}