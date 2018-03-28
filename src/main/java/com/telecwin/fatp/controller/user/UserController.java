package com.telecwin.fatp.controller.user;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.domain.UcUserBankcard;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.service.sys.SysareaCityService;
import com.telecwin.fatp.service.sys.SysareaDistrictService;
import com.telecwin.fatp.service.sys.SysareaProvinceService;
import com.telecwin.fatp.service.sys.SystypeCompanyService;
import com.telecwin.fatp.service.sys.SystypeIndustryService;
import com.telecwin.fatp.service.user.UcUserPubinfoService;
import com.telecwin.fatp.service.user.UcUserService;
import com.telecwin.fatp.service.user.UserBankCardService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private String viewPath = super.viewPath + "user/";

	@Autowired
	private UcUserPubinfoService ucUserPubinfoService;
	@Autowired
	private SysareaProvinceService sysareaProvinceService;
	@Autowired
	private SysareaCityService sysareaCityService;
	@Autowired
	private SysareaDistrictService sysareaDistrictService;
	@Autowired
	private SystypeIndustryService systypeIndustryService;
	@Autowired
	private SystypeCompanyService SystypeCompanyService;
	@Autowired
	protected UcUserService ucUserService;
	@Autowired
	protected UserBankCardService userBankCardService;
	/**
	 * 查看信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/view")
	public String view(int id) {
		UcUser obj = ucUserService.getAllById(id, super.getExchangeId());
		if(obj.getCityId() != null) {
			obj.setCityName(sysareaCityService.getById(obj.getCityId()).getCityName());
		}
		if(obj.getProvinceId() != null) {
			obj.setProName(sysareaProvinceService.getById(obj.getProvinceId()).getProName());
		}
		if(obj.getDisId() != null) {
			obj.setDisName(sysareaDistrictService.getById(obj.getDisId()).getDisName());
		}
		if(obj.getIndustryId() != null){
			obj.setIndustryName(systypeIndustryService.getById(obj.getIndustryId()).getIndustryName());
		}
		if(obj.getCompanyTypeId() != null) {
			obj.setCompanyTypeName(SystypeCompanyService.getById(obj.getCompanyTypeId()).getCompanyTypeName());
		}
		request().setAttribute("user", obj);
		request().setAttribute("userId", id);
		if(obj.getOrgTypeId() == OrgType.其他组织.type) {
			if(obj.getCompanyParentId() != null){
				request().setAttribute("userPub", ucUserPubinfoService.getById(obj.getCompanyParentId()));
			}
		}
		if(obj.getOrgTypeId() == OrgType.自然人.type) {
			List<UcUserBankcard> cardList = userBankCardService.getUserBankcards(id, getExchangeId());
			request().setAttribute("bankCard", CollectionUtils.isEmpty(cardList) ? null : cardList.get(0));
			return viewPath + "info/info_base1";
		}
		return viewPath + "info/infoindex2";
	}
}
