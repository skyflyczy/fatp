package com.fatp.domain.biz;

import com.fatp.enums.biz.RepayStatus;
import com.fatp.po.biz.BizplanRepayPo;

public class BizplanRepay extends BizplanRepayPo{

	public String getRepayStatusDesc(){
		for(RepayStatus status : RepayStatus.values()) {
			if(status.status == super.getRepayStatus()) {
				return status.name();
			}
		}
		return "";
	}
	
	public boolean canRepayCompleted() {
		return super.getRepayStatus() != RepayStatus.还款完成.status;
	}
}
