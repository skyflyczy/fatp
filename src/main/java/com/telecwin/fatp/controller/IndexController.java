package com.telecwin.fatp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.enums.user.OperatorStatus;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.WithoutAuth;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.sys.FeExchangeService;
import com.telecwin.fatp.service.sys.SysMenuService;
import com.telecwin.fatp.util.SessionUtil;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{
	@Autowired
	private MemcachedCache memcachedCache;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private FeExchangeService feExchangeService;
	
	 @RequestMapping("/")
	 @WithoutAuth
	 public String index() {
		 return "redirect:/index.do";
	 }
	
	@RequestMapping("/index")
	@WithoutAuth
	public String toIndex(){
		try {
			MemberOperatorPo operator = super.getMemberOperator();
			if(operator.getOperatorStatus().intValue() == OperatorStatus.待激活.value) {
				return "redirect:/setpswPage.do";
			}
			if(operator.getOperatorStatus().intValue() != OperatorStatus.正常.value) {
				return "redirect:/login.do";
			}
			//获取可操作菜单
			request().setAttribute("list", SessionUtil.getMenuList(memcachedCache, super.getSelfId(), sysMenuService));
			request().setAttribute("exchange", feExchangeService.getExchangeInfo(super.getExchangeId()));
			return "WEB-INF/index";
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "toIndex", e));
			return "redirect:/login.do";
		}
	}
	
	@RequestMapping("/home")
	@WithoutAuth
	public String getSumCfmRightMoney(){
		request().setAttribute("exchange", feExchangeService.getExchangeInfo(super.getExchangeId()));
		request().setAttribute("powerlist", SessionUtil.getPowerList(memcachedCache, super.getSelfId(), sysMenuService));
		return "WEB-INF/home";
	}
}