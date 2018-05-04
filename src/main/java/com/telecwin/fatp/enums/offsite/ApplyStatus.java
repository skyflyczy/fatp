package com.telecwin.fatp.enums.offsite;

public enum ApplyStatus {
	
	待提交((short)1),
	登记中((short)2),
	登记成功((short)3),
	登记失败((short)4),
	已核保((short)5);
	
	public short status;
	
	private ApplyStatus(short status) {
		this.status = status;
	}
}
