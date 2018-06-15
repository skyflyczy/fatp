package com.fatp.po.sys;

import java.io.Serializable;


/**
 * SysareaProvince
 */
public class SysareaProvincePo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 省份id
	 */
	private Integer proId;
	/**
	 * 省份名称
	 */
	private String proName;
	/**
	 * 排序
	 */
	private Integer proSort;
	/**
	 * 备注
	 */
	private String proRemark;

	public void setProId(Integer value) {
		this.proId = value;
	}
	public Integer getProId() {
		return this.proId;
	}
	public void setProName(String value) {
		this.proName = value;
	}
	public String getProName() {
		return this.proName;
	}
	public void setProSort(Integer value) {
		this.proSort = value;
	}
	public Integer getProSort() {
		return this.proSort;
	}
	public void setProRemark(String value) {
		this.proRemark = value;
	}
	public String getProRemark() {
		return this.proRemark;
	}

}

