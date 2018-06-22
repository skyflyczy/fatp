package com.fatp.controller.sys;

import java.util.ArrayList;
import java.util.Collections;
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
import com.fatp.controller.BaseController;
import com.fatp.controller.param.RoleQueryParam;
import com.fatp.domain.MenuTreeNode;
import com.fatp.domain.PageData;
import com.fatp.exception.FatpException;
import com.fatp.interceptor.WithoutAuth;
import com.fatp.po.sys.SysMemberRolePo;
import com.fatp.service.sys.SysMemberRoleService;
import com.fatp.service.sys.SysMemberRolemenuService;
import com.fatp.service.sys.SysMenuService;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;

/**
 * 系统角色管理
 */
@Controller
@RequestMapping("/sys/role")
public class SysMemberRoleController extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysMemberRolemenuService sysMemberRolemenuService;
	@Autowired
	private SysMemberRoleService sysMemberRoleService;
	
	/**
	 * 列表查询
	 * @param roleListParam
	 * @return
	 */
	@RequestMapping("/list")
	@WithoutAuth
	public ModelAndView list(@ModelAttribute RoleQueryParam param){
		ModelAndView mv = init("sys/role/list");
		try {
			param.setMemberId(getMemberId());
			PageData<SysMemberRolePo> pageData = sysMemberRoleService.pageFindByQueryParam(param);
			mv.addObject("sysRoles", pageData.getList())
			.addObject("total", pageData.getTotalsize());
		} catch (Exception e) {
			mv.addObject("sysRoles", Collections.emptyList())
			  .addObject("total", 0);
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "list", e));
		}
		mv.addObject("pageSize",param.getPageSize());
		mv.addObject("pageCurrent", param.getPageCurrent());
		mv.addObject("search", param);
		return mv;
	}
	
	/**
	 * 进入添加角色页面
	 * @return
	 * @author zhiya.chai
	 * 2015年6月30日 上午10:20:19
	 */
	@RequestMapping("/add")
	@WithoutAuth
	public ModelAndView viewAdd(){
		ModelAndView mv = init("sys/role/add");
		return mv;
	}
	
	/**
	 * 添加角色
	 * @param sysMemberRole
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> saveRole(@ModelAttribute SysMemberRolePo sysMemberRole){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberRole.setMemberId(getMemberId());
			sysMemberRole.setCreateOperatorId(getSelfId());
			sysMemberRole.setUpdateOperatorId(getSelfId());
			sysMemberRoleService.addRole(sysMemberRole);
			retMap.put("statusCode", 200);
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "saveRole", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "saveRole", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "保存失败");
		}
		return retMap;
	}
	
	/**
	 * 编辑页面
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年6月30日 上午10:24:48
	 */
	@RequestMapping("/edit")
	@WithoutAuth
	public ModelAndView edit(Integer id){
		ModelAndView mv = init("sys/role/edit");
		try {
			SysMemberRolePo po = sysMemberRoleService.getSysMemberRoleById(id);
			mv.addObject("sysMemberRole", po);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "edit", e));
		}
		return mv;
	}
	
	/**
	 * 更新
	 * @param sysMemberRole
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> updateRole(@ModelAttribute SysMemberRolePo sysMemberRole){
		sysMemberRole.setMemberId(getMemberId());
		sysMemberRole.setUpdateOperatorId(getSelfId());
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberRoleService.updateRole(sysMemberRole);
			retMap.put("statusCode", 200);
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateRole", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateRole", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "更新失败");
		}
		return retMap;
	}
	
	/**
	 * 验证角色名是否存在
	 * @param roleName
	 * @return
	 * @author zhiya.chai
	 * 2015年6月30日 上午10:45:23
	 */
	@RequestMapping("/validrolename")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> validRoleName(@RequestParam(required = true) String roleName){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberRoleService.validRoleName(getMemberId(),roleName);
			retMap.put("ok", "");
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validRoleName", e));
			retMap.put("error", e.getErrorCode().getMessage());
		} catch (Exception e) {
			retMap.put("error", "校验失败");
		}
		return retMap;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年6月30日 上午10:53:30
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> delete(Integer id){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberRoleService.deleteRole(id);
			retMap.put("statusCode", 200);
			retMap.put("message", "删除成功。");
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "删除失败。");
		}
		return retMap;
	}
	
	/**
	 * 进入设置权限页面
	 * @return
	 */
	@RequestMapping("/showpurview")
	@WithoutAuth
	public ModelAndView showPurview(Integer id){
		ModelAndView mv = init("sys/role/setpower");
		try {
			SysMemberRolePo role = sysMemberRoleService.getSysMemberRoleById(id);
			mv.addObject("sysMemberRole", role);
			//角色权限菜单ids
			String roleMenuIds = sysMemberRolemenuService.getRoleMenuIdsByRole(id);
			mv.addObject("roleMenuIds", roleMenuIds);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "showPurview", e));
		}
		return mv;
	}
	
	/**
	 * 获取权限数据
	 * @return
	 */
	@RequestMapping("/getpurviewnodes")
	@ResponseBody
	@WithoutAuth
	public String getPurviewNodes(@RequestParam("roleType") Integer roleType){
		List<MenuTreeNode> menuTreeNodes = new ArrayList<MenuTreeNode>();
		try {
			if(roleType != null) {
				menuTreeNodes = sysMenuService.getMenuNodes(String.valueOf(roleType));
			}
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "getPurviewNodes", e));
		}
		return JSONObject.toJSONString(menuTreeNodes);
	}
	/**
	 * 设置权限
	 * @param sysMemberRole
	 * @return
	 * @author zhiya.chai
	 * 2015年7月2日 下午2:50:32
	 */
	@RequestMapping("/setpurview")
	@ResponseBody
	public Map<String,Object> setPurview(@RequestParam("roleId") Integer roleId,
										 @RequestParam("purviewIds") String purviewIds){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberRoleService.setPurview(roleId, getSelfId(), getMemberId(), purviewIds);
			retMap.put("statusCode", 200);
		}  catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "setPurview", e));
			retMap.put("error", e.getErrorCode().getMessage());
		}  catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "setPurview", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "设置失败。");
		}
		return retMap;
	}
}
