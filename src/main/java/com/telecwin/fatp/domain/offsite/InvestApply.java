package com.telecwin.fatp.domain.offsite;

import java.math.BigDecimal;

public class InvestApply {

	private Integer projectId;
	/**
     * 项目编号
     */
    private String projectCode;
    /**
     * 项目唯一id，用于web展现
     */
    private String projectGuid;
    /**
     * 交易所id,fe_exchange.id
     */
    private Integer exchangeId;
    /**
     * 项目全称
     */
    private String projectFullName;
    private BigDecimal projectMoney;
    /**
     * 放款次数
     */
    private Integer releaseNum;
    /**
     * 申请登记的数量（不包括未提交和登记失败的）
     */
    private Integer applyNum;
    /**
     * 申请登记的金额（不包括未提交和登记失败的）
     */
    private BigDecimal applyMoney;
    /**
     * 申请状态
     */
    private Integer applyStatus;
    /**
     * 申请Guid
     */
    private String ApplyGuid;
    private String loanUserName;
    private Integer projectStatus;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectGuid() {
		return projectGuid;
	}
	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}
	public Integer getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getProjectFullName() {
		return projectFullName;
	}
	public void setProjectFullName(String projectFullName) {
		this.projectFullName = projectFullName;
	}
	public Integer getReleaseNum() {
		return releaseNum;
	}
	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApplyGuid() {
		return ApplyGuid;
	}
	public void setApplyGuid(String applyGuid) {
		ApplyGuid = applyGuid;
	}
	public String getLoanUserName() {
		return loanUserName;
	}
	public void setLoanUserName(String loanUserName) {
		this.loanUserName = loanUserName;
	}
	public BigDecimal getProjectMoney() {
		return projectMoney;
	}
	public void setProjectMoney(BigDecimal projectMoney) {
		this.projectMoney = projectMoney;
	}
	public BigDecimal getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	
}
