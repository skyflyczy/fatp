package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SysBank
 */
public class SysBankPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 银行id
	 */
	private Integer id;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行简称
	 */
	private String bankSname;
	/**
	 * 银行全称
	 */
	private String bankFullname;
	/**
	 * 银行代号
	 */
	private String bankNo;
	/**
	 * 银行客户电话
	 */
	private String bankTel;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 创建人
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 是否启用 1=启用 0=停用
	 */
	private Integer useEnabled;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setBankName(String value) {
		this.bankName = value;
	}
	public String getBankName() {
		return this.bankName;
	}
	public void setBankSname(String value) {
		this.bankSname = value;
	}
	public String getBankSname() {
		return this.bankSname;
	}
	public void setBankFullname(String value) {
		this.bankFullname = value;
	}
	public String getBankFullname() {
		return this.bankFullname;
	}
	public void setBankNo(String value) {
		this.bankNo = value;
	}
	public String getBankNo() {
		return this.bankNo;
	}
	public void setBankTel(String value) {
		this.bankTel = value;
	}
	public String getBankTel() {
		return this.bankTel;
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
	public void setUseEnabled(Integer value) {
		this.useEnabled = value;
	}
	public Integer getUseEnabled() {
		return this.useEnabled;
	}

}

