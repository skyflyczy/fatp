package com.telecwin.fatp.domain.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.enums.project.ProjectLimitType;
import com.telecwin.fatp.enums.project.SettleTypeDesc;

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
	 * 项目起息日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.util.Date valueDate;
	/**
	 * 项目到期日
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
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
	private Integer payinvestType;
	/**
	 * 兑付中间方ID
	 */
	private Integer payinvestId;
	/**
	 * 兑付中间方银行卡
	 */
	private Integer payinvestBankCardId;
	private Integer generatePlan=0;	
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
	private String tradeTerm;
	private String repayTypeName;
	private Integer cfmright;
	private Integer channelId;
	private String companyName;
	private BigDecimal collectMoney;
	private BigDecimal cfmRightMoney;
	private BigDecimal fee;
	private Integer count;
	private BigDecimal quotedMaxMoney;
	private int quotedCnt;
	private String industryName;
	private String proName;
	private String cityName;
	private Integer buyCount;
	private Integer settleStatus;
	private Integer breakPayStatus;
	private Integer buyCnt;
	private String projectTypeName;
	private String recordGuid;
	private String recordCode;
	private String recordName;
	private String transferorBasicNote;
	private Integer transfereeUserId;
	private Integer settleInvestMonth;
	private List<ListingSaleagent> projectSaleagent;
	/**
	 * 最多放款次数
	 */
	private Integer releaseNum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectGuid() {
		return projectGuid;
	}
	public void setProjectGuid(String projectGuid) {
		this.projectGuid = projectGuid;
	}
	public String getProjectSettleCode() {
		return projectSettleCode;
	}
	public void setProjectSettleCode(String projectSettleCode) {
		this.projectSettleCode = projectSettleCode;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
	public Integer getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectFullName() {
		return projectFullName;
	}
	public void setProjectFullName(String projectFullName) {
		this.projectFullName = projectFullName;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public Integer getProjectTypeId() {
		return projectTypeId;
	}
	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	public Integer getProjectSourceId() {
		return projectSourceId;
	}
	public void setProjectSourceId(Integer projectSourceId) {
		this.projectSourceId = projectSourceId;
	}
	public Integer getLoanUserId() {
		return loanUserId;
	}
	public void setLoanUserId(Integer loanUserId) {
		this.loanUserId = loanUserId;
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
	public java.math.BigDecimal getProjectUnitPrice() {
		return projectUnitPrice;
	}
	public void setProjectUnitPrice(java.math.BigDecimal projectUnitPrice) {
		this.projectUnitPrice = projectUnitPrice;
	}
	public java.math.BigDecimal getProjectAmount() {
		return projectAmount;
	}
	public void setProjectAmount(java.math.BigDecimal projectAmount) {
		this.projectAmount = projectAmount;
	}
	public java.math.BigDecimal getProjectMoney() {
		return projectMoney;
	}
	public void setProjectMoney(java.math.BigDecimal projectMoney) {
		this.projectMoney = projectMoney;
	}
	public java.math.BigDecimal getRaiseMoney() {
		return raiseMoney;
	}
	public void setRaiseMoney(java.math.BigDecimal raiseMoney) {
		this.raiseMoney = raiseMoney;
	}
	public java.math.BigDecimal getInvestSpareAmount() {
		return investSpareAmount;
	}
	public void setInvestSpareAmount(java.math.BigDecimal investSpareAmount) {
		this.investSpareAmount = investSpareAmount;
	}
	public java.math.BigDecimal getProjectAmountMin() {
		return projectAmountMin;
	}
	public void setProjectAmountMin(java.math.BigDecimal projectAmountMin) {
		this.projectAmountMin = projectAmountMin;
	}
	public Integer getProjectLimit() {
		return projectLimit;
	}
	public void setProjectLimit(Integer projectLimit) {
		this.projectLimit = projectLimit;
	}
	public java.math.BigDecimal getProjectCost() {
		return projectCost;
	}
	public void setProjectCost(java.math.BigDecimal projectCost) {
		this.projectCost = projectCost;
	}
	public Integer getRepayTypeId() {
		return repayTypeId;
	}
	public void setRepayTypeId(Integer repayTypeId) {
		this.repayTypeId = repayTypeId;
	}
	public java.util.Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(java.util.Date valueDate) {
		this.valueDate = valueDate;
	}
	public java.util.Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.util.Date expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getInterestBaseType() {
		return interestBaseType;
	}
	public void setInterestBaseType(Integer interestBaseType) {
		this.interestBaseType = interestBaseType;
	}
	public Integer getInterestBaseDays() {
		return interestBaseDays;
	}
	public void setInterestBaseDays(Integer interestBaseDays) {
		this.interestBaseDays = interestBaseDays;
	}
	public String getProjectUsing() {
		return projectUsing;
	}
	public void setProjectUsing(String projectUsing) {
		this.projectUsing = projectUsing;
	}
	public Integer getReceiveAccountId() {
		return receiveAccountId;
	}
	public void setReceiveAccountId(Integer receiveAccountId) {
		this.receiveAccountId = receiveAccountId;
	}
	public java.util.Date getBuyTimeStart() {
		return buyTimeStart;
	}
	public void setBuyTimeStart(java.util.Date buyTimeStart) {
		this.buyTimeStart = buyTimeStart;
	}
	public java.util.Date getBuyTimeEnd() {
		return buyTimeEnd;
	}
	public void setBuyTimeEnd(java.util.Date buyTimeEnd) {
		this.buyTimeEnd = buyTimeEnd;
	}
	public java.math.BigDecimal getInvestAmountMin() {
		return investAmountMin;
	}
	public void setInvestAmountMin(java.math.BigDecimal investAmountMin) {
		this.investAmountMin = investAmountMin;
	}
	public java.math.BigDecimal getInvestAmountMax() {
		return investAmountMax;
	}
	public void setInvestAmountMax(java.math.BigDecimal investAmountMax) {
		this.investAmountMax = investAmountMax;
	}
	public java.math.BigDecimal getInvestAmountAppend() {
		return investAmountAppend;
	}
	public void setInvestAmountAppend(java.math.BigDecimal investAmountAppend) {
		this.investAmountAppend = investAmountAppend;
	}
	public java.math.BigDecimal getInvestProfit() {
		return investProfit;
	}
	public void setInvestProfit(java.math.BigDecimal investProfit) {
		this.investProfit = investProfit;
	}
	public Integer getIsGuarantee() {
		return isGuarantee;
	}
	public void setIsGuarantee(Integer isGuarantee) {
		this.isGuarantee = isGuarantee;
	}
	public Integer getGuaranteeUserId() {
		return guaranteeUserId;
	}
	public void setGuaranteeUserId(Integer guaranteeUserId) {
		this.guaranteeUserId = guaranteeUserId;
	}
	public Integer getIsPledge() {
		return isPledge;
	}
	public void setIsPledge(Integer isPledge) {
		this.isPledge = isPledge;
	}
	public Integer getIsInsurance() {
		return isInsurance;
	}
	public void setIsInsurance(Integer isInsurance) {
		this.isInsurance = isInsurance;
	}
	public java.math.BigDecimal getAdvioserFee() {
		return advioserFee;
	}
	public void setAdvioserFee(java.math.BigDecimal advioserFee) {
		this.advioserFee = advioserFee;
	}
	public java.math.BigDecimal getPlatformFee() {
		return platformFee;
	}
	public void setPlatformFee(java.math.BigDecimal platformFee) {
		this.platformFee = platformFee;
	}
	public java.math.BigDecimal getOverduePayFee() {
		return overduePayFee;
	}
	public void setOverduePayFee(java.math.BigDecimal overduePayFee) {
		this.overduePayFee = overduePayFee;
	}
	public String getOverdueDesc() {
		return overdueDesc;
	}
	public void setOverdueDesc(String overdueDesc) {
		this.overdueDesc = overdueDesc;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateOperatorId() {
		return createOperatorId;
	}
	public void setCreateOperatorId(Integer createOperatorId) {
		this.createOperatorId = createOperatorId;
	}
	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getUpateOperatorId() {
		return upateOperatorId;
	}
	public void setUpateOperatorId(Integer upateOperatorId) {
		this.upateOperatorId = upateOperatorId;
	}
	public java.util.Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
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
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}
	public java.math.BigDecimal getGuaranteeMoney() {
		return guaranteeMoney;
	}
	public void setGuaranteeMoney(java.math.BigDecimal guaranteeMoney) {
		this.guaranteeMoney = guaranteeMoney;
	}
	public Integer getPaySettleDay() {
		return paySettleDay;
	}
	public void setPaySettleDay(Integer paySettleDay) {
		this.paySettleDay = paySettleDay;
	}
	public Integer getSettleTypeId() {
		return settleTypeId;
	}
	public void setSettleTypeId(Integer settleTypeId) {
		this.settleTypeId = settleTypeId;
	}
	public java.math.BigDecimal getInvestAdvioserFee() {
		return investAdvioserFee;
	}
	public void setInvestAdvioserFee(java.math.BigDecimal investAdvioserFee) {
		this.investAdvioserFee = investAdvioserFee;
	}
	public Integer getTransferType() {
		return transferType;
	}
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	public Integer getPayclearId() {
		return payclearId;
	}
	public void setPayclearId(Integer payclearId) {
		this.payclearId = payclearId;
	}
	public Integer getRepayPeriodDay() {
		return repayPeriodDay;
	}
	public void setRepayPeriodDay(Integer repayPeriodDay) {
		this.repayPeriodDay = repayPeriodDay;
	}
	public Integer getSettleInvestDay() {
		return settleInvestDay;
	}
	public void setSettleInvestDay(Integer settleInvestDay) {
		this.settleInvestDay = settleInvestDay;
	}
	public Integer getProjectLimitTypeId() {
		return projectLimitTypeId;
	}
	public void setProjectLimitTypeId(Integer projectLimitTypeId) {
		this.projectLimitTypeId = projectLimitTypeId;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getGuaranteeValueType() {
		return guaranteeValueType;
	}
	public void setGuaranteeValueType(Integer guaranteeValueType) {
		this.guaranteeValueType = guaranteeValueType;
	}
	public Integer getPeriodNum() {
		return periodNum;
	}
	public void setPeriodNum(Integer periodNum) {
		this.periodNum = periodNum;
	}
	public Integer getTradeType() {
		return tradeType;
	}
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}
	public Integer getProjectRecordId() {
		return projectRecordId;
	}
	public void setProjectRecordId(Integer projectRecordId) {
		this.projectRecordId = projectRecordId;
	}
	public Integer getInvestSettleTypeId() {
		return investSettleTypeId;
	}
	public void setInvestSettleTypeId(Integer investSettleTypeId) {
		this.investSettleTypeId = investSettleTypeId;
	}
	public Integer getCanTransfer() {
		return canTransfer;
	}
	public void setCanTransfer(Integer canTransfer) {
		this.canTransfer = canTransfer;
	}
	public Integer getTransferAfter() {
		return transferAfter;
	}
	public void setTransferAfter(Integer transferAfter) {
		this.transferAfter = transferAfter;
	}
	public Integer getTransferLimit() {
		return transferLimit;
	}
	public void setTransferLimit(Integer transferLimit) {
		this.transferLimit = transferLimit;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public Integer getManageDuration() {
		return manageDuration;
	}
	public void setManageDuration(Integer manageDuration) {
		this.manageDuration = manageDuration;
	}
	public Integer getValueDateChangeStyle() {
		return valueDateChangeStyle;
	}
	public void setValueDateChangeStyle(Integer valueDateChangeStyle) {
		this.valueDateChangeStyle = valueDateChangeStyle;
	}
	public Integer getExpireDateChangeStyle() {
		return expireDateChangeStyle;
	}
	public void setExpireDateChangeStyle(Integer expireDateChangeStyle) {
		this.expireDateChangeStyle = expireDateChangeStyle;
	}
	public String getTradePlatform() {
		return tradePlatform;
	}
	public void setTradePlatform(String tradePlatform) {
		this.tradePlatform = tradePlatform;
	}
	public Integer getReceiveUserType() {
		return receiveUserType;
	}
	public void setReceiveUserType(Integer receiveUserType) {
		this.receiveUserType = receiveUserType;
	}
	public int getOriginType() {
		return originType;
	}
	public void setOriginType(int originType) {
		this.originType = originType;
	}
	public String getOriginatorId() {
		return originatorId;
	}
	public void setOriginatorId(String originatorId) {
		this.originatorId = originatorId;
	}
	public Integer getPayinvestType() {
		return payinvestType;
	}
	public void setPayinvestType(Integer payinvestType) {
		this.payinvestType = payinvestType;
	}
	public Integer getPayinvestId() {
		return payinvestId;
	}
	public void setPayinvestId(Integer payinvestId) {
		this.payinvestId = payinvestId;
	}
	public Integer getPayinvestBankCardId() {
		return payinvestBankCardId;
	}
	public void setPayinvestBankCardId(Integer payinvestBankCardId) {
		this.payinvestBankCardId = payinvestBankCardId;
	}
	public Integer getGeneratePlan() {
		return generatePlan;
	}
	public void setGeneratePlan(Integer generatePlan) {
		this.generatePlan = generatePlan;
	}
	public String getPartnerBizId() {
		return partnerBizId;
	}
	public void setPartnerBizId(String partnerBizId) {
		this.partnerBizId = partnerBizId;
	}
	public Integer getPlatformFeeType() {
		return platformFeeType;
	}
	public void setPlatformFeeType(Integer platformFeeType) {
		this.platformFeeType = platformFeeType;
	}
	public Integer getInterestMode() {
		return interestMode;
	}
	public void setInterestMode(Integer interestMode) {
		this.interestMode = interestMode;
	}
	public Integer getSaleagentId() {
		return saleagentId;
	}
	public void setSaleagentId(Integer saleagentId) {
		this.saleagentId = saleagentId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
	public BigDecimal getSaleFeeRate() {
		return saleFeeRate;
	}
	public void setSaleFeeRate(BigDecimal saleFeeRate) {
		this.saleFeeRate = saleFeeRate;
	}
	public BigDecimal getSaleReceivedMoney() {
		return saleReceivedMoney;
	}
	public void setSaleReceivedMoney(BigDecimal saleReceivedMoney) {
		this.saleReceivedMoney = saleReceivedMoney;
	}
	public BigDecimal getSaleCfmRightMoney() {
		return saleCfmRightMoney;
	}
	public void setSaleCfmRightMoney(BigDecimal saleCfmRightMoney) {
		this.saleCfmRightMoney = saleCfmRightMoney;
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
	public String getTradeTerm() {
		return tradeTerm;
	}
	public void setTradeTerm(String tradeTerm) {
		this.tradeTerm = tradeTerm;
	}
	public String getRepayTypeName() {
		return repayTypeName;
	}
	public void setRepayTypeName(String repayTypeName) {
		this.repayTypeName = repayTypeName;
	}
	public Integer getCfmright() {
		return cfmright;
	}
	public void setCfmright(Integer cfmright) {
		this.cfmright = cfmright;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public BigDecimal getCollectMoney() {
		return collectMoney;
	}
	public void setCollectMoney(BigDecimal collectMoney) {
		this.collectMoney = collectMoney;
	}
	public BigDecimal getCfmRightMoney() {
		return cfmRightMoney;
	}
	public void setCfmRightMoney(BigDecimal cfmRightMoney) {
		this.cfmRightMoney = cfmRightMoney;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getQuotedMaxMoney() {
		return quotedMaxMoney;
	}
	public void setQuotedMaxMoney(BigDecimal quotedMaxMoney) {
		this.quotedMaxMoney = quotedMaxMoney;
	}
	public int getQuotedCnt() {
		return quotedCnt;
	}
	public void setQuotedCnt(int quotedCnt) {
		this.quotedCnt = quotedCnt;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public Integer getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(Integer settleStatus) {
		this.settleStatus = settleStatus;
	}
	public Integer getBreakPayStatus() {
		return breakPayStatus;
	}
	public void setBreakPayStatus(Integer breakPayStatus) {
		this.breakPayStatus = breakPayStatus;
	}
	public Integer getBuyCnt() {
		return buyCnt;
	}
	public void setBuyCnt(Integer buyCnt) {
		this.buyCnt = buyCnt;
	}
	public String getProjectTypeName() {
		return projectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
	public String getRecordGuid() {
		return recordGuid;
	}
	public void setRecordGuid(String recordGuid) {
		this.recordGuid = recordGuid;
	}
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	public String getRecordName() {
		return recordName;
	}
	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}
	public String getTransferorBasicNote() {
		return transferorBasicNote;
	}
	public void setTransferorBasicNote(String transferorBasicNote) {
		this.transferorBasicNote = transferorBasicNote;
	}
	public Integer getTransfereeUserId() {
		return transfereeUserId;
	}
	public void setTransfereeUserId(Integer transfereeUserId) {
		this.transfereeUserId = transfereeUserId;
	}
	public Integer getSettleInvestMonth() {
		return settleInvestMonth;
	}
	public void setSettleInvestMonth(Integer settleInvestMonth) {
		this.settleInvestMonth = settleInvestMonth;
	}
	public List<ListingSaleagent> getProjectSaleagent() {
		return projectSaleagent;
	}
	public void setProjectSaleagent(List<ListingSaleagent> projectSaleagent) {
		this.projectSaleagent = projectSaleagent;
	}
	public boolean canEdit(){
		return this.projectStatus.intValue() == ListingStatusDesc.待提交.value 
				|| this.projectStatus.intValue() == ListingStatusDesc.审核退回.value;
	}
	
	public boolean canDelete() {
		return this.projectStatus.intValue() == ListingStatusDesc.待提交.value 
				|| this.projectStatus.intValue() == ListingStatusDesc.审核退回.value
				|| this.projectStatus.intValue() == ListingStatusDesc.审核不通过.value;
	}
	public boolean canCheck(){
		return this.projectStatus.intValue() == ListingStatusDesc.待审核.value;
	}
	public boolean canStartBuy() {
		return this.projectStatus.intValue() == ListingStatusDesc.待发布.value 
				|| this.projectStatus.intValue() == ListingStatusDesc.已发布.value;
	}
	public boolean canEndBuy() {
		return this.projectStatus.intValue() == ListingStatusDesc.认购中.value;
	}
	public Integer getSettleInvestDayShow() {
		return this.settleInvestDay == null ? null : Integer
				.valueOf(this.settleInvestDay.intValue() % 100);
	}
	public Integer getSettleInvestMonthShow() {
		return this.settleInvestDay == null ? null : Integer
				.valueOf(this.settleInvestDay.intValue() / 100);
	}
	public String getProjectLimitTypeDesc() {
		return ProjectLimitType.getProjectLimitType(this.getProjectLimitTypeId()).name();
	}
	public String settleName() {
		if(this.settleTypeId == null)
			return null;
		return SettleTypeDesc.getDesc(this.settleTypeId);
	}
	
	public String investSettleName() {
		if(this.investSettleTypeId == null)
			return null;
		return SettleTypeDesc.getDesc(this.investSettleTypeId);
	}
	public Integer getReleaseNum() {
		return releaseNum;
	}
	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}	
	
}
