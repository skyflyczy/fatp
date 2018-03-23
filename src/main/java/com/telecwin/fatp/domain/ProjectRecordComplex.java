package com.telecwin.fatp.domain;

import com.telecwin.fatp.po.project.ProjectRecordinfoPo;
/**
 * 备案混合类
 */
public class ProjectRecordComplex extends ProjectRecordinfoPo{

	private static final long serialVersionUID = -3146644621081976210L;
	//发行人
	private String loanUserName;
	public String getLoanUserName() {
		return loanUserName;
	}
	public void setLoanUserName(String loanUserName) {
		this.loanUserName = loanUserName;
	}
}
