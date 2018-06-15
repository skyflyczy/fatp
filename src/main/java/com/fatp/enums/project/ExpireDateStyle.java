package com.fatp.enums.project;

/**
 * 到期日规则
 */
public enum ExpireDateStyle {

	固定期限(1),
	固定到期日(2);
	
	public int style;
	
	private ExpireDateStyle(int style){
		this.style = style;
	}

	public int getStyle() {
		return style;
	}
}
