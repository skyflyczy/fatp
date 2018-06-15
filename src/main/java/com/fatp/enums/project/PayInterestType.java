package com.fatp.enums.project;
/**
 * 付息方式
 * @author zhiya.chai
 * @date 2018年6月12日 下午5:23:46
 */
public enum PayInterestType {

	一次性到期还本付息(1),
	等额本息(2),
	按月付息到期还本(3),
	按季付息到期还本(4),
	按半年息到期还本(5),
	按年付息到期还本(6);
	
	public int type;
	private PayInterestType(int type){
		this.type = type;
	}
	public int getType() {
		return type;
	}
}
