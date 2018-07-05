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
	/**
	 * 格式化银行卡号
	 * @param cardAccount
	 * @return
	 */
	public static String formatCardAccount(String cardAccount) {
		if(isBlank(cardAccount)) {
			return "";
		}
		int length = cardAccount.length();
	    int beforeLength = 4;
	    int afterLength = 4;
	    //替换字符串，当前使用“*”
	    String replaceSymbol = "*";
	    StringBuffer sb = new StringBuffer();
	    for(int i=0; i<length; i++) {
	       if(i < beforeLength || i >= (length - afterLength)) {
	          sb.append(cardAccount.charAt(i));
	       } else {
	           sb.append(replaceSymbol);
	       }
	    }
	    return sb.toString();
	}
	
}
