package com.fatp.enums.project;

public enum InvestProfitType {

	固定收益率(1),
	阶梯收益率(2),
	浮动收益率(3);
	
	public int type;
	
	private InvestProfitType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
}
