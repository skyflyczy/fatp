package com.fatp.po.sys;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysWorkdate
 * @author zhiya.chai
 * 2018-06-23 24:15:21
 */
public class SysWorkdatePo {
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date workDate;
	/**
	 * 是否工作日
	 */
	private Integer isWorked;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 星期几
	 */
	private String weekDayDesc;
	/**
	 * 备注
	 */
	private String workDesc;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setWorkDate(java.util.Date value) {
		this.workDate = value;
	}
	public java.util.Date getWorkDate() {
		return this.workDate;
	}
	public void setIsWorked(Integer value) {
		this.isWorked = value;
	}
	public Integer getIsWorked() {
		return this.isWorked;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setWeekDayDesc(String value) {
		this.weekDayDesc = value;
	}
	public String getWeekDayDesc() {
		return this.weekDayDesc;
	}
	public void setWorkDesc(String value) {
		this.workDesc = value;
	}
	public String getWorkDesc() {
		return this.workDesc;
	}

}

