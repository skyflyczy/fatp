package com.fatp.enums;

public enum SystypeCategoryDesc {
	项目期限类型(1),
	承销类型(2),
	个人证件类型(3),
	协议类型(4),
	短信类型(5),
	附件类型(6),
	文件类型(7),
	平台费率收费方式(8),
	业务通知类型(9),
	计息方式(10)
	;
	
	public int category;
	
	private SystypeCategoryDesc(int category){
		this.category = category;
	}
}
