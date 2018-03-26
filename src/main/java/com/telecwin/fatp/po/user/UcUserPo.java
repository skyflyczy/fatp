package com.telecwin.fatp.po.user;

import org.springframework.format.annotation.DateTimeFormat;

import com.telecwin.fatp.po.BasePo;

/**
 * UcUser
 */
public class UcUserPo extends BasePo{
	
	private static final long serialVersionUID = 2345696364534017622L;
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 用户唯一编码
	 */
	private String userGuid;
	/**
	 * 唯一代码/参与人编号（机构用户用于登录）
	 */
	private String userCode;
	/**
	 * 归属会员id(uc_usertypes.IsExchangeMember=1),对应本表的id字段，平台id=0
	 */
	private Integer ownerUserId;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 个人用户名/会员简称/客户简称,加密
	 */
	private String userName;
	/**
	 * 个人手机号，加密
	 */
	private String phone;
	/**
	 * 登录密码，登录时验证，加密存储
	 */
	private String secrectCode;
	/**
	 * 登录密码盐字符串，加密存储
	 */
	private String secrectSalt;
	/**
	 * 个人手机号是否经过校验
	 */
	private Integer isValidPhone;
	/**
	 * 证件类型,对应表systype_id.id
	 */
	private Integer idTypeId;
	/**
	 * 个人真实名称/会员全称/客户全称
	 */
	private String realName;
	/**
	 * 个人证件号码，加密
	 */
	private String idNumber;
	/**
	 * 证件是否经过校验
	 */
	private Integer isValidId;
	/**
	 * 个人邮箱地址，加密
	 */
	private String email;
	/**
	 * 邮箱地址是否经过校验
	 */
	private Integer isValidEmail;
	/**
	 * 组织类型Id,systype_userorg.id
	 */
	private Integer orgTypeId;
	/**
	 * 用户状态,
	 */
	private Integer userStatus;
	/**
	 * 创建时间/注册时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 创建人,member_operator.id
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 更新人,member_operator.id
	 */
	private Integer updateOperatorId;
	/**
	 * 微信id
	 */
	private String weiXinId;
	/**
	 * 头像URL
	 */
	private String faceImgUrl;
	/**
	 * 用户身份 会员=1 会员客户=2 （增加）
	 */
	private Integer userIdentity;
	/**
	 * 参与人子类型 会员-主体=1 会员-子机构=2 客户-会员子机构=3 客户-非会员子机构=4 （增加）
	 */
	private Integer userChildType;
	/**
	 * 机构信息id uc_user_pubinfo.id
	 */
	private Integer companyInfoId;
	/**
	 * 审核人
	 */
	private Integer auditOperatorId;
	/**
	 * 审核意见
	 */
	private String auditRemark;
	/**
	 * 审核时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date auditTime;
	/**
	 * 所属法人机构id uc_user_pubinfo.id
	 */
	private Integer companyParentId;
	/**
	 * 交易密码，用于做交易时验证
	 */
	private String tradeSecrectCode;
	/**
	 * 交易密码验盐字符串，加密
	 */
	private String tradeSecrectSalt;
	/**
	 * 是否设定交易名 1=已设定 0=未设定
	 */
	private Integer isSetTradePwd;
	/**
	 * 是否绑定银行卡 1=绑定 0=未绑定
	 */
	private Integer isBindBankCard;
	/**
	 * 注册来源：1-平台注册 2-微信注册 3-渠道导入
	 */
	private Integer registerSource;
	
	private boolean isSendSms;//是否发送短信 ,默认 1：发送 0：不发送

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setUserGuid(String value) {
		this.userGuid = value;
	}
	public String getUserGuid() {
		return this.userGuid;
	}
	public void setUserCode(String value) {
		this.userCode = value;
	}
	public String getUserCode() {
		return this.userCode;
	}
	public void setOwnerUserId(Integer value) {
		this.ownerUserId = value;
	}
	public Integer getOwnerUserId() {
		return this.ownerUserId;
	}
	public void setExchangeId(Integer value) {
		this.exchangeId = value;
	}
	public Integer getExchangeId() {
		return this.exchangeId;
	}
	public void setUserName(String value) {
		this.userName = value;
	}
	public String getUserName() {
		return this.userName;
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
	public void setIdTypeId(Integer value) {
		this.idTypeId = value;
	}
	public Integer getIdTypeId() {
		return this.idTypeId;
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
	public void setOrgTypeId(Integer value) {
		this.orgTypeId = value;
	}
	public Integer getOrgTypeId() {
		return this.orgTypeId;
	}
	public void setUserStatus(Integer value) {
		this.userStatus = value;
	}
	public Integer getUserStatus() {
		return this.userStatus;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateOperatorId(Integer value) {
		this.createOperatorId = value;
	}
	public Integer getCreateOperatorId() {
		return this.createOperatorId;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateOperatorId(Integer value) {
		this.updateOperatorId = value;
	}
	public Integer getUpdateOperatorId() {
		return this.updateOperatorId;
	}
	public void setWeiXinId(String value) {
		this.weiXinId = value;
	}
	public String getWeiXinId() {
		return this.weiXinId;
	}
	public void setFaceImgUrl(String value) {
		this.faceImgUrl = value;
	}
	public String getFaceImgUrl() {
		return this.faceImgUrl;
	}
	public void setUserIdentity(Integer value) {
		this.userIdentity = value;
	}
	public Integer getUserIdentity() {
		return this.userIdentity;
	}
	public void setUserChildType(Integer value) {
		this.userChildType = value;
	}
	public Integer getUserChildType() {
		return this.userChildType;
	}
	public void setCompanyInfoId(Integer value) {
		this.companyInfoId = value;
	}
	public Integer getCompanyInfoId() {
		return this.companyInfoId;
	}
	public void setAuditOperatorId(Integer value) {
		this.auditOperatorId = value;
	}
	public Integer getAuditOperatorId() {
		return this.auditOperatorId;
	}
	public void setAuditRemark(String value) {
		this.auditRemark = value;
	}
	public String getAuditRemark() {
		return this.auditRemark;
	}
	public void setAuditTime(java.util.Date value) {
		this.auditTime = value;
	}
	public java.util.Date getAuditTime() {
		return this.auditTime;
	}
	public void setCompanyParentId(Integer value) {
		this.companyParentId = value;
	}
	public Integer getCompanyParentId() {
		return this.companyParentId;
	}
	public void setTradeSecrectCode(String value) {
		this.tradeSecrectCode = value;
	}
	public String getTradeSecrectCode() {
		return this.tradeSecrectCode;
	}
	public void setTradeSecrectSalt(String value) {
		this.tradeSecrectSalt = value;
	}
	public String getTradeSecrectSalt() {
		return this.tradeSecrectSalt;
	}
	public void setIsSetTradePwd(Integer value) {
		this.isSetTradePwd = value;
	}
	public Integer getIsSetTradePwd() {
		return this.isSetTradePwd;
	}
	public void setIsBindBankCard(Integer value) {
		this.isBindBankCard = value;
	}
	public Integer getIsBindBankCard() {
		return this.isBindBankCard;
	}
	public void setRegisterSource(Integer value) {
		this.registerSource = value;
	}
	public Integer getRegisterSource() {
		return this.registerSource;
	}
	public boolean getIsSendSms() {
		return isSendSms;
	}
	public void setIsSendSms(boolean isSendSms) {
		this.isSendSms = isSendSms;
	}
	

}

