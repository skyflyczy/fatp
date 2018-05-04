package com.telecwin.fatp.po.offsite;

/**
 * 
 * BizimportTradeDetail
 * 
 */
public class BizimportTradeDetailPo {
	/**
	 * 主键
	 */
	private int id;

	/**
	 * 业务登记申请表id, bizimport_apply.id
	 */
	private int bizImportApplyId;

	/**
	 * 数据文件汇总信息表id, bizimport_summary.id
	 */
	private int bizImportFileSummaryId;
	/**
	 * 合作方交易流水号
	 */
	private String partnerTradeSeq;
	/**
	 * 产品挂牌id, listing_base.id
	 */
	private int projectId;
	/**
	 * 承销商id/申请者会员id, uc_user.id
	 */
	private int applyMemberId;
	/**
	 * 交易时间
	 */
	private java.util.Date tradeTime;
	/**
	 * 交易金额
	 */
	private java.math.BigDecimal tradeMoney;
	/**
	 * 客户Id
	 */
	private Integer userId;
	/**
	 * 是否本次新增的用户, 1-是, 0-否
	 */
	private Integer isNewUser;
	/**
	 * 客户编号
	 */
	private String userCode;

	/**
	 * 手机号, 敏感数据加密
	 */
	private String phoneNumber;

	/**
	 * 注册时间
	 */
	private java.util.Date registerTime;

	/**
	 * 证件类型
	 */
	private Integer idTypeId;

	/**
	 * 证件号码, 敏感数据加密
	 */
	private String idNumber;

	/**
	 * 用户真实姓名, 敏感数据加密
	 */
	private String userRealName;

	/**
	 * 订单Id
	 */
	private Integer orderApplyId;

	/**
	 * 交易所id
	 */
	private Integer exchangeId;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	/**
	 * 客户简称
	 */
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBizImportApplyId() {
		return bizImportApplyId;
	}
	public void setBizImportApplyId(int bizImportApplyId) {
		this.bizImportApplyId = bizImportApplyId;
	}
	public int getBizImportFileSummaryId() {
		return bizImportFileSummaryId;
	}
	public void setBizImportFileSummaryId(int bizImportFileSummaryId) {
		this.bizImportFileSummaryId = bizImportFileSummaryId;
	}
	public String getPartnerTradeSeq() {
		return partnerTradeSeq;
	}
	public void setPartnerTradeSeq(String partnerTradeSeq) {
		this.partnerTradeSeq = partnerTradeSeq;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getApplyMemberId() {
		return applyMemberId;
	}
	public void setApplyMemberId(int applyMemberId) {
		this.applyMemberId = applyMemberId;
	}
	public java.util.Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(java.util.Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public java.math.BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(java.math.BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(Integer isNewUser) {
		this.isNewUser = isNewUser;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.util.Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(java.util.Date registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getIdTypeId() {
		return idTypeId;
	}
	public void setIdTypeId(Integer idTypeId) {
		this.idTypeId = idTypeId;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Integer getOrderApplyId() {
		return orderApplyId;
	}
	public void setOrderApplyId(Integer orderApplyId) {
		this.orderApplyId = orderApplyId;
	}
	public Integer getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
