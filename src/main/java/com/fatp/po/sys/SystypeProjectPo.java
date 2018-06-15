package com.fatp.po.sys;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * SystypeMemberProject
 */
public class SystypeProjectPo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一id
	 */
	private Integer id;
	/**
	 * 金融产品类型,systype_product.id,债权=1 X
	 */
	private Integer productTypeId;
	/**
	 * 归属会员id(uc_usertypes.IsExchangeMember=1),对应uc_user.id字段，平台id=0
	 */
	private Integer memberId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setProductTypeId(Integer value) {
		this.productTypeId = value;
	}
	public Integer getProductTypeId() {
		return this.productTypeId;
	}
	public void setMemberId(Integer value) {
		this.memberId = value;
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setTypeName(String value) {
		this.typeName = value;
	}
	public String getTypeName() {
		return this.typeName;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

}

