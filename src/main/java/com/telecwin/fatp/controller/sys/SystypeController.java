
package com.telecwin.fatp.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.service.sys.SystypeCompanyService;
import com.telecwin.fatp.service.sys.SystypeIndustryService;

/**
 * 系统类型controller
 */
@Controller
@RequestMapping("/systype")
public class SystypeController extends BaseController {
	
	@Autowired
	private SystypeIndustryService systypeIndustryService;
	@Autowired
	private SystypeCompanyService SystypeCompanyService;
	
	/**
	 * 选择行业
	 * @param userId
	 * @return
	 * @return Object
	 */
	@RequestMapping("industry")
	@ResponseBody
	public Object getSystypeIndustrys() {
		return systypeIndustryService.getAll();
	}
	
	/**
	 * 选择机构类型
	 * @return
	 * @return Object
	 */
	@RequestMapping("company")
	@ResponseBody
	public Object getSystypecompanys() {
		return SystypeCompanyService.getAll();
	}
}
