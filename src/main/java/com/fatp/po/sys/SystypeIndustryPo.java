package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SystypeIndustry
 */
public class SystypeIndustryPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id,行业id
	 */
	private Integer id;
	/**
	 * 行业名称
	 */
	private String industryName;
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
	public void setIndustryName(String value) {
		this.industryName = value;
	}
	public String getIndustryName() {
		return this.industryName;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}

