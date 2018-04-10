package com.telecwin.fatp.enums.project;

/**
 * 收款方类型
 */
public enum ReceiveUserType {

	发行人(1),产品管理人(2);
	
	public int type;
	
	private ReceiveUserType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
