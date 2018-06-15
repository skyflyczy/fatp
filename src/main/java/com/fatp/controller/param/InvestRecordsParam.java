package com.fatp.controller.param;

import org.apache.commons.lang3.StringUtils;

/**
 * 投资明细更新参数
 */
public class InvestRecordsParam {

	private String listingGuid;
	private String excelFileName;
	private String excelFilePath;
	private String valueDate;
	
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public String getLinkFileName() {
		if(StringUtils.isBlank(excelFileName)) {
			return null;
		}
		String[] names = this.excelFileName.split(":");
		return names.length == 2 ? names[1] : names[0];
	}
	public String getOriginalFileName() {
		if(StringUtils.isBlank(excelFileName)) {
			return null;
		}
		String[] names = this.excelFileName.split(":");
		return names[0];
	}
	public String getListingGuid() {
		return listingGuid;
	}
	public void setListingGuid(String listingGuid) {
		this.listingGuid = listingGuid;
	}
	
}
