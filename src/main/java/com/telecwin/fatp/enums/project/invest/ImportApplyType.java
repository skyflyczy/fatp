package com.telecwin.fatp.enums.project.invest;
/**
 * 申请明细登记类型
 */
public enum ImportApplyType {

	投资登记申请(1);
	
	public int type;
	
	private ImportApplyType(int type) {
		this.type = type;
	}
}
