package com.telecwin.fatp.controller.offsite;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.service.offsite.InvestApplyService;
import com.telecwin.fatp.util.Constant;

/**
 * 投资明细登记
 */
@Controller
@RequestMapping("/offsite/invest")
public class InvestApplyController extends BaseController{

	protected static final ListingStatusDesc[] INVEST_REGISTER_STATUS = new ListingStatusDesc[]{ListingStatusDesc.认购中,ListingStatusDesc.认购结束,ListingStatusDesc.发行成功};
	
	private String viewPath = super.viewPath + "/offsite/invest";
	
	@Autowired
	private InvestApplyService investApplyService;
	/**
	 * 可登记列表
	 * @return
	 */
	@RequestMapping("reglist")
	public String list(){
		Map<String,Object> map = paramToMap(request());
		//处理查询状态
		delSearchStatus(map, INVEST_REGISTER_STATUS);
		map.put("exchangeId", super.getExchangeId());
		//后台调查询list
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<InvestApply> pageData = investApplyService.getCanRegList(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("projectStatusDesc", INVEST_REGISTER_STATUS);
		return viewPath + "/reglist";
	}
	private void delSearchStatus(Map<String,Object> map,ListingStatusDesc[] statusArray){
		int[] searchStatus = new int[statusArray.length];
		for(int i=0; i<statusArray.length; i++) {
			searchStatus[i] = statusArray[i].value;
		}
		map.put("searchStatusList", searchStatus);
	}

}
