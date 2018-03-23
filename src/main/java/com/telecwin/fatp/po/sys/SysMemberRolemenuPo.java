package com.telecwin.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysMemberRolemenu
 */
public class SysMemberRolemenuPo implements Serializable {
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
	 * 会员菜单id,sys_member_menu.id(删除)
	 */
	private Integer memberMenuId;
	/**
	 * 系统菜单id,sys_menu.id
	 */
	private Integer menuId;
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
	public void setMemberMenuId(Integer value) {
		this.memberMenuId = value;
	}
	public Integer getMemberMenuId() {
		return this.memberMenuId;
	}
	public void setMenuId(Integer value) {
		this.menuId = value;
	}
	public Integer getMenuId() {
		return this.menuId;
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

