package com.telecwin.fatp.domain;

import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telecwin.fatp.enums.user.IdTypeDesc;
import com.telecwin.fatp.enums.user.UserStatusDesc;
import com.telecwin.fatp.po.user.UcUserPo;

/**
 * UcUser
 */
public class UcUser extends UcUserPo{
	private static final long serialVersionUID = 1L;
	//user_ext字段
	/**
	 * 个人出生日期/企业注册日期,公用属性
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd") 
	private java.util.Date birthDate;
	/**
	 * 邮编,公用属性
	 */
	private java.lang.String postalCode;
	/**
	 * 传真号码,公用属性
	 */
	private java.lang.String faxPhone;
	/**
	 * 电话号码,公用属性
	 */
	private java.lang.String telePhone;
	/**
	 * 联系地址,公用属性
	 */
	private java.lang.String address;
	/**
	 * 个性网址/企业网址,公用属性
	 */
	private java.lang.String webUrl;
	/**
	 * 教育程度id,个人属性
	 */
	private java.lang.Integer educationId;
	/**
	 * 职业id，个人属性
	 */
	private java.lang.Integer careerId;
	/**
	 * 省份id
	 */
	private java.lang.Integer provinceId;
	/**
	 * 城市id,公用属性
	 */
	private java.lang.Integer cityId;
	/**
	 * 区县id
	 */
	private java.lang.Integer disId;
	/**
	 * 所属行业Id,公用属性
	 */
	private java.lang.Integer industryId;
	/**
	 * 企业全称
	 */
	private String companyName;
	/**
	 * 公司类型id systype_company.id
	 */
	private Integer companyTypeId;
	/**
	 * 企业简称
	 */
	private String companyShortName;
	/**
	 * 企业营业执照
	 */
	private String companyBusinessLicense;
	/**
	 * 企业-法人代表
	 */
	private String companyRepresentative;
	/**
	 * 企业-组织机构代码
	 */
	private String companyOrgCode;
	/**
	 * 企业-税务登记号码
	 */
	private String companyFaxNumber;
	/**
	 * 企业-注册地
	 */
	private String companyRegAddress;
	/**
	 * 个人经营业务/企业主营业务描述,公用属性
	 */
	private java.lang.String mainBuisness;
	/**
	 * 个人描述/企业描述,公用属性
	 */
	private java.lang.String mainDesc;
	/**
	 * 联系人/负责人姓名,公用属性
	 */
	private java.lang.String linkMan;
	/**
	 * 联系/负责人手机,公用属性
	 */
	private java.lang.String linkPhone;
	/**
	 * 联系人/负责人性别
	 */
	private Integer linkSex;
	/**
	 * 联系人/负责人证件类型
	 */
	private Integer linkIdTypeId;
	/**
	 * 联系人/负责人证件号码
	 */
	private String linkIdnumber;
	/**
	 * 联系人/负责人部门
	 */
	private String linkDepartment;
	/**
	 * 联系人/负责人职业/职务
	 */
	private String linkCareer;
	/**
	 * 联系人/负责人email
	 */
	private String linkEmail;
	/**
	 * 联系人/负责人描述
	 */
	private String linkDesc;
	private Integer moneyUnit;
	private Integer moneyUnitFinance;
	/**
	 * 企业营业收入/个人收入
	 */
	private BigDecimal financialBusinessIncome; 
	/**
	 * 集团营业收入/家庭收入
	 */
	private BigDecimal financialGroupBusinessIncome; 
	/**
	 * 企业总资产/个人总资产
	 */
	private BigDecimal financialTotalAsset;
	/**
	 * 企业净资产/个人净资产
	 */
	private BigDecimal financialNetAsset; 
	/**
	 * 集团净资产/家庭净资产
	 */
	private BigDecimal financialGroupNetAsset; 
	/**
	 * 总利润
	 */
	private BigDecimal financialTotalProfit;
	/**
	 * 净利润
	 */
	private BigDecimal financialNetProfit;
	/**
	 * 部门名称
	 */
	private String deparmentName;
	/**
	 * 部门所在省份/直辖市id
	 */
	private Integer deparmentProvinceId;
	/**
	 * 部门所在城市/区id
	 */
	private Integer deparmentCityId;
	/**
	 * 部门所在区/县id
	 */
	private Integer deparmentDisId;
	/**
	 * 部门所在地联系地址
	 */
	private String deparmentAddress;
	/**
	 * 部门电话
	 */
	private String deparmentPhone;
	/**
	 * 部门传真
	 */
	private String deparmentFax;
	private String deparmentPostCode;
	private String orgTypeName;
	private String idTypeName;
	private String industryName;
	private String cityName;
	private String disName;
	private String proName;
	private String companyTypeName;
	private String deparmentProvince;
	private String deparmentCity;
	private String deparmentDis;
	private String ownerUserName;
	private String parentCompanyOrgCode;
	private String parentCompanyName;
	
	public String getParentCompanyOrgCode() {
		return parentCompanyOrgCode;
	}
	public String getParentCompanyName() {
		return parentCompanyName;
	}
	public void setParentCompanyOrgCode(String parentCompanyOrgCode) {
		this.parentCompanyOrgCode = parentCompanyOrgCode;
	}
	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	public java.lang.String getPostalCode() {
		return postalCode;
	}
	public java.lang.String getFaxPhone() {
		return faxPhone;
	}
	public java.lang.String getTelePhone() {
		return telePhone;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public java.lang.String getWebUrl() {
		return webUrl;
	}
	public java.lang.Integer getEducationId() {
		return educationId;
	}
	public java.lang.Integer getCareerId() {
		return careerId;
	}
	public java.lang.Integer getProvinceId() {
		return provinceId;
	}
	public java.lang.Integer getCityId() {
		return cityId;
	}
	public java.lang.Integer getDisId() {
		return disId;
	}
	public java.lang.Integer getIndustryId() {
		return industryId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public Integer getCompanyTypeId() {
		return companyTypeId;
	}
	public String getCompanyShortName() {
		return companyShortName;
	}
	public String getCompanyBusinessLicense() {
		return companyBusinessLicense;
	}
	public String getCompanyRepresentative() {
		return companyRepresentative;
	}
	public String getCompanyOrgCode() {
		return companyOrgCode;
	}
	public String getCompanyFaxNumber() {
		return companyFaxNumber;
	}
	public String getCompanyRegAddress() {
		return companyRegAddress;
	}
	public java.lang.String getMainBuisness() {
		return mainBuisness;
	}
	public java.lang.String getMainDesc() {
		return mainDesc;
	}
	public java.lang.String getLinkMan() {
		return linkMan;
	}
	public java.lang.String getLinkPhone() {
		return linkPhone;
	}
	public Integer getLinkSex() {
		return linkSex;
	}
	public Integer getLinkIdTypeId() {
		return linkIdTypeId;
	}
	public String getLinkIdnumber() {
		return linkIdnumber;
	}
	public String getLinkDepartment() {
		return linkDepartment;
	}
	public String getLinkCareer() {
		return linkCareer;
	}
	public String getLinkEmail() {
		return linkEmail;
	}
	public String getLinkDesc() {
		return linkDesc;
	}
	public BigDecimal getFinancialBusinessIncome() {
		return financialBusinessIncome;
	}
	public BigDecimal getFinancialGroupBusinessIncome() {
		return financialGroupBusinessIncome;
	}
	public BigDecimal getFinancialTotalAsset() {
		return financialTotalAsset;
	}
	public BigDecimal getFinancialNetAsset() {
		return financialNetAsset;
	}
	public BigDecimal getFinancialGroupNetAsset() {
		return financialGroupNetAsset;
	}
	public BigDecimal getFinancialTotalProfit() {
		return financialTotalProfit;
	}
	public BigDecimal getFinancialNetProfit() {
		return financialNetProfit;
	}
	public String getDeparmentName() {
		return deparmentName;
	}
	public Integer getDeparmentProvinceId() {
		return deparmentProvinceId;
	}
	public Integer getDeparmentCityId() {
		return deparmentCityId;
	}
	public Integer getDeparmentDisId() {
		return deparmentDisId;
	}
	public String getDeparmentPhone() {
		return deparmentPhone;
	}
	public String getDeparmentFax() {
		return deparmentFax;
	}
	public String getOrgTypeName() {
		return orgTypeName;
	}
	public String getIdTypeName() {
		return idTypeName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public String getCityName() {
		return cityName;
	}
	public String getDisName() {
		return disName;
	}
	public String getProName() {
		return proName;
	}
	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setPostalCode(java.lang.String postalCode) {
		this.postalCode = postalCode;
	}
	public void setFaxPhone(java.lang.String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public void setTelePhone(java.lang.String telePhone) {
		this.telePhone = telePhone;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public void setWebUrl(java.lang.String webUrl) {
		this.webUrl = webUrl;
	}
	public void setEducationId(java.lang.Integer educationId) {
		this.educationId = educationId;
	}
	public void setCareerId(java.lang.Integer careerId) {
		this.careerId = careerId;
	}
	public void setProvinceId(java.lang.Integer provinceId) {
		this.provinceId = provinceId;
	}
	public void setCityId(java.lang.Integer cityId) {
		this.cityId = cityId;
	}
	public void setDisId(java.lang.Integer disId) {
		this.disId = disId;
	}
	public void setIndustryId(java.lang.Integer industryId) {
		this.industryId = industryId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}
	public void setCompanyShortName(String companyShortName) {
		this.companyShortName = companyShortName;
	}
	public void setCompanyBusinessLicense(String companyBusinessLicense) {
		this.companyBusinessLicense = companyBusinessLicense;
	}
	public void setCompanyRepresentative(String companyRepresentative) {
		this.companyRepresentative = companyRepresentative;
	}
	public void setCompanyOrgCode(String companyOrgCode) {
		this.companyOrgCode = companyOrgCode;
	}
	public void setCompanyFaxNumber(String companyFaxNumber) {
		this.companyFaxNumber = companyFaxNumber;
	}
	public void setCompanyRegAddress(String companyRegAddress) {
		this.companyRegAddress = companyRegAddress;
	}
	public void setMainBuisness(java.lang.String mainBuisness) {
		this.mainBuisness = mainBuisness;
	}
	public void setMainDesc(java.lang.String mainDesc) {
		this.mainDesc = mainDesc;
	}
	public void setLinkMan(java.lang.String linkMan) {
		this.linkMan = linkMan;
	}
	public void setLinkPhone(java.lang.String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public void setLinkSex(Integer linkSex) {
		this.linkSex = linkSex;
	}
	public void setLinkIdTypeId(Integer linkIdTypeId) {
		this.linkIdTypeId = linkIdTypeId;
	}
	public void setLinkIdnumber(String linkIdnumber) {
		this.linkIdnumber = linkIdnumber;
	}
	public void setLinkDepartment(String linkDepartment) {
		this.linkDepartment = linkDepartment;
	}
	public void setLinkCareer(String linkCareer) {
		this.linkCareer = linkCareer;
	}
	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}
	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}
	public void setFinancialBusinessIncome(BigDecimal financialBusinessIncome) {
		this.financialBusinessIncome = financialBusinessIncome;
	}
	public void setFinancialGroupBusinessIncome(
			BigDecimal financialGroupBusinessIncome) {
		this.financialGroupBusinessIncome = financialGroupBusinessIncome;
	}
	public void setFinancialTotalAsset(BigDecimal financialTotalAsset) {
		this.financialTotalAsset = financialTotalAsset;
	}
	public void setFinancialNetAsset(BigDecimal financialNetAsset) {
		this.financialNetAsset = financialNetAsset;
	}
	public void setFinancialGroupNetAsset(BigDecimal financialGroupNetAsset) {
		this.financialGroupNetAsset = financialGroupNetAsset;
	}
	public void setFinancialTotalProfit(BigDecimal financialTotalProfit) {
		this.financialTotalProfit = financialTotalProfit;
	}
	public void setFinancialNetProfit(BigDecimal financialNetProfit) {
		this.financialNetProfit = financialNetProfit;
	}
	public void setDeparmentName(String deparmentName) {
		this.deparmentName = deparmentName;
	}
	public void setDeparmentProvinceId(Integer deparmentProvinceId) {
		this.deparmentProvinceId = deparmentProvinceId;
	}
	public void setDeparmentCityId(Integer deparmentCityId) {
		this.deparmentCityId = deparmentCityId;
	}
	public void setDeparmentDisId(Integer deparmentDisId) {
		this.deparmentDisId = deparmentDisId;
	}
	public void setDeparmentPhone(String deparmentPhone) {
		this.deparmentPhone = deparmentPhone;
	}
	public void setDeparmentFax(String deparmentFax) {
		this.deparmentFax = deparmentFax;
	}
	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}
	public void setIdTypeName(String idTypeName) {
		this.idTypeName = idTypeName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void copy(UcUser old) {
		if(old == null)
			return;
		this.setCompanyOrgCode(old.getCompanyOrgCode());
		this.setCompanyName(old.getCompanyName());
		this.setCompanyShortName(old.getCompanyShortName());
		this.setCompanyTypeId(old.getCompanyTypeId());
		this.setBirthDate(old.getBirthDate());
		this.setPostalCode(old.getPostalCode());
		this.setFaxPhone(old.getFaxPhone());
		this.setTelePhone(old.getTelePhone());
		this.setAddress(old.getAddress());
		this.setWebUrl(old.getWebUrl());
		this.setProvinceId(old.getProvinceId());
		this.setCityId(old.getCityId());
		this.setDisId(old.getDisId());
		this.setIndustryId(old.getIndustryId());
		this.setCompanyBusinessLicense(old.getCompanyBusinessLicense());
		this.setCompanyRepresentative(old.getCompanyRepresentative());
		this.setCompanyFaxNumber(old.getCompanyFaxNumber());
		this.setCompanyRegAddress(old.getCompanyRegAddress());
		this.setMainBuisness(old.getMainBuisness());
		this.setMainDesc(old.getMainDesc());
		this.setIdTypeId(old.getIdTypeId());
		this.setFinancialBusinessIncome(old.getFinancialBusinessIncome());
		this.setFinancialGroupBusinessIncome(old.getFinancialGroupBusinessIncome());
		this.setFinancialGroupNetAsset(old.getFinancialGroupNetAsset());
		this.setFinancialNetAsset(old.getFinancialNetAsset());
		this.setFinancialNetProfit(old.getFinancialNetProfit());
		this.setFinancialTotalAsset(old.getFinancialTotalAsset());
		this.setFinancialTotalProfit(old.getFinancialTotalProfit());
	}
	public String getCompanyTypeName() {
		return companyTypeName;
	}
	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	public String getDeparmentProvince() {
		return deparmentProvince;
	}
	public String getDeparmentCity() {
		return deparmentCity;
	}
	public String getDeparmentDis() {
		return deparmentDis;
	}
	public void setDeparmentProvince(String deparmentProvince) {
		this.deparmentProvince = deparmentProvince;
	}
	public void setDeparmentCity(String deparmentCity) {
		this.deparmentCity = deparmentCity;
	}
	public void setDeparmentDis(String deparmentDis) {
		this.deparmentDis = deparmentDis;
	}
	public String getDeparmentAddress() {
		return deparmentAddress;
	}
	public void setDeparmentAddress(String deparmentAddress) {
		this.deparmentAddress = deparmentAddress;
	}
	public String getOwnerUserName() {
		return ownerUserName;
	}
	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
	public Integer getMoneyUnit() {
		return moneyUnit;
	}
	public void setMoneyUnit(Integer moneyUnit) {
		this.moneyUnit = moneyUnit;
	}
	public Integer getMoneyUnitFinance() {
		return moneyUnitFinance;
	}
	public void setMoneyUnitFinance(Integer moneyUnitFinance) {
		this.moneyUnitFinance = moneyUnitFinance;
	}
	public String getDeparmentPostCode() {
		return deparmentPostCode;
	}
	public void setDeparmentPostCode(String deparmentPostCode) {
		this.deparmentPostCode = deparmentPostCode;
	}
	
	public String showIdTypeDesc(){
		if(super.getIdTypeId() == null){
			return null;
		}
		for(IdTypeDesc desc : IdTypeDesc.values()){
			if(desc.getIdType() == this.getIdTypeId().intValue()){
				return desc.name();
			}
		}
		return null;
	}
	
	public String showUserStatus() {
		if(super.getUserStatus() == null) {
			return "未知状态";
		}
		for(UserStatusDesc desc : UserStatusDesc.values()) {
			if(desc.value == super.getUserStatus().intValue()) {
				return desc.name();
			}
		}
		return "未知状态";
	}
	/**
	 * 是否可编辑
	 * 
	 * @return true 可以
	 */
	public boolean canEdit() {
		return super.getUserStatus() != null && (super.getUserStatus().intValue() == UserStatusDesc.待处理.value
				|| super.getUserStatus().intValue() == UserStatusDesc.审核退回.value
				|| super.getUserStatus().intValue() == UserStatusDesc.未提交.value);
	}
}

