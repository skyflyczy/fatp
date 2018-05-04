package com.telecwin.fatp.enums.offsite;

public enum ApplyType {
	
	投资登记申请((short)1);
	
	public short type;
	
	private ApplyType(short type) {
		this.type = type;
	}

}
