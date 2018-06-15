package com.fatp.enums.project;

public enum ListingStatus {

	删除(0),
	正常(1);
	
	public int status;
	
	private ListingStatus(int status) {
		this.status = status;
	}
}
