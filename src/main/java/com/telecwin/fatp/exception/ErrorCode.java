package com.telecwin.fatp.exception;

public enum ErrorCode {

	SUCCESS(0, "成功"),
	
	MENU_NOT_EXIST(10000,"菜单不存在或已被删除。"),
	MENU_ALREADY_EXIST(10001,"菜单已存在。"),
	
	
	LOGIN_EXPIRED(20000,"登录超时。"),
	
	MEMBER_OPERATOR_NOT_EXIST(30000,"操作员不存在。"),
	UPDATE_PWD_NOT_MACTH(30001,"当前密码输入错误。"),
	MEMBER_PHONE_ALREADY_EXIST(30002,"操作员手机号已存在。"),
	MEMBER_EMAIL_ALREADY_EXIST(30003,"操作员邮箱已存在。"),
	MEMBER_IDNUMBER_ALREADY_EXIST(30004,"操作员身份证已存在。"),
	MEMBER_LONGINNAME_ALREADY_EXIST(30005,"操作员登录名已存在。"),
	ROLE_NAME_ALREADY_EXIST(30006,"角色名称已存在。"),
	ROLE_NOT_EXIST(30007,"角色不存在。"),
	
	
	COMPANY_ORGNAME_NOT_MACTH(40000,"营业执照号码与机构名称不符。"),
	MEMBER_REALNAME_ALREADY_EXIST(40001,"发行人全称已存在。"),
	MEMBER_NAME_ALREADY_EXIST(40002,"发行人简称已存在。"),
	MEMBER_AGENT_NOT_EXIST(40003,"经办人信息不存在。"),
	MEMBER_BANKCARD_NOT_EXIST(40004,"此银行卡不存在。"),
	MEMBER_BANK_BRANCH_CHOICE_ERROR(40005,"请正确选择分支行。"),
	MEMBER_BANKCARD_ALREADY_EXIST(40006,"银行卡已存在。"),
	MEMBER_DEFAULTCARD_NOT_DEL(40007,"默认银行卡不能删除。"),
	MEMBER_BANKCARD_STATUS_ERROR(40008,"银行卡状态错误。"),
	MEMBER_NOT_EXIST(40009,"发行人不存在。"),
	MEMBER_MSG_NOT_COMPLETE(40010,"发行人信息不完整。"),
	MEMBER_SUPERADMIN_NOT_EXIST(40011,"管理员不存在。"),
	MEMBER_REGISTERAGENT_NOT_EXIST(40012,"经办人不存在。"),
	MEMBER_REGISTERAGENT_NOT_COMPLETE(40013,"经办人信息不完整。"),
	COMPANYORGCODE_ALREADY_EXIST(40014,"营业执照号码已存在。"),
	COMPANYNAME_ALREADY_EXIST(40015,"机构名称已存在。"),
	
	
	RECORD_NAME_NOT_MATCH(50000,"备案简称不支持特殊字符。"),
	RECORD_FULLNAME_NOT_MATCH(500001,"备案全称不支持特殊字符。"),
	RECORDINFO_NOT_EXIST(500002,"备案信息不存在。"),
	RECORDINFO_STATUS_ERROR(500002,"备案信息状态错误。"),
	
	
	
	
	SYSTEM_ERROR(90000,"系统处理异常。"),
	SYSTEM_PARAMETERS_EMPTY(90001,"参数为空。"),
	
	
	OTHER_ERROR(99999,"其他错误。");
	
	
	private int code;
	private String message;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
