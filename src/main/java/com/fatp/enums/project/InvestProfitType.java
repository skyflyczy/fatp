package com.fatp.enums.project;

import com.fatp.util.StringUtil;

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
	
	public static InvestProfitType getInvestProfitTypeByName(String name) {
		if(StringUtil.isBlank(name)) {
			return InvestProfitType.固定收益率;
		}
		for(InvestProfitType type : InvestProfitType.values()) {
			if(type.name().equals(name)) {
				return type;
			}
		}
		return InvestProfitType.固定收益率;
	}
	
}
