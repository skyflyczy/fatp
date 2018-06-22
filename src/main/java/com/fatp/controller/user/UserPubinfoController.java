package com.fatp.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.exception.FatpException;
import com.fatp.po.user.UcUserPubinfoPo;
import com.fatp.service.user.UcUserPubinfoService;
import com.fatp.util.Constant;

@Controller
@RequestMapping("/user")
public class UserPubinfoController extends BaseController{
	
	private String viewPath = super.viewPath + "user/";
	
	@Autowired
	private UcUserPubinfoService ucUserPubinfoService;

	/**
	 * 根据组织代码获取公司信息
	 * @param term
	 * @return
	 */
	@RequestMapping("/companySearchCode")
	@ResponseBody
	public Object companySearchCode(String term) {
		UcUserPubinfoPo po =  ucUserPubinfoService.getByCompanyOrgCode(term);
		JSONObject json = new JSONObject();
		if(po != null) {
			json.put("value", po.getCompanyOrgCode());
			json.put("name", po.getCompanyName());
			json.put("label", po.getCompanyOrgCode()+"\t"+po.getCompanyName());
			json.put("isNew", 0);
		} else {
			json.put("isNew",1);
		}
		return json;
	}
	/**
	 * 查找公司
	 * @param request
	 * @return
	 */
	@RequestMapping("/companySearch")
	public String companySearch(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		int pageNo = Integer.parseInt(map.get(Constant._PAGEINDEX).toString());
		int pageSize = Integer.parseInt(map.get(Constant._PAGESIZE).toString());
		PageData<UcUserPubinfoPo> pageData = ucUserPubinfoService.pageFindByCondition(map, pageNo, pageSize);
		request.setAttribute("search", map);
		request.setAttribute("list", pageData.getList());
		request.setAttribute("total", pageData.getTotalsize());
		request.setAttribute("pageCurrent", pageNo);
		request.setAttribute("pageSize", pageSize);
		return viewPath + "companySearch";
	}
	/**
	 * 根据机构名称获取信息
	 * @param term
	 * @return
	 */
	@RequestMapping("/companySearchName")
	@ResponseBody
	public Object companySearchName(String term) {
		UcUserPubinfoPo po =  ucUserPubinfoService.getByCompanyNameEqual(term);
		JSONObject json = new JSONObject();
		if(po != null) {
			json.put("value", po.getCompanyOrgCode());
			json.put("name", po.getCompanyName());
			json.put("label", po.getCompanyOrgCode()+"\t"+po.getCompanyName());
			json.put("isNew", 0);
		} else {
			json.put("isNew",1);
		}
		return json;
	}
	
	/**
	 * 验证公司
	 * @param companyInfoId
	 * @param companyOrgCode
	 * @param companyName
	 * @return
	 */
	@RequestMapping("/validCompanyName")
	@ResponseBody
	public Map<String,Object> validCompanyName(@RequestParam(required=false) String companyInfoId, @RequestParam(required=false) String companyOrgCode, @RequestParam(required=false) String companyName){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			ucUserPubinfoService.validCompanyInfo(companyInfoId, companyOrgCode, companyName);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error", e.getErrorCode().getMessage());
		} catch (Exception e) {
			retMap.put("error","校验失败");
		}
		return retMap;
	}
}
