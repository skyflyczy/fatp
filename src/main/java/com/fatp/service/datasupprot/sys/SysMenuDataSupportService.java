package com.fatp.service.datasupprot.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysMenuDao;
import com.fatp.enums.YesNo;
import com.fatp.po.sys.SysMenuPo;

@Service
public class SysMenuDataSupportService {
	
	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * 根据条件获取菜单
	 * @param map
	 * @return
	 */
	public List<SysMenuPo> select(Map<String,Object> map){
		return sysMenuDao.select(map);
	}
	/**
	 * 根据appId查找
	 * @param appId
	 * @return
	 */
	public List<SysMenuPo> getMenuListByAppId(Integer appId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("isValid", YesNo.是.value);
		return select(map);
	}
	/**
	 * 获取用户菜单
	 * @param memberOperatorId
	 * @return
	 */
	public List<SysMenuPo> getUserMenu(Integer memberOperatorId,Integer isButton){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isValid", YesNo.是.value);
		map.put("memberOperatorId", memberOperatorId);
		if(isButton !=null)
			map.put("isButton", isButton);
		return sysMenuDao.getUserMenus(map);
	}
	/**
	 * 根据角色获取菜单
	 * @param adminTypeList
	 * @return
	 */
	public List<SysMenuPo> getMenuByAdminType(List<Integer> adminTypeList) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isValid", YesNo.是.value);
		if(CollectionUtils.isNotEmpty(adminTypeList)) {
			map.put("adminTypeList", adminTypeList);
		}
		return sysMenuDao.select(map);
	}
	/**
	 * 根据Id获取菜单
	 * @param id
	 * @return
	 */
	public SysMenuPo getById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sysMenuDao.getById(map);
	}
	
	public void deleteByIds(List<Integer> ids){
		if(CollectionUtils.isNotEmpty(ids)){
			sysMenuDao.delete(ids);
		}
	}
	
	public int update(SysMenuPo sysMenuPo){
		return sysMenuDao.update(sysMenuPo);
	}
	
	public int insert(SysMenuPo sysMenu){
		sysMenu.setCreateTime(new Date());
		sysMenu.setUpdateTime(new Date());
		sysMenu.setIsValid(YesNo.是.value);
		return sysMenuDao.insert(sysMenu);
	}
}
