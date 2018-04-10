package com.telecwin.fatp.enums.project;



public enum SettleTypeDesc {

	场内结算(1),场外结算(2),场内间接结算(3);
	
	public int type;
	
	private SettleTypeDesc(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}
	
	public static String getDesc(int value) {
		SettleTypeDesc[] array = SettleTypeDesc.values();
    	for(int i=0; i<array.length; i++) {
    		if(array[i].type == value) {
    			return array[i].toString();
    		}
    	}
    	return "";
    }
}
