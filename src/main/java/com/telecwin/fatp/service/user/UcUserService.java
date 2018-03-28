package com.telecwin.fatp.service.user;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.enums.FlowFeedTypeDesc;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.enums.user.UserIdentityDesc;
import com.telecwin.fatp.enums.user.UserStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.TimelineDetailDataSupportService;
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
	@Autowired
	private MemberOperatorService memberOperatorService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	
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
	public int insertUser(UcUser user,int ownerUserId,int operatorId,int exchangeId,String operatorName) {
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
		user.setUserStatus(UserStatusDesc.未提交.value);
		user.setOwnerUserId(ownerUserId); 
		user.setExchangeId(exchangeId);
		user.setCreateOperatorId(operatorId);
		user.setUpdateOperatorId(operatorId);
		this.ucUserDataSupportService.insert(user);
		//这里不使用ucUserDataSupportService 是因为上面已经加密过
		this.ucUserDataSupportService.insertNoEncryptExt(user);
		//创建动态
		timelineDetailDataSupportService.createMemberTimeLine(user, FlowFeedTypeDesc.创建发行人, "", operatorName);
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
	/**
	 * 获取用户所有信息（uc_user和uc_user_pubinfo）
	 * @param id
	 * @param exchangeId
	 * @return
	 */
	public UcUser getAllById(int id, int exchangeId) {
		return ucUserDataSupportService.getAllById(id, exchangeId);
	}
	
	/**
	 * 获取用户扩展信息
	 * @param userId
	 * @return
	 */
	public UcUser getUserExtById(int userId) {
		return ucUserDataSupportService.getUserExtById(userId);
	}
	
	/**
	 * 更新用户
	 * @param user
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateUserAll(UcUser user,String operatorName) {
		if(user.getCompanyInfoId() != null) {
			UcUserPubinfoPo pub = ucUserPubinfoService.getById(user.getCompanyInfoId());
			int id = pub.getId();
			try {
				BeanUtils.copyProperties(pub, user);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
			pub.setId(id);
			this.ucUserPubinfoService.updatePub(pub);
		}
		if(user.getOrgTypeId() == OrgType.其他组织.type && user.getCompanyParentId() != null
				&& StringUtils.isNoneBlank(user.getParentCompanyOrgCode())
				&& StringUtils.isNoneBlank(user.getParentCompanyName())) {
			UcUserPubinfoPo pub = ucUserPubinfoService.getById(user.getCompanyParentId());
			pub.setCompanyName(user.getParentCompanyName());
			pub.setCompanyOrgCode(user.getParentCompanyOrgCode());
			ucUserPubinfoService.updatePub(pub);
		}
		this.ucUserDataSupportService.update(user);
		//创建动态
		timelineDetailDataSupportService.createMemberTimeLine(user, FlowFeedTypeDesc.编辑保存发行人信息, "", operatorName);
	}
	
	public void updateUserExt(UcUser user) {
		ucUserDataSupportService.updateExt(user);
	}
	/**
	 * 检查提交会员审核的条件是否都满足. 不满足条件会抛出FatpException runtime exception.
	 * @param memberId
	 * @param exchangeId
	 */
	public void checkMemberAuditConditions(int memberId,int exchangeId) {
		UcUser user = this.ucUserDataSupportService.getAllById(memberId, exchangeId);
		if(user == null) {
			throw new FatpException(ErrorCode.MEMBER_NOT_EXIST);
		}
		//判断信息是否完整
		if(user.getCompanyInfoId() == null || StringUtils.isEmpty(user.getCompanyRepresentative())
				|| user.getProvinceId() == null || user.getCityId() == null || user.getDisId() == null
				|| StringUtils.isEmpty(user.getCompanyRegAddress())) {
			throw new FatpException(ErrorCode.MEMBER_MSG_NOT_COMPLETE);
		}
		if(user.getUserIdentity().intValue() == UserIdentityDesc.投资者.value){
			//客户需要验证部门信息，并且不需要验证注册资料与银行条件
			UcUser userExt = this.ucUserDataSupportService.getUserExtById(memberId);
			if(userExt == null || StringUtils.isEmpty(userExt.getDeparmentName()) || StringUtils.isEmpty(userExt.getLinkMan()) 
					|| StringUtils.isEmpty(userExt.getLinkPhone())) {
				throw new FatpException(ErrorCode.MEMBER_MSG_NOT_COMPLETE);
			}
			return ;
		}
		/**
		 * 会员验证
		 */
		//判断会员是否设置了管理员
		if(user.getUserIdentity() == UserIdentityDesc.发行人.value) {
			if(!hasSuperAdmin(user.getId(),exchangeId)){
				throw new FatpException(ErrorCode.MEMBER_SUPERADMIN_NOT_EXIST);
			}
			MemberOperatorPo agent = memberOperatorService.getRegisterAgentByMemberId(memberId, exchangeId);
			if(agent == null){
				throw new FatpException(ErrorCode.MEMBER_REGISTERAGENT_NOT_EXIST);
			}
			//判断经办人必要因素
			if(agent.getGender() == null 
					|| StringUtils.isBlank(agent.getRealName()) 
					|| agent.getIdType() == null
					|| StringUtils.isBlank(agent.getIdNumber())
					|| StringUtils.isBlank(agent.getEmail())){
				throw new FatpException(ErrorCode.MEMBER_REGISTERAGENT_NOT_COMPLETE);
			}
		}
		
	}
	private boolean hasSuperAdmin(int memberId,int exchangeId){
		MemberOperatorPo po = memberOperatorService.getSuperAdmin(memberId, exchangeId);
		return po != null;
	}
	
	public int updateUserStatus(UcUser o,String operatorName) {
		if(o.getUserStatus() == UserStatusDesc.待审核.value) {
			timelineDetailDataSupportService.createMemberTimeLine(o, FlowFeedTypeDesc.发行人提交审核, "", operatorName);
		}
		return ucUserDataSupportService.updateUserStatus(o);
	}
	/**
	 * 会员审核
	 * @param id
	 * @param type
	 * @param exchangeId
	 * @param operatorId
	 * @param auditRemark
	 */
	public void checkMember(int id, int type, int exchangeId, int operatorId, String auditRemark,String operatorName) {
		UcUser member = ucUserDataSupportService.getAllById(id, exchangeId);
		if( member ==null ) {
			throw new FatpException(ErrorCode.MEMBER_NOT_EXIST);
		}
		FlowFeedTypeDesc flowType = FlowFeedTypeDesc.发行人审核不通过;
		if (type == 1) { //通过
			member.setUserStatus(UserStatusDesc.正常.value);
			flowType = FlowFeedTypeDesc.发行人审核通过;
		}else if (type == 2){
			member.setUserStatus(UserStatusDesc.审核退回.value);
			flowType = FlowFeedTypeDesc.发行人审核退回;
		} else {
			member.setUserStatus(UserStatusDesc.审核不通过.value);
			flowType = FlowFeedTypeDesc.发行人审核不通过;
		}
		member.setAuditRemark(auditRemark);
		member.setUpdateOperatorId(operatorId);
		member.setAuditOperatorId(operatorId);
		member.setAuditTime(new Date());
		ucUserDataSupportService.updateUserStatus(member);
		//创建动态
		timelineDetailDataSupportService.createMemberTimeLine(member, flowType, auditRemark, operatorName);
	}
}
