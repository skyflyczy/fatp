package com.telecwin.fatp.po.project;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ListingClearing
 * @author zhiya.chai
 * 2017-06-21 04:10:14
 */
public class ListingClearingPo {
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 挂牌产品Id
	 */
	private Integer projectId;
	/**
	 * 产品资金归集结算渠道id, sys_bankpaycenter.id
	 */
	private Integer payclearId;
	/**
	 * 结算方式,场内结算=1 场外结算=2 (场内资金走交易所，场外不走交易所)
	 */
	private Integer settleTypeId;
	/**
	 * 收款方类型 1-发行人,2-产品管理人
	 */
	private Integer receiveUserType;
	/**
	 * 收款账户id uc_userbankcard.id
	 */
	private Integer receiveAccountId;
	/**
	 * 融资顾问费率，没有乘以%
	 */
	private java.math.BigDecimal advioserFee;
	/**
	 * 投资顾问费率,没有乘以%
	 */
	private java.math.BigDecimal investAdvioserFee;
	/**
	 * 平台管理费率,没有乘以%
	 */
	private java.math.BigDecimal platformFee;
	/**
	 * 平台费率收费方式，1-按次，2-按年化
	 */
	private Integer platformFeeType;
	/**
	 * 逾期还款罚息比例,没有乘以%，按天罚未还本金得到罚息(new)
	 */
	private java.math.BigDecimal overduePayFee;
	/**
	 * 还款方式
	 */
	private Integer repayTypeId;
	/**
	 * 是否管理存续期，即资金是否过交易所 1-经过；0-不经过
	 */
	private Integer manageDuration;
	/**
	 * 是否生成兑付还款计划  0不生成计划，1生成计划
	 */
	private Integer generatePlan;
	/**
	 * 还款期限天数，提前N工作日还款
	 */
	private Integer repayPeriodDay;
	/**
	 * 结息日
	 */
	private Integer settleInvestDay;
	/**
	 * 兑付方式 1兑付给投资人，2兑付给中间方
	 */
	private Integer payinvestType;
	/**
	 * 兑付方uc_user.Id,默认为Null
	 */
	private Integer payinvestId;
	/**
	 * 兑付方银行卡号 uc_user_bankcard.Id,默认为Null
	 */
	private Integer payinvestBankCardId;
	/**
	 * 还款兑付结算方式：1-场内, 2-场外
	 */
	private Integer investSettleTypeId;
	/**
	 * 计息方式：详见systype_base, CategoryId=10
	 */
	private Integer interestMode;
	/**
	 * 计息基准类型, 0:自定义天数，1:365/366
	 */
	private Integer interestBaseType;
	/**
	 * 计息基准天数
	 */
	private Integer interestBaseDays;
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
	 * 最多放款次数
	 */
	private Integer releaseNum;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setProjectId(Integer value) {
		this.projectId = value;
	}
	public Integer getProjectId() {
		return this.projectId;
	}
	public void setPayclearId(Integer value) {
		this.payclearId = value;
	}
	public Integer getPayclearId() {
		return this.payclearId;
	}
	public void setSettleTypeId(Integer value) {
		this.settleTypeId = value;
	}
	public Integer getSettleTypeId() {
		return this.settleTypeId;
	}
	public void setReceiveUserType(Integer value) {
		this.receiveUserType = value;
	}
	public Integer getReceiveUserType() {
		return this.receiveUserType;
	}
	public void setReceiveAccountId(Integer value) {
		this.receiveAccountId = value;
	}
	public Integer getReceiveAccountId() {
		return this.receiveAccountId;
	}
	public void setAdvioserFee(java.math.BigDecimal value) {
		this.advioserFee = value;
	}
	public java.math.BigDecimal getAdvioserFee() {
		return this.advioserFee;
	}
	public void setInvestAdvioserFee(java.math.BigDecimal value) {
		this.investAdvioserFee = value;
	}
	public java.math.BigDecimal getInvestAdvioserFee() {
		return this.investAdvioserFee;
	}
	public void setPlatformFee(java.math.BigDecimal value) {
		this.platformFee = value;
	}
	public java.math.BigDecimal getPlatformFee() {
		return this.platformFee;
	}
	public void setPlatformFeeType(Integer value) {
		this.platformFeeType = value;
	}
	public Integer getPlatformFeeType() {
		return this.platformFeeType;
	}
	public void setOverduePayFee(java.math.BigDecimal value) {
		this.overduePayFee = value;
	}
	public java.math.BigDecimal getOverduePayFee() {
		return this.overduePayFee;
	}
	public void setRepayTypeId(Integer value) {
		this.repayTypeId = value;
	}
	public Integer getRepayTypeId() {
		return this.repayTypeId;
	}
	public void setManageDuration(Integer value) {
		this.manageDuration = value;
	}
	public Integer getManageDuration() {
		return this.manageDuration;
	}
	public void setGeneratePlan(Integer value) {
		this.generatePlan = value;
	}
	public Integer getGeneratePlan() {
		return this.generatePlan;
	}
	public void setRepayPeriodDay(Integer value) {
		this.repayPeriodDay = value;
	}
	public Integer getRepayPeriodDay() {
		return this.repayPeriodDay;
	}
	public void setSettleInvestDay(Integer value) {
		this.settleInvestDay = value;
	}
	public Integer getSettleInvestDay() {
		return this.settleInvestDay;
	}
	public void setPayinvestType(Integer value) {
		this.payinvestType = value;
	}
	public Integer getPayinvestType() {
		return this.payinvestType;
	}
	public void setPayinvestId(Integer value) {
		this.payinvestId = value;
	}
	public Integer getPayinvestId() {
		return this.payinvestId;
	}
	public void setPayinvestBankCardId(Integer value) {
		this.payinvestBankCardId = value;
	}
	public Integer getPayinvestBankCardId() {
		return this.payinvestBankCardId;
	}
	public void setInvestSettleTypeId(Integer value) {
		this.investSettleTypeId = value;
	}
	public Integer getInvestSettleTypeId() {
		return this.investSettleTypeId;
	}
	public void setInterestMode(Integer value) {
		this.interestMode = value;
	}
	public Integer getInterestMode() {
		return this.interestMode;
	}
	public void setInterestBaseType(Integer value) {
		this.interestBaseType = value;
	}
	public Integer getInterestBaseType() {
		return this.interestBaseType;
	}
	public void setInterestBaseDays(Integer value) {
		this.interestBaseDays = value;
	}
	public Integer getInterestBaseDays() {
		return this.interestBaseDays;
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
	public Integer getReleaseNum() {
		return releaseNum;
	}
	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}
}

