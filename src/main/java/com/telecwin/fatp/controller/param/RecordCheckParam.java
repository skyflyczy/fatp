package com.telecwin.fatp.controller.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RecordCheckParam {

	private int id;
	private String flowFeedOpinion;
	private Integer operatorId;
	private String operatorName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date deadline;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlowFeedOpinion() {
		return flowFeedOpinion;
	}
	public void setFlowFeedOpinion(String flowFeedOpinion) {
		this.flowFeedOpinion = flowFeedOpinion;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}
