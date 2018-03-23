package com.telecwin.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.telecwin.fatp.po.sys.SysMemberRolemenuPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.sys.SysMemberRolemenuDataSupportService;

/**
 * 角色与权限菜单的关系
 * @author zhiya.chai
 * 2015年7月2日 下午6:14:55
 */
@Service
public class SysMemberRolemenuService extends BaseService{
	
	@Autowired
	private SysMemberRolemenuDataSupportService sysMemberRolemenuDataSupportService;

	/**
	 * 获取角色拥有菜单权限ids
	 * @param roleId
	 * @return
	 */
	public String getRoleMenuIdsByRole(Integer roleId){
		if(roleId == null) {
			return "";
		}
		List<SysMemberRolemenuPo> roleMenus = sysMemberRolemenuDataSupportService.getRoleMenuListByRole(roleId);
		if(CollectionUtils.isEmpty(roleMenus)) {
			return "";
		}
		return getIdsByRoleMenus(roleMenus);
	}

	private String getIdsByRoleMenus(List<SysMemberRolemenuPo> roleMenus) {
		StringBuffer sb = new StringBuffer();
		for(SysMemberRolemenuPo rolemenu : roleMenus){
			sb.append(rolemenu.getMemberMenuId()+",");
		}
		return sb.substring(0, sb.length()-1);
	}
}
