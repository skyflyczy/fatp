package com.telecwin.fatp.enums.project;


public enum ProductTypeDesc {
	收益权转让计划(1),
	定向融资计划(2),
	定向投资工具(3);
	
	public int value;

    private ProductTypeDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static String getDesc(int value) {
    	ProductTypeDesc[] array = ProductTypeDesc.values();
    	for(int i=0; i<array.length; i++) {
    		if(array[i].value == value) {
    			return array[i].toString();
    		}
    	}
    	return "";
    }
}
