package com.fatp.domain;

import org.apache.commons.lang3.StringUtils;

import com.fatp.po.user.UcUserBankcardPo;

/**
 * UcUserBankcard
 */
public class UcUserBankcard extends UcUserBankcardPo {
	
	private static final long serialVersionUID = 2120378613296846924L;
	private String channelName;
	private String cardAccountShow;
	// 银行在第三方的代码
	private Integer thirdBankId;
	// 组织类型
	private Integer orgTypeId;
	private String subBankNo;

	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCardAccountShow() {
		if(StringUtils.isBlank(cardAccountShow)) {
			try {
				this.cardAccountShow = super.getCardAccount().substring(super.getCardAccount().length()-4, super.getCardAccount().length());
			}catch(Exception e) {
				this.cardAccountShow = super.getCardAccount();
			}
		}
		return this.cardAccountShow;
	}
	public Integer getThirdBankId() {
		return thirdBankId;
	}
	public Integer getOrgTypeId() {
		return orgTypeId;
	}
	public void setThirdBankId(Integer thirdBankId) {
		this.thirdBankId = thirdBankId;
	}
	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}
	public String getSubBankNo() {
		return subBankNo;
	}
	public void setSubBankNo(String subBankNo) {
		this.subBankNo = subBankNo;
	}
	
}

