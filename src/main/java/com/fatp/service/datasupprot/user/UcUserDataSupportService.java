package com.fatp.service.datasupprot.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.user.UcUserDao;
import com.fatp.domain.PageData;
import com.fatp.domain.UcUser;
import com.fatp.enums.user.UserStatusDesc;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.encrypt.SymmetricEncrypt;

/**
 * 用户数据支持类
 * @author zhiya.chai
 * 2016年6月17日 上午11:19:00
 */
@Service
public class UcUserDataSupportService {

	@Autowired
	private UcUserDao ucUserDao;
	/**
	 * 获取用户所有信息（uc_user和uc_user_pubinfo）
	 * @param id
	 * @param exchangeId
	 * @return
	 */
	public UcUser getAllById(int id, int exchangeId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("exchangeId", exchangeId);
		UcUser user = ucUserDao.getAllById(map);
		if(user != null) {
			doDecryptUser(user);
		}
		return user;
	}
	/**
	 * 获取用户扩展信息
	 * @param userId
	 * @return
	 */
	public UcUser getUserExtById(int userId) {
		UcUser user = ucUserDao.getUserExtById(userId);
		if(user != null) {
			doDecryptUser(user);
		}
		return user;
	}
	/**
	 * 根据条件分页查找
	 * @param map
	 * @return
	 */
	public PageData<UcUser> pageFindUsersByCondition(Map<String, Object> map,int pageNo,int pageSize){
		doEncryptUserByMap(map);
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<UcUser> list = ucUserDao.select(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(UcUser user : list){
				doDecryptUser(user);
			}
		}
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 完全匹配真实名称获取用户信息
	 * @param realName
	 * @param userIdentity
	 * @param exchangeId
	 * @return
	 */
	public UcUser getMemberByEqualRealName(String realName,int userIdentity,int exchangeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("realNameEqual", realName);
		map.put("exchangeId", exchangeId);
		map.put("userIdentity", userIdentity);
		return getByMap(map);
	}
	/**
	 * 根据用户简称查找用户(完全匹配)
	 * @param userName
	 * @param exchagneId
	 * @param ownerUserId
	 */
	public UcUser getUserNameByEqualUserName(String userName,Integer exchagneId,Integer ownerUserId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userNameEqual", userName);
		map.put("exchagneId", exchagneId);
		map.put("ownerUserId", ownerUserId);
		map.put("noDelStatus", UserStatusDesc.删除.value);
		return getByMap(map);
	}
	/**
	 * 根据公司名称获取用户
	 * @param companyName
	 * @param exchangeId
	 * @return
	 */
	public UcUser getUserByCompanyName(String companyName, Integer exchangeId) {
		if(StringUtils.isBlank(companyName)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyNameEq", companyName);
		map.put("exchangeId", exchangeId);
		map.put("noDelStatus", UserStatusDesc.删除.value);
		return getByMap(map);
	}
	
	private UcUser getByMap(Map<String,Object> map) {
		doEncryptUserByMap(map);
		List<UcUser> list = ucUserDao.select(map);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		UcUser user =list.get(0);
		doDecryptUser(user);
		return user;
	}
	
	public int insert(UcUser o){
		if(o != null){
			doEncryptUser(o);
		}
		return ucUserDao.insert(o);
	}
	
	/**
	 * 不加密
	 * @return void
	 */
	public void insertNoEncryptExt(UcUser o){
		ucUserDao.insertExt(o);
	}
	/**
	 * 更新
	 * @return int
	 */
	public int update(UcUser o){
		if(o != null){
			doEncryptUser(o);
		}
		return ucUserDao.update(o);
	}
	public void updateExt(UcUser o){
		if(o != null){
			doEncryptUser(o);
		}
		ucUserDao.updateExt(o);
	}
	/**
	 * 更新用户状态
	 * @param o
	 * @return
	 */
	public int updateUserStatus(UcUser o) {
		return ucUserDao.updateUserStatus(o);
	}
	/**
	 * 加密
	 * @param map
	 */
	private void doEncryptUserByMap(Map<String,Object> map) {
		if(map.get("idNumber") != null) {
			map.put("idNumber", SymmetricEncrypt.encryptStr(map.get("idNumber").toString()));
		}
	}
	/**
	 * 解密
	 * @param user
	 */
	private void doDecryptUser(UcUser user) {
		//companyOrgCode
		String companyOrgCode = user.getCompanyOrgCode();
		companyOrgCode = StringUtils.isNotBlank(companyOrgCode) ? SymmetricEncrypt.decryptStr(companyOrgCode) : companyOrgCode;
		user.setCompanyOrgCode(companyOrgCode);
		//parentCompanyOrgCode
		String parentCompanyOrgCode = user.getParentCompanyOrgCode();
		parentCompanyOrgCode = StringUtils.isNotBlank(parentCompanyOrgCode) ? SymmetricEncrypt.decryptStr(parentCompanyOrgCode) : parentCompanyOrgCode;
		user.setParentCompanyOrgCode(parentCompanyOrgCode);
		//faxPhone
		String faxPhone = user.getFaxPhone();
		faxPhone = StringUtils.isNotBlank(faxPhone) ? SymmetricEncrypt.decryptStr(faxPhone) : faxPhone;
		user.setFaxPhone(faxPhone);
		//telePhone
		String telePhone = user.getTelePhone();
		telePhone = StringUtils.isNotBlank(telePhone) ? SymmetricEncrypt.decryptStr(telePhone) : telePhone;
		user.setTelePhone(telePhone);
		//address
		String address = user.getAddress();
		address = StringUtils.isNotBlank(address) ? SymmetricEncrypt.decryptStr(address) : address;
		user.setAddress(address);
		//companyBusinessLicense
		String companyBusinessLicense = user.getCompanyBusinessLicense();
		companyBusinessLicense = StringUtils.isNotBlank(companyBusinessLicense) ? SymmetricEncrypt.decryptStr(companyBusinessLicense) : companyBusinessLicense;
		user.setCompanyBusinessLicense(companyBusinessLicense);
		//companyRepresentative
		String companyRepresentative = user.getCompanyRepresentative();
		companyRepresentative = StringUtils.isNotBlank(companyRepresentative) ? SymmetricEncrypt.decryptStr(companyRepresentative) : companyRepresentative;
		user.setCompanyRepresentative(companyRepresentative);
		//companyFaxNumber
		String companyFaxNumber = user.getCompanyFaxNumber();
		companyFaxNumber = StringUtils.isNotBlank(companyFaxNumber) ? SymmetricEncrypt.decryptStr(companyFaxNumber) : companyFaxNumber;
		user.setCompanyFaxNumber(companyFaxNumber);
		//linkPhone
		String linkPhone = user.getLinkPhone();
		linkPhone = StringUtils.isNotBlank(linkPhone) ? SymmetricEncrypt.decryptStr(linkPhone) : linkPhone;
		user.setLinkPhone(linkPhone);
		//linkIdnumber
		String linkIdnumber = user.getLinkIdnumber();
		linkIdnumber = StringUtils.isNotBlank(linkIdnumber) ? SymmetricEncrypt.decryptStr(linkIdnumber) : linkIdnumber;
		user.setLinkIdnumber(linkIdnumber);
		//linkEmail
		String linkEmail = user.getLinkEmail();
		linkEmail = StringUtils.isNotBlank(linkEmail) ? SymmetricEncrypt.decryptStr(linkEmail) : linkEmail;
		user.setLinkEmail(linkEmail);
		//deparmentName
		String deparmentName = user.getDeparmentName();
		deparmentName = StringUtils.isNotBlank(deparmentName) ? SymmetricEncrypt.decryptStr(deparmentName) : deparmentName;
		user.setDeparmentName(deparmentName);
		//deparmentPhone
		String deparmentPhone = user.getDeparmentPhone();
		deparmentPhone = StringUtils.isNotBlank(deparmentPhone) ? SymmetricEncrypt.decryptStr(deparmentPhone) : deparmentPhone;
		user.setDeparmentPhone(deparmentPhone);
		//deparmentFax
		String deparmentFax = user.getDeparmentFax();
		deparmentFax = StringUtils.isNotBlank(deparmentFax) ? SymmetricEncrypt.decryptStr(deparmentFax) : deparmentFax;
		user.setDeparmentFax(deparmentFax);
		//phone
		String phone= user.getPhone();
		phone = StringUtils.isNotBlank(phone) ? SymmetricEncrypt.decryptStr(phone) : phone;
		user.setPhone(phone);
		//idNumber
		String idNumber = user.getIdNumber();
		idNumber = StringUtils.isNotBlank(idNumber) ? SymmetricEncrypt.decryptStr(idNumber) : idNumber;
		user.setIdNumber(idNumber);
		//email
		String email = user.getEmail();
		email = StringUtils.isNoneBlank(email)?SymmetricEncrypt.decryptStr(email):email;
		user.setEmail(email);
	}
	
	/**
	 * 用户信息加密
	 */
	private void doEncryptUser(UcUser user) {
		//companyOrgCode
		String companyOrgCode = user.getCompanyOrgCode();
		companyOrgCode = StringUtils.isNotBlank(companyOrgCode) ? SymmetricEncrypt.encryptStr(companyOrgCode) : companyOrgCode;
		user.setCompanyOrgCode(companyOrgCode);
		//parentCompanyOrgCode
		String parentCompanyOrgCode = user.getParentCompanyOrgCode();
		parentCompanyOrgCode = StringUtils.isNotBlank(parentCompanyOrgCode) ? SymmetricEncrypt.encryptStr(parentCompanyOrgCode) : parentCompanyOrgCode;
		user.setParentCompanyOrgCode(parentCompanyOrgCode);
		//faxPhone
		String faxPhone = user.getFaxPhone();
		faxPhone = StringUtils.isNotBlank(faxPhone) ? SymmetricEncrypt.encryptStr(faxPhone) : faxPhone;
		user.setFaxPhone(faxPhone);
		//telePhone
		String telePhone = user.getTelePhone();
		telePhone = StringUtils.isNotBlank(telePhone) ? SymmetricEncrypt.encryptStr(telePhone) : telePhone;
		user.setTelePhone(telePhone);
		//address
		String address = user.getAddress();
		address = StringUtils.isNotBlank(address) ? SymmetricEncrypt.encryptStr(address) : address;
		user.setAddress(address);
		//companyBusinessLicense
		String companyBusinessLicense = user.getCompanyBusinessLicense();
		companyBusinessLicense = StringUtils.isNotBlank(companyBusinessLicense) ? SymmetricEncrypt.encryptStr(companyBusinessLicense) : companyBusinessLicense;
		user.setCompanyBusinessLicense(companyBusinessLicense);
		//companyRepresentative
		String companyRepresentative = user.getCompanyRepresentative();
		companyRepresentative = StringUtils.isNotBlank(companyRepresentative) ? SymmetricEncrypt.encryptStr(companyRepresentative) : companyRepresentative;
		user.setCompanyRepresentative(companyRepresentative);
		//companyFaxNumber
		String companyFaxNumber = user.getCompanyFaxNumber();
		companyFaxNumber = StringUtils.isNotBlank(companyFaxNumber) ? SymmetricEncrypt.encryptStr(companyFaxNumber) : companyFaxNumber;
		user.setCompanyFaxNumber(companyFaxNumber);
		//linkPhone
		String linkPhone = user.getLinkPhone();
		linkPhone = StringUtils.isNotBlank(linkPhone) ? SymmetricEncrypt.encryptStr(linkPhone) : linkPhone;
		user.setLinkPhone(linkPhone);
		//linkIdnumber
		String linkIdnumber = user.getLinkIdnumber();
		linkIdnumber = StringUtils.isNotBlank(linkIdnumber) ? SymmetricEncrypt.encryptStr(linkIdnumber) : linkIdnumber;
		user.setLinkIdnumber(linkIdnumber);
		//linkEmail
		String linkEmail = user.getLinkEmail();
		linkEmail = StringUtils.isNotBlank(linkEmail) ? SymmetricEncrypt.encryptStr(linkEmail) : linkEmail;
		user.setLinkEmail(linkEmail);
		//deparmentName
		String deparmentName = user.getDeparmentName();
		deparmentName = StringUtils.isNotBlank(deparmentName) ? SymmetricEncrypt.encryptStr(deparmentName) : deparmentName;
		user.setDeparmentName(deparmentName);
		//deparmentPhone
		String deparmentPhone = user.getDeparmentPhone();
		deparmentPhone = StringUtils.isNotBlank(deparmentPhone) ? SymmetricEncrypt.encryptStr(deparmentPhone) : deparmentPhone;
		user.setDeparmentPhone(deparmentPhone);
		//deparmentFax
		String deparmentFax = user.getDeparmentFax();
		deparmentFax = StringUtils.isNotBlank(deparmentFax) ? SymmetricEncrypt.encryptStr(deparmentFax) : deparmentFax;
		user.setDeparmentFax(deparmentFax);
		//phone
		String phone= user.getPhone();
		phone = StringUtils.isNotBlank(phone) ? SymmetricEncrypt.encryptStr(phone) : phone;
		user.setPhone(phone);
		//idNumber
		String idNumber = user.getIdNumber();
		idNumber = StringUtils.isNotBlank(idNumber) ? SymmetricEncrypt.encryptStr(idNumber) : idNumber;
		user.setIdNumber(idNumber);
		//email
		String email = user.getEmail();
		email = StringUtils.isNoneBlank(email)?SymmetricEncrypt.encryptStr(email):email;
		user.setEmail(email);
	}
}
