package com.genius.core.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author architect.bian
 * @createtime 2014-8-25 下午2:36:22
 */
public class DateUtil extends BaseUtil {

	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public static final String FORMAT_DATETIME_CHINA = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE_CHINA = "yyyy-MM-dd";
	public static final String FORMAT_DATE_SHORT= "yyyyMMdd";
	
	 /** 年月日时分秒(无下划线) yyMMddHHmmssSSS */
    public static final String FORMAT_DATE_NOWID = "yyMMddHHmmssSSS";
    public static final String FORMAT_DATE_TID = "yyMMddHHmmss";
	public static final String FORMAT_DATE_Sitemap = "yyyy-MM-dd'T'HH:mm:ssZZ";
	public static final String FORMAT_DATE_MMdd = "MMdd";
	/** YYYYMMddHHmmss */
	public static final String FORMAT_DATE_YYYYMMddHHmmss = "YYYYMMddHHmmss";
    
	/**
	 * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		long day = date.getTime() / 1000 / 60 / 60 / 24;
		long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		return day == currentDay;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本周，取出日期，依据日期取出该日所在周所有日期，在依据这些日期是否和本日相等
	 * @return
	 */
	public static boolean isThisWeek(Date date) {
		List<Date> dates = dateToWeek(date);
		Boolean flag = false;
		for (Date d : dates) {
			if (isToday(d)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本月的日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isThisMonth(Date date) {
		long year = date.getYear();
		long month = date.getMonth();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year
				&& calendar.getTime().getMonth() == month;
	}

	/**
	 * 
	 * @param date
	 *            判断是否是本年的日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean isThisYear(Date date) {
		long year = date.getYear();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year;
	}

	/**
	 * 
	 * @param mdate
	 *            取出改日的一周所有日期
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(Date mdate) {
		int day = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<>();
		Long fTime = mdate.getTime() - day * 24 * 3600000;
		for (int i = 0; i < 7; i++) {
			fdate = new Date();
			fdate.setTime(fTime + (i * 24 * 3600000));
			list.add(i, fdate);
		}
		return list;
	}

	public static Double diffTwoDate(Date begin, Date end) {
		return (end.getTime() - begin.getTime()) / 1000 / 60.0;
	}

	public static String parseDate(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}

	public static String parseDateTime(DateTime dateTime, String format){
		Date date = new Date(dateTime.getMillis());
		return parseDate(date, format);				
	}
	
	public static Date afterDate(Date date, Integer after) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + after);
		return calendar.getTime();
	}

	/**
	 * @return
	 */
	public static DateTime NowTime() {
		return new DateTime(DateTimeZone.forOffsetHours(8));
	}
	
	public static long Now() {
		return NowTime().getMillis();
	}

	/**得到今天:20151016
	 * @return
	 */
	public static String getToday() {
		return parseDate(new Date(), FORMAT_DATE_SHORT);
	}

	/**得到现在2012-10-25 21:57:36
	 * @return
	 */
	public static String getNow() {
		return parseDate(new Date(), FORMAT_DATETIME_CHINA);
	}
	
	/**得到现在121025215736983
	 * @return
	 */
	public static String getNowForID() {
		return parseDate(new Date(), FORMAT_DATE_NOWID);
	}

	/**
	 * @param forOffsetHours
	 * @return
	 */
	public static String getTodayByTimeZone(DateTimeZone timezong) {
		return new DateTime(timezong).toString(FORMAT_DATE_SHORT);
	}

	/**
	 * 将string类型的2012-09-09 12:36:56的时间转化成DateTime类型
	 * @param strdatetime
	 * @return
	 */
	public static DateTime parse(String strdatetime) {
		if (strdatetime == null) {
			return null;
		}
		return ISODateTimeFormat.dateTimeParser().parseDateTime(strdatetime.replace(" ", "T"));
	}
	
	/**
	 * 字符串转换为Joda DateTime
	 * @param date
	 * @return
	 * @author: LiuYongjian
	 */
	public static DateTime getJodaDateTime(String date) {
		return ISODateTimeFormat.dateTimeParser().parseDateTime(date.replace(" ", "T"));
	}
	 
	/**
	 * 字符串转换成LocalDate
	 * @param date
	 * @return
	 * @author: LiuYongjian
	 */
	public static LocalDate getLocalDate(String date) {
		return ISODateTimeFormat.dateParser().parseDateTime(date).toLocalDate();
	}
	
	/**
	 * 当前日期(2017-01-12)
	 * @return
	 * @author chenminchang
	 * @create 2017年1月12日下午4:57:45
	 */
	public static LocalDate getLocalDate() {
		return new LocalDate(DateTimeZone.forOffsetHours(8));
	}

	/**
	 * @param type
	 * @param val
	 * @return
	 */
	public static <T> T newInstance(Class<T> type, Object val) {
		if (val == null || !StrUtil.isNumeric(val.toString())) {
			return null;
		}
		return newInstance(type, Long.parseLong(val.toString()));
	}
	
	public static <T> T newInstance(Class<T> type, long val) {
		try {
			return type.getConstructor(long.class).newInstance(val);
		} catch (InstantiationException e) {
			logException(log, e);
		} catch (IllegalAccessException e) {
			logException(log, e);
		} catch (IllegalArgumentException e) {
			logException(log, e);
		} catch (InvocationTargetException e) {
			logException(log, e);
		} catch (NoSuchMethodException e) {
			logException(log, e);
		} catch (SecurityException e) {
			logException(log, e);
		}
		return null;
	}
}