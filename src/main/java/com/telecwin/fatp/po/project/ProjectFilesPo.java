package com.telecwin.fatp.po.project;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * ProjectFiles
 * 
 */
public class ProjectFilesPo {
	/**
	 * 附件id，自增主键
	 */
	private Integer id;

	/**
	 * 文件唯一id，用于web表现
	 */
	private String projectFileGuid;

	/**
	 * 产品id,project_recordinfo.id
	 */
	private Integer projectId;

	/**
	 * 文件类型：1：附件，2：备案申请书，3：挂牌申请书
	 */
	private Integer fileType;

	/**
	 * 信息类型 银行见证=1，质押物=2，其他附件=3
	 */
	private Integer infoType;

	/**
	 * 信息名称
	 */
	private String infoName;

	/**
	 * 信息分类id
	 */
	private Integer infoCategoryId;

	/**
	 * 是否见证
	 */
	private Integer isValid;

	/**
	 * 附件是否对外披露 0=不对外披露 1=对外披露
	 */
	private Integer fileIsOpen;

	/**
	 * 信息说明
	 */
	private String infoDesc;

	/**
	 * 附件1
	 */
	private String file1;

	/**
	 * 附件2
	 */
	private String file2;

	/**
	 * 附件3
	 */
	private String file3;

	/**
	 * 附件4
	 */
	private String file4;

	/**
	 * 附件5
	 */
	private String file5;

	/**
	 * 是否使用电子签章：0：否，1：是
	 */
	private Integer useSeal;

	/**
	 * 附件来源,1:产品录入,2:材料补充
	 */
	private Integer fileSource;

	/**
	 * 附件审核状态,0:无需审核 1:未提交,2:待审批,3:审批通过,4:审批退回
	 */
	private Integer fileAuditStatus;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;

	/**
	 * 创建人,member_operator.id
	 */
	private Integer createOperatorId;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;

	/**
	 * 更新人,member_operator.id
	 */
	private Integer updateOperatorId;

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setProjectFileGuid(String value) {
		this.projectFileGuid = value;
	}

	public String getProjectFileGuid() {
		return this.projectFileGuid;
	}

	public void setProjectId(Integer value) {
		this.projectId = value;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setFileType(Integer value) {
		this.fileType = value;
	}

	public Integer getFileType() {
		return this.fileType;
	}

	public void setInfoType(Integer value) {
		this.infoType = value;
	}

	public Integer getInfoType() {
		return this.infoType;
	}

	public void setInfoName(String value) {
		this.infoName = value;
	}

	public String getInfoName() {
		return this.infoName;
	}

	public void setInfoCategoryId(Integer value) {
		this.infoCategoryId = value;
	}

	public Integer getInfoCategoryId() {
		return this.infoCategoryId;
	}

	public void setIsValid(Integer value) {
		this.isValid = value;
	}

	public Integer getIsValid() {
		return this.isValid;
	}

	public void setFileIsOpen(Integer value) {
		this.fileIsOpen = value;
	}

	public Integer getFileIsOpen() {
		return this.fileIsOpen;
	}

	public void setInfoDesc(String value) {
		this.infoDesc = value;
	}

	public String getInfoDesc() {
		return this.infoDesc;
	}

	public void setFile1(String value) {
		this.file1 = value;
	}

	public String getFile1() {
		return this.file1;
	}

	public void setFile2(String value) {
		this.file2 = value;
	}

	public String getFile2() {
		return this.file2;
	}

	public void setFile3(String value) {
		this.file3 = value;
	}

	public String getFile3() {
		return this.file3;
	}

	public void setFile4(String value) {
		this.file4 = value;
	}

	public String getFile4() {
		return this.file4;
	}

	public void setFile5(String value) {
		this.file5 = value;
	}

	public String getFile5() {
		return this.file5;
	}

	public void setUseSeal(Integer value) {
		this.useSeal = value;
	}

	public Integer getUseSeal() {
		return this.useSeal;
	}

	public void setFileSource(Integer value) {
		this.fileSource = value;
	}

	public Integer getFileSource() {
		return this.fileSource;
	}

	public void setFileAuditStatus(Integer value) {
		this.fileAuditStatus = value;
	}

	public Integer getFileAuditStatus() {
		return this.fileAuditStatus;
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

	public String file1Link() {
		return this.getFileLink(this.file1);
	}

	public String file2Link() {
		return this.getFileLink(this.file2);
	}

	public String file3Link() {
		return this.getFileLink(this.file3);
	}

	public String file4Link() {
		return this.getFileLink(this.file4);
	}

	public String file5Link() {
		return this.getFileLink(this.file5);
	}
	
	private String getFileLink(String sourceFileName) {
		String fileLink = null;

		if (StringUtils.isNotBlank(sourceFileName)) {
			String[] fileNames = sourceFileName.split(":");
			fileLink = fileNames.length == 1 ? sourceFileName : fileNames[1];
		}

		return fileLink;
	}
}
