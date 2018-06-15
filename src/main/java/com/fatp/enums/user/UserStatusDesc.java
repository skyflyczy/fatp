package com.fatp.enums.user;


public enum UserStatusDesc {
	
	UNKNOWN(-1),
	未提交(1),
	待审核(2),
	审核退回(3),
	审核不通过(4),
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
    
    public static UserStatusDesc[] getAllNormalUserStatus(){
    	return new UserStatusDesc[]{未提交,待审核,审核退回,审核不通过,正常,冻结,待激活};
    }
}
