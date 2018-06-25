package com.fatp.enums.biz;

public enum RepayStatus {
	未还款(1),
	还款完成(2);
	
	public int status;
	
	private RepayStatus(int status){
		this.status = status;
	}
}
