package com.fatp.enums.user;

public enum OperatorType {

	业务人员(1),
	系统管理员(2),
	超级管理员(3);
	
	public int value;
	
	private OperatorType(int value){
		this.value = value;
	}
}
