package com.fatp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;

/**
 * 日期工具类
 */
public class DateUtil extends DateUtils {

	private static String yyyyMM = "yyyyMM";
	private static String yyyy = "yyyy";
	private static String yyyy_MM_dd = "yyyy-MM-dd";
	
	private static String[] parsePatterns = {
		"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss",
		"yyyyMMddHHmm", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy.MM.dd HH:mm",
		"yyyyMMddHH", "yyyy-MM-dd HH", "yyyy/MM/dd HH", "yyyy.MM.dd HH",
		"yyyyMMdd", "yyyy-MM-dd",  "yyyy/MM/dd", "yyyy.MM.dd"
		};
	
	public static Date autoParseDate(String str) {
		if (StringUtils.isBlank(str)) 
			return null;
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static int getdiffDays(Date MaxDay,Date minDay){
		try {
			long diff = MaxDay.getTime() - minDay.getTime();
			Long days = diff / (1000 * 60 * 60 * 24);
			return days.intValue();
		} catch (Exception e) {
			
		}
		return 0;
	}
	/**
	 * 去掉时间相减
	 * @param maxDay
	 * @param minDay
	 * @return int
	 */
	public static int getDiffByDate(Date maxDay,Date minDay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
			maxDay = format.parse(format.format(maxDay));
			minDay = format.parse(format.format(minDay));
			long diff = maxDay.getTime() - minDay.getTime();
			Long days = diff / (1000 * 60 * 60 * 24);
			return days.intValue();
		} catch (Exception e) {
		}
		return 0;
	}
	/**
	 * 判断结束日期与开始日期的时间差是否大于某个特定的值
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @param field 对应于Calendar的field
	 * @param distance 差值
	 * @return
	 */
	public static boolean compare(Date startDate,Date endDate,int field,int distance){
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(field, distance);
		if(endDate.getTime() >= c.getTime().getTime())
			return true;
		else 
			return false;
	}
	/**
	 * 获取本个月开始日期
	 * @return
	 * @return Date
	 */
	public static Date getMinMonthDate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			String date = format.format(calendar.getTime());
			return format.parse(date);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(DateUtil.class.getName(), "getMinMonthDate", e));
		}
		return null;
	}
	/**
	 * 获取本个月结束日期
	 * @return
	 * @return Date
	 */
	public static Date getMaxMonthDate(){
		try {
			SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			String date = format.format(calendar.getTime());
			return format.parse(date);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(DateUtil.class.getName(), "getMaxMonthDate", e));
		}
		return null;
	}
	/**
	 * 获取年月
	 * @return
	 * @return Integer
	 */
	public static Integer getYearMonth(){
		SimpleDateFormat format = new SimpleDateFormat(yyyyMM);
		return Integer.parseInt(format.format(new Date()));
	}
	/**
	 * 获取当前年月
	 * @return
	 * @return Integer
	 */
	public static Integer getCurrentYearMonth(){
		SimpleDateFormat format = new SimpleDateFormat(yyyyMM);
		Date date = new Date();
		return Integer.parseInt(format.format(date));
	}
	
	/**
	 * 获取年
	 * @param date
	 */
	public static int getYear(Date date){
		SimpleDateFormat format = new SimpleDateFormat(yyyy);
		return  Integer.parseInt(format.format(date));
	}
	
	/**
	 * 获取当前日期所属月的天数与传入的天数的最小值
	 * @return int
	 */
	public static int getMinDay(Date date,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxMonthDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(day < maxMonthDay){
			return day;
		}else{
			return maxMonthDay;
		}
	}
	
	/**
	 * 获取当前月的最大日期
	 * @return
	 * @return String
	 */
	public static String getUpMonthMaxDate(){
		SimpleDateFormat format = new SimpleDateFormat(yyyy_MM_dd);
		Calendar calendar = Calendar.getInstance();  
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return format.format(calendar.getTime());
	}
	/**
	 * 根据格式转换为日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date convertDate(String date,String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null ;
	}
	/**
	 * 是否是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 获取年份的第一天
	 * @param year
	 * @return
	 */
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        return calendar.getTime();  
    }  
    /**
     * 获取年份的最后一天
     * @param year
     * @return
     */
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        return calendar.getTime();  
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
    
}
