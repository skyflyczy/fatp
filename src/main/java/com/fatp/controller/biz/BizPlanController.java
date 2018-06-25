package com.fatp.controller.biz;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.enums.biz.PayinvestStatus;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.enums.project.ListingStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.service.biz.BizplanPayinvestService;
import com.fatp.service.biz.BizplanRepayService;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
/**
 * 还款计划
 */
@Controller
@RequestMapping("/biz/plan/")
public class BizPlanController extends BaseController{
	
	private String viewPath = super.viewPath + "biz/plan/";
	
	@Autowired
	private ListingInfoService listingInfoService;
	@Autowired
	private BizplanRepayService bizplanRepayService;
	@Autowired
	private BizplanPayinvestService bizplanPayinvestService;
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
	/**
	 * 还款计划页面
	 * @return
	 */
	@RequestMapping("repay_plan_list")
	@ResponseBody
	public Object repayPlanList(){
		String listingGuid = request().getParameter("id");
		if(StringUtils.isBlank(listingGuid)) {
			return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
		}
		ListingInfo listingVo = listingInfoService.getByListingGuid(listingGuid);
		if(listingVo == null) {
			return resultError(ErrorCode.LISTING_NOT_EXIST.getMessage());
		}
		Map<String,Object> map = new HashMap<>();
		map.put("listingInfoId", listingVo.getId());
		map.put("sortColumns", " PeriodNumber ASC ");
		List<BizplanRepay> list = bizplanRepayService.findRepayPlan(map);
		request().setAttribute("list", list);
		request().setAttribute("listingInfo", listingVo);
		return new ModelAndView(viewPath + "/repay-plan-list");
	}
	/**
	 * 还款完成
	 * @return
	 */
	@RequestMapping("repay_completed")
	@ResponseBody
	public Object repayCompleted(){
		try {
			String repayPlanGuid = request().getParameter("id");
			if(StringUtils.isBlank(repayPlanGuid)) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			BizplanRepay planRepay = bizplanRepayService.getPlanRepayByGuid(repayPlanGuid);
			if(planRepay == null) {
				return resultError("没有此还款计划");
			}
			bizplanRepayService.updateRepayStatus(planRepay, RepayStatus.还款完成, super.getSelfId());
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "repayCompleted", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "repayCompleted", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 兑付计划页面
	 * @return
	 */
	@RequestMapping("payinvest_plan_list")
	@ResponseBody
	public Object payinvestPlanList(){
		String repayPlanGuid = request().getParameter("id");
		if(StringUtils.isBlank(repayPlanGuid)) {
			return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
		}
		BizplanRepay planRepay = bizplanRepayService.getPlanRepayByGuid(repayPlanGuid);
		if(planRepay == null) {
			return resultError("没有此还款计划");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("repayPlanId", planRepay.getId());
		List<BizplanPayinvest> list = bizplanPayinvestService.findPlanPayinvest(map);
		PayinvestStatus payinvestStatus = bizplanPayinvestService.getPayinvestStatus(map);
		request().setAttribute("list", list);
		request().setAttribute("planRepay", planRepay);
		request().setAttribute("canPayinvestCompleted", payinvestStatus != PayinvestStatus.兑付完成);
		return new ModelAndView(viewPath + "/payinvest-plan-list");
	}
	/**
	 * 兑付完成
	 * @return
	 */
	@RequestMapping("payinvest_completed")
	@ResponseBody
	public Object payinvestCompleted() {
		try {
			String repayPlanGuid = request().getParameter("id");
			if(StringUtils.isBlank(repayPlanGuid)) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			bizplanPayinvestService.payinvestCompleted(repayPlanGuid,super.getSelfId());
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "payinvestCompleted", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "payinvestCompleted", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 导出兑付明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/export_payinvest_list")
	@ResponseBody
	public Object exportPayinvestList(){
		OutputStream outputStream = null;
		try {
			String repayPlanGuid = request().getParameter("id");
			if(StringUtils.isBlank(repayPlanGuid)) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			BizplanRepay planRepay = bizplanRepayService.getPlanRepayByGuid(repayPlanGuid);
			if(planRepay == null) {
				return resultError("没有此还款计划");
			}
			Map<String, Object> map = new HashMap<>();
			map.put("repayPlanId", planRepay.getId());
			List<BizplanPayinvest> list = bizplanPayinvestService.findPlanPayinvest(map);
			String filename = URLEncoder.encode("兑付明细", "utf-8");
			response().setCharacterEncoding("utf-8");
			response().setContentType("application/msexcel");
			response().setHeader("Content-Disposition", "attachment;fileName="+filename+".xls");
	        outputStream = response().getOutputStream();
	        bizplanPayinvestService.genPayinvestListExcel(list, outputStream);
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
	
}
