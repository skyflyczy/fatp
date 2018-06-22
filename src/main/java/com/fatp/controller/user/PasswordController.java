package com.fatp.controller.user;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fatp.controller.BaseController;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.interceptor.WithoutAuth;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.sys.FeExchangeService;
import com.fatp.service.user.MemberOperatorService;
import com.huajin.baymax.encrypt.IrreversibEncrypt;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;

@Controller
@RequestMapping("/")
public class PasswordController extends BaseController{

	@Autowired
	private FeExchangeService feExchangeService;
	
	@Autowired
	private MemberOperatorService memberOperatorService;
	
	/**
	 * 操作员修改密码
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("/toModifyPwd")
	@WithoutAuth
	public String toModifyPwd(HttpServletRequest request) {
		MemberOperatorPo loginOperator = super.getMemberOperator();
		String key = IrreversibEncrypt.MD5Encrypt(UUID.randomUUID().toString());
		JSONObject json = new JSONObject();
		json.put("loginId", loginOperator.getId());
		memcachedCache.set(key, json.toJSONString());
		request.setAttribute("k", key);
		return viewPath + "setpasswd";
	}
	
	/**
	 * 操作员修改密码
	 * @param request
	 * @return
	 * @return Object
	 */
	@RequestMapping("/modifyPwd")
	@ResponseBody
	@WithoutAuth
	public Object modifyPwd(HttpServletRequest request) {
		try {
			String key = request.getParameter("k");
			String str = (String) memcachedCache.get(key);
			if(StringUtils.isBlank(str)) {
				return resultError("登录信息已变更。");
			}
			JSONObject param = JSON.parseObject(str);
			String loginId = param.getString("loginId");
			String password = request.getParameter("password");
			String oldpassword = request.getParameter("oldpassword");
			if(StringUtils.isEmpty(loginId) || Integer.parseInt(loginId) != super.getSelfId().intValue()) {
				return resultError("登录信息已变更。");
			}
			memberOperatorService.setPasswd(super.getSelfId(), oldpassword, password);
			return resultSuccess();
		} catch(FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "modifyPwd", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "modifyPwd", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	
}
