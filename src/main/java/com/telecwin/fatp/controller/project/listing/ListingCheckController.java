package com.telecwin.fatp.controller.project.listing;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.param.ListingCheckParam;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.project.ExpireDateType;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.enums.project.ValueDateType;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
/**
 * 挂牌审核
 */
@Controller
@RequestMapping("/project/listing/check/")
public class ListingCheckController extends ListingSupport{
	private String viewPath = super.viewPath + "project/listing/check";
	/**
	 * 审核列表
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String checkList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		getListingList(map, LISTING_CHECKING_STATUS);
		return viewPath + "/list";
	}
	
	/**
	 * 审核页面
	 * @param guid
	 * @return
	 */
	@RequestMapping("view")
	@ResponseBody
	public Object view(String id) {
		ListingComplex listing = listingService.getListingDetailsByGuid(id, super.getExchangeId());
		request().setAttribute("project", listing);
		request().setAttribute("valueDateTypes", ValueDateType.values());
		request().setAttribute("expireDateTypes", ExpireDateType.values());
		return new ModelAndView(viewPath + "/check");
	}
	
	/**
	 * 产品审核
	 * @param param
	 * @param pass
	 * @return
	 */
	@RequestMapping("check")
	@ResponseBody
	public Object check(@ModelAttribute ListingCheckParam param, int pass) {
		try {
			ListingStatusDesc listingStatus = ListingStatusDesc.待发布;
			if(pass == 0) {
				listingStatus = ListingStatusDesc.审核退回;
			} else if(pass == 1){
				listingStatus = ListingStatusDesc.待发布;
			} else if(pass == 2) {
				listingStatus = ListingStatusDesc.审核不通过;
			}
			param.setOperatorId(super.getSelfId());
			param.setOperatorName(super.getSelfName());
			listingService.checkListing(param, listingStatus);
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "check", e));
			return resultError(e.getErrorCode() != null ? e.getErrorCode().getMessage() : e.getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "check", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
}
