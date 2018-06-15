package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysParam
 */
public class SysParamPo implements Serializable {
	
	private static final long serialVersionUID = 3910433031760257420L;
	/**
	 * 自动增长id
	 */
	private Integer id;
	/**
	 * 参考key
	 */
	private String paramKey;
	/**
	 * 参数名称
	 */
	private String paramName;
	/**
	 * 参数值
	 */
	private String paramValue;
	/**
	 * 显示顺序
	 */
	private Integer showIndex;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 参数值可显示 1=可显示 0=不可显示
	 */
	private Integer valueCanView;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setParamKey(String value) {
		this.paramKey = value;
	}
	public String getParamKey() {
		return this.paramKey;
	}
	public void setParamName(String value) {
		this.paramName = value;
	}
	public String getParamName() {
		return this.paramName;
	}
	public void setParamValue(String value) {
		this.paramValue = value;
	}
	public String getParamValue() {
		return this.paramValue;
	}
	public void setShowIndex(Integer value) {
		this.showIndex = value;
	}
	public Integer getShowIndex() {
		return this.showIndex;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setValueCanView(Integer value) {
		this.valueCanView = value;
	}
	public Integer getValueCanView() {
		return this.valueCanView;
	}

}

