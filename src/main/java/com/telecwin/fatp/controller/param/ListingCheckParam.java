package com.telecwin.fatp.controller.param;

public class ListingCheckParam {
	
	private Integer id;
	/**
	 * 起息日规则
	 */
	private Integer valueDateChangeStyle;
	/**
	 * 到期日规则
	 */
	private Integer expireDateChangeStyle;
	/**
	 * 是否可以多次放款
	 */
	private Integer multipleRelease;
	/**
	 * 最多放款次数
	 */
	private Integer maxReleaseNum;
	
	private Integer operatorId;
	private String operatorName;
	private String auditRemark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getValueDateChangeStyle() {
		return valueDateChangeStyle;
	}
	public void setValueDateChangeStyle(Integer valueDateChangeStyle) {
		this.valueDateChangeStyle = valueDateChangeStyle;
	}
	public Integer getExpireDateChangeStyle() {
		return expireDateChangeStyle;
	}
	public void setExpireDateChangeStyle(Integer expireDateChangeStyle) {
		this.expireDateChangeStyle = expireDateChangeStyle;
	}
	public Integer getMultipleRelease() {
		return multipleRelease;
	}
	public void setMultipleRelease(Integer multipleRelease) {
		this.multipleRelease = multipleRelease;
	}
	public Integer getMaxReleaseNum() {
		return maxReleaseNum;
	}
	public void setMaxReleaseNum(Integer maxReleaseNum) {
		this.maxReleaseNum = maxReleaseNum;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	
}
