package com.telecwin.fatp.enums.user;


public enum IdTypeDesc {

	身份证(1),
	港澳通行证(2),
	台湾通行证(3),
	护照(4),
	军官证(5),
	士官证(6),
	临时身份证(7),
	户口簿(8),
	警官证(9),
	外国人永久居留证(10),
	边民出入境通行证(11),
	组织机构代码(12),
	其他(-1);
	
	private int idType;
	
	private IdTypeDesc(int idType) {
		this.idType = idType;
	}
	public int getIdType() {
		return idType;
	}
	
	public static IdTypeDesc[] getPersonalTypes(){
		return new IdTypeDesc[]{身份证,港澳通行证,台湾通行证,护照,军官证,士官证,临时身份证,户口簿,警官证,外国人永久居留证,边民出入境通行证,其他};
	}
	
	public static IdTypeDesc[] getOrgTypes() {
		return new IdTypeDesc[]{组织机构代码,其他};
	}
}
