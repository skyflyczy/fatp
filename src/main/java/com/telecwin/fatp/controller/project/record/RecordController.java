package com.telecwin.fatp.controller.project.record;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.interceptor.WithoutAuth;

/**
 * 备案Controller
 * 此类放入公用接口
 */
@Controller
@RequestMapping("/project/record/")
public class RecordController extends RecordSupport{
	
	private String viewPath = super.viewPath + "project/record";
	
	/**
	 * 查看备案信息
	 * @param id
	 * @return
	 */
	@WithoutAuth
	@RequestMapping("view")
	public String view(int id) {
		ProjectRecordinfo record = getProjectRecordInfo(id);
		if(record.getProductTypeId().intValue() == ProductTypeDesc.收益权转让计划.value) {
			return viewPath + "/syq/proinfo-index";
		} else if(record.getProductTypeId().intValue() == ProductTypeDesc.定向融资计划.value){
			return viewPath + "/dxrz/proinfo-index";
		}
		//TODO 给一个默认页
		return "";
	}

}
