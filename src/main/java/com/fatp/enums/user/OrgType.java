package com.fatp.enums.user;

public enum OrgType {

	自然人(1),
	法人机构(2),
	其他组织(3);
	
	public int type;
	
	private OrgType(int type){
		this.type = type;
	}
	
	public static Integer getAccountType(int type){
		if(type == 自然人.type){
			return 11;
		}else{
			return 12;
		}
	}

	public int getType() {
		return type;
	}
}
