package com.telecwin.fatp.po.project;

import java.io.Serializable;

/**
 * 
 * 联系人
 */
public class ProjectLinkmanPo implements Serializable{

	private static final long serialVersionUID = 8781310771880829076L;
	
	private Integer id;
	private String realName;
	private String phone;
	private String email;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
