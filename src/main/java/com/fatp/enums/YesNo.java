package com.fatp.enums;

public enum YesNo {

	是(1),
	否(0);

	public int value;

	private YesNo(int value){
		this.value = value;
	}
}
