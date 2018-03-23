package com.telecwin.fatp.enums;

public enum DelStatus {
	正常(1), 删除(0);
	
    public int value;

    private DelStatus(Integer value) {
        this.value = value;
    }

}
