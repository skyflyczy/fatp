package com.fatp.util;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fatp.domain.MenuTreeNode;
import com.fatp.po.sys.SysMenuPo;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.sys.SysMenuService;
import com.fatp.service.user.MemberOperatorService;
import com.huajin.baymax.encrypt.SymmetricEncrypt;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.huajin.baymax.session.SessionFactory;
import com.huajin.baymax.support.ConfigProperties;

public class SessionUtil {
	private final static String USER_MENULIST_CACHE = "MemberOperator_Menus_";
	private final static String OPERATOR_CACHE = "MemberOperator_";
	private final static String USER_CACHE = "UcUser_";
	private final static String PREFIX_CACHE = "FATP_";
	private final static Date LOGIN_EXPIRED_TIME = new Date(Long.parseLong(ConfigProperties.getProperty("login_expired_minute"))*60*1000);
	/**
	 * 缓存操作者
	 * 
	 * @return void
	 */
	public static void setOperator(MemcachedCache memcachedCache, String sessionId, MemberOperatorPo operator, String remoteIp) {
		JSONObject json = new JSONObject();
		json.put("operator", operator.getId());
		json.put("remoteIp", remoteIp);
		memcachedCache.set(PREFIX_CACHE + sessionId, json.toJSONString(), LOGIN_EXPIRED_TIME);
		//缓存用户
		memcachedCache.set(OPERATOR_CACHE + operator.getId(), JSON.toJSONString(operator));
	}
	public static void refresh(MemcachedCache memcachedCache, String sessionId) {
		String str = (String)memcachedCache.get(PREFIX_CACHE + sessionId);
		memcachedCache.set(PREFIX_CACHE + sessionId, str, LOGIN_EXPIRED_TIME);
	}
	/**
	 * 退出
	 * @param memcachedCache
	 * @param sessionId
	 * @return void
	 */
	public static void deleteSessionId(MemcachedCache memcachedCache, String sessionId) {
		memcachedCache.remove(PREFIX_CACHE + sessionId);
	}
	
	public static String getRemoteIPFromRequest(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if(StringUtils.isBlank(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if(StringUtils.isBlank(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getRemoteIp(MemcachedCache memcachedCache, String sessionId) {
		String str = (String)memcachedCache.get(PREFIX_CACHE + sessionId);
		if(StringUtils.isBlank(str)) {
			return null;
		}
		JSONObject json = JSON.parseObject(str);
		return json.getString("remoteIp");
	}
	
	public static Integer getOperatorId(MemcachedCache memcachedCache, String sessionId) {
		String str = (String)memcachedCache.get(PREFIX_CACHE + sessionId);
		if(StringUtils.isBlank(str)) {
			return null;
		}
		JSONObject json = JSON.parseObject(str);
		return json.getInteger("operator");
	}
	/**
	 * 获得操作者
	 * @return
	 * @return JSONObject
	 */
	public static MemberOperatorPo getOperator(MemcachedCache memcachedCache, String sessionId, MemberOperatorService memberOperatorService) {
		String str = (String)memcachedCache.get(PREFIX_CACHE + sessionId);
		if(StringUtils.isBlank(str)) {
			return null;
		}
		JSONObject json = JSON.parseObject(str);
		Integer operatorId = json.getInteger("operator");
		str = (String)memcachedCache.get(OPERATOR_CACHE + operatorId);
		if(StringUtils.isBlank(str)) {
			MemberOperatorPo operator = memberOperatorService.getMemberOperatorById(operatorId);;
			//缓存用户
			str = JSON.toJSONString(operator);
			memcachedCache.set(OPERATOR_CACHE + operator.getId(), str);
		}
		try {
			return JSON.parseObject(str, MemberOperatorPo.class);
		} catch (Exception e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(SessionUtil.class.getName(), "getOperator", e));
		}
		return null;
	}
	/**
	 * 清除用户缓存
	 * @param memcachedCache
	 * @param operatorId
	 * @return void
	 */
	public static void removeOperator(MemcachedCache memcachedCache, Integer operatorId) {
		try {
			memcachedCache.remove(OPERATOR_CACHE + operatorId);
		} catch (Exception e) {
		}
	}
	/**
	 * 清除用户缓存
	 * @param memcachedCache
	 * @param operatorId
	 * @return void
	 */
	public static void removeUcUser(MemcachedCache memcachedCache, Integer userId) {
		try {
			memcachedCache.remove(USER_CACHE + userId);
		} catch (Exception e) {
		}
	}
	/**
	 * 缓存操作者权限
	 * 
	 * @return void
	 */
	public static String setPowerList(MemcachedCache memcachedCache, Integer operatorId, List<SysMenuPo> menuList) {
		Set<String> set = new HashSet<String>(menuList.size());
		for(SysMenuPo o : menuList) {
			set.add(o.getMenuUrl());
			if(StringUtils.isNotBlank(o.getRelationUrl())) {
				String[] array = StringUtils.split(o.getRelationUrl(), ",");
				for(String s : array) {
					set.add(s);
				}
			}
		}
		JSONObject json = new JSONObject();
		json.put("powerlist", set);
		//缓存左侧菜单树
		json.put("menulist", MenuTreeNode.convertToNodes(menuList, true, true));
		memcachedCache.set(USER_MENULIST_CACHE + operatorId, json.toJSONString());
		return json.toJSONString();
	}
	/**
	 * 获取操作者权限
	 * @return
	 * @return JSONObject
	 */
	public static Set<String> getPowerList(MemcachedCache memcachedCache, int operatorId, 
			SysMenuService sysMenuService) {
		String str = (String)memcachedCache.get(USER_MENULIST_CACHE + operatorId);
		if(StringUtils.isBlank(str)) {
			str = SessionUtil.setPowerList(memcachedCache, operatorId, sysMenuService.getUserAllNodes(operatorId));
		}
		JSONObject json = JSON.parseObject(str);
		return new HashSet<String>(JSON.parseArray(json.getString("powerlist"), String.class));
	}
	/**
	 * 获取左侧菜单树
	 * @param memcachedCache
	 * @return
	 * @return List<MenuTreeNode>
	 */
	public static List<MenuTreeNode> getMenuList(MemcachedCache memcachedCache, int operatorId, 
			SysMenuService sysMenuService) {
		String str = (String)memcachedCache.get(USER_MENULIST_CACHE + operatorId);
		if(StringUtils.isBlank(str)) {
			str = SessionUtil.setPowerList(memcachedCache, operatorId, sysMenuService.getUserAllNodes(operatorId));
		}
		JSONObject json = JSON.parseObject(str);
		return JSON.parseArray(json.getString("menulist"), MenuTreeNode.class);
	}
	
	/**
	 * 是否具有访问权限，白名单
	 * @return
	 * @return boolean
	 */
	public static boolean Haveower(MemcachedCache memcachedCache, int operatorId, String reqUrl, 
			SysMenuService sysMenuService) {
		Set<String> powerList = getPowerList(memcachedCache, operatorId, sysMenuService);
		if(CollectionUtils.isNotEmpty(powerList) && powerList.contains(reqUrl)){
			return true;
		}
		return false;
	}
	
	public static String getEncryptSessionId(HttpServletRequest request, HttpServletResponse response) {
		String encryptStr = SymmetricEncrypt.encryptStr(SessionFactory.getCurrentSessionId(request, response));
		return encryptStr.replace("+", "@");
	}
	/**
	 * 清除用户权限缓存
	 * @param memcachedCache
	 * @param operatorId
	 * @return void
	 */
	public static void removeOperatorMenus(MemcachedCache memcachedCache, Integer operatroId) {
		try {
			memcachedCache.remove(USER_MENULIST_CACHE + operatroId);
		} catch (Exception e) {
		}
	}
}
