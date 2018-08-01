package com.fatp.po.offsite;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * BizimportTradeDetail
 * @author zhiya.chai
 * 2018-06-14 19:16:18
 */
public class BizimportTradeDetailPo implements Serializable{
	private static final long serialVersionUID = 3755248167758027495L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 业务登记申请表id, bizimport_apply.id
	 */
	private Integer bizImportApplyId;
	/**
	 * 数据文件汇总信息表id, bizimport_summary.id
	 */
	private Integer bizImportSummaryId;
	/**
	 * 产品挂牌id, listing_info.id
	 */
	private Integer listingInfoId;
	/**
	 * 交易时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date tradeTime;
	/**
	 * 交易金额
	 */
	private java.math.BigDecimal tradeMoney;
	/**
	 * 客户Id,uc_user.id
	 */
	private Integer userId;
	/**
	 * 是否本次新增的用户, 1-是, 0-否
	 */
	private Boolean isNewUser;
	/**
	 * 手机号, 敏感数据加密
	 */
	private String phoneNumber;
	/**
	 * 证件类型
	 */
	private Integer idTypeId;
	/**
	 * 证件号码, 敏感数据加密
	 */
	private String idNumber;
	/**
	 * 用户真实姓名, 敏感数据加密
	 */
	private String userRealName;
	/**
	 * 交易所id
	 */
	private Integer exchangeId;
	/**
	 * 加息利率
	 */
	private java.math.BigDecimal addInvestProfit;
	/**
	 * 加息天数
	 */
	private Integer addInvestProfitDays;
	/**
	 * 银行卡号(加密)
	 */
	private String cardAccount;
	/**
	 * 开户行名称
	 */
	private String subBankName;
	/**
	 * 转账类型
	 */
	private Integer transferType;
	/**
	 * 支行省份
	 */
	private String subBankProvince;
	/**
	 * 支行城市
	 */
	private String subBankCity;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setBizImportApplyId(Integer value) {
		this.bizImportApplyId = value;
	}
	public Integer getBizImportApplyId() {
		return this.bizImportApplyId;
	}
	public void setBizImportSummaryId(Integer value) {
		this.bizImportSummaryId = value;
	}
	public Integer getBizImportSummaryId() {
		return this.bizImportSummaryId;
	}
	public void setListingInfoId(Integer value) {
		this.listingInfoId = value;
	}
	public Integer getListingInfoId() {
		return this.listingInfoId;
	}
	public void setTradeTime(java.util.Date value) {
		this.tradeTime = value;
	}
	public java.util.Date getTradeTime() {
		return this.tradeTime;
	}
	public void setTradeMoney(java.math.BigDecimal value) {
		this.tradeMoney = value;
	}
	public java.math.BigDecimal getTradeMoney() {
		return this.tradeMoney;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	public Integer getUserId() {
		return this.userId;
	}
	public void setIsNewUser(Boolean value) {
		this.isNewUser = value;
	}
	public Boolean getIsNewUser() {
		return this.isNewUser;
	}
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public void setIdTypeId(Integer value) {
		this.idTypeId = value;
	}
	public Integer getIdTypeId() {
		return this.idTypeId;
	}
	public void setIdNumber(String value) {
		this.idNumber = value;
	}
	public String getIdNumber() {
		return this.idNumber;
	}
	public void setUserRealName(String value) {
		this.userRealName = value;
	}
	public String getUserRealName() {
		return this.userRealName;
	}
	public void setExchangeId(Integer value) {
		this.exchangeId = value;
	}
	public Integer getExchangeId() {
		return this.exchangeId;
	}
	public void setAddInvestProfit(java.math.BigDecimal value) {
		this.addInvestProfit = value;
	}
	public java.math.BigDecimal getAddInvestProfit() {
		return this.addInvestProfit;
	}
	public void setAddInvestProfitDays(Integer value) {
		this.addInvestProfitDays = value;
	}
	public Integer getAddInvestProfitDays() {
		return this.addInvestProfitDays;
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
	public Integer getTransferType() {
		return transferType;
	}
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	public String getSubBankProvince() {
		return subBankProvince;
	}
	public void setSubBankProvince(String subBankProvince) {
		this.subBankProvince = subBankProvince;
	}
	public String getSubBankCity() {
		return subBankCity;
	}
	public void setSubBankCity(String subBankCity) {
		this.subBankCity = subBankCity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}

