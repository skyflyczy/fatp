package com.telecwin.fatp.service.datasupprot.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.encrypt.SymmetricEncrypt;
import com.telecwin.fatp.dao.user.UcUserPubinfoDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;

/**
 * 用户公共信息数据支持类
 */
@Service
public class UcUserPubinfoDataSupportService {

	@Autowired
	private UcUserPubinfoDao ucUserPubinfoDao;
	
	/**
	 * 解密
	 */
	private void doDecryptPublicinfo(UcUserPubinfoPo pubinfo) {
		//companyOrgCode
		String companyOrgCode = pubinfo.getCompanyOrgCode();
		companyOrgCode = StringUtils.isNotBlank(companyOrgCode) ? SymmetricEncrypt.decryptStr(companyOrgCode) : companyOrgCode;
		pubinfo.setCompanyOrgCode(companyOrgCode);
		//faxPhone
		String faxPhone = pubinfo.getFaxPhone();
		faxPhone = StringUtils.isNotBlank(faxPhone) ? SymmetricEncrypt.decryptStr(faxPhone) : faxPhone;
		pubinfo.setFaxPhone(faxPhone);
		//telePhone
		String telePhone = pubinfo.getTelePhone();
		telePhone = StringUtils.isNotBlank(telePhone) ? SymmetricEncrypt.decryptStr(telePhone) : telePhone;
		pubinfo.setTelePhone(telePhone);
		//address
		String address = pubinfo.getAddress();
		address = StringUtils.isNotBlank(address) ? SymmetricEncrypt.decryptStr(address) : address;
		pubinfo.setAddress(address);
		//companyBusinessLicense
		String companyBusinessLicense = pubinfo.getCompanyBusinessLicense();
		companyBusinessLicense = StringUtils.isNotBlank(companyBusinessLicense) ? SymmetricEncrypt.decryptStr(companyBusinessLicense) : companyBusinessLicense;
		pubinfo.setCompanyBusinessLicense(companyBusinessLicense);
		//companyRepresentative
		String companyRepresentative = pubinfo.getCompanyRepresentative();
		companyRepresentative = StringUtils.isNotBlank(companyRepresentative) ? SymmetricEncrypt.decryptStr(companyRepresentative) : companyRepresentative;
		pubinfo.setCompanyRepresentative(companyRepresentative);
		//companyFaxNumber
		String companyFaxNumber = pubinfo.getCompanyFaxNumber();
		companyFaxNumber = StringUtils.isNotBlank(companyFaxNumber) ? SymmetricEncrypt.decryptStr(companyFaxNumber) : companyFaxNumber;
		pubinfo.setCompanyFaxNumber(companyFaxNumber);
	}
	
	/**
	 * 加密
	 * @return void
	 */
	private void doEncryptPublicinfo(UcUserPubinfoPo pubinfo) {
		//companyOrgCode
		String companyOrgCode = pubinfo.getCompanyOrgCode();
		companyOrgCode = StringUtils.isNotBlank(companyOrgCode) ? SymmetricEncrypt.encryptStr(companyOrgCode) : companyOrgCode;
		pubinfo.setCompanyOrgCode(companyOrgCode);
		//faxPhone
		String faxPhone = pubinfo.getFaxPhone();
		faxPhone = StringUtils.isNotBlank(faxPhone) ? SymmetricEncrypt.encryptStr(faxPhone) : faxPhone;
		pubinfo.setFaxPhone(faxPhone);
		//telePhone
		String telePhone = pubinfo.getTelePhone();
		telePhone = StringUtils.isNotBlank(telePhone) ? SymmetricEncrypt.encryptStr(telePhone) : telePhone;
		pubinfo.setTelePhone(telePhone);
		//address
		String address = pubinfo.getAddress();
		address = StringUtils.isNotBlank(address) ? SymmetricEncrypt.encryptStr(address) : address;
		pubinfo.setAddress(address);
		//companyBusinessLicense
		String companyBusinessLicense = pubinfo.getCompanyBusinessLicense();
		companyBusinessLicense = StringUtils.isNotBlank(companyBusinessLicense) ? SymmetricEncrypt.encryptStr(companyBusinessLicense) : companyBusinessLicense;
		pubinfo.setCompanyBusinessLicense(companyBusinessLicense);
		//companyRepresentative
		String companyRepresentative = pubinfo.getCompanyRepresentative();
		companyRepresentative = StringUtils.isNotBlank(companyRepresentative) ? SymmetricEncrypt.encryptStr(companyRepresentative) : companyRepresentative;
		pubinfo.setCompanyRepresentative(companyRepresentative);
		//companyFaxNumber
		String companyFaxNumber = pubinfo.getCompanyFaxNumber();
		companyFaxNumber = StringUtils.isNotBlank(companyFaxNumber) ? SymmetricEncrypt.encryptStr(companyFaxNumber) : companyFaxNumber;
		pubinfo.setCompanyFaxNumber(companyFaxNumber);
	}
	/**
	 * 查询加密
	 * @param map
	 * @return void
	 */
	private void doEncryptForSearch(Map<String,Object> map){
		if(map.containsKey("companyOrgCode"))
			map.put("companyOrgCode", SymmetricEncrypt.encryptStr(map.get("companyOrgCode").toString()));
//		if(map.containsKey("companyName"))
//			map.put("companyName", SymmetricEncrypt.encryptStr(map.get("companyName").toString()));
//		if(map.containsKey("companyShortName"))
//			map.put("companyShortName", SymmetricEncrypt.encryptStr(map.get("companyShortName").toString()));
		if(map.containsKey("faxPhone"))
			map.put("faxPhone", SymmetricEncrypt.encryptStr(map.get("faxPhone").toString()));
		if(map.containsKey("telePhone"))
			map.put("telePhone", SymmetricEncrypt.encryptStr(map.get("telePhone").toString()));
		if(map.containsKey("address"))
			map.put("address", SymmetricEncrypt.encryptStr(map.get("address").toString()));
		if(map.containsKey("companyBusinessLicense"))
			map.put("companyBusinessLicense", SymmetricEncrypt.encryptStr(map.get("companyBusinessLicense").toString()));
		if(map.containsKey("companyRepresentative"))
			map.put("companyRepresentative", SymmetricEncrypt.encryptStr(map.get("companyRepresentative").toString()));
		if(map.containsKey("companyFaxNumber"))
			map.put("companyFaxNumber", SymmetricEncrypt.encryptStr(map.get("companyFaxNumber").toString()));
//		if(map.containsKey("companyRegAddress"))
//			map.put("companyRegAddress", SymmetricEncrypt.encryptStr(map.get("companyRegAddress").toString()));
	}
	/**
	 * 分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<UcUserPubinfoPo> pageFindByCondition(Map<String, Object> map,int pageNo,int pageSize) {
		doEncryptForSearch(map);
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<UcUserPubinfoPo> list = ucUserPubinfoDao.select(map);
		for(UcUserPubinfoPo o : list){
			doDecryptPublicinfo(o);
		}
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}

	/**
	 * 根据营业执照号码代码获取记录
	 * @return UcUserPubinfo
	 */
	public UcUserPubinfoPo getByCompanyOrgCode(String companyOrgCode) {
		companyOrgCode = SymmetricEncrypt.encryptStr(companyOrgCode);
		UcUserPubinfoPo o = this.ucUserPubinfoDao.getByCompanyOrgCode(companyOrgCode);
		if(o != null){
			doDecryptPublicinfo(o);
		}
		return o;
	}
	
	/**
	 * 根据机构名称代码获取记录
	 * @return UcUserPubinfo
	 */
	public UcUserPubinfoPo getByCompanyNameEqual(String companyName) {
		UcUserPubinfoPo o = this.ucUserPubinfoDao.getByCompanyNameEqual(companyName);
		if(o != null){
			doDecryptPublicinfo(o);
		}
		return o;
	}

	public void insertPub(UcUserPubinfoPo pub) {
		if(pub != null){
			doEncryptPublicinfo(pub);
		}
		this.ucUserPubinfoDao.insertPub(pub);
	}

	public UcUserPubinfoPo getById(Integer companyInfoId) {
		UcUserPubinfoPo o = this.ucUserPubinfoDao.getById(companyInfoId);
		if(o != null){
			doDecryptPublicinfo(o);
		}
		return o;
	}

	public void updatePub(UcUserPubinfoPo pub) {
		if(pub != null){
			doEncryptPublicinfo(pub);
		}
		this.ucUserPubinfoDao.updatePub(pub);
	}
}
