package com.telecwin.fatp.controller.project.record;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.WithoutAuth;

/**
 * 定向融资计划
 */
@Controller
@RequestMapping("/project/record/dxrz/")
public class DxrzRecordController extends RecordSupport{
	
	private String viewPath = super.viewPath + "project/record/dxrz";
	
	/**
	 * 备案列表查询
	 * @return String
	 */
	@RequestMapping("list")
	@WithoutAuth
	public String list(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.定向融资计划.value);
		super.getRecordingList(map);
		return viewPath + "/list";
	}
	
	/**
	 * 添加备案信息
	 * @return String
	 */
	@RequestMapping("new")
	@WithoutAuth
	public String add(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.定向融资计划.value);
		this.beforeAdd(map);
		return viewPath + "/addpro";
	}
	/**
	 * 验证
	 * @param id
	 * @return
	 * @return Object
	 */
	@RequestMapping("valid")
	@ResponseBody
	@WithoutAuth
	public Object valid(HttpServletRequest request) {
		return doValidProject(request);
	}
	
	/**
	 * 添加备案
	 * @return Object
	 */
	@RequestMapping("add")
	@ResponseBody
	@WithoutAuth
	public Object create(@ModelAttribute ProjectRecordinfo projectRecordinfo) {
		try {
			projectRecordinfo.setProductTypeId(ProductTypeDesc.定向融资计划.value);
			return super.doCreate(projectRecordinfo);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "create", e));
			return resultError("保存项目发生异常");
		}
	}
	
	/**
	 * 修改备案信息前
	 * @return String
	 */
	@RequestMapping("edit")
	public String edit(int id) {
		getProjectRecordInfo(id);
		return viewPath + "/editindex";
	}
	
	/**
	 * 修改备案信息
	 * @return Object
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@ModelAttribute ProjectRecordinfo projectRecordinfo, boolean toAudit) {
		try {
			projectRecordinfo.setProductTypeId(ProductTypeDesc.定向融资计划.value);
			if(toAudit) {
				projectRecordinfo.setRecordStatus(RecordStatusDesc.待审核.value);
			}
			projectRecordService.updateRecord(projectRecordinfo, super.getMemberOperator());
			JSONObject json = resultSuccess();
			json.put("forCheck", toAudit);
			json.put("recordId", projectRecordinfo.getId());
			return json;
		} catch(FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 删除备案信息
	 * @return Object
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		try {
			projectRecordService.deleteRecord(id);
			return resultSuccess();
		} catch(FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
}
