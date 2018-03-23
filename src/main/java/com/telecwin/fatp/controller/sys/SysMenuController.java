package com.telecwin.fatp.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.MenuTreeNode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.WithoutLogin;
import com.telecwin.fatp.po.sys.SysAppsPo;
import com.telecwin.fatp.po.sys.SysMenuPo;
import com.telecwin.fatp.service.sys.SysAppsService;
import com.telecwin.fatp.service.sys.SysMenuService;
import com.telecwin.fatp.util.Constant;

/**
 * 菜单管理
 * @author zhiya.chai
 * 2015年7月1日 上午9:25:56
 */
@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysAppsService sysAppsService;
	
	/**
	 * 进入菜单设置页面
	 * @return
	 * @author zhiya.chai
	 * 2015年7月1日 上午9:46:41
	 */
	@RequestMapping("/setmenu")
	@WithoutLogin
	public ModelAndView setMenu(Integer appId){
		ModelAndView mv = init("sys/menu/menusetting");
		List<SysAppsPo> sysApps = sysAppsService.getAppList();
		mv.addObject("sysApps", sysApps)
		  .addObject("appId", Constant.SYSAPPID);
		return mv;
	}
	
	/**
	 * 获取菜单的节点数据
	 * @return
	 * @author zhiya.chai
	 * 2015年7月1日 上午11:05:36
	 */
	@RequestMapping("/getmenunodes")
	@ResponseBody
	@WithoutLogin
	public String getMenuNodes(@RequestParam("appId") Integer appId){
		List<MenuTreeNode> menuTreeNodes = new ArrayList<MenuTreeNode>();
		try {
			menuTreeNodes = sysMenuService.getMenuNodes(appId);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "getMenuNodes", e));
		}
		return JSONObject.toJSONString(menuTreeNodes);
	}
	
	/**
	 * 保存菜单
	 * @param sysMenu
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@WithoutLogin
	public Map<String,Object> saveMenu(@ModelAttribute SysMenuPo sysMenu){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			Integer id = sysMenuService.savaMenu(sysMenu,getSelfId());
			retMap.put("retcode", 0);
			retMap.put("id", id);
		} catch (Exception e) {
			retMap.put("retcode", -1);
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "saveMenu", e));
		}
		
		return retMap;
	}
	
	/**
	 * 验证菜单名是否唯一
	 * @param appId
	 * @param menuName
	 * @return
	 * @author zhiya.chai
	 * 2015年7月1日 下午2:36:28
	 */
	@RequestMapping("/validmenuname")
	@ResponseBody
	@WithoutLogin
	public Map<String,Object> validMenuName(@RequestParam(required = true) Integer parentId,
											@RequestParam(required = true) String menuName){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMenuService.validMenuName(parentId,menuName);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			retMap.put("error", "校验失败");
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validmenuname", e));
		}
		return retMap;
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年7月1日 下午2:52:20
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@WithoutLogin
	public Map<String,Object> deleteMenu(@RequestParam(required = true) Integer id){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMenuService.deleteMenu(id,getSelfId());
			retMap.put("ok", "删除成功");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			retMap.put("error", "删除失败");
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "deleteMenu", e));
		}
		return retMap;
	}
	
	/**
	 * 菜单信息
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年7月1日 下午7:46:15
	 */
	@RequestMapping("/menuinfo")
	@WithoutLogin
	public ModelAndView menuInfo(@RequestParam(value="id",required=true) Integer id, @RequestParam(value="pid",required=false) Integer pid
			, @RequestParam(value="appId",required=false) Integer appId){
		ModelAndView mv = init("sys/menu/menusettingedit");
		SysMenuPo sysMenu = sysMenuService.getMenuInfo(id);
		if(id == null && pid != null) {
			if(pid.intValue() != 0) {
				SysMenuPo parentSysMenu = sysMenuService.getMenuInfo(pid);
				sysMenu.setAppId(parentSysMenu.getAppId());
				sysMenu.setMenuType(parentSysMenu.getMenuType());
				sysMenu.setAdminType(parentSysMenu.getAdminType());
			}else {
				sysMenu.setAppId(appId);
				sysMenu.setMenuType(sysMenu.getAppId() - 1);
			}
			sysMenu.setParentId(pid);
			
		}
		mv.addObject("sysMenu", sysMenu);
		return mv;
	}
	
}
