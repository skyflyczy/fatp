package com.telecwin.fatp.po.project;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ListingSaleagent
 */
public class ListingSaleagentPo {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 产品id,project_baseinfo.id
	 */
	private Integer projectId;
	/**
	 * 承销会员id,uc_user.id
	 */
	private Integer saleMemberId;
	/**
	 * 承销类型Id,systype_base.CategoryId=2的typeid
	 */
	private Integer saleTypeId;
	/**
	 * 承销费率
	 */
	private java.math.BigDecimal saleFeeRate;
	/**
	 * 承销份额，单位份
	 */
	private java.math.BigDecimal saleAmount;
	/**
	 * 承销金额，单位元
	 */
	private java.math.BigDecimal saleMoney;
	/**
	 * 产品所属会员id,uc_user.id,冗余字段
	 */
	private Integer memberId;
	/**
	 * 产品所属交易所id,fe_exchange.id,冗余字段
	 */
	private Integer exchangeId;
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
	private Integer updateOperatorId;

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
	public void setSaleMemberId(Integer value) {
		this.saleMemberId = value;
	}
	public Integer getSaleMemberId() {
		return this.saleMemberId;
	}
	public void setSaleTypeId(Integer value) {
		this.saleTypeId = value;
	}
	public Integer getSaleTypeId() {
		return this.saleTypeId;
	}
	public void setSaleFeeRate(java.math.BigDecimal value) {
		this.saleFeeRate = value;
	}
	public java.math.BigDecimal getSaleFeeRate() {
		return this.saleFeeRate;
	}
	public void setSaleAmount(java.math.BigDecimal value) {
		this.saleAmount = value;
	}
	public java.math.BigDecimal getSaleAmount() {
		return this.saleAmount;
	}
	public void setSaleMoney(java.math.BigDecimal value) {
		this.saleMoney = value;
	}
	public java.math.BigDecimal getSaleMoney() {
		return this.saleMoney;
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

}

