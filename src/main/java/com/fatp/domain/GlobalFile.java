package com.fatp.domain;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.fatp.po.GlobalFilePo;

public class GlobalFile extends GlobalFilePo{

	private static final long serialVersionUID = -9087911360173479034L;
	
	private String linkFileName;
	private String excelFilePath;

	public String getLinkFileName() {
		if (StringUtils.isBlank(super.getFilePath())) {
			return null;
		}
		Pattern r = Pattern.compile(File.separator, Pattern.LITERAL);
		String[] files = r.split(super.getFilePath());
		this.linkFileName = files[(files.length - 1)];
		return this.linkFileName;
	}
	public void setLinkFileName(String linkFileName) {
		this.linkFileName = linkFileName;
	}

	public String getExcelFilePath() {
		return excelFilePath;
	}

	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
}
