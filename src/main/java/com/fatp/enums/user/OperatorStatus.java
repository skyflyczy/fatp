package com.fatp.enums.user;

public enum OperatorStatus {
	正常(1), 冻结(0),待激活(2);
	
    public int value;

    private OperatorStatus(Integer value) {
        this.value = value;
    }

}
