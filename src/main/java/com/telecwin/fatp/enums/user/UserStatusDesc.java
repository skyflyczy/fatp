package com.telecwin.fatp.enums.user;


public enum UserStatusDesc {
	
	UNKNOWN(-1),
	待处理(0),
	未提交(1),
	待审核(2),
	审核退回(3),
	不通过(4),
	正常(5),
	冻结(6),
	待激活(7),
	删除(8);

	public int value;

    private UserStatusDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserStatusDesc valueOf(int intValue) {
    	for (UserStatusDesc status : values()) {
    		if (intValue == status.value) {
    			return status;
    		}
    	}
    	return UNKNOWN;
    }
}
