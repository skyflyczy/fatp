package com.telecwin.fatp.domain.project;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListingComplex {
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 项目编号
	 */
	private String projectCode;
	/**
	 * 项目唯一id，用于web展现
	 */
	private String projectGuid;
	/**
	 * 项目结算编号 用于支付结算
	 */
	private String projectSettleCode;
	/**
	 * 项目所属会员id,uc_user.id
	 */
	private Integer memberId;
	/**
	 * 项目所属会员名称
	 */
	private String memberName;
	/**
	 * 账户名
	 */
	private String accountName;
	/**
	 * 卡账号
	 */
	private String cardAccount;
	/**
	 * 银行名
	 */
	private String subBankName;
	/**
	 * 银行通道名
	 */
	private String bankChannelName;
	/**
	 * 交易所id,fe_exchange.id
	 */
	private Integer exchangeId;
	/**
	 * 项目简称
	 */
	private String projectName;
	/**
	 * 项目全称
	 */
	private String projectFullName;
	/**
	 * 金融产品类型,systype_product.id,
	 */
	private Integer productTypeId;
	/**
	 * 项目类型,systype_member_project.id
	 */
	private Integer projectTypeId;
	/**
	 * 项目来源,来自分支机构
	 */
	private Integer projectSourceId;
	/**
	 * 借款人Id,uc_user.id
	 */
	private Integer loanUserId;
	/**
	 * 借款人名称
	 */
	private String loanUserName;
	/**
	 * 贷款人GUID
	 */
	private String userGuid;
	/**
	 * 创建人名称
	 */
	private String createOperatorName;
	/**
	 * 项目面值/净值/价格(元/份) 默认为1元/份 (new+)
	 */
	private java.math.BigDecimal projectUnitPrice;
	/**
	 * 项目融资规模份额，单位份（new+）
	 */
	private java.math.BigDecimal projectAmount;
	/**
	 * 项目融资资金规模,单位元
	 */
	private java.math.BigDecimal projectMoney;
	/**
	 * 募集到账金额(元)
	 */
	private java.math.BigDecimal raiseMoney;
	/**
	 * 剩余可投金额,单位元或份(new)
	 */
	private java.math.BigDecimal investSpareAmount;
	/**
	 * 项目融资规模下限份额(new)
	 */
	private java.math.BigDecimal projectAmountMin;
	/**
	 * 项目期限(天)
	 */
	private Integer projectLimit;
	/**
	 * 融资年化成本 没有乘以%
	 */
	private java.math.BigDecimal projectCost;
	/**
	 * 还款方式
	 */
	private Integer repayTypeId;
	/**
	 * 项目起息日(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date valueDate;
	/**
	 * 项目到期日(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date expireDate;
	/**
	 * 计息基准类型 0:自定义天数，1:365/366
	 */
	private Integer interestBaseType;
	/**
	 * 计息基准天数
	 */
	private Integer interestBaseDays;
	/**
	 * 项目用户，简要说明
	 */
	private String projectUsing;
	/**
	 * 收款账户id uc_userbankcard.id
	 */
	private Integer receiveAccountId;
	/**
	 * 申购开始时间(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date buyTimeStart;
	/**
	 * 申购结束时间(计划迁移至发行表)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date buyTimeEnd;
	/**
	 * 起投份额(new)
	 */
	private java.math.BigDecimal investAmountMin;
	/**
	 * 申购份额上限 (new)
	 */
	private java.math.BigDecimal investAmountMax;
	/**
	 * 申购追加份额，总投资金额应该是起投+N*追加(new)
	 */
	private java.math.BigDecimal investAmountAppend;
	/**
	 * 投资年化收益率，没有乘以%(计划迁移至发行表)
	 */
	private java.math.BigDecimal investProfit;
	/**
	 * 是否有担保 有担保=1，无担保=0
	 */
	private Integer isGuarantee;
	/**
	 * 担保公司id,uc_user.id
	 */
	private Integer guaranteeUserId;
	/**
	 * 是否有质押 有质押=1，无质押=0
	 */
	private Integer isPledge;
	/**
	 * 是否有保险 有保险=1，无保险=0
	 */
	private Integer isInsurance;
	/**
	 * 融资顾问费率，没有乘以%
	 */
	private java.math.BigDecimal advioserFee;
	/**
	 * 平台管理费率,没有乘以%
	 */
	private java.math.BigDecimal platformFee;
	/**
	 * 逾期还款罚息比例,没有乘以%，按天罚未还本金得到罚息(new)
	 */
	private java.math.BigDecimal overduePayFee;
	/**
	 * 逾期还款罚息备注说明
	 */
	private String overdueDesc;
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
	 * 发布时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date publishTime;
	/**
	 * 项目状态,未提交=1,待审核=2,审核不通过=3,审核退回=4,待发布=5,已发布=6,申购中=7,申购结束=8,发行成功=9 发行失败=-1 取消发行=-2 项目撤销=-3
	 */
	private Integer projectStatus;
	/**
	 * 最后一次审核时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date auditTime;
	/**
	 * 最后一次审核操作人
	 */
	private Integer auditOperatorId;
	private String auditRemark;
	/**
	 * 数据版本号,保障并发修改的唯一性
	 */
	private Integer versionNo;
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
	 * 保证金金额 (new)
	 */
	private java.math.BigDecimal guaranteeMoney;
	/**
	 * 成交后N个工作日结算 (new)
	 */
	private Integer paySettleDay;
	/**
	 * 结算方式,场内结算=1 场外结算=2 目前系统只支持场内
	 */
	private Integer settleTypeId;
	/**
	 * 投资顾问费/费率 (new)
	 */
	private java.math.BigDecimal investAdvioserFee;
	/**
	 * 转让方式,拆分转让 =1 整体转让=2
	 */
	private Integer transferType;
	/**
	 * 项目资金归集结算渠道id ，sys_bankchannel.id
	 */
	private Integer payclearId;
	/**
	 * 还款期限天数
	 */
	private Integer repayPeriodDay;
	/**
	 * 结息日
	 */
	private Integer settleInvestDay;
	/**
	 * 项目期限类型id,systype_base.CategoryId=1的typeid
	 */
	private Integer projectLimitTypeId;
	/**
	 * 数据来源：1-pro,2-gray
	 */
	private Integer source;
	/**
	 * 保证金设值类型 金额=1 百分比=2
	 */
	private Integer guaranteeValueType;
	/**
	 * 项目发行期数
	 */
	private Integer periodNum;
	/**
	 * 交易方式：1-场内交易；2-场外交易；3-场内+场外
	 */
	private Integer tradeType;
	/**
	 * 项目备案信息id
	 */
	private Integer projectRecordId;
	/**
	 * 兑付期的还款资金结算方式，场内直接结算-1，场内间接结算-3，场外结算-2
	 */
	private Integer investSettleTypeId;
	/**
	 * 是否允许转让，是-1，否-0
	 */
	private Integer canTransfer;
	/**
	 * 项目成立后几天可以转让
	 */
	private Integer transferAfter;
	/**
	 * 转让期限
	 */
	private Integer transferLimit;
	/**
	 * 备案截止期限
	 */
	private Date deadline;
	/**
	 * 是否管理存续期
	 */
	private Integer manageDuration;
	/**
	 * 起息日变动规则  1：募集满日，2：放款日 3：固定起息日
	 */
	private Integer valueDateChangeStyle;
	/**
	 * 到期日变动规则 1：固定期限 2：固定到期日
	 */
	private Integer expireDateChangeStyle;
	private String tradePlatform;
	private Integer receiveUserType;
	private int originType = 0;		//数据产生类型，0:三大web平台；1:开放API
	private String originatorId;	//数据生成者id，开放API是appId；三大平台是null值
	/**
	 * 兑付收款类型
	 */
	private Short payinvestType;
	/**
	 * 兑付中间方ID
	 */
	private Integer payinvestId;
	/**
	 * 兑付中间方银行卡
	 */
	private Integer payinvestBankCardId;
	private Short generatePlan=0;	
	/**
	 * 合作方挂牌业务ID
	 */
	private String partnerBizId;
	private Integer platformFeeType;//平台费用收费方式
	private Integer interestMode;
	
	private Integer saleagentId;
	private Integer projectId;
	private BigDecimal saleMoney;
	private BigDecimal saleFeeRate;
	private BigDecimal saleReceivedMoney;
	private BigDecimal saleCfmRightMoney;
	/**
	 * 项目详情
	 */
	private String projectInfo;
	/**
	 * 基础资产说明
	 */
	private String basicAssetNote;
	/**
	 * 交易对手方资质
	 */
	private String tradePartyQualification;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	/**
	 * @return the projectGuid
	 */
	public String getProjectGuid() {
		return projectGuid;
	}
	/**
	 * @param projectGuid the projectGuid to set
	 */
	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}
	/**
	 * @return the projectSettleCode
	 */
	public String getProjectSettleCode() {
		return projectSettleCode;
	}
	/**
	 * @param projectSettleCode the projectSettleCode to set
	 */
	public void setProjectSettleCode(String projectSettleCode) {
		this.projectSettleCode = projectSettleCode;
	}
	/**
	 * @return the memberId
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the exchangeId
	 */
	public Integer getExchangeId() {
		return exchangeId;
	}
	/**
	 * @param exchangeId the exchangeId to set
	 */
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the projectFullName
	 */
	public String getProjectFullName() {
		return projectFullName;
	}
	/**
	 * @param projectFullName the projectFullName to set
	 */
	public void setProjectFullName(String projectFullName) {
		this.projectFullName = projectFullName;
	}
	/**
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}
	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	/**
	 * @return the projectTypeId
	 */
	public Integer getProjectTypeId() {
		return projectTypeId;
	}
	/**
	 * @param projectTypeId the projectTypeId to set
	 */
	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	/**
	 * @return the projectSourceId
	 */
	public Integer getProjectSourceId() {
		return projectSourceId;
	}
	/**
	 * @param projectSourceId the projectSourceId to set
	 */
	public void setProjectSourceId(Integer projectSourceId) {
		this.projectSourceId = projectSourceId;
	}
	/**
	 * @return the loanUserId
	 */
	public Integer getLoanUserId() {
		return loanUserId;
	}
	/**
	 * @param loanUserId the loanUserId to set
	 */
	public void setLoanUserId(Integer loanUserId) {
		this.loanUserId = loanUserId;
	}
	/**
	 * @return the projectUnitPrice
	 */
	public java.math.BigDecimal getProjectUnitPrice() {
		return projectUnitPrice;
	}
	/**
	 * @param projectUnitPrice the projectUnitPrice to set
	 */
	public void setProjectUnitPrice(java.math.BigDecimal projectUnitPrice) {
		this.projectUnitPrice = projectUnitPrice;
	}
	/**
	 * @return the projectAmount
	 */
	public java.math.BigDecimal getProjectAmount() {
		return projectAmount;
	}
	/**
	 * @param projectAmount the projectAmount to set
	 */
	public void setProjectAmount(java.math.BigDecimal projectAmount) {
		this.projectAmount = projectAmount;
	}
	/**
	 * @return the projectMoney
	 */
	public java.math.BigDecimal getProjectMoney() {
		return projectMoney;
	}
	/**
	 * @param projectMoney the projectMoney to set
	 */
	public void setProjectMoney(java.math.BigDecimal projectMoney) {
		this.projectMoney = projectMoney;
	}
	/**
	 * @return the raiseMoney
	 */
	public java.math.BigDecimal getRaiseMoney() {
		return raiseMoney;
	}
	/**
	 * @param raiseMoney the raiseMoney to set
	 */
	public void setRaiseMoney(java.math.BigDecimal raiseMoney) {
		this.raiseMoney = raiseMoney;
	}
	/**
	 * @return the investSpareAmount
	 */
	public java.math.BigDecimal getInvestSpareAmount() {
		return investSpareAmount;
	}
	/**
	 * @param investSpareAmount the investSpareAmount to set
	 */
	public void setInvestSpareAmount(java.math.BigDecimal investSpareAmount) {
		this.investSpareAmount = investSpareAmount;
	}
	/**
	 * @return the projectAmountMin
	 */
	public java.math.BigDecimal getProjectAmountMin() {
		return projectAmountMin;
	}
	/**
	 * @param projectAmountMin the projectAmountMin to set
	 */
	public void setProjectAmountMin(java.math.BigDecimal projectAmountMin) {
		this.projectAmountMin = projectAmountMin;
	}
	/**
	 * @return the projectLimit
	 */
	public Integer getProjectLimit() {
		return projectLimit;
	}
	/**
	 * @param projectLimit the projectLimit to set
	 */
	public void setProjectLimit(Integer projectLimit) {
		this.projectLimit = projectLimit;
	}
	/**
	 * @return the projectCost
	 */
	public java.math.BigDecimal getProjectCost() {
		return projectCost;
	}
	/**
	 * @param projectCost the projectCost to set
	 */
	public void setProjectCost(java.math.BigDecimal projectCost) {
		this.projectCost = projectCost;
	}
	/**
	 * @return the repayTypeId
	 */
	public Integer getRepayTypeId() {
		return repayTypeId;
	}
	/**
	 * @param repayTypeId the repayTypeId to set
	 */
	public void setRepayTypeId(Integer repayTypeId) {
		this.repayTypeId = repayTypeId;
	}
	/**
	 * @return the valueDate
	 */
	public java.util.Date getValueDate() {
		return valueDate;
	}
	/**
	 * @param valueDate the valueDate to set
	 */
	public void setValueDate(java.util.Date valueDate) {
		this.valueDate = valueDate;
	}
	/**
	 * @return the expireDate
	 */
	public java.util.Date getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * @return the interestBaseType
	 */
	public Integer getInterestBaseType() {
		return interestBaseType;
	}
	/**
	 * @param interestBaseType the interestBaseType to set
	 */
	public void setInterestBaseType(Integer interestBaseType) {
		this.interestBaseType = interestBaseType;
	}
	/**
	 * @return the interestBaseDays
	 */
	public Integer getInterestBaseDays() {
		return interestBaseDays;
	}
	/**
	 * @param interestBaseDays the interestBaseDays to set
	 */
	public void setInterestBaseDays(Integer interestBaseDays) {
		this.interestBaseDays = interestBaseDays;
	}
	/**
	 * @return the projectUsing
	 */
	public String getProjectUsing() {
		return projectUsing;
	}
	/**
	 * @param projectUsing the projectUsing to set
	 */
	public void setProjectUsing(String projectUsing) {
		this.projectUsing = projectUsing;
	}
	/**
	 * @return the receiveAccountId
	 */
	public Integer getReceiveAccountId() {
		return receiveAccountId;
	}
	/**
	 * @param receiveAccountId the receiveAccountId to set
	 */
	public void setReceiveAccountId(Integer receiveAccountId) {
		this.receiveAccountId = receiveAccountId;
	}
	/**
	 * @return the buyTimeStart
	 */
	public java.util.Date getBuyTimeStart() {
		return buyTimeStart;
	}
	/**
	 * @param buyTimeStart the buyTimeStart to set
	 */
	public void setBuyTimeStart(java.util.Date buyTimeStart) {
		this.buyTimeStart = buyTimeStart;
	}
	/**
	 * @return the buyTimeEnd
	 */
	public java.util.Date getBuyTimeEnd() {
		return buyTimeEnd;
	}
	/**
	 * @param buyTimeEnd the buyTimeEnd to set
	 */
	public void setBuyTimeEnd(java.util.Date buyTimeEnd) {
		this.buyTimeEnd = buyTimeEnd;
	}
	/**
	 * @return the investAmountMin
	 */
	public java.math.BigDecimal getInvestAmountMin() {
		return investAmountMin;
	}
	/**
	 * @param investAmountMin the investAmountMin to set
	 */
	public void setInvestAmountMin(java.math.BigDecimal investAmountMin) {
		this.investAmountMin = investAmountMin;
	}
	/**
	 * @return the investAmountMax
	 */
	public java.math.BigDecimal getInvestAmountMax() {
		return investAmountMax;
	}
	/**
	 * @param investAmountMax the investAmountMax to set
	 */
	public void setInvestAmountMax(java.math.BigDecimal investAmountMax) {
		this.investAmountMax = investAmountMax;
	}
	/**
	 * @return the investAmountAppend
	 */
	public java.math.BigDecimal getInvestAmountAppend() {
		return investAmountAppend;
	}
	/**
	 * @param investAmountAppend the investAmountAppend to set
	 */
	public void setInvestAmountAppend(java.math.BigDecimal investAmountAppend) {
		this.investAmountAppend = investAmountAppend;
	}
	/**
	 * @return the investProfit
	 */
	public java.math.BigDecimal getInvestProfit() {
		return investProfit;
	}
	/**
	 * @param investProfit the investProfit to set
	 */
	public void setInvestProfit(java.math.BigDecimal investProfit) {
		this.investProfit = investProfit;
	}
	/**
	 * @return the isGuarantee
	 */
	public Integer getIsGuarantee() {
		return isGuarantee;
	}
	/**
	 * @param isGuarantee the isGuarantee to set
	 */
	public void setIsGuarantee(Integer isGuarantee) {
		this.isGuarantee = isGuarantee;
	}
	/**
	 * @return the guaranteeUserId
	 */
	public Integer getGuaranteeUserId() {
		return guaranteeUserId;
	}
	/**
	 * @param guaranteeUserId the guaranteeUserId to set
	 */
	public void setGuaranteeUserId(Integer guaranteeUserId) {
		this.guaranteeUserId = guaranteeUserId;
	}
	/**
	 * @return the isPledge
	 */
	public Integer getIsPledge() {
		return isPledge;
	}
	/**
	 * @param isPledge the isPledge to set
	 */
	public void setIsPledge(Integer isPledge) {
		this.isPledge = isPledge;
	}
	/**
	 * @return the isInsurance
	 */
	public Integer getIsInsurance() {
		return isInsurance;
	}
	/**
	 * @param isInsurance the isInsurance to set
	 */
	public void setIsInsurance(Integer isInsurance) {
		this.isInsurance = isInsurance;
	}
	/**
	 * @return the advioserFee
	 */
	public java.math.BigDecimal getAdvioserFee() {
		return advioserFee;
	}
	/**
	 * @param advioserFee the advioserFee to set
	 */
	public void setAdvioserFee(java.math.BigDecimal advioserFee) {
		this.advioserFee = advioserFee;
	}
	/**
	 * @return the platformFee
	 */
	public java.math.BigDecimal getPlatformFee() {
		return platformFee;
	}
	/**
	 * @param platformFee the platformFee to set
	 */
	public void setPlatformFee(java.math.BigDecimal platformFee) {
		this.platformFee = platformFee;
	}
	/**
	 * @return the overduePayFee
	 */
	public java.math.BigDecimal getOverduePayFee() {
		return overduePayFee;
	}
	/**
	 * @param overduePayFee the overduePayFee to set
	 */
	public void setOverduePayFee(java.math.BigDecimal overduePayFee) {
		this.overduePayFee = overduePayFee;
	}
	/**
	 * @return the overdueDesc
	 */
	public String getOverdueDesc() {
		return overdueDesc;
	}
	/**
	 * @param overdueDesc the overdueDesc to set
	 */
	public void setOverdueDesc(String overdueDesc) {
		this.overdueDesc = overdueDesc;
	}
	/**
	 * @return the createTime
	 */
	public java.util.Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the createOperatorId
	 */
	public Integer getCreateOperatorId() {
		return createOperatorId;
	}
	/**
	 * @param createOperatorId the createOperatorId to set
	 */
	public void setCreateOperatorId(Integer createOperatorId) {
		this.createOperatorId = createOperatorId;
	}
	/**
	 * @return the updateTime
	 */
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the upateOperatorId
	 */
	public Integer getUpateOperatorId() {
		return upateOperatorId;
	}
	/**
	 * @param upateOperatorId the upateOperatorId to set
	 */
	public void setUpateOperatorId(Integer upateOperatorId) {
		this.upateOperatorId = upateOperatorId;
	}
	/**
	 * @return the publishTime
	 */
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the projectStatus
	 */
	public Integer getProjectStatus() {
		return projectStatus;
	}
	/**
	 * @param projectStatus the projectStatus to set
	 */
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	/**
	 * @return the versionNo
	 */
	public Integer getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo the versionNo to set
	 */
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	/**
	 * @return the provinceId
	 */
	public Integer getProvinceId() {
		return provinceId;
	}
	/**
	 * @param provinceId the provinceId to set
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the industryId
	 */
	public Integer getIndustryId() {
		return industryId;
	}
	/**
	 * @param industryId the industryId to set
	 */
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}
	/**
	 * @return the guaranteeMoney
	 */
	public java.math.BigDecimal getGuaranteeMoney() {
		return guaranteeMoney;
	}
	/**
	 * @param guaranteeMoney the guaranteeMoney to set
	 */
	public void setGuaranteeMoney(java.math.BigDecimal guaranteeMoney) {
		this.guaranteeMoney = guaranteeMoney;
	}
	/**
	 * @return the paySettleDay
	 */
	public Integer getPaySettleDay() {
		return paySettleDay;
	}
	/**
	 * @param paySettleDay the paySettleDay to set
	 */
	public void setPaySettleDay(Integer paySettleDay) {
		this.paySettleDay = paySettleDay;
	}
	/**
	 * @return the settleTypeId
	 */
	public Integer getSettleTypeId() {
		return settleTypeId;
	}
	/**
	 * @param settleTypeId the settleTypeId to set
	 */
	public void setSettleTypeId(Integer settleTypeId) {
		this.settleTypeId = settleTypeId;
	}
	/**
	 * @return the investAdvioserFee
	 */
	public java.math.BigDecimal getInvestAdvioserFee() {
		return investAdvioserFee;
	}
	/**
	 * @param investAdvioserFee the investAdvioserFee to set
	 */
	public void setInvestAdvioserFee(java.math.BigDecimal investAdvioserFee) {
		this.investAdvioserFee = investAdvioserFee;
	}
	/**
	 * @return the transferType
	 */
	public Integer getTransferType() {
		return transferType;
	}
	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	/**
	 * @return the payclearId
	 */
	public Integer getPayclearId() {
		return payclearId;
	}
	/**
	 * @param payclearId the payclearId to set
	 */
	public void setPayclearId(Integer payclearId) {
		this.payclearId = payclearId;
	}
	/**
	 * @return the repayPeriodDay
	 */
	public Integer getRepayPeriodDay() {
		return repayPeriodDay;
	}
	/**
	 * @param repayPeriodDay the repayPeriodDay to set
	 */
	public void setRepayPeriodDay(Integer repayPeriodDay) {
		this.repayPeriodDay = repayPeriodDay;
	}
	/**
	 * @return the settleInvestDay
	 */
	public Integer getSettleInvestDay() {
		return settleInvestDay;
	}
	/**
	 * @param settleInvestDay the settleInvestDay to set
	 */
	public void setSettleInvestDay(Integer settleInvestDay) {
		this.settleInvestDay = settleInvestDay;
	}
	/**
	 * @return the projectLimitTypeId
	 */
	public Integer getProjectLimitTypeId() {
		return projectLimitTypeId;
	}
	/**
	 * @param projectLimitTypeId the projectLimitTypeId to set
	 */
	public void setProjectLimitTypeId(Integer projectLimitTypeId) {
		this.projectLimitTypeId = projectLimitTypeId;
	}
	/**
	 * @return the source
	 */
	public Integer getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	/**
	 * @return the guaranteeValueType
	 */
	public Integer getGuaranteeValueType() {
		return guaranteeValueType;
	}
	/**
	 * @param guaranteeValueType the guaranteeValueType to set
	 */
	public void setGuaranteeValueType(Integer guaranteeValueType) {
		this.guaranteeValueType = guaranteeValueType;
	}
	/**
	 * @return the periodNum
	 */
	public Integer getPeriodNum() {
		return periodNum;
	}
	/**
	 * @param periodNum the periodNum to set
	 */
	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}
	/**
	 * @return the tradeType
	 */
	public Integer getTradeType() {
		return tradeType;
	}
	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	/**
	 * @return the projectRecordId
	 */
	public Integer getProjectRecordId() {
		return projectRecordId;
	}
	/**
	 * @param projectRecordId the projectRecordId to set
	 */
	public void setProjectRecordId(Integer projectRecordId) {
		this.projectRecordId = projectRecordId;
	}
	/**
	 * @return the investSettleTypeId
	 */
	public Integer getInvestSettleTypeId() {
		return investSettleTypeId;
	}
	/**
	 * @param investSettleTypeId the investSettleTypeId to set
	 */
	public void setInvestSettleTypeId(Integer investSettleTypeId) {
		this.investSettleTypeId = investSettleTypeId;
	}
	/**
	 * @return the canTransfer
	 */
	public Integer getCanTransfer() {
		return canTransfer;
	}
	/**
	 * @param canTransfer the canTransfer to set
	 */
	public void setCanTransfer(Integer canTransfer) {
		this.canTransfer = canTransfer;
	}
	/**
	 * @return the transferAfter
	 */
	public Integer getTransferAfter() {
		return transferAfter;
	}
	/**
	 * @param transferAfter the transferAfter to set
	 */
	public void setTransferAfter(Integer transferAfter) {
		this.transferAfter = transferAfter;
	}
	/**
	 * @return the transferLimit
	 */
	public Integer getTransferLimit() {
		return transferLimit;
	}
	/**
	 * @param transferLimit the transferLimit to set
	 */
	public void setTransferLimit(Integer transferLimit) {
		this.transferLimit = transferLimit;
	}
	/**
	 * @return the manageDuration
	 */
	public Integer getManageDuration() {
		return manageDuration;
	}
	/**
	 * @param manageDuration the manageDuration to set
	 */
	public void setManageDuration(Integer manageDuration) {
		this.manageDuration = manageDuration;
	}
	/**
	 * @return the valueDateChangeStyle
	 */
	public Integer getValueDateChangeStyle() {
		return valueDateChangeStyle;
	}
	/**
	 * @param valueDateChangeStyle the valueDateChangeStyle to set
	 */
	public void setValueDateChangeStyle(Integer valueDateChangeStyle) {
		this.valueDateChangeStyle = valueDateChangeStyle;
	}
	/**
	 * @return the expireDateChangeStyle
	 */
	public Integer getExpireDateChangeStyle() {
		return expireDateChangeStyle;
	}
	/**
	 * @param expireDateChangeStyle the expireDateChangeStyle to set
	 */
	public void setExpireDateChangeStyle(Integer expireDateChangeStyle) {
		this.expireDateChangeStyle = expireDateChangeStyle;
	}
	/**
	 * @return the tradePlatform
	 */
	public String getTradePlatform() {
		return tradePlatform;
	}
	/**
	 * @param tradePlatform the tradePlatform to set
	 */
	public void setTradePlatform(String tradePlatform) {
		this.tradePlatform = tradePlatform;
	}
	/**
	 * @return the receiveUserType
	 */
	public Integer getReceiveUserType() {
		return receiveUserType;
	}
	/**
	 * @param receiveUserType the receiveUserType to set
	 */
	public void setReceiveUserType(Integer receiveUserType) {
		this.receiveUserType = receiveUserType;
	}
	/**
	 * @return the originType
	 */
	public int getOriginType() {
		return originType;
	}
	/**
	 * @param originType the originType to set
	 */
	public void setOriginType(int originType) {
		this.originType = originType;
	}
	/**
	 * @return the originatorId
	 */
	public String getOriginatorId() {
		return originatorId;
	}
	/**
	 * @param originatorId the originatorId to set
	 */
	public void setOriginatorId(String originatorId) {
		this.originatorId = originatorId;
	}
	/**
	 * @return the payinvestType
	 */
	public Short getPayinvestType() {
		return payinvestType;
	}
	/**
	 * @param payinvestType the payinvestType to set
	 */
	public void setPayinvestType(Short payinvestType) {
		this.payinvestType = payinvestType;
	}
	/**
	 * @return the payinvestId
	 */
	public Integer getPayinvestId() {
		return payinvestId;
	}
	/**
	 * @param payinvestId the payinvestId to set
	 */
	public void setPayinvestId(Integer payinvestId) {
		this.payinvestId = payinvestId;
	}
	/**
	 * @return the payinvestBankCardId
	 */
	public Integer getPayinvestBankCardId() {
		return payinvestBankCardId;
	}
	/**
	 * @param payinvestBankCardId the payinvestBankCardId to set
	 */
	public void setPayinvestBankCardId(Integer payinvestBankCardId) {
		this.payinvestBankCardId = payinvestBankCardId;
	}
	/**
	 * @return the generatePlan
	 */
	public Short getGeneratePlan() {
		return generatePlan;
	}
	/**
	 * @param generatePlan the generatePlan to set
	 */
	public void setGeneratePlan(Short generatePlan) {
		this.generatePlan = generatePlan;
	}
	/**
	 * @return the partnerBizId
	 */
	public String getPartnerBizId() {
		return partnerBizId;
	}
	/**
	 * @param partnerBizId the partnerBizId to set
	 */
	public void setPartnerBizId(String partnerBizId) {
		this.partnerBizId = partnerBizId;
	}
	/**
	 * @return the platformFeeType
	 */
	public Integer getPlatformFeeType() {
		return platformFeeType;
	}
	/**
	 * @param platformFeeType the platformFeeType to set
	 */
	public void setPlatformFeeType(Integer platformFeeType) {
		this.platformFeeType = platformFeeType;
	}
	/**
	 * @return the interestMode
	 */
	public Integer getInterestMode() {
		return interestMode;
	}
	/**
	 * @param interestMode the interestMode to set
	 */
	public void setInterestMode(Integer interestMode) {
		this.interestMode = interestMode;
	}
	
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCardAccount() {
		return cardAccount;
	}

	public void setCardAccount(String cardAccount) {
		this.cardAccount = cardAccount;
	}

	public String getSubBankName() {
		return subBankName;
	}

	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}

	public String getBankChannelName() {
		return bankChannelName;
	}

	public void setBankChannelName(String bankChannelName) {
		this.bankChannelName = bankChannelName;
	}

	public String getLoanUserName() {
		return loanUserName;
	}

	public void setLoanUserName(String loanUserName) {
		this.loanUserName = loanUserName;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getCreateOperatorName() {
		return createOperatorName;
	}

	public void setCreateOperatorName(String createOperatorName) {
		this.createOperatorName = createOperatorName;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getBasicAssetNote() {
		return basicAssetNote;
	}

	public void setBasicAssetNote(String basicAssetNote) {
		this.basicAssetNote = basicAssetNote;
	}

	public String getTradePartyQualification() {
		return tradePartyQualification;
	}

	public void setTradePartyQualification(String tradePartyQualification) {
		this.tradePartyQualification = tradePartyQualification;
	}
	
	/**
	 * @return the saleagentId
	 */
	public Integer getSaleagentId() {
		return saleagentId;
	}
	/**
	 * @param saleagentId the saleagentId to set
	 */
	public void setSaleagentId(Integer saleagentId) {
		this.saleagentId = saleagentId;
	}
	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the saleMoney
	 */
	public BigDecimal getSaleMoney() {
		return saleMoney;
	}
	/**
	 * @param saleMoney the saleMoney to set
	 */
	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
	/**
	 * @return the saleFeeRate
	 */
	public BigDecimal getSaleFeeRate() {
		return saleFeeRate;
	}
	/**
	 * @param saleFeeRate the saleFeeRate to set
	 */
	public void setSaleFeeRate(BigDecimal saleFeeRate) {
		this.saleFeeRate = saleFeeRate;
	}
	/**
	 * @return the saleReceivedMoney
	 */
	public BigDecimal getSaleReceivedMoney() {
		return saleReceivedMoney;
	}
	/**
	 * @param saleReceivedMoney the saleReceivedMoney to set
	 */
	public void setSaleReceivedMoney(BigDecimal saleReceivedMoney) {
		this.saleReceivedMoney = saleReceivedMoney;
	}
	/**
	 * @return the saleCfmRightMoney
	 */
	public BigDecimal getSaleCfmRightMoney() {
		return saleCfmRightMoney;
	}
	/**
	 * @param saleCfmRightMoney the saleCfmRightMoney to set
	 */
	public void setSaleCfmRightMoney(BigDecimal saleCfmRightMoney) {
		this.saleCfmRightMoney = saleCfmRightMoney;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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
