package com.telecwin.fatp.controller.param;

import org.apache.commons.lang3.StringUtils;

/**
 * 投资明细更新参数
 */
public class InvestRecordsParam {

	private String applyGuid;
	private Integer projectId;
	private String excelFileName;
	private String excelFilePath;
	private String valueDate;
	private int submit;
	
	public String getApplyGuid() {
		return applyGuid;
	}
	public void setApplyGuid(String applyGuid) {
		this.applyGuid = applyGuid;
	}
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
	public int getSubmit() {
		return submit;
	}
	public void setSubmit(int submit) {
		this.submit = submit;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
}
