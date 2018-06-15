package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysMemberRole
 */
public class SysMemberRolePo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 会员id,uc_user.id,平台角色=0
	 */
	private Integer memberId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 角色描述
	 */
	private String roleDesc;
	/**
	 * 角色类型 管理角色=1 业务角色=2
	 */
	private Integer roleType;
	/**
	 * 是否正常，正常=1，删除=0
	 */
	private Integer isValid;
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
	public void setRoleName(String value) {
		this.roleName = value;
	}
	public String getRoleName() {
		return this.roleName;
	}
	public void setRoleDesc(String value) {
		this.roleDesc = value;
	}
	public String getRoleDesc() {
		return this.roleDesc;
	}
	public void setRoleType(Integer value) {
		this.roleType = value;
	}
	public Integer getRoleType() {
		return this.roleType;
	}
	public void setIsValid(Integer value) {
		this.isValid = value;
	}
	public Integer getIsValid() {
		return this.isValid;
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

