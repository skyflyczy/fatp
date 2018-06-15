package com.fatp.po.user;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberOperateorLoginPo {
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 登录名类型:身份证=1,用户名=2,手机号=3,邮箱=4
	 */
	private Integer loginType;
	/**
	 * 对应member_operator.id,可以为null
	 */
	private Integer operatorId;
	/**
	 * 所属会员id,uc_user.id
	 */
	private Integer memberId;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 登录的系统id
	 */
	private Integer appId;
	/**
	 * 累计失败次数
	 */
	private Integer failNum;
	/**
	 * 累计成功次数
	 */
	private Integer successNum;
	/**
	 * 连续失败次数
	 */
	private Integer failNumLx;
	/**
	 * 连续成功次数
	 */
	private Integer successNumLx;
	/**
	 * 上一次登录时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date preLoginTime;
	/**
	 * 最后一次登录时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date lastLoginTime;
	/**
	 * 最近一次成功登录时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date lastSuccessTime;
	/**
	 * 最近一次登录失败时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date lastFailTime;
	/**
	 * 最后一次登录返回码
	 */
	private Integer retCode;
	/**
	 * 最后一次登录返回信息，失败才记录
	 */
	private String retMsg;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	public String getLoginName() {
		return this.loginName;
	}
	public void setLoginType(Integer value) {
		this.loginType = value;
	}
	public Integer getLoginType() {
		return this.loginType;
	}
	public void setOperatorId(Integer value) {
		this.operatorId = value;
	}
	public Integer getOperatorId() {
		return this.operatorId;
	}
	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setExchangeId(Integer value) {
		this.exchangeId = value;
	}
	public Integer getExchangeId() {
		return this.exchangeId;
	}
	public void setAppId(Integer value) {
		this.appId = value;
	}
	public Integer getAppId() {
		return this.appId;
	}
	public void setFailNum(Integer value) {
		this.failNum = value;
	}
	public Integer getFailNum() {
		return this.failNum;
	}
	public void setSuccessNum(Integer value) {
		this.successNum = value;
	}
	public Integer getSuccessNum() {
		return this.successNum;
	}
	public void setFailNumLx(Integer value) {
		this.failNumLx = value;
	}
	public Integer getFailNumLx() {
		return this.failNumLx;
	}
	public void setSuccessNumLx(Integer value) {
		this.successNumLx = value;
	}
	public Integer getSuccessNumLx() {
		return this.successNumLx;
	}
	public void setPreLoginTime(java.util.Date value) {
		this.preLoginTime = value;
	}
	public java.util.Date getPreLoginTime() {
		return this.preLoginTime;
	}
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	public void setLastSuccessTime(java.util.Date value) {
		this.lastSuccessTime = value;
	}
	public java.util.Date getLastSuccessTime() {
		return this.lastSuccessTime;
	}
	public void setLastFailTime(java.util.Date value) {
		this.lastFailTime = value;
	}
	public java.util.Date getLastFailTime() {
		return this.lastFailTime;
	}
	public void setRetCode(Integer value) {
		this.retCode = value;
	}
	public Integer getRetCode() {
		return this.retCode;
	}
	public void setRetMsg(String value) {
		this.retMsg = value;
	}
	public String getRetMsg() {
		return this.retMsg;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

}

