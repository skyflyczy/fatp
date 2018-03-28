package com.telecwin.fatp.po.sys;

import java.io.Serializable;


/**
 * 
 * SysareaCity
 */
public class SysareaCityPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 城市id
	 */
	private Integer cityId;
	/**
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 省份id
	 */
	private Integer proId;
	/**
	 * 城市排序
	 */
	private Integer citySort;

	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setCityName(String value) {
		this.cityName = value;
	}
	public String getCityName() {
		return this.cityName;
	}
	public void setProId(Integer value) {
		this.proId = value;
	}
	public Integer getProId() {
		return this.proId;
	}
	public void setCitySort(Integer value) {
		this.citySort = value;
	}
	public Integer getCitySort() {
		return this.citySort;
	}

}

