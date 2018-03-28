package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SysBankSerialno
 */
public class SysBankSerialnoPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所属银行 sys_bank.id
	 */
	private Integer bankId;
	/**
	 * 分(支)行联行号
	 */
	private String subBankNo;
	/**
	 * 分(支)行名称
	 */
	private String subBankName;
	/**
	 * 所属直辖市省份id sysarea_province.id
	 */
	private Integer provinceId;
	/**
	 * 所属城市id sysarea_district.id
	 */
	private Integer cityId;
	/**
	 * 所属区/县id sysarea_city.id
	 */
	private Integer districtId;
	/**
	 * 是否启用 1=启用 0=停用
	 */
	private Integer useEnabled;
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
	/**
	 * 原始分支行名称
	 */
	private String originBankName;
	/**
	 * 是否对外公布 1=公布 0=隐藏
	 */
	private Integer isOpen;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setBankId(Integer value) {
		this.bankId = value;
	}
	public Integer getBankId() {
		return this.bankId;
	}
	public void setSubBankNo(String value) {
		this.subBankNo = value;
	}
	public String getSubBankNo() {
		return this.subBankNo;
	}
	public void setSubBankName(String value) {
		this.subBankName = value;
	}
	public String getSubBankName() {
		return this.subBankName;
	}
	public void setProvinceId(Integer value) {
		this.provinceId = value;
	}
	public Integer getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setDistrictId(Integer value) {
		this.districtId = value;
	}
	public Integer getDistrictId() {
		return this.districtId;
	}
	public void setUseEnabled(Integer value) {
		this.useEnabled = value;
	}
	public Integer getUseEnabled() {
		return this.useEnabled;
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
	public void setOriginBankName(String value) {
		this.originBankName = value;
	}
	public String getOriginBankName() {
		return this.originBankName;
	}
	public void setIsOpen(Integer value) {
		this.isOpen = value;
	}
	public Integer getIsOpen() {
		return this.isOpen;
	}

}

