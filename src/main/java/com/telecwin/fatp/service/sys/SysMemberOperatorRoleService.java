package com.telecwin.fatp.service.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.sys.SysMemberOperatorRolePo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.sys.SysMemberOperatorRoleDataSupportService;
import com.telecwin.fatp.service.datasupprot.user.MemberOperatorDataSupportService;
import com.telecwin.fatp.util.SessionUtil;

/**
 * 操作员与角色关系
 * @author zhiya.chai
 * 2015年7月3日 下午3:09:54
 */
@Service
public class SysMemberOperatorRoleService extends BaseService{
    
	@Autowired
	private SysMemberOperatorRoleDataSupportService sysMemberOperatorRoleDataSupportService;
	@Autowired
	private MemberOperatorDataSupportService memberOperatorDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	/**
	 * 根据操作员id获取角色ids
	 * @param id
	 * @return
	 */
	public List<Integer> getRoleIdsByMemberOperatorId(Integer memberOperatorId) {
		if(memberOperatorId == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		List<SysMemberOperatorRolePo> operatorRoles = sysMemberOperatorRoleDataSupportService.getRolesByOperatorId(memberOperatorId);
		if(CollectionUtils.isEmpty(operatorRoles)) {
			return Collections.emptyList();
		}
		List<Integer> roleIds = new ArrayList<Integer>();
		for(SysMemberOperatorRolePo operatorRole:operatorRoles){
			roleIds.add(operatorRole.getMemberRoleId());
		}
		return roleIds;
	}
	/**
	 * 添加角色信息
	 * @param memberOperatorId
	 * @param roleIds
	 * @param memberId
	 * @param operatorId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addOperatorRoles(Integer memberOperatorId,String roleIds,Integer memberId,int operatorId) {
		if(memberOperatorId == null || memberId == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		/**
		 * 查询操作员信息
		 */
		MemberOperatorPo operator = memberOperatorDataSupportService.getMemberOperatorById(memberOperatorId);
		if(operator == null) {
			throw new FatpException(ErrorCode.MEMBER_OPERATOR_NOT_EXIST);
		}
		if(StringUtils.isBlank(roleIds)){
			//没有给操作员赋予角色，需要把数据库之前设置的角色清除
			sysMemberOperatorRoleDataSupportService.deleteByOpeartor(memberOperatorId);
		} else {
			String[] roleIdArray = roleIds.split(",");
			//该操作员本身已经有的角色
			List<SysMemberOperatorRolePo> operatorRoleList = sysMemberOperatorRoleDataSupportService.getRolesByOperatorId(memberOperatorId);
			Set<Integer> oldRoleSet = new HashSet<>();
			for(SysMemberOperatorRolePo operatorRole: operatorRoleList) {
				oldRoleSet.add(operatorRole.getMemberRoleId().intValue());
			}
			List<SysMemberOperatorRolePo> newOperatorRoleList = new ArrayList<SysMemberOperatorRolePo>();
			SysMemberOperatorRolePo operatorRole = null;
			for(String newRoleId : roleIdArray) {
				//得到新增的角色
				if(!oldRoleSet.contains(Integer.parseInt(newRoleId))) {
					operatorRole = new SysMemberOperatorRolePo();
					operatorRole.setCreateOperatorId(operatorId);
					operatorRole.setCreateTime(new Date());
					operatorRole.setMemberId(memberId);
					operatorRole.setMemberOperatorId(memberOperatorId);
					operatorRole.setMemberRoleId(Integer.parseInt(newRoleId));
					operatorRole.setUpdateOperatorId(operatorId);
					operatorRole.setUpdateTime(new Date());
					newOperatorRoleList.add(operatorRole);
				}
			}
			sysMemberOperatorRoleDataSupportService.deleteByNoDelRoles(memberOperatorId,roleIdArray);
			sysMemberOperatorRoleDataSupportService.updateByOpeartorOrRole(operatorId,memberOperatorId,roleIdArray);
			sysMemberOperatorRoleDataSupportService.insertBatch(newOperatorRoleList);
			
		}
		SessionUtil.removeOperatorMenus(memcachedCache, operator.getId());
	}

}
