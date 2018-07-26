package com.fatp.enums.user;




public enum IdTypeDesc {

	身份证(1),
	港澳通行证(2),
	台湾通行证(3),
	护照(4),
	军官证(5),
	统一社会信用代码(6),
	其他(-1);
	
	private int idType;
	
	private IdTypeDesc(int idType) {
		this.idType = idType;
	}
	public int getIdType() {
		return idType;
	}
	
	public static IdTypeDesc fromIdTypeName(String name) {
		for (IdTypeDesc item : values()) {
			if (item.name().equalsIgnoreCase(name)) {
				return item;
			}
		}
		if(name !=null && name.contains("营业执照")) {
			return IdTypeDesc.统一社会信用代码;
		}
		return null;
	}
	public static IdTypeDesc fromIdIdType(int type) {
		for (IdTypeDesc item : values()) {
			if (item.idType == type) {
				return item;
			}
		}
		return null;
	}
	
	public static IdTypeDesc[] getPersonalTypes(){
		return new IdTypeDesc[]{身份证,港澳通行证,台湾通行证,护照,军官证,其他};
	}
	
	public static IdTypeDesc[] getOrgTypes() {
		return new IdTypeDesc[]{统一社会信用代码,其他};
	}
}
