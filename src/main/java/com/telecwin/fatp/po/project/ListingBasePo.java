package com.telecwin.fatp.po.project;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ListingBase
 * @author zhiya.chai
 * 2017-06-21 04:10:04
 */
public class ListingBasePo {
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 产品所属会员id,uc_user.id
	 */
	private Integer memberId;
	/**
	 * 借款人Id,uc_user.id
	 */
	private Integer loanUserId;
	/**
	 * 产品备案信息id
	 */
	private Integer projectRecordId;
	/**
	 * 产品编号
	 */
	private String projectCode;
	/**
	 * 产品唯一id，用于web展现
	 */
	private String projectGuid;
	/**
	 * 合作方挂牌业务id，开放API使用
	 */
	private String partnerBizId;
	/**
	 * 产品结算编号 用于支付结算
	 */
	private String projectSettleCode;
	/**
	 * 产品简称
	 */
	private String projectName;
	/**
	 * 产品全称
	 */
	private String projectFullName;
	/**
	 * 金融产品类型,systype_product.id,
	 */
	private Integer productTypeId;
	/**
	 * 产品类型,systype_member_project.id
	 */
	private Integer projectTypeId;
	/**
	 * 产品面值/净值/价格(元/份) 默认为1元/份 (new+)
	 */
	private java.math.BigDecimal projectUnitPrice;
	/**
	 * 产品融资资金规模,单位元
	 */
	private java.math.BigDecimal projectMoney;
	/**
	 * 产品融资规模下限金额
	 */
	private java.math.BigDecimal projectAmountMin;
	/**
	 * 所在省份/直辖市 (new)
	 */
	private Integer provinceId;
	/**
	 * 所在地城市 (new)
	 */
	private Integer cityId;
	/**
	 * 所属行业 (new)
	 */
	private Integer industryId;
	/**
	 * 融资年化成本 没有乘以%
	 */
	private java.math.BigDecimal projectCost;
	/**
	 * 是否有担保 有担保=1，无担保=0
	 */
	private Integer isGuarantee;
	/**
	 * 是否有质押 有质押=1，无质押=0
	 */
	private Integer isPledge;
	/**
	 * 是否有保险 有保险=1，无保险=0
	 */
	private Integer isInsurance;
	/**
	 * 产品用途，简要说明
	 */
	private String projectUsing;
	/**
	 * 产品期限(天)
	 */
	private Integer projectLimit;
	/**
	 * 产品期限类型id,systype_base.CategoryId=1的typeid
	 */
	private Integer projectLimitTypeId;
	/**
	 * 起息日变动规则  1：募集满日，2：放款日 3：固定起息日
	 */
	private Integer valueDateChangeStyle;
	/**
	 * 到期日变动规则 1：固定期限 2：固定到期日
	 */
	private Integer expireDateChangeStyle;
	/**
	 * 产品起息日(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date valueDate;
	/**
	 * 产品到期日(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date expireDate;
	/**
	 * 挂牌产品状态，未提交=1,待平台审核=4,平台退回=5,审核通过(待发布)=6
	 */
	private Integer projectStatus;
	/**
	 * 数据产生类型，0:三大web平台；1:开放API
	 */
	private Integer originType;
	/**
	 * 数据生成者id，开放API是appId；三大平台是null值
	 */
	private String originatorId;
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
	/**
	 * 募集到账金额元
	 */
	private BigDecimal raiseMoney;
	/**
	 * 剩余可投金额元
	 */
	private BigDecimal investSpareAmount;
	/**
	 * 审核时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date auditTime;
	/**
	 * 审核人
	 */
	private Integer auditOperatorId;
	
	private String auditRemark;

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
	public void setLoanUserId(Integer value) {
		this.loanUserId = value;
	}
	public Integer getLoanUserId() {
		return this.loanUserId;
	}
	public void setProjectRecordId(Integer value) {
		this.projectRecordId = value;
	}
	public Integer getProjectRecordId() {
		return this.projectRecordId;
	}
	public void setProjectCode(String value) {
		this.projectCode = value;
	}
	public String getProjectCode() {
		return this.projectCode;
	}
	public void setProjectGuid(String value) {
		this.projectGuid = value;
	}
	public String getProjectGuid() {
		return this.projectGuid;
	}
	public void setPartnerBizId(String value) {
		this.partnerBizId = value;
	}
	public String getPartnerBizId() {
		return this.partnerBizId;
	}
	public void setProjectSettleCode(String value) {
		this.projectSettleCode = value;
	}
	public String getProjectSettleCode() {
		return this.projectSettleCode;
	}
	public void setProjectName(String value) {
		this.projectName = value;
	}
	public String getProjectName() {
		return this.projectName;
	}
	public void setProjectFullName(String value) {
		this.projectFullName = value;
	}
	public String getProjectFullName() {
		return this.projectFullName;
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
	public void setProjectAmountMin(java.math.BigDecimal value) {
		this.projectAmountMin = value;
	}
	public java.math.BigDecimal getProjectAmountMin() {
		return this.projectAmountMin;
	}
	public void setProvinceId(Integer value) {
		this.provinceId = value;
	}
	public Integer getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setIndustryId(Integer value) {
		this.industryId = value;
	}
	public Integer getIndustryId() {
		return this.industryId;
	}
	public void setProjectCost(java.math.BigDecimal value) {
		this.projectCost = value;
	}
	public java.math.BigDecimal getProjectCost() {
		return this.projectCost;
	}
	public void setIsGuarantee(Integer value) {
		this.isGuarantee = value;
	}
	public Integer getIsGuarantee() {
		return this.isGuarantee;
	}
	public void setIsPledge(Integer value) {
		this.isPledge = value;
	}
	public Integer getIsPledge() {
		return this.isPledge;
	}
	public void setIsInsurance(Integer value) {
		this.isInsurance = value;
	}
	public Integer getIsInsurance() {
		return this.isInsurance;
	}
	public void setProjectUsing(String value) {
		this.projectUsing = value;
	}
	public String getProjectUsing() {
		return this.projectUsing;
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
	public void setValueDateChangeStyle(Integer value) {
		this.valueDateChangeStyle = value;
	}
	public Integer getValueDateChangeStyle() {
		return this.valueDateChangeStyle;
	}
	public void setExpireDateChangeStyle(Integer value) {
		this.expireDateChangeStyle = value;
	}
	public Integer getExpireDateChangeStyle() {
		return this.expireDateChangeStyle;
	}
	public void setValueDate(java.util.Date value) {
		this.valueDate = value;
	}
	public java.util.Date getValueDate() {
		return this.valueDate;
	}
	public void setExpireDate(java.util.Date value) {
		this.expireDate = value;
	}
	public java.util.Date getExpireDate() {
		return this.expireDate;
	}
	public void setProjectStatus(Integer value) {
		this.projectStatus = value;
	}
	public Integer getProjectStatus() {
		return this.projectStatus;
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
	public BigDecimal getRaiseMoney() {
		return raiseMoney;
	}
	public void setRaiseMoney(BigDecimal raiseMoney) {
		this.raiseMoney = raiseMoney;
	}
	public BigDecimal getInvestSpareAmount() {
		return investSpareAmount;
	}
	public void setInvestSpareAmount(BigDecimal investSpareAmount) {
		this.investSpareAmount = investSpareAmount;
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

