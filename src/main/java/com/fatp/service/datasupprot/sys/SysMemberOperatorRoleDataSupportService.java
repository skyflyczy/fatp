package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysMemberOperatorRoleDao;
import com.fatp.po.sys.SysMemberOperatorRolePo;

/**
 * SysMemberOperatorRole
 * @author zhiya.chai
 * 2016年8月2日 下午2:28:27
 */
@Service
public class SysMemberOperatorRoleDataSupportService {

	@Autowired
	private SysMemberOperatorRoleDao sysMemberOperatorRoleDao;
	
	/**
	 * 根据角色id查找操作员
	 * @param memberRoleId
	 * @return
	 */
	public List<SysMemberOperatorRolePo> findByMemberRoleId(int memberRoleId) {
		Map<String,Object> map = new HashMap<>();
		map.put("memberRoleId", memberRoleId);
		return sysMemberOperatorRoleDao.select(map);
	}
	/**
	 * 根据操作员Id删除角色
	 * @param memberOperatorId
	 */
	public void deleteByOpeartor(int memberOperatorId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberOperatorId", memberOperatorId);
		sysMemberOperatorRoleDao.deleteByOpeartorOrRole(map);
	}
	/**
	 * 删除不包含数组中角色的信息
	 * @param memberOperatorId
	 * @param noDelRoleArray
	 */
	public void deleteByNoDelRoles(int memberOperatorId,String[] noDelRoleArray) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberOperatorId", memberOperatorId);
		map.put("noDelRoleArray", noDelRoleArray);
		sysMemberOperatorRoleDao.deleteByOpeartorOrRole(map);
	}
	
	/**
	 * 用户角色Id删除
	 * @param memberRoleId
	 */
	public void deleteByRole(int memberRoleId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberRoleId", memberRoleId);
		sysMemberOperatorRoleDao.deleteByOpeartorOrRole(map);
	}
	/**
	 * 更新操作员角色
	 * @param operatorRoles
	 */
	public void insertBatch(List<SysMemberOperatorRolePo> operatorRoles){
		if(CollectionUtils.isNotEmpty(operatorRoles)){
			sysMemberOperatorRoleDao.insertBatch(operatorRoles);
		}
	}
	
	/**
	 * 根据操作员id获取角色信息
	 * @param memberopeid
	 * @return
	 */
	public List<SysMemberOperatorRolePo> getRolesByOperatorId(Integer memberOperatorId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memberOperatorId", memberOperatorId);
		return sysMemberOperatorRoleDao.select(map);
	}
	/**
	 * 更新用户角色
	 * @param updateOperatorId
	 * @param memberOperatorId
	 * @param roleIds
	 * @return
	 */
	public int updateByOpeartorOrRole(int updateOperatorId,int memberOperatorId,String[]roleIds){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memberOperatorId", memberOperatorId);
		map.put("updateOperatorId", updateOperatorId);
		map.put("roleIds", roleIds);
		return sysMemberOperatorRoleDao.updateByOpeartorOrRole(map);
	}
}
