package com.springboot.myapp.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyyMMdd HH:mm:ss", "yyyyMMdd", "yyyy-MM-dd HHmm", "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
			"yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	public static String getFormatUS(String date) {
		String result = null;
		if (date == null || date.length() < 2) {
			return result;
		}
		String code = date.substring(0, 2);
		Map<String, String> map = new HashMap<>();
		map.put("MO", "MON");
		map.put("TU", "TUE");
		map.put("WE", "WED");
		map.put("TH", "THU");
		map.put("FR", "FRI");
		map.put("SA", "SAT");
		map.put("SU", "SUN");
		String temp = map.get(code);
		result = date.replace(code, temp);
		return result;
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 返回订单号的时间戳
	 */
	public static String getOrderNoTimeStamp() {
		String timeStamp = formatDate(new Date(), "yyyyMMddHHmmsss");
		return timeStamp.substring(2);
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取 当前时间加几天后的日期 如果 2017-4-5 号，2天后是什么时间
	 * 
	 * @param amount
	 */
	public static String getAfterDateFromNowDate(int amount) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, amount);
		return sf.format(c.getTime());
	}

	/**
	 * 获取 当前时间加几天后的日期 如果 2017-4-5 号，2天后是什么时间
	 * 
	 * @param amount
	 */
	public static String getAfterDateFromNowDate(int amount, Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return sf.format(c.getTime());
	}

	/**
	 * 获取 当前时间 多少个小时后
	 * 
	 * @param amount
	 */
	public static Long getAfterHourFromNowDate(int amount, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, amount);
		return c.getTime().getTime();
	}

	/**
	 * 获取 当前时间 多少个小时前
	 * 
	 * @param amount
	 */
	public static Long getBeforeHourFromNowDate(int amount, Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - amount);
		return c.getTime().getTime();
	}

	/**
	 * String TO String
	 * 
	 * @param date
	 * @param parsePatterns
	 * @return
	 */
	public static String getDateStr(String date, String parsePatterns) {
		try {
			Date parseDate = DateUtils.parseDate(date, parsePatterns);
			return DateUtils.formatDate(parseDate, parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取当前时间 TIME LONG
	 * 
	 * @return
	 */
	public static Long getNowDateTime() {
		String dateTime = DateUtils.getDateTime();
		Date parseDate = DateUtils.parseDate(dateTime);
		Long time = null;
		if (parseDate != null) {
			time = parseDate.getTime();
		}
		return time;
	}

	/**
	 * 今天还有秒数
	 */
	public static int todayLeftSecond() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return subSecond(cal.getTime(), new Date());
	}

	/**
	 * 返回date1-dat2相差的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int subSecond(Date date1, Date date2) {

		long d1 = date1.getTime();
		long d2 = date2.getTime();
		int sub = (int) ((d1 - d2) / 1000);
		return sub;
	}

	/**
	 * 日期拼接时间
	 * 
	 * @param beginDate
	 * @return
	 */
	public static Date getDateStart(Date beginDate) {
		if (beginDate != null) {
			String date = DateUtils.formatDate(beginDate, "yyyy-MM-dd") + " 00:00:00";
			beginDate = DateUtils.parseDate(date);
		}
		return beginDate;
	}

	/**
	 * 日期拼接时间
	 * 
	 * @param endDate
	 * @return
	 */
	public static Date getDateEnd(Date endDate) {
		if (endDate != null) {
			String date = DateUtils.formatDate(endDate, "yyyy-MM-dd") + " 23:59:59";
			endDate = DateUtils.parseDate(date);
		}
		return endDate;
	}

	/**
	 * 计算两个时间相差多少小时和多少分钟
	 * 
	 * @param endDate
	 *            结束时间
	 * @param nowDate
	 *            开始时间
	 * @return
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {

		if (endDate == null || nowDate == null) {
			return null;
		}

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return (day * 24) + hour + "h" + min + "m";
	}

	/**
	 * 获取时间戳格式的时间
	 */
	public static String getTimeStamp(Date date){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
			return sdf.format(date);
		}catch (Exception e){
			return "";
		}
	}

	public static Date reformate(Date date,String pattern){
		try{
			String strDate = formatDate(date,pattern);
			return parseDate(strDate,pattern);
		}catch (Exception e){
			LogUtils.errorPrint("",e);
		}
		return null;
	}

	public static Date trimLeftDate(Date date){
		return reformate(date,"yyyy-MM-dd");
	}

	public static Date trimLeftTime(Date date){
		return reformate(date,"HH:mm:ss");
	}
}
