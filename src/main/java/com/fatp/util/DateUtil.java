package com.fatp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
	private static String MM = "MM";
	private static String dd = "dd";
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
	 * 月份相减，得到N月零N天  int[0]:月，int[1]天
	 * @param minDay
	 * @param maxDay
	 * @return
	 */
	public static int[] getDiffByMonth(Date maxDay,Date minDay) {
		int y = Period.between(LocalDate.of(getYear(minDay), getMonth(minDay), getDay(minDay)),  LocalDate.of(getYear(maxDay), getMonth(maxDay), getDay(maxDay))).getYears();
		int m = Period.between(LocalDate.of(getYear(minDay), getMonth(minDay), getDay(minDay)),  LocalDate.of(getYear(maxDay), getMonth(maxDay), getDay(maxDay))).getMonths();
		m = y * 12 + m;
		Date calDate = add(minDay, Calendar.MONTH, m);
		if(calDate.before(maxDay)) {
        	return new int[]{m,getDiffByDate(maxDay, calDate)};
        } else if(calDate.after(maxDay)){
        	Date calDate2 = add(calDate, Calendar.MONTH, -1);
        	return new int[]{m-1,getDiffByDate(maxDay, calDate2)};
        } else {
        	return new int[]{m,0};
        } 
	}
	/**
	 * 年份相减，得到N年零N天  int[0]:年，int[1]天
	 * @param minDay
	 * @param maxDate
	 * @return
	 */
	public static int[] getDiffByYear(Date maxDay,Date minDay) {
		int y = Period.between(LocalDate.of(getYear(minDay), getMonth(minDay), getDay(minDay)),  LocalDate.of(getYear(maxDay), getMonth(maxDay), getDay(maxDay))).getYears();
		if(y == 0) {
			return new int[]{0,getDiffByDate(maxDay, minDay)};
		}
		Date calDate = add(minDay, Calendar.YEAR, y);
		if(calDate.before(maxDay)) {
			return new int[]{y,getDiffByDate(maxDay, calDate)};
		} else if(calDate.after(maxDay)){
			 Date calDate2 = add(calDate, Calendar.YEAR, -1);
			 return new int[]{y-1,getDiffByDate(maxDay, calDate2)};
		} else {
			return new int[]{0,1};
		}
	}
	/**
	 * 季相减，得到N季零N天  int[0]:季，int[1]天
	 * @param minDay
	 * @param maxDate
	 * @return
	 */
	public static int[] getDiffBySeason(Date maxDay,Date minDay) {
		int []months = getDiffByMonth(maxDay, minDay);
		int m = months[0];
		int d = months[1];
		if(m % 3 == 0 ){
			return new int[]{m/3 , d};
		} else {
			//剩余月数按天计算
			int sm = m % 3;
			Date calDate = add(minDay, Calendar.MONTH, m - sm);
			d = getDiffByDate(maxDay, calDate);
			return new int[]{m/3 , d};
		}
	}
	/**
	 * 半年相减，得到N半年零N天  int[0]:半年，int[1]天
	 * @param minDay
	 * @param maxDate
	 * @return
	 */
	public static int[] getDiffByHalfAYear(Date maxDay,Date minDay) {
		int []months = getDiffByMonth(maxDay, minDay);
		int m = months[0];
		int d = months[1];
		if(m % 6 == 0 ){
			return new int[]{m/6 , d};
		} else {
			//剩余月数按天计算
			int sm = m % 6;
			Date calDate = add(minDay, Calendar.MONTH, m - sm);
			d = getDiffByDate(maxDay, calDate);
			return new int[]{m/6 , d};
		}
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
	 * 获取月
	 * @param date
	 * @author zhiya.chai
	 * @date 2017年5月17日 上午10:10:29
	 */
	public static int getMonth(Date date){
		SimpleDateFormat format = new SimpleDateFormat(MM);
		return  Integer.parseInt(format.format(date));
	}
	
	/**
	 * 获取日
	 * @param date
	 * @author zhiya.chai
	 * @date 2017年5月17日 上午10:10:29
	 */
	public static int getDay(Date date){
		SimpleDateFormat format = new SimpleDateFormat(dd);
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
	/**
	 * 获取N天、月、年后的日期
	 * @param date
	 * @param field 
	 * @param value
	 * @return
	 */
	public static Date add(Date date, int field, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, n);
		return c.getTime();
	}
    
}
