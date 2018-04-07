package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SystypeRepay
 * @author peng.liu
 * 2016-08-10 33:10:36
 */
public class SystypeRepayPo implements Serializable {
	/**
	 * @author peng.liu
	 * 2016年8月11日 上午9:55:33
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 还款方式类型
	 */
	private String repayTypeName;
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
	public void setRepayTypeName(String value) {
		this.repayTypeName = value;
	}
	public String getRepayTypeName() {
		return this.repayTypeName;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}

