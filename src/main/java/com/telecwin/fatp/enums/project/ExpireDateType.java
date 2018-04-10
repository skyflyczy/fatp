package com.telecwin.fatp.enums.project;

/**
 * 到期日方式
 */
public enum ExpireDateType {

	固定期限(1),
	固定到期日(2);
	
	public int type;
	
	private ExpireDateType(int type){
		this.type = type;
	}
}
