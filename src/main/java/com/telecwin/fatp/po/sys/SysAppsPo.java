package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysApps
 */
public class SysAppsPo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 应用en名称
	 */
	private String appKey;
	/**
	 * 应用zn名称
	 */
	private String appName;
	/**
	 * 是否需要管理菜单 不需要=0 需要=1,需要的在管理平台维护菜单
	 */
	private Integer hasAdminMenu;
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
	public void setAppKey(String value) {
		this.appKey = value;
	}
	public String getAppKey() {
		return this.appKey;
	}
	public void setAppName(String value) {
		this.appName = value;
	}
	public String getAppName() {
		return this.appName;
	}
	public void setHasAdminMenu(Integer value) {
		this.hasAdminMenu = value;
	}
	public Integer getHasAdminMenu() {
		return this.hasAdminMenu;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}

