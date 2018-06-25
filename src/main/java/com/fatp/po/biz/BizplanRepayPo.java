package com.fatp.po.biz;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * BizplanRepay
 * @author zhiya.chai
 * 2018-06-22 13:19:29
 */
public class BizplanRepayPo {
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 还款计划唯一id，用于web表现
	 */
	private String repayPlanGuid;
	/**
	 * 挂牌产品Id
	 */
	private Integer listingInfoId;
	/**
	 * 借款方（融资方）
	 */
	private Integer loanUserId;
	/**
	 * 还款期次
	 */
	private Integer periodNumber;
	/**
	 * 正常计息天数
	 */
	private Integer interestDay;
	/**
	 * 本期正常计息开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date interestStartDate;
	/**
	 * 本期正常计息结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date interestEndDate;
	/**
	 * 本期计息本金（元）
	 */
	private java.math.BigDecimal interestPrincipal;
	/**
	 * 计划应还日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private java.util.Date planRepayDate;
	/**
	 * 本期应还本金（元）
	 */
	private java.math.BigDecimal principal;
	/**
	 * 本期应还利息（元）
	 */
	private java.math.BigDecimal interest;
	/**
	 * 累计逾期利息
	 */
	private java.math.BigDecimal overInterest;
	/**
	 * 累计逾期天数
	 */
	private Integer overDay;
	/**
	 * 还款状态
	 */
	private int repayStatus;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新人
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 更新时间
	 */
	private Integer updateOperatorId;
	/**
	 * 版本号
	 */
	private Integer versionNo;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setRepayPlanGuid(String value) {
		this.repayPlanGuid = value;
	}
	public String getRepayPlanGuid() {
		return this.repayPlanGuid;
	}
	public void setListingInfoId(Integer value) {
		this.listingInfoId = value;
	}
	public Integer getListingInfoId() {
		return this.listingInfoId;
	}
	public void setLoanUserId(Integer value) {
		this.loanUserId = value;
	}
	public Integer getLoanUserId() {
		return this.loanUserId;
	}
	public void setPeriodNumber(Integer value) {
		this.periodNumber = value;
	}
	public Integer getPeriodNumber() {
		return this.periodNumber;
	}
	public void setInterestDay(Integer value) {
		this.interestDay = value;
	}
	public Integer getInterestDay() {
		return this.interestDay;
	}
	public void setInterestStartDate(java.util.Date value) {
		this.interestStartDate = value;
	}
	public java.util.Date getInterestStartDate() {
		return this.interestStartDate;
	}
	public void setInterestEndDate(java.util.Date value) {
		this.interestEndDate = value;
	}
	public java.util.Date getInterestEndDate() {
		return this.interestEndDate;
	}
	public void setInterestPrincipal(java.math.BigDecimal value) {
		this.interestPrincipal = value;
	}
	public java.math.BigDecimal getInterestPrincipal() {
		return this.interestPrincipal;
	}
	public void setPlanRepayDate(java.util.Date value) {
		this.planRepayDate = value;
	}
	public java.util.Date getPlanRepayDate() {
		return this.planRepayDate;
	}
	public void setPrincipal(java.math.BigDecimal value) {
		this.principal = value;
	}
	public java.math.BigDecimal getPrincipal() {
		return this.principal;
	}
	public void setInterest(java.math.BigDecimal value) {
		this.interest = value;
	}
	public java.math.BigDecimal getInterest() {
		return this.interest;
	}
	public void setOverInterest(java.math.BigDecimal value) {
		this.overInterest = value;
	}
	public java.math.BigDecimal getOverInterest() {
		return this.overInterest;
	}
	public void setOverDay(Integer value) {
		this.overDay = value;
	}
	public Integer getOverDay() {
		return this.overDay;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateOperatorId(Integer value) {
		this.createOperatorId = value;
	}
	public Integer getCreateOperatorId() {
		return this.createOperatorId;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateOperatorId(Integer value) {
		this.updateOperatorId = value;
	}
	public Integer getUpdateOperatorId() {
		return this.updateOperatorId;
	}
	public void setVersionNo(Integer value) {
		this.versionNo = value;
	}
	public Integer getVersionNo() {
		return this.versionNo;
	}
	public int getRepayStatus() {
		return repayStatus;
	}
	public void setRepayStatus(int repayStatus) {
		this.repayStatus = repayStatus;
	}
}

