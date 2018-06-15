package com.fatp.enums.offsite;

public enum ApplyStatus {
	
	待提交(1),
	登记中(2),
	登记成功(3),
	登记失败(4);
	
	public int status;
	
	private ApplyStatus(int status) {
		this.status = status;
	}
}
