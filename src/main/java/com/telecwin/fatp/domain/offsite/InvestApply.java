package com.telecwin.fatp.domain.offsite;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

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
    
    /**
	 * 项目起息日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date valueDate;
	/**
	 * 申购开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date buyTimeStart;
	/**
	 * 申购结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date buyTimeEnd;
	/**
	 * 起投份额
	 */
	private java.math.BigDecimal investAmountMin;
	/**
	 * 申购份额上限 
	 */
	private java.math.BigDecimal investAmountMax;
	/**
	 * 追加金额
	 */
	private java.math.BigDecimal investAmountAppend;
    
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
	public java.util.Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(java.util.Date valueDate) {
		this.valueDate = valueDate;
	}
	public java.util.Date getBuyTimeStart() {
		return buyTimeStart;
	}
	public void setBuyTimeStart(java.util.Date buyTimeStart) {
		this.buyTimeStart = buyTimeStart;
	}
	public java.util.Date getBuyTimeEnd() {
		return buyTimeEnd;
	}
	public void setBuyTimeEnd(java.util.Date buyTimeEnd) {
		this.buyTimeEnd = buyTimeEnd;
	}
	public java.math.BigDecimal getInvestAmountMin() {
		return investAmountMin;
	}
	public void setInvestAmountMin(java.math.BigDecimal investAmountMin) {
		this.investAmountMin = investAmountMin;
	}
	public java.math.BigDecimal getInvestAmountMax() {
		return investAmountMax;
	}
	public void setInvestAmountMax(java.math.BigDecimal investAmountMax) {
		this.investAmountMax = investAmountMax;
	}
	public java.math.BigDecimal getInvestAmountAppend() {
		return investAmountAppend;
	}
	public void setInvestAmountAppend(java.math.BigDecimal investAmountAppend) {
		this.investAmountAppend = investAmountAppend;
	}
}
