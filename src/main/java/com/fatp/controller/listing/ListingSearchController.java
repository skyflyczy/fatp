package com.fatp.controller.listing;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.listing.ListingTrade;
import com.fatp.enums.project.ListingStatus;
import com.fatp.enums.project.PayInterestType;
import com.fatp.exception.ErrorCode;
import com.fatp.service.project.ListingExcelService;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
/**
 * 挂牌产品查询
 * 
 * @author zhiya.chai
 * @date 2018年6月27日 上午10:20:51
 */
@Controller
@RequestMapping("/listing/search/")
public class ListingSearchController extends BaseController{
	
	private String viewPath = super.viewPath + "project/listing/search";
	
	@Autowired
	private ListingInfoService listingInfoService;
	@Autowired
	private ListingExcelService listingExcelService;
	/**
	 * 查询挂牌产品信息
	 * @return
	 */
	@RequestMapping("list")
	public String list(){
		Map<String,Object> map =paramToMap(request());
		Object valueDateEnd = map.get("valueDateEnd");
		Object expireDateEnd = map.get("expireDateEnd");
		genSearchMap(map);
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ListingInfo> pageData = listingInfoService.pageFindByCondition(map, pageNo, pageSize);
		map.put("valueDateEnd", valueDateEnd);
		map.put("expireDateEnd", expireDateEnd);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("payInterestTypeList", PayInterestType.values());
		return viewPath + "/list";
	}
	/**
	 * 导出
	 * @return
	 */
	@RequestMapping("export")
	@ResponseBody
	public Object export() {
		OutputStream outputStream = null;
		try {
			Map<String,Object> map =paramToMap(request());
			genSearchMap(map);
			List<ListingInfo> list = listingInfoService.findByCondition(map);
			if(CollectionUtils.isNotEmpty(list)) {
				list.stream().forEach(listingInfo ->{
					List<ListingTrade> tradeList = listingInfoService.getTradeByListingInfoId(listingInfo.getId());
					listingInfo.setListingTradeList(tradeList);
				});
			}
			String filename = URLEncoder.encode("挂牌产品明细", "utf-8");
			response().setCharacterEncoding("utf-8");
			response().setContentType("application/msexcel");
			response().setHeader("Content-Disposition", "attachment;fileName="+filename+".xls");
	        outputStream = response().getOutputStream();
	        listingExcelService.genExcel(list, outputStream);
	        outputStream.flush();
	        return resultSuccess();
		}catch(Exception e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "exportPayinvestList", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		} finally{
			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
				}
		}
	}
	/**
	 * 生成查询Map
	 * @return
	 */
	private Map<String,Object> genSearchMap(Map<String,Object> map) {
		Object valueDateEnd = map.get("valueDateEnd");
		if(valueDateEnd != null) {
			map.put("valueDateEnd", valueDateEnd.toString() + " 23:59:59");
		}
		Object expireDateEnd = map.get("expireDateEnd");
		if(expireDateEnd != null){
			map.put("expireDateEnd", expireDateEnd.toString() + " 23:59:59");
		}
		map.put("exchangeId", super.getExchangeId());
		map.put("listingStatus", ListingStatus.正常.status);
		map.put("sortColumns", " CreateTime desc ");
		return map;
	}
}
