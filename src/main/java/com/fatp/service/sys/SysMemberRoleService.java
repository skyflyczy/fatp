package com.fatp.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.controller.param.RoleQueryParam;
import com.fatp.domain.PageData;
import com.fatp.enums.YesNo;
import com.fatp.enums.user.OperatorType;
import com.fatp.enums.user.RoleType;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.sys.SysMemberOperatorRolePo;
import com.fatp.po.sys.SysMemberRolePo;
import com.fatp.po.sys.SysMemberRolemenuPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SysMemberOperatorRoleDataSupportService;
import com.fatp.service.datasupprot.sys.SysMemberRoleDataSupportService;
import com.fatp.service.datasupprot.sys.SysMemberRolemenuDataSupportService;
import com.fatp.util.SessionUtil;
import com.huajin.baymax.memcache.client.MemcachedCache;

@Service
public class SysMemberRoleService extends BaseService {

	@Autowired
	private SysMemberRoleDataSupportService sysMemberRoleDataSupportService;
	@Autowired
	private SysMemberRolemenuDataSupportService sysMemberRolemenuDataSupportService;
	@Autowired
	private SysMemberOperatorRoleDataSupportService sysMemberOperatorRoleDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	/**
	 * 分页查找信息
	 * @param param
	 * @return
	 */
	public PageData<SysMemberRolePo> pageFindByQueryParam(RoleQueryParam param){
		return sysMemberRoleDataSupportService.pageFindByQueryParam(param);
	}
	
	/**
	 * 添加角色
	 * @param sysMemberRole
	 * @return
	 * @author zhiya.chai
	 * 2015年6月29日 下午7:03:59
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int addRole(SysMemberRolePo sysMemberRole) {
		if(sysMemberRole == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		/**
		 * 验证角色名在同意memberId下的唯一性
		 */
		validRoleName(sysMemberRole.getMemberId(),sysMemberRole.getRoleName());
		sysMemberRole.setIsValid(YesNo.是.value);
		sysMemberRole.setCreateTime(new Date());
		sysMemberRole.setUpdateTime(new Date());
		return sysMemberRoleDataSupportService.insert(sysMemberRole);
	}
	/**
	 * 验证同一memberId下角色名是否唯一
	 * @param memberId
	 * @param roleName
	 * @return
	 */
	public void validRoleName(Integer memberId, String roleName) {
		if(memberId == null || StringUtils.isBlank(roleName)) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		SysMemberRolePo po = sysMemberRoleDataSupportService.getByRoleName(memberId, roleName);
		if(po != null) {
			throw new FatpException(ErrorCode.ROLE_NAME_ALREADY_EXIST);
		}
	}
	/**
	 * 根据id获取角色信息
	 * @param id
	 * @return
	 */
	public SysMemberRolePo getSysMemberRoleById(int id) {
		return sysMemberRoleDataSupportService.getById(id);
	}
	
	/**
	 * 更新角色
	 * @param sysMemberRole
	 * @return
	 */
	public int updateRole(SysMemberRolePo sysMemberRole) {
		if(sysMemberRole == null || sysMemberRole.getId() == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		SysMemberRolePo role = sysMemberRoleDataSupportService.getById(sysMemberRole.getId());
		if(role == null) {
			throw new FatpException(ErrorCode.ROLE_NOT_EXIST);
		}
		if(!sysMemberRole.getMemberId().equals(role.getMemberId()) || !sysMemberRole.getRoleName().equals(role.getRoleName())){
			/**
			* 验证角色名在同意memberId下的唯一性
			*/
			validRoleName(sysMemberRole.getMemberId(),sysMemberRole.getRoleName());
		
		}
		role.setRoleDesc(sysMemberRole.getRoleDesc());
		role.setRoleName(sysMemberRole.getRoleName());
		role.setUpdateOperatorId(sysMemberRole.getUpdateOperatorId());
		role.setUpdateTime(new Date());
		return sysMemberRoleDataSupportService.update(role);
	}
	/**
	 * 删除角色
	 * @param id
	 * @param operatorId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteRole(Integer id) {
		//删除角色权限
		this.sysMemberRolemenuDataSupportService.deleteByRole(id);
		//删除角色操作员
		List<SysMemberOperatorRolePo> list = sysMemberOperatorRoleDataSupportService.findByMemberRoleId(id);
		for(SysMemberOperatorRolePo o : list) {
			SessionUtil.removeOperatorMenus(memcachedCache, o.getMemberOperatorId());
		}
		this.sysMemberOperatorRoleDataSupportService.deleteByRole(id);
		//删除角色
		this.sysMemberRoleDataSupportService.delete(id);
	}
	/**
	 * 设置权限
	 * @param roleId
	 * @param operatorId
	 * @param memberId
	 * @param roleMenuIds
	 */
	@Transactional(rollbackFor = Exception.class)
	public void setPurview(Integer roleId, Integer operatorId, Integer memberId,String roleMenuIds) {
		if(roleId == null || operatorId == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		if(StringUtils.isBlank(roleMenuIds)) {
			//删除该roleId下的所有权限
			sysMemberRolemenuDataSupportService.deleteByRole(roleId);
		} else {
			List<SysMemberRolemenuPo> rolemenus = sysMemberRolemenuDataSupportService.getRoleMenuListByRole(roleId);
			Set<String> allRoleMenus = new HashSet<>();
			for(SysMemberRolemenuPo po :rolemenus) {
				allRoleMenus.add(String.valueOf(po.getMemberMenuId()));
			}
			String[] roleMenuIdArr = roleMenuIds.split(",");
			List<SysMemberRolemenuPo> addRoleMenuList = new ArrayList<>();
			for(String roleMenuId : roleMenuIdArr) {
				//找到需要增加的
				if(!allRoleMenus.contains(roleMenuId)) {
					SysMemberRolemenuPo roleMenu =  new SysMemberRolemenuPo();
					roleMenu.setCreateOperatorId(operatorId);
					roleMenu.setCreateTime(new Date());
					roleMenu.setMemberId(memberId);
					roleMenu.setMemberMenuId(Integer.parseInt(roleMenuId));
					roleMenu.setMemberRoleId(roleId);
					roleMenu.setUpdateOperatorId(operatorId);
					roleMenu.setUpdateTime(new Date());
					addRoleMenuList.add(roleMenu);
				}
			}
			sysMemberRolemenuDataSupportService.deleltByRoleAndMenu(roleId, memberId, roleMenuIdArr);
			sysMemberRolemenuDataSupportService.updateByRoleAndMenu(roleId, memberId, roleMenuIdArr, operatorId);
			sysMemberRolemenuDataSupportService.insertBatch(addRoleMenuList);
		}
		List<SysMemberOperatorRolePo> list = sysMemberOperatorRoleDataSupportService.findByMemberRoleId(roleId);
		for(SysMemberOperatorRolePo o : list) {
			SessionUtil.removeOperatorMenus(memcachedCache, o.getMemberOperatorId());
		}
	}
	
	/**
	 * 根据操作员类型获取相关角色列表
	 * @param memberId
	 * @param operatorType
	 * @return
	 */
	public List<SysMemberRolePo> getMemberRolesByRoleType(Integer memberId, Integer operatorType) {
		if(memberId == null || operatorType == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);	
		}
		if(operatorType.intValue() == OperatorType.业务人员.value) {
			operatorType = RoleType.业务角色.type;
		} else if(operatorType.intValue() == OperatorType.系统管理员.value) {
			operatorType =  RoleType.管理角色.type;
		}
		return sysMemberRoleDataSupportService.getMemberRolesByRoleType(memberId, operatorType);
	}
}
