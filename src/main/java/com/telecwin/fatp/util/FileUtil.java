package com.telecwin.fatp.util;

/**
 * 文件帮助类
 */
public class FileUtil {
	
	
	/**
	 * 获取文件后缀名
	 * @param originalFileName
	 * @return String
	 */
	public static String contentType(String originalFileName) {
		if (originalFileName == null || originalFileName.indexOf('.') == -1) {
			return "";
		}
		int endIndex = originalFileName.lastIndexOf('.');
		return originalFileName.substring(endIndex+1);
	}
	/**
	 * 获取文件显示名称
	 * @param originalFileName
	 * @return String
	 */
	public static String displayName(String originalFileName) {
		if (originalFileName == null || originalFileName.indexOf('.') == -1) {
			return "";
		}
		int endIndex = originalFileName.lastIndexOf('.');
		return  originalFileName.substring(0, endIndex);
	}
}