package com.fatp.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvestApplyVo implements Serializable{

	private static final long serialVersionUID = 9106520338426849539L;
	
	private String listingGuid;
	private String listingCode;
	private String listingFullName;//挂牌产品全称
	private String issuer;//发行人
	private BigDecimal listingMoney;//挂牌金额
	private BigDecimal applyMoney;//申请金额
	private BigDecimal applyNum;//申请数量

	public String getListingGuid() {
		return listingGuid;
	}

	public void setListingGuid(String listingGuid) {
		this.listingGuid = listingGuid;
	}

	public String getListingFullName() {
		return listingFullName;
	}

	public void setListingFullName(String listingFullName) {
		this.listingFullName = listingFullName;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public BigDecimal getListingMoney() {
		return listingMoney;
	}

	public void setListingMoney(BigDecimal listingMoney) {
		this.listingMoney = listingMoney;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getListingCode() {
		return listingCode;
	}

	public void setListingCode(String listingCode) {
		this.listingCode = listingCode;
	}

	public BigDecimal getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(BigDecimal applyNum) {
		this.applyNum = applyNum;
	}
	
}
