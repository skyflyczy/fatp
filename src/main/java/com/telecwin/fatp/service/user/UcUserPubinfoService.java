package com.telecwin.fatp.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;
import com.telecwin.fatp.service.datasupprot.user.UcUserPubinfoDataSupportService;
import com.telecwin.fatp.util.UUIDUtil;

/**
 * UcUserPubinfo
 */
@Service
public class UcUserPubinfoService{
	@Autowired
	private UcUserPubinfoDataSupportService ucUserPubinfoDataSupportService;
	/**
	 * 分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<UcUserPubinfoPo> pageFindByCondition(Map<String,Object> map,int pageNo,int pageSize) {
		return ucUserPubinfoDataSupportService.pageFindByCondition(map, pageNo, pageSize);
	}
	
	public UcUserPubinfoPo getById(Integer companyInfoId) {
		return ucUserPubinfoDataSupportService.getById(companyInfoId);
	}
	
	public void updatePub(UcUserPubinfoPo pub) {
		ucUserPubinfoDataSupportService.updatePub(pub);
	}
	/**
	 * 插入用户公司
	 * @param user
	 * @return
	 */
	public UcUserPubinfoPo insertPubByUserCompany(UcUser user) {
		UcUserPubinfoPo pub = new UcUserPubinfoPo();
		pub.setCompanyGuid(UUIDUtil.getUUID());
		pub.setCompanyOrgCode(user.getCompanyOrgCode());
		pub.setCompanyName(user.getCompanyName());
		ucUserPubinfoDataSupportService.insertPub(pub);
		return pub;
	}
	/**
	 * 插入用户父公司
	 * @param user
	 * @return
	 */
	public UcUserPubinfoPo insertPubByUserParentCompany(UcUser user) {
		UcUserPubinfoPo pub = new UcUserPubinfoPo();
		pub.setCompanyGuid(UUIDUtil.getUUID());
		pub.setCompanyOrgCode(user.getParentCompanyOrgCode());
		pub.setCompanyName(user.getParentCompanyName());
		ucUserPubinfoDataSupportService.insertPub(pub);
		return pub;
	}
	
	public UcUserPubinfoPo getByCompanyOrgCode(String companyOrgCode) {
		return this.ucUserPubinfoDataSupportService.getByCompanyOrgCode(companyOrgCode);
	}
	public UcUserPubinfoPo getByCompanyNameEqual(String companyName) {
		return this.ucUserPubinfoDataSupportService.getByCompanyNameEqual(companyName);
	}
}
