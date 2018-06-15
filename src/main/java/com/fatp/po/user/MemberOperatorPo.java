package com.fatp.po.user;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * MemberOperator
 */
public class MemberOperatorPo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 操作员唯一id，用于web表现
	 */
	private String operatorGuid;
	/**
	 * 操作员编号
	 */
	private String operatorCode;
	/**
	 * 归属会员id(uc_usertypes.IsExchangeMember=1),对应uc_user.id字段，平台id=0
	 */
	private Integer memberId;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 操作员登录名，加密
	 */
	private String loginName;
	/**
	 * 手机号，加密
	 */
	private String phone;
	/**
	 * 加密的密码，加密
	 */
	private String secrectCode;
	/**
	 * 密码字符串，加密
	 */
	private String secrectSalt;
	/**
	 * 手机号是否经过校验
	 */
	private Integer isValidPhone;
	/**
	 * 证件类型
	 */
	private Integer idType;
	/**
	 * 真实名称
	 */
	private String realName;
	/**
	 * 证件号码，加密
	 */
	private String idNumber;
	/**
	 * 证件是否经过校验/V实名
	 */
	private Integer isValidId;
	/**
	 * 操作员邮箱地址，加密
	 */
	private String email;
	/**
	 * 邮箱地址是否经过校验
	 */
	private Integer isValidEmail;
	/**
	 * 操作员类型 业务人员=1,系统管理员=2,（超级管理员=3，取消）
	 */
	private Integer operatorType;
	/**
	 * 用户状态 正常=1,冻结=0 待激活=2 (new)
	 */
	private Integer operatorStatus;
	/**
	 * 删除状态,正常=1,删除=0
	 */
	private Integer delStatus;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 头像URL
	 */
	private String faceImgUrl;
	/**
	 * 备注描述
	 */
	private String remark;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * 职务/职业
	 */
	private String duty;
	/**
	 * =1时，重要操作员，代表不可删除,或者作为第一联系人 
	 */
	private Integer vipOperator;
	/**
	 * 是否是经办人  add 2016/09/23
	 */
	private boolean isRegisterAgent;
	/**
	 * 性别 （1-男 2-女 ）add 2016/10/12
	 */
	private Integer gender;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setOperatorGuid(String value) {
		this.operatorGuid = value;
	}
	public String getOperatorGuid() {
		return this.operatorGuid;
	}
	public void setOperatorCode(String value) {
		this.operatorCode = value;
	}
	public String getOperatorCode() {
		return this.operatorCode;
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
	public void setLoginName(String value) {
		this.loginName = value;
	}
	public String getLoginName() {
		return this.loginName;
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	public String getPhone() {
		return this.phone;
	}
	public void setSecrectCode(String value) {
		this.secrectCode = value;
	}
	public String getSecrectCode() {
		return this.secrectCode;
	}
	public void setSecrectSalt(String value) {
		this.secrectSalt = value;
	}
	public String getSecrectSalt() {
		return this.secrectSalt;
	}
	public void setIsValidPhone(Integer value) {
		this.isValidPhone = value;
	}
	public Integer getIsValidPhone() {
		return this.isValidPhone;
	}
	public void setIdType(Integer value) {
		this.idType = value;
	}
	public Integer getIdType() {
		return this.idType;
	}
	public void setRealName(String value) {
		this.realName = value;
	}
	public String getRealName() {
		return this.realName;
	}
	public void setIdNumber(String value) {
		this.idNumber = value;
	}
	public String getIdNumber() {
		return this.idNumber;
	}
	public void setIsValidId(Integer value) {
		this.isValidId = value;
	}
	public Integer getIsValidId() {
		return this.isValidId;
	}
	public void setEmail(String value) {
		this.email = value;
	}
	public String getEmail() {
		return this.email;
	}
	public void setIsValidEmail(Integer value) {
		this.isValidEmail = value;
	}
	public Integer getIsValidEmail() {
		return this.isValidEmail;
	}
	public void setOperatorType(Integer value) {
		this.operatorType = value;
	}
	public Integer getOperatorType() {
		return this.operatorType;
	}
	public void setOperatorStatus(Integer value) {
		this.operatorStatus = value;
	}
	public Integer getOperatorStatus() {
		return this.operatorStatus;
	}
	public void setDelStatus(Integer value) {
		this.delStatus = value;
	}
	public Integer getDelStatus() {
		return this.delStatus;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setFaceImgUrl(String value) {
		this.faceImgUrl = value;
	}
	public String getFaceImgUrl() {
		return this.faceImgUrl;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setDepartment(String value) {
		this.department = value;
	}
	public String getDepartment() {
		return this.department;
	}
	public void setDuty(String value) {
		this.duty = value;
	}
	public String getDuty() {
		return this.duty;
	}
	public void setVipOperator(Integer value) {
		this.vipOperator = value;
	}
	public Integer getVipOperator() {
		return this.vipOperator;
	}
	public boolean getIsRegisterAgent() {
		return isRegisterAgent;
	}
	public void setIsRegisterAgent(boolean isRegisterAgent) {
		this.isRegisterAgent = isRegisterAgent;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

}

