package com.fatp.enums.user;

public enum RoleType {
	管理角色(1),业务角色(2);
	
	public int type;
	
	private RoleType(int type) {
		this.type = type;
	}

}
