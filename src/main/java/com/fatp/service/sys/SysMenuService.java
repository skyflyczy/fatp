package com.fatp.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.MenuTreeNode;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.sys.SysMenuPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SysMemberRolemenuDataSupportService;
import com.fatp.service.datasupprot.sys.SysMenuDataSupportService;
import com.fatp.util.StringUtil;

/**
 * 菜单管理
 * @author zhiya.chai
 * 2015年7月1日 上午11:06:34
 */
@Service
public class SysMenuService extends BaseService {
	
	@Autowired
	private SysMenuDataSupportService sysMenuDataSupportService;
	
	@Autowired
	private SysMemberRolemenuDataSupportService sysMemberRolemenuDataSupportService;
	
	/**
	 * 获取所有菜单
	 * @return List<MenuTreeNode>
	 * @author zhiya.chai
	 */
	public List<MenuTreeNode> getMenuNodes(int appId) {
		List<SysMenuPo> sysMenus = sysMenuDataSupportService.getMenuListByAppId(appId);
		/**
		 * 把菜单转换为node，并处理层次结构
		 */
		return MenuTreeNode.convertToNodes(sysMenus, false, false);
	}
	/**
	 * 根据appId获取所有菜单
	 * @param appId
	 * @return
	 */
	public List<SysMenuPo> getMenuListByAppId(Integer appId) {
		return sysMenuDataSupportService.getMenuListByAppId(appId);
	}
	/**
	 * 获取用户链接，按钮权限信息
	 * @param operatorId
	 * @return
	 * @return List<SysMenu>
	 * @author zhiya.chai
	 */
	public List<SysMenuPo> getUserAllNodes(Integer operatorId){
		return sysMenuDataSupportService.getUserMenu(operatorId, null);
	}
	/**
	 * 获取不同角色类型对应的菜单
	 * @param roleType
	 * @return
	 * @author zhiya.chai
	 */
	public List<MenuTreeNode> getMenuNodes(String roleType) {
		if(roleType==null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		List<SysMenuPo> sysMenus = sysMenuDataSupportService.getMenuByAdminType(StringUtil.stringSplitToInteger(roleType, ","));
		/**
		 * 把菜单转换为node，并处理层次结构
		 */
		return MenuTreeNode.convertToNodes(sysMenus, false, false);
	}
	
	/**
	 * 保存菜单
	 * @param sysMenu
	 * @param operatorId
	 * @return
	 * @author zhiya.chai
	 */
	public Integer savaMenu(SysMenuPo sysMenu, Integer operatorId) {
		if(sysMenu == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		sysMenu.setUpdateOperatorId(operatorId);
		Integer id = -1;
		if(sysMenu.getId() == null) {
			sysMenu.setCreateOperatorId(operatorId);
			id = addMenu(sysMenu);
		} else {
			id = updateMenu(sysMenu);
		}
		if(id < 0) {
			throw new FatpException(ErrorCode.SYSTEM_ERROR);
		}
		return id;
	}
	
	private Integer addMenu(SysMenuPo sysMenu) {
		//验证菜单名称是否唯一
		validMenuName(sysMenu.getParentId(), sysMenu.getMenuName());
		sysMenuDataSupportService.insert(sysMenu);
		return sysMenu.getId();
	}
	/**
	 * 更新菜单
	 * @param sysMenu
	 * @return
	 */
	private Integer updateMenu(SysMenuPo sysMenu){
		SysMenuPo menu = sysMenuDataSupportService.getById(sysMenu.getId());
		if(menu == null) {
			throw new FatpException(ErrorCode.MENU_NOT_EXIST);
		}
		//menuName不同需要校验
		if(!sysMenu.getMenuName().equals(menu.getMenuName())){
			validMenuName(sysMenu.getParentId(), sysMenu.getMenuName());
		}
		return updateAndSaveMenu(sysMenu,menu);
	}
	
	/**
	 * 更新并保存菜单
	 * @param sysMenu 前端传递的菜单
	 * @param menu 需要修改的原菜单
	 * @return
	 */
	private int updateAndSaveMenu(SysMenuPo sysMenu, SysMenuPo menu) {
		menu.setAdminType(sysMenu.getAdminType());
		menu.setIsButton(sysMenu.getIsButton());
		menu.setMenuImgUrl(sysMenu.getMenuImgUrl());
		menu.setMenuName(sysMenu.getMenuName());
		menu.setMenuType(sysMenu.getMenuType());
		menu.setMenuUrl(sysMenu.getMenuUrl());
		menu.setParentId(sysMenu.getParentId());
		menu.setRemark(sysMenu.getRemark());
		menu.setShowIndex(sysMenu.getShowIndex());
		menu.setTarget(sysMenu.getTarget());
		menu.setUpdateOperatorId(sysMenu.getUpdateOperatorId());
		menu.setUpdateTime(new Date());
		menu.setRelationUrl(sysMenu.getRelationUrl());
		return sysMenuDataSupportService.update(menu);
	}

	/**
	 * 验证菜单名是否唯一
	 * @param parentId
	 * @param menuName
	 * @return
	 * @author zhiya.chai
	 */
	public void validMenuName(Integer parentId, String menuName) {
		if(parentId == null || StringUtils.isBlank(menuName)) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", parentId);
		queryMap.put("menuName", menuName);
		List<SysMenuPo> list = sysMenuDataSupportService.select(queryMap);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new FatpException(ErrorCode.MENU_ALREADY_EXIST);
		}
	}

	/**
	 * 删除菜单
	 * @param id
	 * @param operatorId
	 * @return
	 * @author zhiya.chai
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteMenu(Integer id, Integer operatorId) {
		SysMenuPo sysMenu = sysMenuDataSupportService.getById(id);
		if(sysMenu == null) {
			throw new FatpException(ErrorCode.MENU_NOT_EXIST);
		}
		//0级
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(id);
		//现在最深为3级，下探1级
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("parentId", id);
		List<SysMenuPo> list = sysMenuDataSupportService.select(map);
		for(SysMenuPo o : list) {
			ids.add(o.getId());
			//下探2级
			map.clear();
			map.put("parentId", o.getId());
			List<SysMenuPo> sublist = sysMenuDataSupportService.select(map);
			for(SysMenuPo p : sublist) {
				ids.add(p.getId());
			}
		}
		//删除菜单
		sysMenuDataSupportService.deleteByIds(ids);
		//删除菜单角色关系
		this.sysMemberRolemenuDataSupportService.deleteByMenus(ids);;
	}

	/**
	 * 获取菜单信息
	 * @param id
	 * @return
	 */
	public SysMenuPo getMenuInfo(Integer id) {
		if(id == null)
			return new SysMenuPo();
		return sysMenuDataSupportService.getById(id);
	}

}
