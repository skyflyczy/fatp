package com.fatp.enums.biz;

public enum PayinvestStatus {
	
	未兑付(1),
	兑付完成(2);
	
	public int status;
	
	private PayinvestStatus(int status){
		this.status = status;
	}
}
