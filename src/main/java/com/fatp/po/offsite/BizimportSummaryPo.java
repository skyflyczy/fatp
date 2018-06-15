package com.fatp.po.offsite;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * BizimportSummary
 * @author zhiya.chai
 * 2018-06-14 21:16:45
 */
public class BizimportSummaryPo {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 全局文件表id, global_files.id
	 */
	private Integer globalFileId;
	/**
	 * 业务登记申请表id, bizimport_apply.id
	 */
	private Integer bizImportApplyId;
	/**
	 * 文件中最早的交易日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date startDate;
	/**
	 * 文件中最晚的交易日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date endDate;
	/**
	 * 总金额（元）
	 */
	private java.math.BigDecimal totalMoney;
	/**
	 * 卖方总人数
	 */
	private Integer sellerNum;
	/**
	 * 买方总人数
	 */
	private Integer buyerNum;
	/**
	 * 记录总数
	 */
	private Integer totalNum;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 创建操作员
	 */
	private Integer createOperatorId;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setGlobalFileId(Integer value) {
		this.globalFileId = value;
	}
	public Integer getGlobalFileId() {
		return this.globalFileId;
	}
	public void setBizImportApplyId(Integer value) {
		this.bizImportApplyId = value;
	}
	public Integer getBizImportApplyId() {
		return this.bizImportApplyId;
	}
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	public void setEndDate(java.util.Date value) {
		this.endDate = value;
	}
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	public void setTotalMoney(java.math.BigDecimal value) {
		this.totalMoney = value;
	}
	public java.math.BigDecimal getTotalMoney() {
		return this.totalMoney;
	}
	public void setSellerNum(Integer value) {
		this.sellerNum = value;
	}
	public Integer getSellerNum() {
		return this.sellerNum;
	}
	public void setBuyerNum(Integer value) {
		this.buyerNum = value;
	}
	public Integer getBuyerNum() {
		return this.buyerNum;
	}
	public void setTotalNum(Integer value) {
		this.totalNum = value;
	}
	public Integer getTotalNum() {
		return this.totalNum;
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

}

