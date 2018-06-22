package com.fatp.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils{
	
	/**
	 * 将字符串分割转换成Integer数组
	 * @param str 分割的字符串
	 * @param symbol 分割符号 ：","等
	 * @return
	 * @author zhiya.chai
	 */
	public static List<Integer> stringSplitToInteger(String str,String symbol) {
		List<Integer> list = new ArrayList<>();
		if(StringUtil.isNotBlank(str)) {
			String[] array = StringUtil.split(str, symbol);
			for(String s : array) {
				if(isBlank(s)) {
					continue;
				}
				list.add(Integer.valueOf(s));
			}
		}
		return list;
	}
	
}
