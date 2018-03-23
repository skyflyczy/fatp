package com.telecwin.fatp.po.project;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * ProjectFlowfeed
 */
public class ProjectFlowfeedPo {
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 产品id
	 */
	private Integer projectId;
	/**
	 * 会员id
	 */
	private Integer memberId;
	/**
	 * 流程动态类型,systype_projectflow.id
	 */
	private Integer flowFeedType;
	/**
	 * 流程动态意见
	 */
	private String flowFeedOpinion;
	/**
	 * 流程动态内容
	 */
	private String flowFeedText;
	/**
	 * 更新人
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 操作人
	 */
	private Integer createOperatorId;

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
	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setFlowFeedType(Integer value) {
		this.flowFeedType = value;
	}
	public Integer getFlowFeedType() {
		return this.flowFeedType;
	}
	public void setFlowFeedOpinion(String value) {
		this.flowFeedOpinion = value;
	}
	public String getFlowFeedOpinion() {
		return this.flowFeedOpinion;
	}
	public void setFlowFeedText(String value) {
		this.flowFeedText = value;
	}
	public String getFlowFeedText() {
		return this.flowFeedText;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateOperatorId(Integer value) {
		this.createOperatorId = value;
	}
	public Integer getCreateOperatorId() {
		return this.createOperatorId;
	}

}

