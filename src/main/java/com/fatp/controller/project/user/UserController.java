package com.fatp.controller.project.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fatp.domain.UcUser;
import com.fatp.domain.UcUserBankcard;
import com.fatp.enums.user.OrgType;
import com.fatp.enums.user.UserStatusDesc;
import com.fatp.interceptor.WithoutAuth;
import com.fatp.service.sys.SysareaCityService;
import com.fatp.service.sys.SysareaDistrictService;
import com.fatp.service.sys.SysareaProvinceService;
import com.fatp.service.sys.SystypeCompanyService;
import com.fatp.service.sys.SystypeIndustryService;
import com.fatp.service.user.UcUserPubinfoService;

@Controller
@RequestMapping("/user")
public class UserController extends UserSupport{
	
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
	/**
	 * 用户查询
	 * @param request
	 * @return
	 */
	@RequestMapping("lookupload")
	public String lookUpload(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("ownerUserId", super.getMemberId()); 
		UserStatusDesc[] allowStatus = new UserStatusDesc[]{
				UserStatusDesc.正常
		};
		listMembers(map, allowStatus);
		return viewPath + "lookupload";
	}
	
	/**
	 * 选择用户
	 * @param memberName
	 * @return
	 */
	@RequestMapping("/chooseuser")
	@ResponseBody
	@WithoutAuth
	public Object chooseUser(String memberName){
		UcUser ucUser = ucUserService.getUserByCompanyName(memberName.trim(), getExchangeId());
		if(ucUser == null){
			return resultError("用户不存在。");
		}else{
			JSONObject json = resultSuccess();
			json.put("", "");
			json.put("userId", ucUser.getId());
			return json;
		}
	}
}
