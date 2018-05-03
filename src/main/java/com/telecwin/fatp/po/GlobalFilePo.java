package com.telecwin.fatp.po;

import java.io.Serializable;

/**
 * 
 * GlobalFile
 * 
 */
public class GlobalFilePo implements Serializable {
	
	private static final long serialVersionUID = -2268946230336345381L;

	/**
	 * 主键
	 */
	private int id;

	/**
	 * 系统生成的文件GUID
	 */
	private String fileGuid;

	/**
	 * 文件的 MIME Content-Type
	 */
	private String contentType;

	/**
	 * 文件存储路径，包含文件名，相对于系统参数FileBase定义的根路径
	 */
	private String filePath;

	/**
	 * 原始文件名
	 */
	private String originalFileName;

	/**
	 * 显示名称
	 */
	private String displayName;

	/**
	 * 创建者操作员id, member_operator.id
	 */
	private Integer createOperatorId;

	/**
	 * 创建者的用户id, 交易所为0, uc_user.id
	 */
	private Integer createUserId;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;

	public void setId(int value) {
		this.id = value;
	}

	public int getId() {
		return this.id;
	}

	public void setFileGuid(String value) {
		this.fileGuid = value;
	}

	public String getFileGuid() {
		return this.fileGuid;
	}

	public void setContentType(String value) {
		this.contentType = value;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setFilePath(String value) {
		this.filePath = value;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setOriginalFileName(String value) {
		this.originalFileName = value;
	}

	public String getOriginalFileName() {
		return this.originalFileName;
	}

	public void setDisplayName(String value) {
		this.displayName = value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setCreateOperatorId(Integer value) {
		this.createOperatorId = value;
	}

	public Integer getCreateOperatorId() {
		return this.createOperatorId;
	}

	public void setCreateUserId(Integer value) {
		this.createUserId = value;
	}

	public Integer getCreateUserId() {
		return this.createUserId;
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
