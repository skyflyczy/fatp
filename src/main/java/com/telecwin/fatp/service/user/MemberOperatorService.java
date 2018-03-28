package com.telecwin.fatp.service.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huajin.baymax.encrypt.IrreversibEncrypt;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.huajin.baymax.token.GUIDGenerator;
import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.controller.param.OperatorQueryParam;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.enums.DelStatus;
import com.telecwin.fatp.enums.YesNo;
import com.telecwin.fatp.enums.user.OperatorStatus;
import com.telecwin.fatp.enums.user.OperatorType;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.sys.SysMemberOperatorRoleDataSupportService;
import com.telecwin.fatp.service.datasupprot.sys.SysMemberRoleDataSupportService;
import com.telecwin.fatp.service.datasupprot.user.MemberOperatorDataSupportService;
import com.telecwin.fatp.service.sys.SysParamService;
import com.telecwin.fatp.service.sys.SysbizcodeSequenceService;
import com.telecwin.fatp.util.SessionUtil;
import com.telecwin.fatp.util.UUIDUtil;

/**
 * 
 * MemberOperator
 * @author auto-generator
 * 2015-07-02 00:17:19
 */
@Service
public class MemberOperatorService extends BaseService{
	
	@Autowired
	private MemberOperatorDataSupportService memberOperatorDataSupportService;
	@Autowired
	private SysMemberOperatorRoleDataSupportService sysMemberOperatorRoleDataSupportService;
	@Autowired
	private SysMemberRoleDataSupportService sysMemberRoleDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * 根据登录名称获取操作员信息
	 * @param loginName
	 * @param exchangeId
	 * @return
	 * @return MemberOperator
	 */
	public MemberOperatorPo getMemberOperatorByLoginName(String loginName,int exchangeId){
		return memberOperatorDataSupportService.getMemberOperatorByLoginName(loginName, exchangeId, new int[]{OperatorStatus.正常.value, OperatorStatus.待激活.value});
	} 
	/**
	 * 根据ID查找信息
	 * @param id
	 * @return
	 */
	public MemberOperatorPo getMemberOperatorById(int id) {
		return memberOperatorDataSupportService.getMemberOperatorById(id);
	}
	
	/**
	 * 比较两者密码是否一致
	 * @param loginSecrectCode
	 * @param operator
	 * @return
	 * @return boolean
	 * @author zhiya.chai
	 */
	public boolean compareSecrectCode(String loginSecrectCode,MemberOperatorPo operator){
		loginSecrectCode = IrreversibEncrypt.MD5Encrypt(operator.getSecrectSalt() + loginSecrectCode);
		return loginSecrectCode.equals(operator.getSecrectCode());
	}
	
	/**
	 * 设置密码
	 * @param id
	 * @param oldPassword
	 * @param password
	 */
	@Transactional(rollbackFor = Exception.class)
	public void setPasswd(Integer id,String oldPassword,String password) {
		MemberOperatorPo operator = getMemberOperatorById(id);
		if(operator == null) {
			throw new FatpException(ErrorCode.MEMBER_OPERATOR_NOT_EXIST);
		}
		if(!compareSecrectCode(oldPassword, operator)){
			throw new FatpException(ErrorCode.UPDATE_PWD_NOT_MACTH);
		}
		if(StringUtils.isBlank(operator.getSecrectSalt())){
			String salt = GUIDGenerator.getGUID();
			operator.setSecrectSalt(salt);
		}
		operator.setSecrectCode(IrreversibEncrypt.MD5Encrypt(operator.getSecrectSalt() + password));
		operator.setUpdateTime(new Date());
		if(operator.getOperatorStatus().intValue() == OperatorStatus.待激活.value) {
			operator.setOperatorStatus(OperatorStatus.正常.value);
		}
		memberOperatorDataSupportService.updateMemberOperator(operator);
	}
	/**
	 * 更新操作员
	 * @param newOperator
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Integer updateMemberOperator(MemberOperatorPo newOperator,boolean isRemoveMenus) {
		MemberOperatorPo oldOperator = memberOperatorDataSupportService.getMemberOperatorById(newOperator.getId());
		if(oldOperator == null) {
			throw new FatpException(ErrorCode.MEMBER_OPERATOR_NOT_EXIST);
		}
		//如果要修改的用户的手机号与参数传递的手机号不等时需要进行唯一性验证，身份证号，邮箱同理
		validPhone(newOperator.getMemberId(), newOperator.getPhone(), oldOperator.getPhone());
		validIdNumber(newOperator.getMemberId(), newOperator.getIdType(), oldOperator.getIdType(), newOperator.getIdNumber(), oldOperator.getIdNumber());
		validEmail(newOperator.getMemberId(), newOperator.getEmail(), oldOperator.getEmail());
		/**
		 * 如果操作员类型发生改变，需要删除之前的所有角色
		 */
		if(newOperator.getOperatorType().intValue() != oldOperator.getOperatorType().intValue()){
			sysMemberOperatorRoleDataSupportService.deleteByOpeartor(oldOperator.getId());
		}
		/**
		 * 更新用户信息
		 */
		if(StringUtils.isNotBlank(newOperator.getLoginName())) {
			oldOperator.setLoginName(newOperator.getLoginName());
		}
		oldOperator.setDepartment(newOperator.getDepartment());
		oldOperator.setDuty(newOperator.getDuty());
		oldOperator.setEmail(newOperator.getEmail());
		oldOperator.setIdType(newOperator.getIdType());
		oldOperator.setIdNumber(newOperator.getIdNumber());
		oldOperator.setPhone(newOperator.getPhone());
		oldOperator.setRealName(newOperator.getRealName());
		oldOperator.setRemark(newOperator.getRemark());
		oldOperator.setUpdateTime(new Date());
		oldOperator.setOperatorType(newOperator.getOperatorType());
		oldOperator.setGender(newOperator.getGender());
		memberOperatorDataSupportService.updateMemberOperator(oldOperator);
		if(isRemoveMenus) {
			SessionUtil.removeOperator(memcachedCache, oldOperator.getId());
			SessionUtil.removeOperatorMenus(memcachedCache, oldOperator.getId());
		}
		return oldOperator.getId();
	}
	/**
	 * 新增操作员
	 * @param operator
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public MemberOperatorPo insertMemberOperator(MemberOperatorPo operator) {
		//验证登录名，手机号，idNumber,邮箱
		validLoginName(operator.getMemberId(), operator.getLoginName());
		validPhone(operator.getMemberId(), operator.getPhone(),null);
		validIdNumber(operator.getMemberId(), operator.getIdType(), null,operator.getIdNumber(),null);
		validEmail(operator.getMemberId(), operator.getEmail(),null);
		operator.setIsValidPhone(YesNo.是.value);
		operator.setIsValidEmail(YesNo.是.value);
		operator.setIsValidId(YesNo.是.value);
		operator.setOperatorStatus(OperatorStatus.正常.value);
		/**
		 * 获取盐 进行加密,设置默认密码
		 */
		String salt = GUIDGenerator.getGUID();
		operator.setSecrectCode(IrreversibEncrypt.MD5Encrypt(salt + IrreversibEncrypt.MD5Encrypt(sysParamService.getSysDefaultPwd())));
		operator.setSecrectSalt(salt);
		operator.setCreateTime(new Date());
		operator.setUpdateTime(new Date());
		operator.setDelStatus(DelStatus.正常.value);
		operator.setVipOperator(0);
		operator.setOperatorGuid(UUIDUtil.getUUID());
		operator.setOperatorCode(sysbizcodeSequenceService.getOperatorSequence());
		memberOperatorDataSupportService.insertMemberOperator(operator);
		return operator;
	}
	
	/**
	 * 验证手机号
	 * @param memberId
	 * @param newPhone
	 * @param oldPhone
	 */
	public void validPhone(int memberId,String newPhone,String oldPhone){
		if(StringUtils.isBlank(newPhone) || !newPhone.equals(oldPhone) || StringUtils.isBlank(oldPhone)) {
			MemberOperatorPo po = memberOperatorDataSupportService.getMemberOperatorByPhone(newPhone, memberId);
			if(po != null) {
				throw new FatpException(ErrorCode.MEMBER_PHONE_ALREADY_EXIST);
			}
		}
	}
	/**
	 * 验证IdNumer
	 * @param memberId
	 * @param newIdType
	 * @param oldIdType
	 * @param newIdNumber
	 * @param oldIdNumber
	 */
	public void validIdNumber(int memberId,Integer newIdType,Integer oldIdType,String newIdNumber,String oldIdNumber) {
		if(oldIdType == null 
				|| StringUtils.isBlank(oldIdNumber) 
				|| StringUtils.isBlank(newIdNumber) 
				|| newIdType== null 
				|| newIdType.intValue() != oldIdType.intValue()
				|| !newIdNumber.equals(oldIdNumber)){
			MemberOperatorPo po = memberOperatorDataSupportService.getMemberOperatorByIdNumber(newIdNumber, newIdType, memberId);
			if(po != null) {
				throw new FatpException(ErrorCode.MEMBER_IDNUMBER_ALREADY_EXIST);
			}
		}
	}
	/**
	 * 验证Email
	 * @param memberId
	 * @param newEmail
	 * @param oldEmail
	 */
	public void validEmail(int memberId,String newEmail,String oldEmail){
		if(StringUtils.isBlank(newEmail) 
				|| StringUtils.isBlank(oldEmail) 
				|| !newEmail.equals(oldEmail)){
			MemberOperatorPo po = memberOperatorDataSupportService.getMemberOperatorByEmail(newEmail, memberId);
			if(po != null) {
				throw new FatpException(ErrorCode.MEMBER_EMAIL_ALREADY_EXIST);
			}
		}
	}
	/**
	 * 验证登录名称
	 * @param memberId
	 * @param loginName
	 */
	public void validLoginName(int memberId,String loginName){
		MemberOperatorPo po = memberOperatorDataSupportService.getMemberOperatorByLoginName(loginName, memberId);
		if(po != null) {
			throw new FatpException(ErrorCode.MEMBER_LONGINNAME_ALREADY_EXIST);
		}
	}
	/**
	 * 分页查找信息
	 * @param param
	 * @return
	 */
	public PageData<MemberOperatorPo> pageFindByQueryParam(OperatorQueryParam param){
		return memberOperatorDataSupportService.pageFindByQueryParam(param);
	}
	/**
	 * 删除操作员
	 * @param id
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteMemberOperator(Integer id) {
		MemberOperatorPo memberOperator = memberOperatorDataSupportService.getMemberOperatorById(id);
		if(memberOperator == null) {
			throw new FatpException(ErrorCode.MEMBER_OPERATOR_NOT_EXIST);
		}
		memberOperator.setDelStatus(DelStatus.删除.value);
		memberOperator.setUpdateTime(new Date());
		String suffix = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
		memberOperator.setLoginName(memberOperator.getLoginName()+"_"+suffix);
		memberOperator.setEmail(memberOperator.getEmail()+"_"+suffix);
		memberOperator.setIdNumber(memberOperator.getIdNumber()+"_"+suffix);
		memberOperator.setPhone(memberOperator.getPhone()+"_"+suffix);
		memberOperator.setRealName(memberOperator.getRealName()+"_"+suffix);
		memberOperatorDataSupportService.updateMemberOperator(memberOperator);
		SessionUtil.removeOperator(memcachedCache, id);
		SessionUtil.removeOperatorMenus(memcachedCache, id);
	}
	/**
	 * 更新操作员状态
	 * @param globalDto
	 * @param id
	 * @param operatorStatus
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateMemberOperatorStatu(Integer id, Integer operatorStatus) {
		MemberOperatorPo memberOperator = memberOperatorDataSupportService.getMemberOperatorById(id);
		if(memberOperator == null) {
			throw new FatpException(ErrorCode.MEMBER_OPERATOR_NOT_EXIST);
		}
		if(operatorStatus.intValue() == OperatorStatus.冻结.value) {
			operatorStatus = OperatorStatus.正常.value;
		} else if(operatorStatus.intValue() == OperatorStatus.正常.value) {
			operatorStatus = OperatorStatus.冻结.value;
		}
		memberOperator.setOperatorStatus(operatorStatus);
		memberOperator.setUpdateTime(new Date());
		memberOperatorDataSupportService.updateMemberOperator(memberOperator);
		SessionUtil.removeOperator(memcachedCache, id);
		SessionUtil.removeOperatorMenus(memcachedCache, id);
	}
	
	/**
	 * 获取经办人信息
	 * @param memberIds
	 * @param exchangeId
	 * @return
	 * @return MemberOperatorPo
	 */
	public List<MemberOperatorPo> getRegisterAgentList(String memberIds,int exchangeId){
		return memberOperatorDataSupportService.getRegisterAgentList(memberIds, exchangeId);
	}
	/**
	 * 获取经办人信息
	 * @param memberId
	 * @param exchangeId
	 * @return
	 */
	public MemberOperatorPo getRegisterAgentByMemberId(int memberId,int exchangeId) {
		String memberIds = memberId +"";
		List<MemberOperatorPo> list =  memberOperatorDataSupportService.getRegisterAgentList(memberIds, exchangeId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	/**
	 * 获取管理人员信息
	 * @param memberId
	 * @param exchangeId
	 * @return
	 */
	public MemberOperatorPo getSuperAdmin(int memberId,int exchangeId){
		return memberOperatorDataSupportService.getSuperAdmin(memberId, exchangeId);
	}
	
	/**
	 * 编辑管理员
	 * @param globalDto
	 * @param o
	 * @return void
	 */
	@Transactional(rollbackFor = Exception.class)
	public void editSuperAdmin(MemberOperatorPo newOperator) {
		//获取经办人信息
		MemberOperatorPo agent = this.getRegisterAgentByMemberId(newOperator.getMemberId(),newOperator.getExchangeId());
		//如果设置经办人为管理员，查询经办人信息，修改经办人操作员类型
		if(newOperator.getIsRegisterAgent()) {
			setAgentIsAdmin(newOperator, agent);
			return ;
		}
		newOperator.setIsRegisterAgent(false);
		//如果原来的经办人是管理员，需要修改经办人为非管理员
		if(agent != null 
				&& newOperator.getId() != null 
				&& agent.getId().intValue() == newOperator.getId().intValue()) {
			agent.setVipOperator(0);
			agent.setUpdateTime(new Date());
			agent.setOperatorType(OperatorType.业务人员.value);
			memberOperatorDataSupportService.updateMemberOperator(agent);
		}
		if(newOperator.getId() == null) {
			this.insertMemberOperator(newOperator);
		} else {
			this.updateMemberOperator(newOperator, false);
		}
	}
	/**
	 * 设置经办人为管理员
	 * @param globalDto
	 * @param newOperator
	 * @param agent
	 * @return void
	 */
	@Transactional(rollbackFor = Exception.class)
	public void setAgentIsAdmin(MemberOperatorPo newOperator,MemberOperatorPo agent){
		if(agent == null ) {
			throw new FatpException(ErrorCode.MEMBER_AGENT_NOT_EXIST);
		}
		//如果原来的经办人不是管理员，需要删除原来的管理员信息
		if(agent.getVipOperator().intValue() != 1) {
			MemberOperatorPo admin = memberOperatorDataSupportService.getSuperAdmin(newOperator.getMemberId(),agent.getExchangeId());
			if(admin != null) {
				memberOperatorDataSupportService.deleteById(admin.getId(),admin.getExchangeId());
			}
		}
		agent.setOperatorType(newOperator.getOperatorType());
		agent.setUpdateTime(new Date());
		agent.setVipOperator(newOperator.getVipOperator());
		agent.setExchangeId(newOperator.getExchangeId());
		memberOperatorDataSupportService.updateMemberOperator(agent);
	}
}
