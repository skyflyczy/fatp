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
	
	
	COMPANY_ORGNAME_NOT_MACTH(40000,"营业执照号码与机构名称不符"),
	MEMBER_REALNAME_ALREADY_EXIST(40001,"发行人全称已存在"),
	MEMBER_NAME_ALREADY_EXIST(40001,"发行人简称已存在"),
	
	RECORD_NAME_NOT_MATCH(50000,"备案简称不支持特殊字符。"),
	RECORD_FULLNAME_NOT_MATCH(500001,"备案全称不支持特殊字符。"),
	
	
	
	
	
	
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
