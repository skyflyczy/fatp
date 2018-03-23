package com.telecwin.fatp.controller.project.record;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.param.project.ProjectRecordinfoVo;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.interceptor.WithoutAuth;
/**
 * 收益权转让计划备案
 * @author zhiya.chai
 */
@Controller
@RequestMapping("/project/record/syq/")
public class SyqRecordController extends RecordSupport{
	
	private String viewPath = super.viewPath + "project/record/syq";

	/**
	 * 备案列表查询
	 * @return String
	 */
	@RequestMapping("list")
	@WithoutAuth
	public String list(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.收益权转让计划.value);
		super.getRecordingList(map);
		return viewPath + "/list";
	}
	
	/**
	 * 添加备案信息
	 * @return String
	 * @author zhiya.chai
	 * 2016年8月5日 上午11:23:47
	 */
	@RequestMapping("new")
	@WithoutAuth
	public String add(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("productTypeId", ProductTypeDesc.收益权转让计划.value);
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
	 * @author zhiya.chai
	 * 2016年8月8日 上午10:50:22
	 */
	@RequestMapping("add")
	@ResponseBody
	@WithoutAuth
	public Object create(@ModelAttribute ProjectRecordinfoVo projectRecordinfo) {
		try {
			projectRecordinfo.setProductTypeId(ProductTypeDesc.收益权转让计划.value);
			return super.doCreate(projectRecordinfo);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "create", e));
			return resultError("保存项目发生异常");
		}
	}
	
}
