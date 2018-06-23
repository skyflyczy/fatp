package com.fatp.enums.project;
/**
 * 计息基准
 * 
 * @author zhiya.chai
 * @date 2018年6月12日 下午5:40:50
 */
public enum InterestBase {

	ACT_365(1),
	ACT_360(2),
	ACT_ACT(3);
	
	public int value;
	
	private InterestBase(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	
	public static InterestBase getInterestBaseByValue(int value){
		for(InterestBase base : InterestBase.values()) {
			if(base.value == value) {
				return base;
			}
		}
		return null;
	}
	
}
