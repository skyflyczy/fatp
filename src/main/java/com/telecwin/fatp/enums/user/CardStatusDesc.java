package com.telecwin.fatp.enums.user;

public enum CardStatusDesc {
	正常(1),
	冻结(2),
	删除(0);
	
	public int value;

    private CardStatusDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
