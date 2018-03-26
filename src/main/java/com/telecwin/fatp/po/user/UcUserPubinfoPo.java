package com.telecwin.fatp.po.user;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * UcUserPubinfo
 */
public class UcUserPubinfoPo {
	/**
	 * 唯一id 自增id
	 */
	private Integer id;
	/**
	 * 机构唯一id，用于web显示
	 */
	private String companyGuid;
	/**
	 * 企业-组织机构代码
	 */
	private String companyOrgCode;
	/**
	 * 企业全称
	 */
	private String companyName;
	/**
	 * 企业简称
	 */
	private String companyShortName;
	/**
	 * 公司类型id systype_company.id
	 */
	private Integer companyTypeId;
	/**
	 * 企业注册日期,公用属性
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date birthDate;
	/**
	 * 邮编,公用属性
	 */
	private String postalCode;
	/**
	 * 传真号码,公用属性
	 */
	private String faxPhone;
	/**
	 * 电话号码,公用属性
	 */
	private String telePhone;
	/**
	 * 联系地址,公用属性
	 */
	private String address;
	/**
	 * 企业网址,公用属性
	 */
	private String webUrl;
	/**
	 * 省份id,公用属性
	 */
	private Integer provinceId;
	/**
	 * 城市id,公用属性
	 */
	private Integer cityId;
	/**
	 * 区县id,公用属性
	 */
	private Integer disId;
	/**
	 * 所属行业Id,公用属性
	 */
	private Integer industryId;
	/**
	 * 企业营业执照
	 */
	private String companyBusinessLicense;
	/**
	 * 企业-法人代表(修改字段名)
	 */
	private String companyRepresentative;
	/**
	 * 企业-税务登记号码(修改字段名)
	 */
	private String companyFaxNumber;
	/**
	 * 企业-注册地(修改字段名)
	 */
	private String companyRegAddress;
	/**
	 * 企业主营业务描述,公用属性
	 */
	private String mainBuisness;
	/**
	 * 企业描述,公用属性
	 */
	private String mainDesc;
	/**
	 * 企业营业收入
	 */
	private java.math.BigDecimal financialBusinessIncome;
	/**
	 * 集团营业收入
	 */
	private java.math.BigDecimal financialGroupBusinessIncome;
	/**
	 * 企业总资产
	 */
	private java.math.BigDecimal financialTotalAsset;
	/**
	 * 企业净资产
	 */
	private java.math.BigDecimal financialNetAsset;
	/**
	 * 集团净资产
	 */
	private java.math.BigDecimal financialGroupNetAsset;
	/**
	 * 总利润
	 */
	private java.math.BigDecimal financialTotalProfit;
	/**
	 * 净利润
	 */
	private java.math.BigDecimal financialNetProfit;
	/**
	 * 创建人id
	 */
	private Integer createOperatorId;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date createTime;
	/**
	 * 更新人id
	 */
	private Integer updateOperatorId;
	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private java.util.Date updateTime;
	/**
	 * 货币单位 1=人民币 2=港币 3=美元
	 */
	private Integer moneyUnit;
	/**
	 * 财务数据货币单位 1=人民币 2=港币 3=美元
	 */
	private Integer moneyUnitFinance;

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCompanyGuid(String value) {
		this.companyGuid = value;
	}
	public String getCompanyGuid() {
		return this.companyGuid;
	}
	public void setCompanyOrgCode(String value) {
		this.companyOrgCode = value;
	}
	public String getCompanyOrgCode() {
		return this.companyOrgCode;
	}
	public void setCompanyName(String value) {
		this.companyName = value;
	}
	public String getCompanyName() {
		return this.companyName;
	}
	public void setCompanyShortName(String value) {
		this.companyShortName = value;
	}
	public String getCompanyShortName() {
		return this.companyShortName;
	}
	public void setCompanyTypeId(Integer value) {
		this.companyTypeId = value;
	}
	public Integer getCompanyTypeId() {
		return this.companyTypeId;
	}
	public void setBirthDate(java.util.Date value) {
		this.birthDate = value;
	}
	public java.util.Date getBirthDate() {
		return this.birthDate;
	}
	public void setPostalCode(String value) {
		this.postalCode = value;
	}
	public String getPostalCode() {
		return this.postalCode;
	}
	public void setFaxPhone(String value) {
		this.faxPhone = value;
	}
	public String getFaxPhone() {
		return this.faxPhone;
	}
	public void setTelePhone(String value) {
		this.telePhone = value;
	}
	public String getTelePhone() {
		return this.telePhone;
	}
	public void setAddress(String value) {
		this.address = value;
	}
	public String getAddress() {
		return this.address;
	}
	public void setWebUrl(String value) {
		this.webUrl = value;
	}
	public String getWebUrl() {
		return this.webUrl;
	}
	public void setProvinceId(Integer value) {
		this.provinceId = value;
	}
	public Integer getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(Integer value) {
		this.cityId = value;
	}
	public Integer getCityId() {
		return this.cityId;
	}
	public void setDisId(Integer value) {
		this.disId = value;
	}
	public Integer getDisId() {
		return this.disId;
	}
	public void setIndustryId(Integer value) {
		this.industryId = value;
	}
	public Integer getIndustryId() {
		return this.industryId;
	}
	public void setCompanyBusinessLicense(String value) {
		this.companyBusinessLicense = value;
	}
	public String getCompanyBusinessLicense() {
		return this.companyBusinessLicense;
	}
	public void setCompanyRepresentative(String value) {
		this.companyRepresentative = value;
	}
	public String getCompanyRepresentative() {
		return this.companyRepresentative;
	}
	public void setCompanyFaxNumber(String value) {
		this.companyFaxNumber = value;
	}
	public String getCompanyFaxNumber() {
		return this.companyFaxNumber;
	}
	public void setCompanyRegAddress(String value) {
		this.companyRegAddress = value;
	}
	public String getCompanyRegAddress() {
		return this.companyRegAddress;
	}
	public void setMainBuisness(String value) {
		this.mainBuisness = value;
	}
	public String getMainBuisness() {
		return this.mainBuisness;
	}
	public void setMainDesc(String value) {
		this.mainDesc = value;
	}
	public String getMainDesc() {
		return this.mainDesc;
	}
	public void setFinancialBusinessIncome(java.math.BigDecimal value) {
		this.financialBusinessIncome = value;
	}
	public java.math.BigDecimal getFinancialBusinessIncome() {
		return this.financialBusinessIncome;
	}
	public void setFinancialGroupBusinessIncome(java.math.BigDecimal value) {
		this.financialGroupBusinessIncome = value;
	}
	public java.math.BigDecimal getFinancialGroupBusinessIncome() {
		return this.financialGroupBusinessIncome;
	}
	public void setFinancialTotalAsset(java.math.BigDecimal value) {
		this.financialTotalAsset = value;
	}
	public java.math.BigDecimal getFinancialTotalAsset() {
		return this.financialTotalAsset;
	}
	public void setFinancialNetAsset(java.math.BigDecimal value) {
		this.financialNetAsset = value;
	}
	public java.math.BigDecimal getFinancialNetAsset() {
		return this.financialNetAsset;
	}
	public void setFinancialGroupNetAsset(java.math.BigDecimal value) {
		this.financialGroupNetAsset = value;
	}
	public java.math.BigDecimal getFinancialGroupNetAsset() {
		return this.financialGroupNetAsset;
	}
	public void setFinancialTotalProfit(java.math.BigDecimal value) {
		this.financialTotalProfit = value;
	}
	public java.math.BigDecimal getFinancialTotalProfit() {
		return this.financialTotalProfit;
	}
	public void setFinancialNetProfit(java.math.BigDecimal value) {
		this.financialNetProfit = value;
	}
	public java.math.BigDecimal getFinancialNetProfit() {
		return this.financialNetProfit;
	}
	public void setCreateOperatorId(Integer value) {
		this.createOperatorId = value;
	}
	public Integer getCreateOperatorId() {
		return this.createOperatorId;
	}
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateOperatorId(Integer value) {
		this.updateOperatorId = value;
	}
	public Integer getUpdateOperatorId() {
		return this.updateOperatorId;
	}
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setMoneyUnit(Integer value) {
		this.moneyUnit = value;
	}
	public Integer getMoneyUnit() {
		return this.moneyUnit;
	}
	public void setMoneyUnitFinance(Integer value) {
		this.moneyUnitFinance = value;
	}
	public Integer getMoneyUnitFinance() {
		return this.moneyUnitFinance;
	}

}

