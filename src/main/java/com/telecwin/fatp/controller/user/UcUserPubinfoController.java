package com.telecwin.fatp.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;
import com.telecwin.fatp.service.user.UcUserPubinfoService;
import com.telecwin.fatp.util.Constant;

@Controller
@RequestMapping("/user")
public class UcUserPubinfoController extends BaseController{
	
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
	@RequestMapping("companySearch")
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
}
