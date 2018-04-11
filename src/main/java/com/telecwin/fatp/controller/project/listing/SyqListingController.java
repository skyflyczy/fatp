package com.telecwin.fatp.controller.project.listing;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;

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
		getListingList(map, NEED_EDIT_STATUS);
		return viewPath + "/prolist";
	}
	
	/**
	 * 添加项目
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("new")
	public String add(HttpServletRequest request) {
		//取项目来源
		request().setAttribute("systypeProjectList", systypeProjectService.findByProductTypeId(ProductTypeDesc.收益权转让计划.value));
		return viewPath + "/addpro";
	}
	
	/**
	 * 可挂牌的备案列表
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("recordlist")
	public String canQuotedRecordList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.收益权转让计划.value);
		super.getCanQuotedRecordList(map);
		return viewPath + "/recordlist";
	}
	
	/**
	 * 添加项目
	 * @param projectBaseinfo
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Object create(@ModelAttribute ListingComplex listingComplex) {
		try {
			listingComplex.setProductTypeId(ProductTypeDesc.收益权转让计划.value);
			return this.doCreate(listingComplex);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "create", e));
			return resultError("保存项目发生异常");
		}
	}
	
	/**
	 * 修改项目
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("edit")
	public String edit(int id) {
		beforeEdit(id);
		return viewPath + "/editindex";
	}
	
	/**
	 * 修改
	 * @param projectBaseinfo
	 * @return
	 * @return Object
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@ModelAttribute ListingComplex listingComplex, boolean toAudit) {
		try {
			listingComplex.setProductTypeId(ProductTypeDesc.收益权转让计划.value);
			doUpdate(listingComplex,toAudit);
			JSONObject retJson = resultSuccess();
			retJson.put("forCheck", toAudit);
			return retJson;
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(e.getErrorCode() != null ? e.getErrorCode().getMessage() : e.getMessage());
		} catch (Exception e) {
			XMsgError error = XMsgError.buildSimple(getClass().getName(), "update", e);
			error.setMoreMsg(new Object[]{"id="+listingComplex.getId()});
			Xlogger.error(error);
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
}
