package com.fatp.po.offsite;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * BizimportApply
 * @author zhiya.chai
 * 2018-06-14 23:16:21
 */
public class BizimportApplyPo implements Serializable{
	
	private static final long serialVersionUID = 7712523012310315495L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 申请唯一编码
	 */
	private String applyGuid;
	/**
	 * 产品挂牌id, listing_info.id
	 */
	private Integer listingInfoId;
	/**
	 * 申请类型：投资登记申请(1)
	 */
	private Integer applyType;
	/**
	 * 申请状态。待提交(1), 登记中(2), 登记成功(3), 登记失败(4);
	 */
	private Integer applyStatus;
	/**
	 * 是否删除1：是，0：否
	 */
	private int isDelete;
	/**
	 * 申请操作员id, member_operator.id
	 */
	private Integer applyOperatorId;
	/**
	 * 申请者会员id
	 */
	private Integer applyMemberId;
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
	/**
	 * 产品起息日，上传投资明细时可以变更
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private java.util.Date valueDate;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setApplyGuid(String value) {
		this.applyGuid = value;
	}
	public String getApplyGuid() {
		return this.applyGuid;
	}
	public void setListingInfoId(Integer value) {
		this.listingInfoId = value;
	}
	public Integer getListingInfoId() {
		return this.listingInfoId;
	}
	public void setApplyType(Integer value) {
		this.applyType = value;
	}
	public Integer getApplyType() {
		return this.applyType;
	}
	public void setApplyStatus(Integer value) {
		this.applyStatus = value;
	}
	public Integer getApplyStatus() {
		return this.applyStatus;
	}
	public void setApplyOperatorId(Integer value) {
		this.applyOperatorId = value;
	}
	public Integer getApplyOperatorId() {
		return this.applyOperatorId;
	}
	public void setApplyMemberId(Integer value) {
		this.applyMemberId = value;
	}
	public Integer getApplyMemberId() {
		return this.applyMemberId;
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
	public void setValueDate(java.util.Date value) {
		this.valueDate = value;
	}
	public java.util.Date getValueDate() {
		return this.valueDate;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}

