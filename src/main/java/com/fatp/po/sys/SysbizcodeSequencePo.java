package com.fatp.po.sys;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SysbizcodeSequence
 */
public class SysbizcodeSequencePo {
	/**
	 * 自增id
	 */
	private Integer id;
	/**
	 * 编码序列名称
	 */
	private String codeSeqName;
	/**
	 * 序列名描述
	 */
	private String codeDescName;
	/**
	 * 编码前缀
	 */
	private String codePrefix;
	/**
	 * 编码总长度
	 */
	private Integer codeLength;
	/**
	 * 编码含义描述
	 */
	private String remark;
	/**
	 * 编码序列旧名
	 */
	private String codeSeqOldName;
	/**
	 * 日期 yyyyMMdd
	 */
	private Integer codeSeqDate;
	/**
	 * 当前值
	 */
	private Integer currentVal;
	/**
	 * 增量值
	 */
	private Integer incrementVal;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCodeSeqName(String value) {
		this.codeSeqName = value;
	}
	public String getCodeSeqName() {
		return this.codeSeqName;
	}
	public void setCodeDescName(String value) {
		this.codeDescName = value;
	}
	public String getCodeDescName() {
		return this.codeDescName;
	}
	public void setCodePrefix(String value) {
		this.codePrefix = value;
	}
	public String getCodePrefix() {
		return this.codePrefix;
	}
	public void setCodeLength(Integer value) {
		this.codeLength = value;
	}
	public Integer getCodeLength() {
		return this.codeLength;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setCodeSeqOldName(String value) {
		this.codeSeqOldName = value;
	}
	public String getCodeSeqOldName() {
		return this.codeSeqOldName;
	}
	public void setCodeSeqDate(Integer value) {
		this.codeSeqDate = value;
	}
	public Integer getCodeSeqDate() {
		return this.codeSeqDate;
	}
	public void setCurrentVal(Integer value) {
		this.currentVal = value;
	}
	public Integer getCurrentVal() {
		return this.currentVal;
	}
	public void setIncrementVal(Integer value) {
		this.incrementVal = value;
	}
	public Integer getIncrementVal() {
		return this.incrementVal;
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

