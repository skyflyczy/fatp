package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SystypeBase
 */
public class SystypeBasePo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 分类id systype_base_category.id
	 */
	private Integer categoryId;
	/**
	 * 类型id
	 */
	private Integer typeId;
	/**
	 * 类型名称
	 */
	private String typeName;
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
	 * 更新人
	 */
	private Integer updateOperatorId;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCategoryId(Integer value) {
		this.categoryId = value;
	}
	public Integer getCategoryId() {
		return this.categoryId;
	}
	public void setTypeId(Integer value) {
		this.typeId = value;
	}
	public Integer getTypeId() {
		return this.typeId;
	}
	public void setTypeName(String value) {
		this.typeName = value;
	}
	public String getTypeName() {
		return this.typeName;
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

