package com.fatp.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.user.MemberOperatorService;
import com.fatp.util.Constant;
import com.fatp.util.SessionUtil;
import com.huajin.baymax.encrypt.IrreversibEncrypt;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.huajin.baymax.session.SessionFactory;

@Controller
public class BaseController {
	protected String viewPath = "/WEB-INF/jsp/";
	@Autowired
	protected MemcachedCache memcachedCache;
	@Autowired
	protected MemberOperatorService memberOperatorService;
	
	private ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();
	protected ModelAndView init(String viewName){
		ModelAndView mv = new ModelAndView(viewPath + viewName);
		//设置上下文路径
		mv.addObject("ctx",request().getContextPath());
		return mv;
	}
	// 获取基于应用程序的url绝对路径
	public final String getAppbaseUrl(HttpServletRequest request, String url){
		return request.getContextPath() + url;
	}
	// 根据url获取当前控制器名	
	public String[] controllerName(HttpServletRequest request){
		String url = request.getRequestURI();
		String[] urlArr = url.split("/");
		return urlArr;
	}
	// 根据url获取当前操作名称
	public String actionName(String url){
		return url;
	}
	@ModelAttribute
	public void setHttpServletRequestAndResponse(HttpServletRequest request,HttpServletResponse response){
		currentRequest.set(request);
		currentResponse.set(response);
	}
	/**
	 * 获取request
	 * @return
	 * @return HttpServletRequest
	 * @author zhiya.chai
	 */
	public HttpServletRequest request() {
	    return currentRequest.get();
	}
	/**
	 * 获取response
	 * @return
	 * @return HttpServletResponse
	 * @author zhiya.chai
	 */
	public HttpServletResponse response(){
		return currentResponse.get();
	}
	/**
	 * request转Map
	 * @param request
	 * @return
	 */
	protected Map<String, Object> paramToMap(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<>();
		Enumeration<String> keys = request.getParameterNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = request.getParameter(key);
			if (StringUtils.isNotEmpty(value)){
				map.put(key, value);
			}
		}
		if (StringUtils.isEmpty(request.getParameter(Constant._PAGEINDEX))) {
			map.put("pageCurrent", Constant.DEFAULT_PAGEINDEX);
		}
		if (StringUtils.isEmpty(request.getParameter(Constant._PAGESIZE))) {
			map.put("pageSize", Constant.DEFAULT_PAGESIZE);
		}
		String orderField = request.getParameter("orderField");
		String orderDirection = request.getParameter("orderDirection");
		if (StringUtils.isNotEmpty(orderField)) {
			map.put("sortColumns",orderField+ " "+ ((StringUtils.isNotEmpty(orderDirection)) ? orderDirection: ""));
		}
		return map;
	}
	public JSONObject resultError(String msg) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("statusCode", Integer.valueOf(300));
		jsonObj.put("message", msg);
		return jsonObj;
	}
	public JSONObject resultSuccess() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("statusCode", Integer.valueOf(200));
		jsonObj.put("message", "操作成功。");
		return jsonObj;
	}
	/**
	 * 获取登录用户
	 * @return
	 */
	public MemberOperatorPo getMemberOperator(){
		String sessionId = SessionFactory.getCurrentSessionId(request(), response());
		MemberOperatorPo operator = (MemberOperatorPo)SessionUtil.getOperator(memcachedCache, sessionId, memberOperatorService);
		if(operator == null)
			throw new FatpException(ErrorCode.LOGIN_EXPIRED);
		return operator;
	}
	/**
	 * 返回登录人id
	 * @return
	 * @return Integer
	 */
	public Integer getSelfId() {
		MemberOperatorPo operator = getMemberOperator();
		return operator.getId();
	}
	public String getSelfName() {
		MemberOperatorPo operator = getMemberOperator();
		return operator.getRealName();
	}
	public int getMemberId(){
		return 0;
	}
	public int getExchangeId() {
		MemberOperatorPo operator = this.getMemberOperator();
		return operator.getExchangeId();
	}
	/**
	 * 上传文件
	 * @param fileMap
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	protected String upload(Map<String, MultipartFile> fileMap, String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		String originalFilename = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			originalFilename = mf.getOriginalFilename();
			String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
			String pattern = originalFilename.substring(originalFilename.lastIndexOf("."));
			fileName = IrreversibEncrypt.MD5Encrypt(name)+pattern;
			File uploadFile = new File(filePath + fileName);
			for(int i=1; ; i++) {
				if(uploadFile.exists()){
					fileName = IrreversibEncrypt.MD5Encrypt(name+"_"+i)+pattern;
					uploadFile = new File(filePath+fileName);
					continue;
				}else{
					FileCopyUtils.copy(mf.getBytes(), uploadFile);
					break;
				}
			}	
		}
		return originalFilename+":"+fileName;
	}
}
