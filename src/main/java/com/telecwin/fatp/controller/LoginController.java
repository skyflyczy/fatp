package com.telecwin.fatp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huajin.baymax.session.SessionFactory;
import com.telecwin.fatp.domain.FeExchange;
import com.telecwin.fatp.enums.user.OperatorStatus;
import com.telecwin.fatp.interceptor.WithoutAuth;
import com.telecwin.fatp.interceptor.WithoutLogin;
import com.telecwin.fatp.po.sys.SysMenuPo;
import com.telecwin.fatp.po.user.MemberOperateorLoginPo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.sys.FeExchangeService;
import com.telecwin.fatp.service.sys.SysMenuService;
import com.telecwin.fatp.service.user.MemberOperateorLoginService;
import com.telecwin.fatp.service.user.MemberOperatorService;
import com.telecwin.fatp.service.user.SysParamService;
import com.telecwin.fatp.util.Constant;
import com.telecwin.fatp.util.SessionUtil;


@RequestMapping("/")
@Controller
public class LoginController extends CaptchaController{
	
	@Autowired
	private MemberOperateorLoginService memberOperateorLoginService;
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private MemberOperatorService memberOperatorService;
	@Autowired
	private FeExchangeService feExchangeService;
	@Autowired
	private SysMenuService sysMenuService;

	
	@RequestMapping("/login")
	@WithoutLogin
	public Object loginPage(HttpServletRequest request){
		request.setAttribute("exchange", feExchangeService.getDefaultExchangeInfo());
		return "/WEB-INF/login";
	}
	
	/**
	 * 登录
	 * @param request
	 * @return
	 * @return Object
	 * @author zhiya.chai
	 */
	@RequestMapping("/dologin")
	@WithoutLogin
	public Object login(HttpServletRequest request){
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return "/WEB-INF/login";
		}
		FeExchange exchange = feExchangeService.getDefaultExchangeInfo();
		request.setAttribute("exchange", exchange);
		String code = request.getParameter("code");
		String loginName = request.getParameter("loginName");
		String secrectCode = request.getParameter("secrectCode");
		if (StringUtils.isBlank(loginName) || StringUtils.isBlank(secrectCode)) {
			request.setAttribute("message", "用户或密码不能为空");
			return "/WEB-INF/login";
		}
		//验证码验证
		boolean validCodeResult = validCode(request, response(), code);
		if (!validCodeResult) {
			request.setAttribute("message", "验证码不正确或验证码已过期");
			return "/WEB-INF/login";
		}
		MemberOperateorLoginPo loginRecord = memberOperateorLoginService.getLoginRecord(loginName,super.getMemberId(),exchange.getId());
		if (loginRecord == null) {
			loginRecord = memberOperateorLoginService.createNewLoginRecord(loginName,exchange.getId());
		}
		boolean validFail = validLoginFail(loginRecord);
		if(!validFail) {
			request.setAttribute("message", "账号已冻结，请稍后再试");
			return "/WEB-INF/login";
		}
		MemberOperatorPo operator = memberOperatorService.getMemberOperatorByLoginName(loginName, exchange.getId());
		if (operator == null) {
			String msg = "用户或者密码不正确";
			request.setAttribute("message", msg);
			loginFail(msg, loginRecord);
			memberOperateorLoginService.saveLoginRecord(loginRecord);
			return "/WEB-INF/login";
		}
		loginRecord.setMemberId(operator.getMemberId());
		loginRecord.setOperatorId(operator.getId());
		if (!memberOperatorService.compareSecrectCode(secrectCode, operator)) {
			String msg = "用户或者密码不正确";
			request.setAttribute("message", msg);
			loginFail(msg, loginRecord);
			memberOperateorLoginService.saveLoginRecord(loginRecord);
			return "/WEB-INF/login";
		}
		//存放Cache
		createCache(operator);
		//保存成功登录记录
		loginSuccess(loginRecord);
		memberOperateorLoginService.saveLoginRecord(loginRecord);
		if(operator.getOperatorStatus().intValue() == OperatorStatus.待激活.value) {
			return "redirect:/setpswPage.do";
		}
		
		return "redirect:/index.do";
	}
	/**
	 * 退出
	 * @param request
	 * @return
	 * @return Object
	 * @author zhiya.chai
	 * 2015年8月3日 下午3:40:51
	 */
	@RequestMapping("/logout")
	@WithoutAuth
	public String logOut(HttpServletRequest request){
		String sessionId = SessionFactory.getCurrentSessionId(request, response());
		SessionUtil.deleteSessionId(memcachedCache, sessionId);
		return "redirect:/login.do";
	}
	/**
	 * 验证登录失败次数和时长
	 * @param loginRecord
	 * @return
	 */
	private boolean validLoginFail(MemberOperateorLoginPo loginRecord) {
		int loginFrozenFailNum = sysParamService.loginFrozenFailNum();
		if (loginRecord.getFailNumLx() < loginFrozenFailNum) { 
			// 连续失败次数小于冻结次数
			return true;
		}
		int loginFrozenMinute = sysParamService.loginFrozenMinute();
		if (loginRecord.getLastFailTime().compareTo(DateUtils.addMinutes(new Date(), -loginFrozenMinute)) > 0) {
			// 上次失败时间还不到冻结时长
			return false;
		}
		return true;
	}
	/**
	 * 登录失败记录
	 * @param retMsg
	 * @param loginRecord
	 * @return void
	 */
	private void loginFail(String retMsg,MemberOperateorLoginPo loginRecord){
		loginRecord.setFailNumLx(loginRecord.getFailNumLx()+1);
		loginRecord.setFailNum(loginRecord.getFailNum()+1);
		loginRecord.setSuccessNumLx(0);
		loginRecord.setRetMsg(retMsg);
		loginRecord.setPreLoginTime(loginRecord.getLastLoginTime());
		loginRecord.setLastFailTime(new Date());
		loginRecord.setLastLoginTime(new Date());
	}
	
	/**
	 * 登录成功记录
	 * @param loginRecord
	 * @return void
	 */
	private void loginSuccess(MemberOperateorLoginPo loginRecord){
		loginRecord.setFailNumLx(0);
		loginRecord.setSuccessNumLx(loginRecord.getSuccessNumLx()+1);
		loginRecord.setSuccessNum(loginRecord.getSuccessNum()+1);
		loginRecord.setPreLoginTime(loginRecord.getLastLoginTime());
		loginRecord.setLastLoginTime(new Date());
		loginRecord.setLastSuccessTime(new Date());
		loginRecord.setRetMsg(null);
	}
	
	/**
	 * 存放cache
	 * @param operator
	 * @return void
	 * @author Administrator
	 * 2015年7月31日 下午5:19:08
	 */
	private void createCache(MemberOperatorPo operator){
		//用户
		String sessionId = SessionFactory.getCurrentSessionId(request(), response());
		SessionUtil.setOperator(memcachedCache, sessionId, operator, SessionUtil.getRemoteIPFromRequest(request()));
		
		//权限
		List<SysMenuPo> menuList = null;
		if(operator.getVipOperator() == 1) { //初始vip操作员系统管理全部权限
			menuList = sysMenuService.getMenuListByAppId(Constant.SYSAPPID);
		}else {
			menuList = sysMenuService.getUserAllNodes(operator.getId());
		}
		SessionUtil.setPowerList(memcachedCache, operator.getId(), menuList);
	}
	
}
