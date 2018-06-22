package com.fatp.enums.user;

/**
 * 用户注册来源枚举
 * 
 */
public enum UserRegisterSource {
	
	平台注册(1), 微信注册(2), 交易明细导入(3);
	
	private int value;
	
	private UserRegisterSource(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
