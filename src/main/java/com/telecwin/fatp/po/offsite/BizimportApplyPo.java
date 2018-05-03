package com.telecwin.fatp.po.offsite;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * BizimportApply
 */
public class BizimportApplyPo {
	/**
	 * 主键
	 */
	private int id;

	/**
	 * 申请唯一编码
	 */
	private String applyGuid;

	/**
	 * 产品id, project_baseinfo.id
	 */
	private int projectId;

	/**
	 * 产品管理人id, uc_user.id
	 */
	private Integer memberId;

	/**
	 * 申请类型：投资登记申请(1)
	 */
	private short applyType;

	/**
	 * 申请状态。待提交(1), 登记中(2), 登记成功(3), 登记失败(4),已核保(5);
	 */
	private short applyStatus;

	/**
	 * 审核意见/校验结果
	 */
	private String auditRemark;

	/**
	 * 申请操作员id, member_operator.id
	 */
	private Integer applyOperatorId;

	/**
	 * 申请者会员id, uc_user.id
	 */
	private Integer applyMemberId;

	/**
	 * 审核时间
	 */
	private java.util.Date auditTime;

	/**
	 * 审核者id, member_operator.id
	 */
	private Integer auditOperatorId;

	/**
	 * 申请来源：默认（1）
	 */
	private short applySource;
	
	/**
	 * 申请提交审核时间
	 */
	private java.util.Date submitTime;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private java.util.Date valueDate;

	public void setId(int value) {
		this.id = value;
	}

	public int getId() {
		return this.id;
	}

	public void setApplyGuid(String value) {
		this.applyGuid = value;
	}

	public String getApplyGuid() {
		return this.applyGuid;
	}

	public void setProjectId(int value) {
		this.projectId = value;
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setMemberId(Integer value) {
		this.memberId = value;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setApplyType(short value) {
		this.applyType = value;
	}

	public short getApplyType() {
		return this.applyType;
	}

	public void setApplyStatus(short value) {
		this.applyStatus = value;
	}

	public short getApplyStatus() {
		return this.applyStatus;
	}

	public void setAuditRemark(String value) {
		this.auditRemark = value;
	}

	public String getAuditRemark() {
		return this.auditRemark;
	}

	public void setApplyOperatorId(Integer value) {
		this.applyOperatorId = value;
	}

	public Integer getApplyOperatorId() {
		return this.applyOperatorId;
	}

	public void setApplyMemberId(Integer value) {
		this.applyMemberId = value;
	}

	public Integer getApplyMemberId() {
		return this.applyMemberId;
	}

	public void setAuditTime(java.util.Date value) {
		this.auditTime = value;
	}

	public java.util.Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditOperatorId(Integer value) {
		this.auditOperatorId = value;
	}

	public Integer getAuditOperatorId() {
		return this.auditOperatorId;
	}

	public void setApplySource(short value) {
		this.applySource = value;
	}

	public short getApplySource() {
		return this.applySource;
	}

	public java.util.Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(java.util.Date submitTime) {
		this.submitTime = submitTime;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public java.util.Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(java.util.Date valueDate) {
		this.valueDate = valueDate;
	}
	
}
