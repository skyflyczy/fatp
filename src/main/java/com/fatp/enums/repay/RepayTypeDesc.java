package com.fatp.enums.repay;

public enum RepayTypeDesc {
	一次性还本付息(1),
	等额本息按月还款(2),
	等额本金按月还款(3),
	按月付息到期还本(4),
	按季付息到期还本(5),
	按年付息到期还本(6);
	
	public int value;
	private RepayTypeDesc(int status){
		this.value = status;
	}
	
	public static RepayTypeDesc getRepayTypeDesc(Integer value){
		if(value == null) {
			return 一次性还本付息;
		}
		for(RepayTypeDesc desc : RepayTypeDesc.values()){
			if(desc.value == value){
				return desc;
			}
		}
		return 一次性还本付息;
	}

	public static String getDesc(Integer value) {
		if(value == null) {
			return "";
		}
		RepayTypeDesc[] array = RepayTypeDesc.values();
		for(int i=0; i<array.length; i++) {
			if(array[i].value == value) {
				return array[i].toString();
			}
		}
		return "";
	}

	public int getValue() {
		return value;
	}
}