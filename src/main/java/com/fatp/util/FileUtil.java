package com.fatp.util;

import java.io.File;

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
	/**
	 * 删除文件，如果文件夹目录为空，删除此文件夹
	 * @param fileDir
	 * @param fileName
	 */
	public static void fileDelete(String fileDir,String fileName) {
		String filePath = fileDir + File.separator + fileName;
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		File dir = new File(fileDir);
		if(dir.exists() 
				&& (dir.listFiles() != null || dir.listFiles().length ==0)) {
			dir.delete();
		}
	}
}