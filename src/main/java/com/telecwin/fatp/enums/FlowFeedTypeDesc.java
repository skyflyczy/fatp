package com.telecwin.fatp.enums;


public enum FlowFeedTypeDesc {
	创建(1,"创建项目"),
	编辑保存(2,"修改保存项目"),
	提交审核(3,"提交审核"),
	审核通过(4,"审核通过"),
	审核退回(5,"审核退回"),
	审核不通过(6,"审核退回"),
	发布(7,"发布此项目"),
	认购中(8,"项目开始认购"),
	认购结束(9,"认购结束，总募集资金${money}"),
	核保通过(10,"核保通过，确权资金${money}，确权投资者${usernumbers}位"),
	核保不通过(11,"核保不通过"),
	放款成功(12,"放款完成,放款金额${money}"),
	还款完成(13,"还款完成"),
	本息兑付(14,"本次兑付本息总额为${money},兑付${usernumbers}位投资者"),
	项目撤销(15,"项目作废"),
	取消发行(16,"取消发行"),
	备案作废(17,"备案作废"),
	
	创建发行人(17,"创建"),
	编辑保存发行人信息(18,"编辑保存信息"),
	发行人提交审核(19,"提交审核"),
	发行人审核通过(20,"审核通过"),
	发行人审核退回(21,"审核退回"),
	发行人审核不通过(22,"审核不通过");
	
	private int type;
	private String text;
	
	private FlowFeedTypeDesc(int value,String text){
		this.type = value;
		this.text = text;
	}
	public static String getTextByType(int type){
		for(FlowFeedTypeDesc feedEnum : FlowFeedTypeDesc.values()){
			if(feedEnum.type == type){
				return feedEnum.getText();
			}
			
		}
		return "";
	}
	public int getType() {
		return type;
	}

	public String getText() {
		return text;
	}
}
