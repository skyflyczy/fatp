package com.fatp.domain;

import com.fatp.po.sys.SysMemberRolePo;

public class SysMemberRole extends SysMemberRolePo {
	
	private static final long serialVersionUID = -91670265616793690L;
	/**
	 * 角色拥有的所有菜单
	 */
	private String memberMenuIds;

	public String getMemberMenuIds() {
		return memberMenuIds;
	}
	public void setMemberMenuIds(String memberMenuIds) {
		this.memberMenuIds = memberMenuIds;
	}
}

