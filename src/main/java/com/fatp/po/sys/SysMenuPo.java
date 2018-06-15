package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * SysMenu
 */
public class SysMenuPo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id，自增
	 */
	private Integer id;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 父id
	 */
	private Integer parentId;
	/**
	 * 菜单类型(继承父类) 交易所运营平台菜单=1 会员运营平台菜单=2 在线交易平台菜单=3
	 */
	private Integer menuType;
	/**
	 * 管理类型(继承父类) 系统管理=1 普通业务=2 直接融资项目=3 金融资产转让=4 金融产品发行=5
	 */
	private Integer adminType;
	/**
	 * 所属应用系统，在配置文件中寻找对应的domain host
	 */
	private Integer appId;
	/**
	 * 相对链接地址
	 */
	private String menuUrl;
	/**
	 * 管理地址,支持多个，西文逗号分割
	 */
	private String relationUrl;
	/**
	 * 打开方式 navtab=1,dialog=2
	 */
	private Integer target;
	/**
	 * 是否是Button 1表示菜单，2表示按扭
	 */
	private Integer isButton;
	/**
	 * 同一个父id下的排序
	 */
	private Integer showIndex;
	/**
	 * 菜单图形地址
	 */
	private String menuImgUrl;
	/**
	 * 更新人id
	 */
	private Integer updateOperatorId;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 创建人id
	 */
	private Integer createOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 是否正常，正常=1，删除=0
	 */
	private Integer isValid;
	/**
	 * 备注
	 */
	private String remark;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setMenuName(String value) {
		this.menuName = value;
	}
	public String getMenuName() {
		return this.menuName;
	}
	public void setParentId(Integer value) {
		this.parentId = value;
	}
	public Integer getParentId() {
		return this.parentId;
	}
	public void setMenuType(Integer value) {
		this.menuType = value;
	}
	public Integer getMenuType() {
		return this.menuType;
	}
	public void setAdminType(Integer value) {
		this.adminType = value;
	}
	public Integer getAdminType() {
		return this.adminType;
	}
	public void setAppId(Integer value) {
		this.appId = value;
	}
	public Integer getAppId() {
		return this.appId;
	}
	public void setMenuUrl(String value) {
		this.menuUrl = value;
	}
	public String getMenuUrl() {
		return this.menuUrl;
	}
	public void setRelationUrl(String value) {
		this.relationUrl = value;
	}
	public String getRelationUrl() {
		return this.relationUrl;
	}
	public void setTarget(Integer value) {
		this.target = value;
	}
	public Integer getTarget() {
		return this.target;
	}
	public void setIsButton(Integer value) {
		this.isButton = value;
	}
	public Integer getIsButton() {
		return this.isButton;
	}
	public void setShowIndex(Integer value) {
		this.showIndex = value;
	}
	public Integer getShowIndex() {
		return this.showIndex;
	}
	public void setMenuImgUrl(String value) {
		this.menuImgUrl = value;
	}
	public String getMenuImgUrl() {
		return this.menuImgUrl;
	}
	public void setUpdateOperatorId(Integer value) {
		this.updateOperatorId = value;
	}
	public Integer getUpdateOperatorId() {
		return this.updateOperatorId;
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
	public void setIsValid(Integer value) {
		this.isValid = value;
	}
	public Integer getIsValid() {
		return this.isValid;
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	public String getRemark() {
		return this.remark;
	}

}

