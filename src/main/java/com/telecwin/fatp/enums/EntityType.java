package com.telecwin.fatp.enums;


public enum EntityType {
	备案(1),
	挂牌(2),
	发行人(3),
	投资者(4),
	场外回传申请(5);

	public int value;

    private EntityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getEntityType(Integer value) {
		EntityType[] array = EntityType.values();
    	if(value == null) {
    		return "";
    	}
    	for(int i=0; i<array.length; i++) {
    		if(array[i].value == value) {
    			return array[i].toString();
    		}
    	}
    	return "";
    }
}
