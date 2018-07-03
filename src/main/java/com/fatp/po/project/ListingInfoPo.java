package com.fatp.po.project;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ListingInfo
 * 
 * @author zhiya.chai 2018-06-12 50:15:51
 */
public class ListingInfoPo implements Serializable {

	private static final long serialVersionUID = -9123376231557170000L;
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 发行人
	 */
	private String issuer;
	/**
	 * 产品管理人
	 */
	private String listingManager;
	/**
	 * 投资管理人
	 */
	private String investManager;
	/**
	 * 产品编号
	 */
	private String listingCode;
	/**
	 * 产品唯一id，用于web展现
	 */
	private String listingGuid;
	/**
	 * 合作方挂牌代码
	 */
	private String partnerBizCode;
	/**
	 * 合作方（发行渠道）
	 */
	private String partnerBiz;
	/**
	 * 产品名称
	 */
	private String listingName;
	/**
	 * 产品全称
	 */
	private String listingFullName;
	/**
	 * 产品融资资金规模,单位元
	 */
	private java.math.BigDecimal listingMoney;
	/**
	 * 产品期限
	 */
	private Integer listingLimit;
	/**
	 * 产品期限类型：1天2月3年
	 */
	private Integer listingLimitType;
	/**
	 * 收益率类型
	 */
	private Integer investProfitType;
	/**
	 * 到期日规则 1：固定期限 2：固定到期日
	 */
	private Integer expireDateStyle;
	/**
	 * 产品起息日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date valueDate;
	/**
	 * 产品到期日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date expireDate;
	/**
	 * 付息方式：
	 */
	private Integer payInterestType;
	/**
	 * 计息方式：单利1，复利2
	 */
	private Integer interestType;
	/**
	 * 计息频率：1按日计息，2按月计息，3按年计息,4按季计息，5按半年计息
	 */
	private Integer interestRate;
	/**
	 * 计息基准：1、ACT/365，2、ACT/360，3、ACT/ACT
	 */
	private Integer interestBase;
	/**
	 * 计息基准天数：默认365
	 */
	private Integer interestBaseDays;
	/**
	 * 到期日是否计息：1是0否
	 */
	private Integer expireDateInterest;
	/**
	 * 挂牌状态
	 */
	private Integer listingStatus;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**
	 * 创建人,member_operator.id
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**
	 * 更新人,member_operator.id
	 */
	private Integer upateOperatorId;
	/**
	 * 数据版本号,保障并发修改的唯一性
	 */
	private Integer versionNo;

	// 预期收益率值（%）
	private String profitValue;

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setExchangeId(Integer value) {
		this.exchangeId = value;
	}

	public Integer getExchangeId() {
		return this.exchangeId;
	}

	public void setIssuer(String value) {
		this.issuer = value;
	}

	public String getIssuer() {
		return this.issuer;
	}

	public void setListingManager(String value) {
		this.listingManager = value;
	}

	public String getListingManager() {
		return this.listingManager;
	}

	public void setInvestManager(String value) {
		this.investManager = value;
	}

	public String getInvestManager() {
		return this.investManager;
	}

	public void setListingCode(String value) {
		this.listingCode = value;
	}

	public String getListingCode() {
		return this.listingCode;
	}

	public void setListingGuid(String value) {
		this.listingGuid = value;
	}

	public String getListingGuid() {
		return this.listingGuid;
	}

	public void setPartnerBizCode(String value) {
		this.partnerBizCode = value;
	}

	public String getPartnerBizCode() {
		return this.partnerBizCode;
	}

	public void setPartnerBiz(String value) {
		this.partnerBiz = value;
	}

	public String getPartnerBiz() {
		return this.partnerBiz;
	}

	public void setListingName(String value) {
		this.listingName = value;
	}

	public String getListingName() {
		return this.listingName;
	}

	public void setListingFullName(String value) {
		this.listingFullName = value;
	}

	public String getListingFullName() {
		return this.listingFullName;
	}

	public void setListingMoney(java.math.BigDecimal value) {
		this.listingMoney = value;
	}

	public java.math.BigDecimal getListingMoney() {
		return this.listingMoney;
	}

	public void setListingLimit(Integer value) {
		this.listingLimit = value;
	}

	public Integer getListingLimit() {
		return this.listingLimit;
	}

	public void setListingLimitType(Integer value) {
		this.listingLimitType = value;
	}

	public Integer getListingLimitType() {
		return this.listingLimitType;
	}

	public void setExpireDateStyle(Integer value) {
		this.expireDateStyle = value;
	}

	public Integer getExpireDateStyle() {
		return this.expireDateStyle;
	}

	public void setValueDate(java.util.Date value) {
		this.valueDate = value;
	}

	public java.util.Date getValueDate() {
		return this.valueDate;
	}

	public void setExpireDate(java.util.Date value) {
		this.expireDate = value;
	}

	public java.util.Date getExpireDate() {
		return this.expireDate;
	}

	public void setPayInterestType(Integer value) {
		this.payInterestType = value;
	}

	public Integer getPayInterestType() {
		return this.payInterestType;
	}

	public void setInterestType(Integer value) {
		this.interestType = value;
	}

	public Integer getInterestType() {
		return this.interestType;
	}

	public void setInterestRate(Integer value) {
		this.interestRate = value;
	}

	public Integer getInterestRate() {
		return this.interestRate;
	}

	public void setInterestBase(Integer value) {
		this.interestBase = value;
	}

	public Integer getInterestBase() {
		return this.interestBase;
	}

	public void setInterestBaseDays(Integer value) {
		this.interestBaseDays = value;
	}

	public Integer getInterestBaseDays() {
		return this.interestBaseDays;
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

	public void setUpateOperatorId(Integer value) {
		this.upateOperatorId = value;
	}

	public Integer getUpateOperatorId() {
		return this.upateOperatorId;
	}

	public void setVersionNo(Integer value) {
		this.versionNo = value;
	}

	public Integer getVersionNo() {
		return this.versionNo;
	}

	public Integer getExpireDateInterest() {
		return expireDateInterest;
	}

	public void setExpireDateInterest(Integer expireDateInterest) {
		this.expireDateInterest = expireDateInterest;
	}

	public Integer getInvestProfitType() {
		return investProfitType;
	}

	public void setInvestProfitType(Integer investProfitType) {
		this.investProfitType = investProfitType;
	}

	public Integer getListingStatus() {
		return listingStatus;
	}

	public void setListingStatus(Integer listingStatus) {
		this.listingStatus = listingStatus;
	}

	public String toString() {
		return "{ListingInfoPo [id=" + id + "][exchangeId=" + exchangeId + "][issuer=" + issuer + "][listingManager="
				+ listingManager + "][investManager=" + investManager + "][listingCode=" + listingCode
				+ "][listingGuid=" + listingGuid + "][partnerBizCode=" + partnerBizCode + "][partnerBiz=" + partnerBiz
				+ "][listingName=" + listingName + "][listingFullName=" + listingFullName + "][listingMoney="
				+ listingMoney + "][listingLimit=" + listingLimit + "][listingLimitType=" + listingLimitType
				+ "][investProfitType=" + investProfitType + "][expireDateStyle=" + expireDateStyle + "][valueDate="
				+ valueDate + "][expireDate=" + expireDate + "][payInterestType=" + payInterestType + "][interestType="
				+ interestType + "][interestRate=" + interestRate + "][interestBase=" + interestBase
				+ "][interestBaseDays=" + interestBaseDays + "][expireDateInterest=" + expireDateInterest
				+ "][listingStatus=" + listingStatus + "][createTime=" + createTime + "][createOperatorId="
				+ createOperatorId + "][updateTime=" + updateTime + "][upateOperatorId=" + upateOperatorId
				+ "][profitValue=" + profitValue+ "][versionNo=" + versionNo + "]}";
	}

	//保存预期收益率值（%）
	public void setProfitValue(String value) {
		this.profitValue = value;
	}

	public String getProfitValue() {
		return this.profitValue;
	}
}
