package com.telecwin.fatp.po.project;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ProjectContent
 */
public class ProjectContentPo {
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 产品id,project_recordinfo.id
	 */
	private Integer projectId;
	/**
	 * 基础资产说明
	 */
	private String basicAssetNote;
	/**
	 * 产品详情
	 */
	private String projectInfo;
	/**
	 * 交易对手方资质
	 */
	private String tradePartyQualification;
	/**
	 * 交易条款
	 */
	private String tradeTerm;
	/**
	 * 转让方基本情况
	 */
	private String transferorBasicNote;
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
	public void setProjectId(Integer value) {
		this.projectId = value;
	}
	public Integer getProjectId() {
		return this.projectId;
	}
	public void setBasicAssetNote(String value) {
		this.basicAssetNote = value;
	}
	public String getBasicAssetNote() {
		return this.basicAssetNote;
	}
	public void setProjectInfo(String value) {
		this.projectInfo = value;
	}
	public String getProjectInfo() {
		return this.projectInfo;
	}
	public void setTradePartyQualification(String value) {
		this.tradePartyQualification = value;
	}
	public String getTradePartyQualification() {
		return this.tradePartyQualification;
	}
	public void setTradeTerm(String value) {
		this.tradeTerm = value;
	}
	public String getTradeTerm() {
		return this.tradeTerm;
	}
	public void setTransferorBasicNote(String value) {
		this.transferorBasicNote = value;
	}
	public String getTransferorBasicNote() {
		return this.transferorBasicNote;
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

