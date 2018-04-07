package com.telecwin.fatp.enums.project;


public enum RecordStatusDesc {
	作废(-1),
	待提交(1),
	待审核(2),
	审核通过(3),
	审核退回(4),
	审核不通过(5);
	public int value;

    private RecordStatusDesc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getDesc(Integer value) {
    	RecordStatusDesc[] array = RecordStatusDesc.values();
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
