package com.fatp.controller.project.listing;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.enums.project.InterestType;
import com.fatp.enums.project.InvestProfitType;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.enums.project.ListingStatus;
import com.fatp.enums.project.PayInterestType;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
import com.fatp.util.StringUtil;
import com.fatp.vo.ListingInfoVo;
import com.fatp.vo.ListingTradeVo;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;

@Controller
@RequestMapping("/project/listinginfo/")
public class ListingInfoController extends BaseController{

	private String viewPath = super.viewPath + "project/listing";
	
	@Autowired
	private ListingInfoService listingInfoService;
	/**
	 * 查询挂牌产品信息
	 * @param request
	 * @return
	 */
	@RequestMapping("infolist")
	public String infoList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("exchangeId", super.getExchangeId());
		map.put("listingStatus", ListingStatus.正常.status);
		map.put("sortColumns", " CreateTime desc ");
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ListingInfoVo> pageData = listingInfoService.pageFindByCondition(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("projectLimitTypeList", ListingLimitType.values());
		return viewPath + "/infolist";
	}
	/**
	 * 修改项目
	 * @return
	 * @return String
	 */
	@RequestMapping("edit")
	public String edit(String id) {
		if(StringUtil.isNotBlank(id)) {
			ListingInfoVo listingInfoVo = listingInfoService.getByListingGuid(id);
			if(listingInfoVo != null) {
				List<ListingTradeVo> tradeVoList = listingInfoService.getTradeByListingInfoId(listingInfoVo.getId());
				request().setAttribute("listingTradeList", tradeVoList);
			}
			request().setAttribute("obj", listingInfoVo);
		}
		request().setAttribute("todayForJudge", DateUtils.getDate());
		request().setAttribute("investProfitTypeList", InvestProfitType.values());
		request().setAttribute("listingLimitTypeList", ListingLimitType.values());
		request().setAttribute("expireDateStyleList", ExpireDateStyle.values());
		request().setAttribute("payInterestTypeList", PayInterestType.values());
		request().setAttribute("interestTypeList", InterestType.values());
		request().setAttribute("interestRateList", InterestRate.values());
		request().setAttribute("interestBaseList", InterestBase.values());
		return viewPath + "/editindex";
	}
	/**
	 * 更新挂牌信息
	 * @param listingInfoVo
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@ModelAttribute ListingInfoVo listingInfoVo) {
		try {
			setOperatorData(listingInfoVo);
			listingInfoService.updateListingInfo(listingInfoVo);
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			XMsgError error = XMsgError.buildSimple(getClass().getName(), "update", e);
			error.setMoreMsg(new Object[]{"id="+listingInfoVo.getId()});
			Xlogger.error(error);
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 删除挂牌产品
	 * @param request
	 * @return
	 * @return Object
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		try {
			listingInfoService.deleteListingInfo(id);
			return resultSuccess();
		} catch (FatpException e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	
	private void setOperatorData(ListingInfoVo listingInfoVo) {
		MemberOperatorPo operator = super.getMemberOperator();
		listingInfoVo.setExchangeId(operator.getExchangeId());
		listingInfoVo.setCreateOperatorId(operator.getId());
		listingInfoVo.setUpateOperatorId(operator.getId());
		listingInfoVo.setCreateOperatorName(operator.getRealName());
	}
}
