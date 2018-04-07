package com.telecwin.fatp.enums.project;

public enum ProjectSaleType {

	全额包销(1),余额包销(2),代销(3);
	private int type;
	
	private ProjectSaleType (int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}
