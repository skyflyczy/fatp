
package com.fatp.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.fatp.enums.user.OperatorStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.sys.SysMenuService;
import com.fatp.service.user.MemberOperatorService;
import com.fatp.util.SessionUtil;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.huajin.baymax.session.SessionFactory;
import com.huajin.baymax.support.ConfigProperties;


/**
 * 全局拦截器
 * @author zhiya.chai
 * 2015年7月29日 下午3:33:47
 */
@Component
public class GlobalInterceptor extends HandlerInterceptorAdapter  {
	@Autowired
	private MemcachedCache memcachedCache;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private MemberOperatorService memberOperatorService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String contextPath = request.getContextPath();
		String reqUrl = request.getRequestURI();
		reqUrl = reqUrl.replace(contextPath, ""); //请求url去掉根路径
		log.debug("Enter GlobalInterceptor, request url is {}", reqUrl);
		if (! (handler instanceof HandlerMethod) ){
			log.info("不认识的 Handler: {}", handler);
			return true;
		}
		
	    HandlerMethod handlerMethod = (HandlerMethod) handler;
	    
	    Object handleBean = handlerMethod.getBean();
		if (handleBean != null && (handleBean instanceof HealthMvcEndpoint ) ) {
			log.info("Do not require login for non-HandlerMethod-object.");
			return true;
		}
	    
	    request.setAttribute("hm", StringUtils.isBlank(ConfigProperties.getProperty("isTest")) || !ConfigProperties.getProperty("isTest").equals("true"));
		//1.跳过不需要校验登录的地址
	    WithoutLogin withoutLogin = handlerMethod.getMethodAnnotation(WithoutLogin.class); //登录验证
    	if(withoutLogin != null) { //不需要登录验证的
    		return true;
    	}
    	//2.检查登录
    	String sessionId = SessionFactory.getCurrentSessionId(request, response);
		//2.2.检查登录用户
    	MemberOperatorPo operator = SessionUtil.getOperator(memcachedCache, sessionId, memberOperatorService);
		if(operator == null || operator.getId() == null) {
			responseLogin(request, response);
			return false;
		}
		//2.3.刷新登录状态
		SessionUtil.refresh(memcachedCache, sessionId);
		//3.检查是否具有访问权限
		WithoutAuth withoutAuth = handlerMethod.getMethodAnnotation(WithoutAuth.class); //登录验证
		if(withoutAuth != null){
			return true;
		}
		//3.2.检查状态
		if(operator.getOperatorStatus().intValue() == OperatorStatus.待激活.value) {
			responseActive(request, response);
			return false;
		}
		if(operator.getOperatorStatus().intValue() != OperatorStatus.正常.value) {
			responseLogin(request, response);
			return false;
		}
		request.setAttribute("powerlist", SessionUtil.getPowerList(memcachedCache, operator.getId(), sysMenuService));
		if(SessionUtil.Haveower(memcachedCache, operator.getId(), reqUrl, sysMenuService)) 
			return true;
		responsePower(request, response, handlerMethod);
		return false;
	}
	
	private void responseLogin(HttpServletRequest request, HttpServletResponse response) {
		String isAjax = request.getHeader("X-Requested-With");
		if (StringUtils.isNotBlank(isAjax) && isAjax.equalsIgnoreCase("XMLHttpRequest")) {
			responseJSON(response, "登录超时", 301);
		} else {
			response.addHeader("location", request.getContextPath() + "/login.do");
		}
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	}
	
	private void responseActive(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("location", request.getContextPath() + "/setpswPage.do");
		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
	}
	
	private void responsePower(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class); //ajax请求
		if(responseBody != null) {
			responseJSON(response, "没有权限", 300);
		}else {
			response.addHeader("location", request.getContextPath() + "/permission.html");
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		}
	}
	
	private void responseError(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, String msg) {
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class); //ajax请求
		if(responseBody != null) {
			responseJSON(response, msg, 300);
		}else {
			response.addHeader("location", request.getContextPath() + "/error.html");
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		}
	}

	private void responseJSON(HttpServletResponse response, String message, int statusCode) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message); 
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(jsonObj.toString());  
	        out.flush();
	    } catch (IOException e) {  
	    	Xlogger.error(XMsgError.buildSimple(getClass().getName(), "responseJSON", e)); 
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null && ex instanceof FatpException) {
			FatpException fatpException = (FatpException)ex;
			if(fatpException.getErrorCode().getCode() == ErrorCode.LOGIN_EXPIRED.getCode()) {
				//登录过期
				responseLogin(request, response);
				return;
			}
			responseError(request, response, (HandlerMethod) handler, fatpException.getMessage());
		}
	}
}
