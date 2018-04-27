package com.telecwin.fatp.domain.offsite;

import com.telecwin.fatp.po.offsite.BizimportTradeDetailPo;

public class BizimportTradeDetail extends BizimportTradeDetailPo{

	private String projectCode;
	private String projectName;
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
}
