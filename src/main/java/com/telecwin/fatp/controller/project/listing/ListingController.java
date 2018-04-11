package com.telecwin.fatp.controller.project.listing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
/**
 * 挂牌接口
 * 
 */
@Controller
@RequestMapping("/project/listing/")
public class ListingController extends ListingSupport{

	private String viewPath = super.viewPath + "project/listing";
	/**
	 * 查看挂牌产品
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("view")
	public String view(int id) {
		ListingComplex listing = super.listingView(id);
		if(listing.getProductTypeId().intValue() == ProductTypeDesc.收益权转让计划.value) {
			return viewPath + "/syq/proinfo-index";
		} else if(listing.getProductTypeId().intValue() == ProductTypeDesc.定向融资计划.value){
			return viewPath + "/dxrz/proinfo-index";
		}
		//TODO 给一个默认页
		return "";
	}
	/**
	 * 项目启动认购
	 * @param id
	 * @return
	 */
	@RequestMapping("startbuy")
	@ResponseBody
	public Object startBuy(String id){
		try {
			listingService.startBuy(id, super.getSelfId(), super.getSelfName());
			return super.resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "startBuy", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "startBuy", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 认购结束
	 * @param id
	 * @return
	 */
	@RequestMapping("endbuy")
	@ResponseBody
	public Object endBuy(String id){
		try {
			listingService.endBuy(id, super.getSelfId(), super.getSelfName());
			return super.resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "endBuy", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "endBuy", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
}
