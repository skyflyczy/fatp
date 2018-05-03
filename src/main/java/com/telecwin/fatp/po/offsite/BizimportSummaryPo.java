package com.telecwin.fatp.po.offsite;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * BizimportSummary
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
	 * 开始日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date startDate;
	/**
	 * 结束日期
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
	 * 总本金(元)
	 */
	private BigDecimal totalPrincipal;
	
	/**
	 * 参与总人数
	 */
	private int totalPerson;
	
	/**
	 * 兑付总期数
	 */
	private int periodNumber;
	
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	
	private String operatorName;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date submitTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date auditTime;

	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
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
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public BigDecimal getTotalPrincipal() {
		return totalPrincipal;
	}
	public void setTotalPrincipal(BigDecimal totalPrincipal) {
		this.totalPrincipal = totalPrincipal;
	}
	public int getTotalPerson() {
		return totalPerson;
	}
	public void setTotalPerson(int totalPerson) {
		this.totalPerson = totalPerson;
	}
	public int getPeriodNumber() {
		return periodNumber;
	}
	public void setPeriodNumber(int periodNumber) {
		this.periodNumber = periodNumber;
	}

}

