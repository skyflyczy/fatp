package com.telecwin.fatp.po.project;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ProjectRecordinfo
 */
public class ProjectRecordinfoPo implements Serializable{
	private static final long serialVersionUID = -3979288821405250750L;
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 产品所属会员Id,uc_user.id
	 */
	private Integer memberId;
	/**
	 * 备案编号
	 */
	private String recordCode;
	/**
	 * 备案唯一id，用于web展现
	 */
	private String recordGuid;
	/**
	 * 合作方备案业务ID，开放API使用
	 */
	private String partnerBizId;
	/**
	 * 备案简称
	 */
	private String recordName;
	/**
	 * 备案全称
	 */
	private String recordFullName;
	/**
	 * 金融产品类型,systype_product.id,仅仅支持直融、和金融产品类型登记
	 */
	private Integer productTypeId;
	/**
	 * 产品类型,systype_member_project.id
	 */
	private Integer projectTypeId;
	/**
	 * 借款人Id,uc_user.id
	 */
	private Integer loanUserId;
	/**
	 * 产品面值/净值/价格(元/份) 默认为1元/份
	 */
	private java.math.BigDecimal projectUnitPrice;
	/**
	 * 拟募集规模,单位元
	 */
	private java.math.BigDecimal projectMoney;
	/**
	 * 拟定期限,systype_base.CategoryId=1，天=1，月=2，年=3
	 */
	private Integer projectLimit;
	/**
	 * 产品期限类型id,systype_base.CategoryId=1的typeid
	 */
	private Integer projectLimitTypeId;
	/**
	 * 拟定还款方式,systype_repay.id，
	 */
	private Integer repayTypeId;
	/**
	 * 产品用途，简要说明
	 */
	private String projectUsing;
	/**
	 * 拟定年化收益率,没有乘以%
	 */
	private java.math.BigDecimal investProfit;
	/**
	 * 是否有担保 有担保=1，无担保=0
	 */
	private Integer isGuarantee;
	/**
	 * 是否有质押 有质押=1，无质押=0
	 */
	private Integer isPledge;
	/**
	 * 备案截止时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date deadline;
	/**
	 * 数据产生类型，0:三大web平台；1:开放API
	 */
	private Integer originType;
	/**
	 * 数据生成者id，开放API是appId；三大平台是null值
	 */
	private String originatorId;
	/**
	 * 评级机构名称
	 */
	private String ratingOrgName;
	/**
	 * 主体信用级别
	 */
	private String creditRating;
	/**
	 * 债项级别
	 */
	private String debtLevel;
	/**
	 * 主承销商指定联系人id
	 */
	private Integer underwriterLinkId;
	/**
	 * 融资方指定联系人id
	 */
	private Integer loanLinkId;
	/**
	 * 备案状态，备案处理中=1，备案通过=2，备案不通过=3
	 */
	private Integer recordStatus;
	/**
	 * 增信说明
	 */
	private String creditNote;
	/**
	 * 机构最后一次审核时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date auditTime;
	/**
	 * 最后一次审核操作人
	 */
	private Integer auditOperatorId;
	
	private String auditRemark;
	/**
	 * 创建时间
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
	private Integer upateOperatorId;
	/**
	 * 数据版本号,保障并发修改的唯一性
	 */
	private Integer versionNo;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setExchangeId(Integer value) {
		this.exchangeId = value;
	}
	public Integer getExchangeId() {
		return this.exchangeId;
	}
	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setRecordCode(String value) {
		this.recordCode = value;
	}
	public String getRecordCode() {
		return this.recordCode;
	}
	public void setRecordGuid(String value) {
		this.recordGuid = value;
	}
	public String getRecordGuid() {
		return this.recordGuid;
	}
	public void setPartnerBizId(String value) {
		this.partnerBizId = value;
	}
	public String getPartnerBizId() {
		return this.partnerBizId;
	}
	public void setRecordName(String value) {
		this.recordName = value;
	}
	public String getRecordName() {
		return this.recordName;
	}
	public void setRecordFullName(String value) {
		this.recordFullName = value;
	}
	public String getRecordFullName() {
		return this.recordFullName;
	}
	public void setProductTypeId(Integer value) {
		this.productTypeId = value;
	}
	public Integer getProductTypeId() {
		return this.productTypeId;
	}
	public void setProjectTypeId(Integer value) {
		this.projectTypeId = value;
	}
	public Integer getProjectTypeId() {
		return this.projectTypeId;
	}
	public void setLoanUserId(Integer value) {
		this.loanUserId = value;
	}
	public Integer getLoanUserId() {
		return this.loanUserId;
	}
	public void setProjectUnitPrice(java.math.BigDecimal value) {
		this.projectUnitPrice = value;
	}
	public java.math.BigDecimal getProjectUnitPrice() {
		return this.projectUnitPrice;
	}
	public void setProjectMoney(java.math.BigDecimal value) {
		this.projectMoney = value;
	}
	public java.math.BigDecimal getProjectMoney() {
		return this.projectMoney;
	}
	public void setProjectLimit(Integer value) {
		this.projectLimit = value;
	}
	public Integer getProjectLimit() {
		return this.projectLimit;
	}
	public void setProjectLimitTypeId(Integer value) {
		this.projectLimitTypeId = value;
	}
	public Integer getProjectLimitTypeId() {
		return this.projectLimitTypeId;
	}
	public void setRepayTypeId(Integer value) {
		this.repayTypeId = value;
	}
	public Integer getRepayTypeId() {
		return this.repayTypeId;
	}
	public void setProjectUsing(String value) {
		this.projectUsing = value;
	}
	public String getProjectUsing() {
		return this.projectUsing;
	}
	public void setInvestProfit(java.math.BigDecimal value) {
		this.investProfit = value;
	}
	public java.math.BigDecimal getInvestProfit() {
		return this.investProfit;
	}
	
	public void setDeadline(java.util.Date value) {
		this.deadline = value;
	}
	public java.util.Date getDeadline() {
		return this.deadline;
	}
	public void setOriginType(Integer value) {
		this.originType = value;
	}
	public Integer getOriginType() {
		return this.originType;
	}
	public void setOriginatorId(String value) {
		this.originatorId = value;
	}
	public String getOriginatorId() {
		return this.originatorId;
	}
	public void setRatingOrgName(String value) {
		this.ratingOrgName = value;
	}
	public String getRatingOrgName() {
		return this.ratingOrgName;
	}
	public void setCreditRating(String value) {
		this.creditRating = value;
	}
	public String getCreditRating() {
		return this.creditRating;
	}
	public void setDebtLevel(String value) {
		this.debtLevel = value;
	}
	public String getDebtLevel() {
		return this.debtLevel;
	}
	public void setUnderwriterLinkId(Integer value) {
		this.underwriterLinkId = value;
	}
	public Integer getUnderwriterLinkId() {
		return this.underwriterLinkId;
	}
	public void setLoanLinkId(Integer value) {
		this.loanLinkId = value;
	}
	public Integer getLoanLinkId() {
		return this.loanLinkId;
	}
	public void setRecordStatus(Integer value) {
		this.recordStatus = value;
	}
	public Integer getRecordStatus() {
		return this.recordStatus;
	}
	public void setCreditNote(String value) {
		this.creditNote = value;
	}
	public String getCreditNote() {
		return this.creditNote;
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
	public void setUpateOperatorId(Integer value) {
		this.upateOperatorId = value;
	}
	public Integer getUpateOperatorId() {
		return this.upateOperatorId;
	}
	public void setVersionNo(Integer value) {
		this.versionNo = value;
	}
	public Integer getVersionNo() {
		return this.versionNo;
	}
	public Integer getIsGuarantee() {
		return isGuarantee;
	}
	public void setIsGuarantee(Integer isGuarantee) {
		this.isGuarantee = isGuarantee;
	}
	public Integer getIsPledge() {
		return isPledge;
	}
	public void setIsPledge(Integer isPledge) {
		this.isPledge = isPledge;
	}
	public java.util.Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditOperatorId() {
		return auditOperatorId;
	}
	public void setAuditOperatorId(Integer auditOperatorId) {
		this.auditOperatorId = auditOperatorId;
	}
	public String getAuditRemark() {
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
}

