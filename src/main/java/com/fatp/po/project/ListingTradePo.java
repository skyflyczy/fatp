package com.fatp.po.project;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ListingTrade
 * @author zhiya.chai
 * 2018-06-13 56:10:38
 */
public class ListingTradePo implements Serializable{
	private static final long serialVersionUID = 7605280404788028771L;
	/**
	 * 唯一id，自增主键
	 */
	private Integer id;
	/**
	 * 挂牌产品Id,listing_info.id
	 */
	private Integer listingInfoId;
	/**
	 * 最低投资金额，元（包含此值）
	 */
	private java.math.BigDecimal minInvestMoney;
	/**
	 * 最高投资金额，元（不包含此值）
	 */
	private java.math.BigDecimal maxInvestMoney;
	/**
	 * 拟定年化收益率,没有乘以%
	 */
	private java.math.BigDecimal investProfit;
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
	public void setMinInvestMoney(java.math.BigDecimal value) {
		this.minInvestMoney = value;
	}
	public java.math.BigDecimal getMinInvestMoney() {
		return this.minInvestMoney;
	}
	public void setMaxInvestMoney(java.math.BigDecimal value) {
		this.maxInvestMoney = value;
	}
	public java.math.BigDecimal getMaxInvestMoney() {
		return this.maxInvestMoney;
	}
	public void setInvestProfit(java.math.BigDecimal value) {
		this.investProfit = value;
	}
	public java.math.BigDecimal getInvestProfit() {
		return this.investProfit;
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
	public Integer getListingInfoId() {
		return listingInfoId;
	}
	public void setListingInfoId(Integer listingInfoId) {
		this.listingInfoId = listingInfoId;
	}

}

