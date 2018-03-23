package com.telecwin.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.dao.sys.SysMemberRolemenuDao;
import com.telecwin.fatp.po.sys.SysMemberRolemenuPo;

@Service
public class SysMemberRolemenuDataSupportService {

	@Autowired
	private SysMemberRolemenuDao sysMemberRolemenuDao;
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenus(List<Integer> ids) {
		Map<String,Object> map = new HashMap<>();
		map.put("menuIds", ids);
		this.sysMemberRolemenuDao.delete(map);
	}
	
	public List<SysMemberRolemenuPo> getRoleMenuListByRole(int memberRoleId) {
		Map<String,Object> map = new HashMap<>();
		map.put("memberRoleId", memberRoleId);
		return sysMemberRolemenuDao.select(map);
	}
	/**
	 * 通过角色删除
	 * @param roleId
	 */
	public void deleteByRole(int roleId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		sysMemberRolemenuDao.deleteByRoleOrMenu(map);
	}
	/**
	 * 通过角色和菜单删除
	 * @param roleId
	 * @param memberId
	 * @param noDelMenuIdArr
	 */
	public void deleltByRoleAndMenu(int roleId,int memberId,String[] noDelMenuIdArr){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("noDelMenuIdArr", noDelMenuIdArr);
		map.put("memeberId", memberId);
		sysMemberRolemenuDao.deleteByRoleOrMenu(map);
	}
	/**
	 * 通过角色和菜单更新
	 * @param roleId
	 * @param memberId
	 * @param menuIdArr
	 * @param operatorId
	 * @return
	 */
	public int updateByRoleAndMenu(int roleId,int memberId,String[] menuIdArr,int operatorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("roleMenuIds", menuIdArr);
		map.put("memeberId", memberId);
		map.put("operatorId", operatorId);
		return sysMemberRolemenuDao.updateByRoleOrMenu(map);
	}
	
	public void insertBatch(List<SysMemberRolemenuPo> sysMemberRolemenus){
		if(CollectionUtils.isNotEmpty(sysMemberRolemenus)) {
			sysMemberRolemenuDao.insertBatch(sysMemberRolemenus);
		}
	}
	
	
}
