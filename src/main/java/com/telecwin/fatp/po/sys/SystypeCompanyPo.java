package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SystypeCompany
 */
public class SystypeCompanyPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id,id
	 */
	private Integer id;
	/**
	 * 公司类型名称
	 */
	private String companyTypeName;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCompanyTypeName(String value) {
		this.companyTypeName = value;
	}
	public String getCompanyTypeName() {
		return this.companyTypeName;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}

