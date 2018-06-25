package com.fatp.po.biz;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * BizplanPayinvest
 * @author zhiya.chai
 * 2018-06-23 39:14:44
 */
public class BizplanPayinvestPo {
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 兑付计划唯一id，用于web表现
	 */
	private String payinvestPlanGuid;
	/**
	 * 还款计划id bizplan_repay.id(
	 */
	private Integer repayPlanId;
	/**
	 * 挂牌产品Id
	 */
	private Integer listingInfoId;
	/**
	 * 投资方/兑付用户id
	 */
	private Integer investUserId;
	/**
	 * 兑付账号
	 */
	private String cardAccount;
	/**
	 * 支行名称
	 */
	private String subBankName;
	/**
	 * 兑付期次
	 */
	private Integer periodNumber;
	/**
	 * 本期应兑付本金(元)
	 */
	private java.math.BigDecimal principal;
	/**
	 * 本期应兑付利息(元)
	 */
	private java.math.BigDecimal interest;
	/**
	 * 累计逾期利息(元)
	 */
	private java.math.BigDecimal overInterest;
	/**
	 * 累计逾期天数
	 */
	private Integer overDay;
	/**
	 * 兑付状态
	 */
	private int payinvestStatus;
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
	public void setPayinvestPlanGuid(String value) {
		this.payinvestPlanGuid = value;
	}
	public String getPayinvestPlanGuid() {
		return this.payinvestPlanGuid;
	}
	public void setRepayPlanId(Integer value) {
		this.repayPlanId = value;
	}
	public Integer getRepayPlanId() {
		return this.repayPlanId;
	}
	public void setListingInfoId(Integer value) {
		this.listingInfoId = value;
	}
	public Integer getListingInfoId() {
		return this.listingInfoId;
	}
	public void setInvestUserId(Integer value) {
		this.investUserId = value;
	}
	public Integer getInvestUserId() {
		return this.investUserId;
	}
	public void setCardAccount(String value) {
		this.cardAccount = value;
	}
	public String getCardAccount() {
		return this.cardAccount;
	}
	public void setSubBankName(String value) {
		this.subBankName = value;
	}
	public String getSubBankName() {
		return this.subBankName;
	}
	public void setPeriodNumber(Integer value) {
		this.periodNumber = value;
	}
	public Integer getPeriodNumber() {
		return this.periodNumber;
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
	public int getPayinvestStatus() {
		return payinvestStatus;
	}
	public void setPayinvestStatus(int payinvestStatus) {
		this.payinvestStatus = payinvestStatus;
	}
}

