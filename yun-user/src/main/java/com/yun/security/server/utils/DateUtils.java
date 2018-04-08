package com.yun.security.server.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author HyNo
 * 
 */
public class DateUtils {

	public static Logger log = Logger.getLogger(DateUtils.class);

	public static final String DATE_FORMAT_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_MM = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_HH = "yyyy-MM-dd HH";
	public static final String DATE_FORMAT_DD = "yyyy-MM-dd";
	public static final String TIME_FORMAT_MM = "HH:mm";

	public static final String DATE_FORMAT_NO_SPLITE_MM = "yyyyMM";
	public static final String DATE_FORMAT_NO_SPLITE_DD = "yyyyMMdd";
	public static final String DATE_FORMAT_NO_SPLITE_TIME = "HHmmssSSS";// 精确到毫秒
	public static final String DATE_FORMAT_NO_SPLITE_HH = "yyyyMMddHH";
	public static final String DATE_FORMAT_NO_SPLITE_MMM = "yyyyMMddHHmm";
	public static final String DATE_FORMAT_NO_SPLITE_SS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_NO_SPLITE_NOW_TIME = "yyyyMMddHHmmssSSS";// 精确到毫秒

	public static final String DATE_FORMAT_SPLITE_DD = "yyyy.MM.dd";
	public static final String YEAR = "yyyy";
	public static final String DATE_FORMAT_MM_DD = "MM-dd";
	public static final String DATE_FORMAT_MMDD = "M月d日";
	public static final String DATE_FORMAT_MMDDHH = "M月d日 HH:mm";
	public static final String DATE_FORMAT_WEEK = "星期";

	public static final String DATE_TIME_MORNING = "早上";
	public static final String DATE_TIME_AFTERNOON = "下午";
	public static final String DATE_TIME_NIGHT = "晚上";

	public static final String CENTRE_SCRIBING = "-";

	protected static final String EMPTY = "";
	protected static final String ZERO = "0";
	protected static final String SPLITE_CHAR = ":";

	protected static final String START_TIME = " 00:00:00";// 空格不能删除
	protected static final String END_TIME = " 23:59:59";// 空格不能删除

	protected static final int WEEK_DAYS = 7;
	protected static final int MONTH_DAYS = 30;
	protected static final int YEAR_DAYS = 365;
	protected static final String[] WEEKS = { "一", "二", "三", "四", "五", "六", "日" };

	public static int DATE_UNIT_SECOND = 1;// 秒
	public static int DATE_UNIT_MINUTE = 2;// 分钟
	public static int DATE_UNIT_HOUR = 3;// 小时
	public static int DATE_UNIT_DAY = 4;// 天
	public static int DATE_UNIT_WEEK = 5;// 周
	public static int DATE_UNIT_MONTH = 6;// 月
	public static int DATE_UNIT_QUARTER = 7;// 季度
	public static int DATE_UNIT_YEAR = 8;// 年

	private static final long ONE_MINUTE = 60000L;
	private static final long ONE_HOUR = 3600000L;
	private static final long ONE_DAY = 86400000L;
	private static final long ONE_WEEK = 604800000L;

	private static final String ONE_SECOND_AGO = "秒前";
	private static final String ONE_MINUTE_AGO = "分钟前";
	private static final String ONE_HOUR_AGO = "小时前";
	private static final String ONE_DAY_AGO = "天前";
	private static final String ONE_MONTH_AGO = "月前";
	private static final String ONE_YEAR_AGO = "年前";

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(java.util.Date date) {
		try {
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(date);
			return c.get(java.util.Calendar.YEAR);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return 0;
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(java.util.Date date) {
		try {
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(date);
			return c.get(java.util.Calendar.MONTH) + 1;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return 0;
	}

	/**
	 * 返回月中的第几天
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getDay(java.util.Date date) {
		try {
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(date);
			return c.get(java.util.Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return 0;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return EMPTY;
		}
		DateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}
	
	/**
	 * 将日期转换为指定精度的日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date dateToDate(Date date, String format) {
		if (date == null) {
			return null;
		}

		return stringToDate(dateToString(date, format), format);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format)) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					"EEE MMM dd HH:mm:ss Z yyyy",
					new Locale("ENGLISH", "CHINA"));
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e2) {
				log.error(e2.getLocalizedMessage(), e2);
			}
		}
		return null;
	}

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date enStringToDate(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss Z yyyy", new Locale("ENGLISH", "CHINA"));
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return null;
	}

	/**
	 * 比较两个日期是否一致
	 * 
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean equals(Date dateA, Date dateB) {
		if (dateA == null || dateB == null) {
			return false;
		}
		String strA = dateToString(dateA, DATE_FORMAT_SS);
		String strB = dateToString(dateB, DATE_FORMAT_SS);

		if (StringUtils.equals(strA, strB)) {
			return true;
		}

		return false;
	}
	
	public static boolean equals(Date dateA, Date dateB, String format) {
		if (dateA == null || dateB == null) {
			return false;
		}
		String strA = dateToString(dateA, format);
		String strB = dateToString(dateB, format);
		
		if (StringUtils.equals(strA, strB)) {
			return true;
		}
		
		return false;
	}

	/**
	 * 比较两个日期是否一致
	 * 
	 * @param dateA
	 * @param dateB
	 * @return
	 */
	public static boolean notEquals(Date dateA, Date dateB) {
		return !equals(dateA, dateB);
	}

	/**
	 * 判断给定的日期是一周中的第几天，注意：按照中国的习惯，周日是第七天
	 * 
	 * @param date
	 * @return
	 */
	public static int dateToWeek(Date date) {
		if (date == null) {
			return 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			return 7;
		} else {
			return cal.get(Calendar.DAY_OF_WEEK) - 1;
		}
	}

	public static String dateOfWeek(Date date) {
		return DATE_FORMAT_WEEK + WEEKS[dateToWeek(date) - 1];
	}

	/**
	 * 指定时间的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextDate(Date date) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			return cal.getTime();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return null;
	}

	/**
	 * 指定时间的前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date previousDate(Date date) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			return cal.getTime();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return null;
	}

	/**
	 * 指定时间的下N天
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextNDate(Date date, int nDay) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, nDay);
			return cal.getTime();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return null;
	}
	
	/**
	 * 指定时间的下N分钟
	 * 
	 * @param date
	 * @return
	 */
	public static Date nextNMinute(Date date, int nMinute) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.MINUTE, nMinute);
			return cal.getTime();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return null;
	}

	/**
	 * 指定时间的前N天
	 * 
	 * @param date
	 * @return
	 */
	public static Date previousNDate(Date date, int nDay) {
		if (date == null) {
			return date;
		}

		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, -nDay);
			return cal.getTime();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return null;
	}

	/**
	 * 获取当前时间
	 * 
	 * @author Jes
	 * @version 创建时间：2014年12月10日 下午5:38:51
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	public static String getNowTimeStirng() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	public static String getNowTimeHH() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 获取一天的起始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return date;
		}

		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_DD);
		String dateStr = fmt.format(date);
		dateStr = dateStr + START_TIME;
		fmt = new SimpleDateFormat(DATE_FORMAT_SS);
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return date;
	}

	/**
	 * 获取一天的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		if (date == null) {
			return date;
		}

		DateFormat fmt = new SimpleDateFormat(DATE_FORMAT_DD);
		String dateStr = fmt.format(date);
		dateStr = dateStr + END_TIME;
		fmt = new SimpleDateFormat(DATE_FORMAT_SS);
		try {
			return fmt.parse(dateStr);
		} catch (ParseException e) {
			log.error(e.getLocalizedMessage(), e);
		}

		return date;
	}

	/**
	 * currentDat是否在referenceDate日期之前
	 * 
	 * @param referenceDate
	 * @param currentDat
	 * @return
	 */
	public static boolean isBeforeDate(Date referenceDate, Date currentDate) {
		if (currentDate == null) {
			return false;
		}
		if (referenceDate == null) {
			return true;
		}
		return currentDate.before(referenceDate);
	}

	/**
	 * currentDat是否在referenceDate日期之后
	 * 
	 * @param referenceDate
	 * @param currentDat
	 * @return
	 */
	public static boolean isAffterDate(Date referenceDate, Date currentDate) {
		if (currentDate == null) {
			return false;
		}
		if (referenceDate == null) {
			return true;
		}
		return currentDate.after(referenceDate);
	}

	/**
	 * 判断currentDate是否在startDate和endDate之间，不包括startDate和endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currentDate
	 * @return
	 */
	public static boolean isDuringDate(Date startDate, Date endDate,
			Date currentDate) {
		if (currentDate == null) {
			return false;
		}

		if (isAffterDate(startDate, currentDate)
				&& isBeforeDate(endDate, currentDate)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断currentDate是否在startDate和endDate之间，包括startDate和endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currentDate
	 * @return
	 */
	public static boolean isBetweenDate(Date startDate, Date endDate,
			Date currentDate) {
		if (currentDate == null) {
			return false;
		}

		if (isAffterDate(startDate, currentDate)
				&& isBeforeDate(endDate, currentDate)) {
			return true;
		}
		// 开始接受日期判断
		else if (equals(startDate, currentDate) || equals(endDate, currentDate)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断currentDate是否不在startDate和endDate之间，包括startDate和endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param currentDate
	 * @return
	 */
	public static boolean isNotBetweenDate(Date startDate, Date endDate,
			Date currentDate) {
		return !isBetweenDate(startDate, endDate, currentDate);
	}

	/**
	 * 获取startDate到endDate之间的星期day（中文星期）不包括startDate和endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param day
	 * @return
	 */
	public static List<Date> findDayDuringDate(Date startDate, Date endDate,
			int day) {
		List<Date> listDate = new ArrayList<Date>();
		int startDay = dateToWeek(startDate);

		Date date = null;
		if (startDay == day) {
			date = nextNDate(startDate, WEEK_DAYS);
		} else {
			date = nextNDate(startDate, day - startDay);
		}
		while (isDuringDate(startDate, endDate, date)) {
			listDate.add(date);
			date = nextNDate(date, WEEK_DAYS);
		}

		return listDate;
	}

	/**
	 * 获取startDate到endDate之间的星期day（中文星期）包括startDate和endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @param day
	 * @return
	 */
	public static List<Date> findDayBetweenDate(Date startDate, Date endDate,
			int day) {
		List<Date> listDate = new ArrayList<Date>();
		int startDay = dateToWeek(startDate);

		Date date = null;
		if (startDay == day) {
			date = startDate;
		} else {
			date = nextNDate(startDate, day - startDay);
		}
		while (isBetweenDate(startDate, endDate, date)) {
			listDate.add(date);
			date = nextNDate(date, WEEK_DAYS);
		}

		return listDate;
	}

	/**
	 * date转换成Timestamp
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date, String format) {
		if (date == null) {
			return null;
		}

		if (StringUtils.isBlank(format)) {
			format = DATE_FORMAT_SS;
		}

		DateFormat fmt = new SimpleDateFormat(format);

		return Timestamp.valueOf(fmt.format(date));
	}

	/**
	 * 计算两个时间之间的时间差，可以指定时间差单位，默认为天
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param unit
	 *            时间差单位
	 * @return
	 */
	public static long getDifftime(Date startTime, Date endTime, int unit) {
		if (startTime == null || endTime == null) {
			return 0;
		}

		long between = (endTime.getTime() - startTime.getTime()) / 1000;// 除以1000是为了转换成秒
		switch (unit) {
		case 1:// 秒DATE_UNIT_SECOND
			return between;
		case 2:// 分DATE_UNIT_MINUTE
			return between / 60;
		case 3:// 小时DATE_UNIT_HOUR
			return between / (60 * 60);
		case 4:// 天DATE_UNIT_DAY
			return between / (60 * 60 * 24);
		case 5:// 周,一周7天DATE_UNIT_WEEK
			return between / (60 * 60 * 24 * 7);
		case 6:// 月,一个月按照30天计算DATE_UNIT_MONTH
			return between / (60 * 60 * 24 * 30);
		case 7:// 季度，一个季度按照90天计算DATE_UNIT_QUARTER
			return between / (60 * 60 * 24 * 90);
		case 8:// 年，一年按照365天计算DATE_UNIT_YEAR
			return between / (60 * 60 * 24 * 365);
		default:// 天
			return between / (60 * 60 * 24);
		}
	}

	/**
	 * 获取时间周期
	 * 
	 * @param cycle
	 * @param cycleUnit
	 * @return
	 */
	public static long getTimeFromCycle(int cycle, int cycleUnit) {
		if (cycle == 0 || cycleUnit == 0) {
			return 0;
		}
		switch (cycleUnit) {
		case 1:// 代表枚举中的天
			return cycle * cycleUnit;
		case 2:
			return cycle * WEEK_DAYS;
		case 3:
			return cycle * MONTH_DAYS;
		case 4:
			return cycle * YEAR_DAYS;
		default:
			break;
		}
		return 0;
	}

	/**
	 * 将日期时间对象转换为新媒体展示的字符串
	 * 
	 * @param dateTime
	 * @param types
	 * @return
	 */
	public static String getTimeForCn(Date dateTime, int types) {
		return dateToString(dateTime, DATE_FORMAT_MM);
	}

	public static String webFormat(Date date) {
		if (date == null) {
			return "";
		}
		long delta = new Date().getTime() - date.getTime();
		if (delta < 1L * ONE_MINUTE) {
			long seconds = toSeconds(delta);
			return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
		}
		if (delta < 45L * ONE_MINUTE) {
			long minutes = toMinutes(delta);
			return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
		}
		if (delta < 24L * ONE_HOUR) {
			long hours = toHours(delta);
			return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
		}
		if (delta < 48L * ONE_HOUR) {
			return "昨天";
		}
		if (delta < 30L * ONE_DAY) {
			long days = toDays(delta);
			return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
		}
		if (delta < 12L * 4L * ONE_WEEK) {
			long months = toMonths(delta);
			return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
		} else {
			long years = toYears(delta);
			return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
		}
	} 

	public static long toSeconds(long date) {
		return date / 1000L;
	}

	public static long toMinutes(long date) {
		return toSeconds(date) / 60L;
	}

	public static long toHours(long date) {
		return toMinutes(date) / 60L;
	}

	public static long toDays(long date) {
		return toHours(date) / 24L;
	}

	public static long toMonths(long date) {
		return toDays(date) / 30L;
	}

	public static long toYears(long date) {
		return toMonths(date) / 365L;
	}

	/**
	 * 获取上周日期
	 * 
	 * @return
	 */
	public static String lastWeekDate() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - 6;

		if (day < 1) {
			month -= 1;
			if (month == 0) {
				year -= 1;
				month = 12;
			}
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				day = 30 + day;
			} else if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day = 31 + day;
			} else if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29 + day;
				} else {
					day = 28 + day;
				}
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return y+"-"+m+"-"+d;
	}

	/**
	 * 获取几个月前的日期
	 * 
	 * @param allMonth
	 * @return
	 */
	public static String lastMonth(int allMonth) {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date))
				- allMonth;
		int day = Integer.parseInt(new SimpleDateFormat("dd").format(date));
		if (month <= 0) {
			int yearFlag = (month * (-1)) / 12 + 1;
			int monthFlag = (month * (-1)) % 12;
			year -= yearFlag;
			month = monthFlag * (-1) + 12;
		} else if (day > 28) {
			if (month == 2) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else {
					day = 28;
				}
			} else if ((month == 4 || month == 6 || month == 9 || month == 11)
					&& day == 31) {
				day = 30;
			}
		}
		String y = year + "";
		String m = "";
		String d = "";
		if (month < 10) {
			m = "0" + month;
		} else {
			m = month + "";
		}
		if (day < 10) {
			d = "0" + day;
		} else {
			d = day + "";
		}
		return y+"-"+m+"-"+d;
	}
	
    static int[] DAYS = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };  
    
    /** 
     * 校验是否有效日期
     * @param date yyyy-MM-dd 
     * @return 
     */  
    public static boolean isValidDate(String date) {  
        try {  
            int year = Integer.parseInt(date.substring(0, 4));  
            if (year <= 0) {
				return false;
			}  
            int month = Integer.parseInt(date.substring(5, 7));  
            if (month <= 0 || month > 12) {
				return false;
			}  
            int day = Integer.parseInt(date.substring(8, 10));  
            if (day <= 0 || day > DAYS[month]) {
				return false;
			}  
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {  
                return false;  
            }  
            
            if(date.length() != 10){
            	return false;  
            }
      
        } catch (Exception e) {     
            //e.printStackTrace();  
            return false;  
        }  
        return true;  
    }  
    
    /** 
     * 校验是否有效时间
     * @param date yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static boolean isValidDateTime(String date) {  
        try {  
            int year = Integer.parseInt(date.substring(0, 4));  
            if (year <= 0) {
				return false;
			}  
            int month = Integer.parseInt(date.substring(5, 7));  
            if (month <= 0 || month > 12) {
				return false;
			}  
            int day = Integer.parseInt(date.substring(8, 10));  
            if (day <= 0 || day > DAYS[month]) {
				return false;
			}  
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {  
                return false;  
            }  
            int hour = Integer.parseInt(date.substring(11, 13));  
            if (hour < 0 || hour > 23) {
				return false;
			}  
            int minute = Integer.parseInt(date.substring(14, 16));  
            if (minute < 0 || minute > 59) {
				return false;
			}  
            int second = Integer.parseInt(date.substring(17, 19));  
            if (second < 0 || second > 59) {
				return false;
			}  
      
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    } 
    
    public static final boolean isGregorianLeapYear(int year) {  
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);  
    }  
    
    public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊" };
    
    public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };
     
    public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
     
    /**
     * 根据日期获取生肖
     * @return
     */
    public static String getZodica(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }
     
    
    /**
     * 根据日期获取星座
     * @return
     */
    public static String getConstellation(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }
    
    /**
     * 获取当前日期是星期几<br>
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
			w = 0;
		}
        return weekDays[w];
    }
    
    // 是否今天
  	public static Boolean isToday(Date date) {
  		if (date == null) {
  			return false;
  		}
  		
  		String df = "yyyy-MM-dd HH:mm:ss";
  		SimpleDateFormat sdf = new SimpleDateFormat(df);
  		
  		String df1 = "yyyy-MM-dd";
  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
  		String dateStr1= sdf1.format(new Date());
  		String todayStart = dateStr1+" 00:00:00";
  		Date todayStartDate = null;
  		try {
			todayStartDate = sdf.parse(todayStart);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
  		
  		String todayEnd = dateStr1+" 23:59:59";
  		Date todayEndDate = null;
  		try {
  			todayEndDate = sdf.parse(todayEnd);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
  		if((date.after(todayStartDate) && date.before(todayEndDate)) || date.compareTo(todayEndDate)==0 ){
  			return true;
  		}
  		
  		return false;
  	}
  	
    // 是否昨天
   	public static Boolean isYesterday(Date date) {
   		if (date == null) {
   			return false;
   		}
   		Date yesterday = previousNDate(new Date(),1);
   		
   		String df = "yyyy-MM-dd HH:mm:ss";
   		SimpleDateFormat sdf = new SimpleDateFormat(df);
  		
   		String df1 = "yyyy-MM-dd";
   		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
   		String dateStr1= sdf1.format(yesterday);
   		
   		String start = dateStr1+" 00:00:00";
   		Date startDate = null;
   		try {
   			startDate = sdf.parse(start);
 		} catch (ParseException e) {
 			e.printStackTrace();
 			return false;
 		}
   		
   		String end = dateStr1+" 23:59:59";
   		Date endDate = null;
   		try {
   			endDate = sdf.parse(end);
 		} catch (ParseException e) {
 			e.printStackTrace();
 			return false;
 		}
   		if((date.after(startDate) && date.before(endDate)) || date.compareTo(endDate)==0 ){
   			return true;
   		}
   		
   		return false;
   	}
   	
    // 是否本周(上个星期日算本周)
    public static boolean isThisWeek(long time)  
    {  
        Calendar calendar = Calendar.getInstance();  
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
        calendar.setTime(new Date(time));  
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);  
        if(paramWeek==currentWeek){  
           return true;  
        }  
        return false;  
    } 
    
    // 是否年内 
    public static boolean isThisYear(Date date)  
    {  
    	if (date == null) {
   			return false;
   		}
   		Date today = new Date();
   		
   		String df = "yyyy-MM-dd HH:mm:ss";
   		SimpleDateFormat sdf = new SimpleDateFormat(df);
  		
   		String df1 = "yyyy";
   		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
   		String dateStr1= sdf1.format(today);
   		
   		String start = dateStr1+"-01-01 00:00:00";
   		Date startDate = null;
   		try {
   			startDate = sdf.parse(start);
 		} catch (ParseException e) {
 			e.printStackTrace();
 			return false;
 		}
   		
   		String end = dateStr1+"-12-31 23:59:59";
   		Date endDate = null;
   		try {
   			endDate = sdf.parse(end);
 		} catch (ParseException e) {
 			e.printStackTrace();
 			return false;
 		}
   		if((date.after(startDate) && date.before(endDate)) || date.compareTo(endDate)==0 ){
   			return true;
   		}
   		
   		return false;
    } 
    
	//针对聊天
	public static String chatFormatCn(Date date) {
		if (date == null) {
			return "";
		}
		
		if(isToday(date)){
			String df1 = "HH:mm";
	  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
	  		String dateStr1= sdf1.format(date);
			return "今天："+dateStr1;
		}
		
		if(isYesterday(date)){
			String df1 = "HH:mm";
	  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
	  		String dateStr1= sdf1.format(date);
			return "昨天："+dateStr1;
		}
		
		if(isThisWeek(date.getTime())){
			String df1 = "HH:mm";
	  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
	  		String dateStr1= sdf1.format(date);
	  		String weekStr =  getWeekOfDate(date);
			return "本周内："+weekStr +" " +dateStr1;
		}
		
		if(isThisYear(date)){
			String df1 = "MM月dd日 HH:mm";  
	  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
	  		String dateStr1= sdf1.format(date);
			return "年内：" +dateStr1;
		}
		
		String df1 = "yyyy年MM月dd日 HH:mm";  
  		SimpleDateFormat sdf1 = new SimpleDateFormat(df1);
  		String dateStr1= sdf1.format(date);
		return dateStr1;
	}
	/**
	 * 获取当前时间的前一天的凌晨一点
	 */
	public static Date getStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0); //凌晨1点  
		calendar.set(Calendar.MINUTE, 0);  
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 获取当前时间的前一天的凌晨24点
	 */
	public static Date getEndTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23); //凌晨1点  
        calendar.set(Calendar.MINUTE, 59);  
        calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
    public static void main(String[] args) throws ParseException {  
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
    	DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String today="2017-07-03";//new Date()
    	Date parse = format1.parse(today);
    	Date startDate = getStartDate(parse);
    	Date endDate = getEndDate(parse);
    	String startDate1 = format2.format(startDate);
    	String endDate1 = format2.format(endDate);
    	System.out.println(startDate1); 
    	System.out.println(endDate1); 
    }  

}
