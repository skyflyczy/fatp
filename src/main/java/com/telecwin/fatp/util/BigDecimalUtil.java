package com.telecwin.fatp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * BigDeciamlUtil
 */
public class BigDecimalUtil {

	/**
	 * 转换,，默认值为0
	 * @param value
	 * @return BigDecimal
	 */
	public static BigDecimal convertDefaultZero(String value) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
		}
		return BigDecimal.ZERO;
	}
	/**
	 * 转换有默认值
	 * @param value
	 * @return BigDecimal
	 */
	public static BigDecimal convertAutoDefaultValue(String value,BigDecimal defaultValue){
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
		}
		return defaultValue;
	}
	
	public static String format(BigDecimal bg, String format) {
		try {
			DecimalFormat df = new DecimalFormat(format);
			return df.format(bg);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String formatMoney(BigDecimal bg) {
		return format(bg, "#,##0.00");
	}
	
}
