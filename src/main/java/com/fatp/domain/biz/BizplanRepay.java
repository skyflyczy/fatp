package com.fatp.domain.biz;

import com.fatp.enums.biz.RepayStatus;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.enums.project.PayInterestType;
import com.fatp.po.biz.BizplanRepayPo;

public class BizplanRepay extends BizplanRepayPo{
	
	/**
	 * 发行人
	 */
	private String issuer;
	/**
	 * 产品编号
	 */
	private String listingCode;
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
	 * 付息方式：
	 */
	private Integer payInterestType;
	private String listingGuid;
	
	public boolean canRepayCompleted() {
		return super.getRepayStatus() != RepayStatus.还款完成.status;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getListingCode() {
		return listingCode;
	}

	public void setListingCode(String listingCode) {
		this.listingCode = listingCode;
	}

	public String getListingFullName() {
		return listingFullName;
	}

	public void setListingFullName(String listingFullName) {
		this.listingFullName = listingFullName;
	}
	public java.math.BigDecimal getListingMoney() {
		return listingMoney;
	}

	public void setListingMoney(java.math.BigDecimal listingMoney) {
		this.listingMoney = listingMoney;
	}

	public Integer getListingLimit() {
		return listingLimit;
	}

	public void setListingLimit(Integer listingLimit) {
		this.listingLimit = listingLimit;
	}

	public Integer getListingLimitType() {
		return listingLimitType;
	}

	public void setListingLimitType(Integer listingLimitType) {
		this.listingLimitType = listingLimitType;
	}

	public Integer getPayInterestType() {
		return payInterestType;
	}

	public void setPayInterestType(Integer payInterestType) {
		this.payInterestType = payInterestType;
	}
	
	public String getListingGuid() {
		return listingGuid;
	}
	
	public void setListingGuid(String listingGuid) {
		this.listingGuid = listingGuid;
	}
	public String getRepayStatusDesc(){
		for(RepayStatus status : RepayStatus.values()) {
			if(status.status == super.getRepayStatus()) {
				return status.name();
			}
		}
		return "";
	}
	
	/**
	 * 获取投资期限类型
	 * @return
	 */
	public String getListingLimitTypeDesc() {
		if(this.listingLimitType == null) {
			return "";
		}
		for(ListingLimitType desc : ListingLimitType.values()){
			if(desc.type == this.listingLimitType.intValue()){
				return desc.toString();
			}
		}
		return "";
	}
	/**
	 * 付息方式
	 * @return
	 */
	public String getPayInterestTypeDesc(){
		if(this.payInterestType == null) {
			return "";
		}
		for(PayInterestType type : PayInterestType.values()) {
			if(type.type == this.payInterestType.intValue()) {
				return type.name();
			}
		}
		return "";
	}
}
