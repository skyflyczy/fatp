package com.fatp.domain.offsite;

import java.math.BigDecimal;

import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.po.offsite.BizimportApplyPo;

public class BizImportApply extends BizimportApplyPo{

	private static final long serialVersionUID = 7352080379442289328L;
	
	private BigDecimal totalMoney;
	
	private int totalNum;
	
	private int totalPeopleNum;

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalPeopleNum() {
		return totalPeopleNum;
	}

	public void setTotalPeopleNum(int totalPeopleNum) {
		this.totalPeopleNum = totalPeopleNum;
	}
	/**
	 * 得到登记状态
	 * @return
	 */
	public String getApplyStatusDesc() {
		if(super.getApplyStatus() == null) {
			return "";
		}
		for(ApplyStatus status : ApplyStatus.values()) {
			if(status.status == super.getApplyStatus().intValue()) {
				return status.name();
			}
		}
		return "";
	}
}
