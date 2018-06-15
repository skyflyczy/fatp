package com.fatp.enums.project;
/**
 * 计息频率
 * 
 * @author zhiya.chai
 * @date 2018年6月12日 下午5:38:35
 */
public enum InterestRate {

	按日计息(1),
	按月计息(2),
	按年计息(3),
	按季计息(4),
	按半年计息(5);
	
	public int value;
	
	private InterestRate(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
}
