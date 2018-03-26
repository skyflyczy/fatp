package com.telecwin.fatp.service.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.enums.user.UserStatusDesc;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.user.UcUserDataSupportService;
import com.telecwin.fatp.util.UUIDUtil;

/**
 * UcUser
 */
@Service
public class UcUserService extends BaseService {
	@Autowired
	private UcUserDataSupportService ucUserDataSupportService;
	@Autowired
	private UcUserPubinfoService ucUserPubinfoService;
	
	/**
	 * 根据条件分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<UcUser> pageFindUsersByCondition(Map<String, Object> map,int pageNo,int pageSize) {
		return ucUserDataSupportService.pageFindUsersByCondition(map, pageNo, pageSize);
	}
	
	/**
	 * 插入用户
	 * 
	 * @param user
	 * @param userTypeId
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int insertUser(UcUser user,int ownerUserId,int operatorId,int exchangeId) {
		if(user.getCompanyInfoId() == null && user.getOrgTypeId() != OrgType.自然人.type) { //没选择已有的公司
			UcUserPubinfoPo pub = ucUserPubinfoService.insertPubByUserCompany(user);
			user.setCompanyInfoId(pub.getId());
		}
		if(user.getOrgTypeId() == OrgType.其他组织.type) {
			if(user.getCompanyParentId() == null) {//新公司
				if(!user.getCompanyOrgCode().equals(user.getParentCompanyOrgCode())
						&& !user.getCompanyName().equals(user.getParentCompanyName())) {
					UcUserPubinfoPo pub = ucUserPubinfoService.insertPubByUserParentCompany(user);
					user.setCompanyParentId(pub.getId());
				} else {
					user.setCompanyParentId(user.getCompanyInfoId());
				}
			} 
		}
		user.setIsValidEmail(1);
		user.setIsValidId(1);
		user.setIsValidPhone(1);
		user.setUserGuid(UUIDUtil.getUUID());
		user.setUserStatus(UserStatusDesc.待处理.value);
		user.setOwnerUserId(ownerUserId); 
		user.setExchangeId(exchangeId);
		user.setCreateOperatorId(operatorId);
		user.setUpdateOperatorId(operatorId);
		this.ucUserDataSupportService.insert(user);
		//这里不使用ucUserDataSupportService 是因为上面已经加密过
		this.ucUserDataSupportService.insertNoEncryptExt(user);
		return user.getId();
	}
	/**
	 * 完全匹配真实名称获取用户信息
	 * @param realName
	 * @param userIdentity
	 * @param exchangeId
	 * @return
	 */
	public UcUser getMemberByEqualRealName(String realName,int userIdentity,int exchangeId) {
		return ucUserDataSupportService.getMemberByEqualRealName(realName, userIdentity, exchangeId);
	}
	/**
	 * 完全匹配用户简称获取用户信息
	 * @param userName
	 * @param exchagneId
	 * @param ownerUserId
	 */
	public UcUser getUserNameByEqualUserName(String userName,Integer exchagneId,Integer ownerUserId){
		return ucUserDataSupportService.getUserNameByEqualUserName(userName, exchagneId, ownerUserId);
	}
	
}
