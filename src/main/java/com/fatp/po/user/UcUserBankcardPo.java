package com.fatp.po.user;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 
 * UcUserBankcard
 */
public class UcUserBankcardPo implements Serializable{
	private static final long serialVersionUID = -5726017847981072283L;
	/**
	 * 卡id
	 */
	private Integer id;
	/**
	 * 卡唯一id，用于web显示
	 */
	private String userCardGuid;
	/**
	 * 用户id,uc_user.id
	 */
	private Integer userId;
	/**
	 * 银行鉴权渠道id sys_bankchannel.id
	 */
	private Integer channelId;
	/**
	 * 银行卡号(加密)
	 */
	private String cardAccount;
	/**
	 * 银行账号名称(加密)
	 */
	private String accountName;
	/**
	 * 卡状态，正常=1,删除=0,冻结=2
	 */
	private Integer cardStatus;
	/**
	 * 是否本人卡支付 本人卡=1 非本人卡=2
	 */
	private Integer isMyCard;
	/**
	 * 验卡状态 未验证=0,已验证=1
	 */
	private Integer identifyStatus;
	/**
	 * 验证结果数据
	 */
	private String identifyData;
	/**
	 * 网银=1,快捷=2,其他=3
	 */
	private Integer identifyType;
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
	 * 创建人
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 更新人
	 */
	private Integer updateOperatorId;
	/**
	 * 开户行省份id，可为空
	 */
	private Integer provinceId;
	/**
	 * 开户行省份名称
	 */
	private String provinceName;
	/**
	 * 开户行城市id 可为空
	 */
	private Integer cityId;
	/**
	 * 开户行城市名称
	 */
	private String cityName;
	/**
	 * 非默认卡=0  默认卡=1
	 */
	private Integer defaultCard;
	/**
	 * 代收签约账户=1 普通回款账户=0
	 */
	private Integer isPayCollecting;
	/**
	 * 银行id sys_bank.id
	 */
	private Integer bankId;
	/**
	 * 银行联行号id sys_bank_serialno.id
	 */
	private Integer bankSerialId;
	/**
	 * 银行卡手机号(加密)
	 */
	private String phoneInBank;
	/**
	 * 绑卡支付渠道id
	 */
	private Integer payCenterId;
	/**
	 * 渠道与银行卡的绑定流水号
	 */
	private String cardBindingNo;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setUserCardGuid(String value) {
		this.userCardGuid = value;
	}
	public String getUserCardGuid() {
		return this.userCardGuid;
	}
	public void setUserId(Integer value) {
		this.userId = value;
	}
	public Integer getUserId() {
		return this.userId;
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
	public void setCardStatus(Integer value) {
		this.cardStatus = value;
	}
	public Integer getCardStatus() {
		return this.cardStatus;
	}
	public void setIsMyCard(Integer value) {
		this.isMyCard = value;
	}
	public Integer getIsMyCard() {
		return this.isMyCard;
	}
	public void setIdentifyStatus(Integer value) {
		this.identifyStatus = value;
	}
	public Integer getIdentifyStatus() {
		return this.identifyStatus;
	}
	public void setIdentifyData(String value) {
		this.identifyData = value;
	}
	public String getIdentifyData() {
		return this.identifyData;
	}
	public void setIdentifyType(Integer value) {
		this.identifyType = value;
	}
	public Integer getIdentifyType() {
		return this.identifyType;
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
	public void setProvinceId(Integer value) {
		this.provinceId = value;
	}
	public Integer getProvinceId() {
		return this.provinceId;
	}
	public void setProvinceName(String value) {
		this.provinceName = value;
	}
	public String getProvinceName() {
		return this.provinceName;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setCityName(String value) {
		this.cityName = value;
	}
	public String getCityName() {
		return this.cityName;
	}
	public void setDefaultCard(Integer value) {
		this.defaultCard = value;
	}
	public Integer getDefaultCard() {
		return this.defaultCard;
	}
	public void setIsPayCollecting(Integer value) {
		this.isPayCollecting = value;
	}
	public Integer getIsPayCollecting() {
		return this.isPayCollecting;
	}
	public void setBankId(Integer value) {
		this.bankId = value;
	}
	public Integer getBankId() {
		return this.bankId;
	}
	public void setBankSerialId(Integer value) {
		this.bankSerialId = value;
	}
	public Integer getBankSerialId() {
		return this.bankSerialId;
	}
	public void setPhoneInBank(String value) {
		this.phoneInBank = value;
	}
	public String getPhoneInBank() {
		return this.phoneInBank;
	}
	public void setPayCenterId(Integer value) {
		this.payCenterId = value;
	}
	public Integer getPayCenterId() {
		return this.payCenterId;
	}
	public void setCardBindingNo(String value) {
		this.cardBindingNo = value;
	}
	public String getCardBindingNo() {
		return this.cardBindingNo;
	}

}

