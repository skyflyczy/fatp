package com.fatp.controller.biz;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.enums.project.ListingStatus;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
/**
 * 还款计划
 */
@Controller
@RequestMapping("/biz/plan/")
public class BizPlanController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private String viewPath = super.viewPath + "biz/plan/";
	
	@Autowired
	private ListingInfoService listingInfoService;
	/**
	 * 查询挂牌信息
	 * @param request
	 * @return
	 */
	@RequestMapping("listinglist")
	public String listingList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("exchangeId", super.getExchangeId());
		map.put("listingStatus", ListingStatus.正常.status);
		map.put("sortColumns", " CreateTime desc ");
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ListingInfo> pageData = listingInfoService.pageFindByCondition(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("projectLimitTypeList", ListingLimitType.values());
		return viewPath + "listing-list";
	}
	
}
