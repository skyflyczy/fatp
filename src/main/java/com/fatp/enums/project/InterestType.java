package com.fatp.enums.project;
/**
 * 计息方式
 * 
 * @author zhiya.chai
 * @date 2018年6月12日 下午5:33:41
 */
public enum InterestType {

	单利(1),
	复利(2);
	
	public int type;
	
	private InterestType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}
