package com.fatp.po.sys;

import java.io.Serializable;


/**
 * 
 * SysareaDistrict
 */
public class SysareaDistrictPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 区县id
	 */
	private Integer disId;
	/**
	 * 区县名称
	 */
	private String disName;
	/**
	 * 城市id
	 */
	private Integer cityId;
	/**
	 * 区县排序
	 */
	private Integer disSort;

	public void setDisId(Integer value) {
		this.disId = value;
	}
	public Integer getDisId() {
		return this.disId;
	}
	public void setDisName(String value) {
		this.disName = value;
	}
	public String getDisName() {
		return this.disName;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setDisSort(Integer value) {
		this.disSort = value;
	}
	public Integer getDisSort() {
		return this.disSort;
	}

}

