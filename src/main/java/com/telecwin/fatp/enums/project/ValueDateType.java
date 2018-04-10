package com.telecwin.fatp.enums.project;

/**
 * 起息日确定方式
 */
public enum ValueDateType {

	认购结束日(1),
	放款日(2),
	项目录入(3),
	起息日待定(4);
	
	public int type;
	
	private ValueDateType(int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
}
