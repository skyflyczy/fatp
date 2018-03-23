package com.telecwin.fatp.enums.user;

public enum LoginType {
	
	身份证(1),用户名(2),手机号(3),邮箱(4);
	
	public int type;
	
	private LoginType(int type) {
		this.type = type;
	}

}
