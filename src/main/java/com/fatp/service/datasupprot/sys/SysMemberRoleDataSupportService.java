package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.controller.param.RoleQueryParam;
import com.fatp.dao.sys.SysMemberRoleDao;
import com.fatp.domain.PageData;
import com.fatp.enums.YesNo;
import com.fatp.po.sys.SysMemberRolePo;
import com.fatp.util.MapUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * SysMemberRole
 * @author zhiya.chai
 * 2016年8月2日 下午3:53:42
 */
@Service
public class SysMemberRoleDataSupportService {	

	@Autowired
	private SysMemberRoleDao sysMemberRoleDao;
	/**
	 * 分页查找信息
	 * @param param
	 * @return
	 */
	public PageData<SysMemberRolePo> pageFindByQueryParam(RoleQueryParam param){
		Map<String,Object> map = MapUtil.getValueMap(param);
		Page<?> page = PageHelper.startPage(param.getPageCurrent(), param.getPageSize(), true);
		List<SysMemberRolePo> list = sysMemberRoleDao.select(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	
	/**
	 * 根据id获取角色信息
	 * @param id
	 * @return
	 */
	public SysMemberRolePo getById(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sysMemberRoleDao.getById(map);
	}
	/**
	 * 根据roleName获取角色信息
	 * @param memberId
	 * @param roleName
	 * @return
	 */
	public SysMemberRolePo getByRoleName(int memberId,String roleName) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("roleName", roleName);
		return findByMap(map);
	}
	private SysMemberRolePo findByMap(Map<String,Object> map) {
		List<SysMemberRolePo> list = sysMemberRoleDao.select(map);
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 根据RoleType获取相关列表
	 * @param memberId
	 * @param roleType
	 * @return
	 */
	public List<SysMemberRolePo> getMemberRolesByRoleType(Integer memberId, Integer roleType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("isValid", YesNo.是.value);
		map.put("roleType", roleType);
		return sysMemberRoleDao.select(map);
	}
	
	public int insert(SysMemberRolePo sysMemberRole){
		return sysMemberRoleDao.insert(sysMemberRole);
	}
	public int update(SysMemberRolePo sysMemberRole){
		return sysMemberRoleDao.update(sysMemberRole);
	}
	
	public void delete(Integer id){
		sysMemberRoleDao.delete(id);
	}
}
