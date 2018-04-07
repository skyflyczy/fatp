package com.telecwin.fatp.domain.project;

import com.telecwin.fatp.enums.project.ProjectLimitType;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
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
	
	public String getRecordStatusDesc() {
		return RecordStatusDesc.getDesc(super.getRecordStatus());
	}
	public boolean canEdit() {
		return super.getRecordStatus() == RecordStatusDesc.审核退回.value
				|| super.getRecordStatus() == RecordStatusDesc.待提交.value;
	}
	public boolean canDelete() {
		return super.getRecordStatus() == RecordStatusDesc.审核退回.value
				|| super.getRecordStatus() == RecordStatusDesc.待提交.value
				|| super.getRecordStatus() == RecordStatusDesc.审核不通过.value;
	}
	public String getProjectLimitTypeDesc() {
		return ProjectLimitType.getProjectLimitType(super.getProjectLimitTypeId()).toString();
	}
}
