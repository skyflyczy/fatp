package com.telecwin.fatp.controller.project.listing;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.enums.project.ProductTypeDesc;

/**
 * 收益权转让计划
 */
@Controller
@RequestMapping("/project/listing/syq")
public class SyqListingController extends ListingSupport{
	
	private String viewPath = super.viewPath + "project/listing/syq";
	/**
	 * 项目列表、查询
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.收益权转让计划.value);
		super.getProjectNeedEditList(map);
		return viewPath + "/prolist";
	}
}
