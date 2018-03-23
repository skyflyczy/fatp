package com.telecwin.fatp.po;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * FeExchange
 */
public class FeExchangePo implements Serializable {
	private static final long serialVersionUID = 1522127452172046444L;
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 交易所唯一id，用于web显示
	 */
	private String exChangeGuid;
	/**
	 * 交易所编号
	 */
	private String exChangeCode;
	/**
	 * 交易所全称
	 */
	private String exchangeName;
	/**
	 * 交易所简称
	 */
	private String simpleName;
	/**
	 * 账户类型
	 */
	private Integer accountType;
	/**
	 * 银行网点代码，sys_bankchannel.id (如果与渠道相关则填)
	 */
	private Integer channelId;
	/**
	 * 交易所收入户-银行卡号(加密)
	 */
	private String cardAccount;
	/**
	 * 交易所收入户-银行账号名称(加密)
	 */
	private String accountName;
	/**
	 * 开户行省份Id
	 */
	private Integer provinceId;
	/**
	 * 开户行城市id
	 */
	private Integer cityId;
	/**
	 * 开户行名称
	 */
	private String subBankName;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 版权所有
	 */
	private String poweredBy;
	/**
	 * ats系统中文名称
	 */
	private String atsSysName;
	/**
	 * jgoms系统中文名称
	 */
	private String jgomsSysName;
	/**
	 * ptoms系统中文名称
	 */
	private String ptomsSysName;
	/**
	 * 银行id sys_bank.id
	 */
	private Integer bankId;
	/**
	 * 开户行联行号
	 */
	private String subBankNo;
	
	private String description;
	private String exchangeEng;
	private String poweredBegin;
	private String websiteRecord;
	private String icpRecord;
	private String recordProvince;
	private String businessLicense; //营业执照号 add by 2016-10-27
	private String legalRepName;//法定代表人
	private String legalRepIdNumber;//法定代表人身份证\
	private String address; //地址
	private String postalCode; //邮编

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setExChangeGuid(String value) {
		this.exChangeGuid = value;
	}
	public String getExChangeGuid() {
		return this.exChangeGuid;
	}
	public void setExChangeCode(String value) {
		this.exChangeCode = value;
	}
	public String getExChangeCode() {
		return this.exChangeCode;
	}
	public void setExchangeName(String value) {
		this.exchangeName = value;
	}
	public String getExchangeName() {
		return this.exchangeName;
	}
	public void setSimpleName(String value) {
		this.simpleName = value;
	}
	public String getSimpleName() {
		return this.simpleName;
	}
	public void setAccountType(Integer value) {
		this.accountType = value;
	}
	public Integer getAccountType() {
		return this.accountType;
	}
	public void setChannelId(Integer value) {
		this.channelId = value;
	}
	public Integer getChannelId() {
		return this.channelId;
	}
	public void setCardAccount(String value) {
		this.cardAccount = value;
	}
	public String getCardAccount() {
		return this.cardAccount;
	}
	public void setAccountName(String value) {
		this.accountName = value;
	}
	public String getAccountName() {
		return this.accountName;
	}
	public void setProvinceId(Integer value) {
		this.provinceId = value;
	}
	public Integer getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
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
	public void setPoweredBy(String value) {
		this.poweredBy = value;
	}
	public String getPoweredBy() {
		return this.poweredBy;
	}
	public void setAtsSysName(String value) {
		this.atsSysName = value;
	}
	public String getAtsSysName() {
		return this.atsSysName;
	}
	public void setJgomsSysName(String value) {
		this.jgomsSysName = value;
	}
	public String getJgomsSysName() {
		return this.jgomsSysName;
	}
	public void setPtomsSysName(String value) {
		this.ptomsSysName = value;
	}
	public String getPtomsSysName() {
		return this.ptomsSysName;
	}
	public void setBankId(Integer value) {
		this.bankId = value;
	}
	public Integer getBankId() {
		return this.bankId;
	}
	public void setSubBankNo(String value) {
		this.subBankNo = value;
	}
	public String getSubBankNo() {
		return this.subBankNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExchangeEng() {
		return exchangeEng;
	}
	public String getPoweredBegin() {
		return poweredBegin;
	}
	public String getWebsiteRecord() {
		return websiteRecord;
	}
	public String getIcpRecord() {
		return icpRecord;
	}
	public String getRecordProvince() {
		return recordProvince;
	}
	public void setExchangeEng(String exchangeEng) {
		this.exchangeEng = exchangeEng;
	}
	public void setPoweredBegin(String poweredBegin) {
		this.poweredBegin = poweredBegin;
	}
	public void setWebsiteRecord(String websiteRecord) {
		this.websiteRecord = websiteRecord;
	}
	public void setIcpRecord(String icpRecord) {
		this.icpRecord = icpRecord;
	}
	public void setRecordProvince(String recordProvince) {
		this.recordProvince = recordProvince;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getLegalRepName() {
		return legalRepName;
	}
	public void setLegalRepName(String legalRepName) {
		this.legalRepName = legalRepName;
	}
	public String getLegalRepIdNumber() {
		return legalRepIdNumber;
	}
	public void setLegalRepIdNumber(String legalRepIdNumber) {
		this.legalRepIdNumber = legalRepIdNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
}

