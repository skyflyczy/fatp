package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysMemberOperatorRole
 * @author peng.liu
 * 2016-08-10 33:10:35
 */
public class SysMemberOperatorRolePo implements Serializable {
	/**
	 * @author peng.liu
	 * 2016年8月11日 上午9:55:33
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 会员id,uc_user.id
	 */
	private Integer memberId;
	/**
	 * 会员角色id,sys_member_role.id
	 */
	private Integer memberRoleId;
	/**
	 * 会员操作员id,member_operator.id
	 */
	private Integer memberOperatorId;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 创建人
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 更新人
	 */
	private Integer updateOperatorId;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setMemberRoleId(Integer value) {
		this.memberRoleId = value;
	}
	public Integer getMemberRoleId() {
		return this.memberRoleId;
	}
	public void setMemberOperatorId(Integer value) {
		this.memberOperatorId = value;
	}
	public Integer getMemberOperatorId() {
		return this.memberOperatorId;
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
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateOperatorId(Integer value) {
		this.updateOperatorId = value;
	}
	public Integer getUpdateOperatorId() {
		return this.updateOperatorId;
	}

}

