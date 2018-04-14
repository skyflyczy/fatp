package com.telecwin.fatp.enums.project.invest;
/**
 * 申请明细登记状态
 */
public enum ImportApplyStatus {
	
	待提交(1), 登记中(2), 登记成功(3), 登记失败(4),已核保(5);
	
	public int status;
	
	private ImportApplyStatus(int status) {
		this.status = status;
	}
	
}
