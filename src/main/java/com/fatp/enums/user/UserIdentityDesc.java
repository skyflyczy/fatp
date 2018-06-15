package com.fatp.enums.user;

public enum UserIdentityDesc {
	发行人(1),
	投资者(2);
	
	public int value;

    private UserIdentityDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
