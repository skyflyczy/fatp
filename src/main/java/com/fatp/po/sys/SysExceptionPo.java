package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysException
 * @author peng.liu
 * 2016-08-10 33:10:35
 */
public class SysExceptionPo implements Serializable {
	/**
	 * @author peng.liu
	 * 2016年8月11日 上午9:55:32
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * exceptionType
	 */
	private String exceptionType;
	/**
	 * exceptionMsg
	 */
	private String exceptionMsg;
	/**
	 * exceptionCode
	 */
	private Integer exceptionCode;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * updateTime
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setExceptionType(String value) {
		this.exceptionType = value;
	}
	public String getExceptionType() {
		return this.exceptionType;
	}
	public void setExceptionMsg(String value) {
		this.exceptionMsg = value;
	}
	public String getExceptionMsg() {
		return this.exceptionMsg;
	}
	public void setExceptionCode(Integer value) {
		this.exceptionCode = value;
	}
	public Integer getExceptionCode() {
		return this.exceptionCode;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

}

