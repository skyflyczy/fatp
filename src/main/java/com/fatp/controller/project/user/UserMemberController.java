package com.fatp.controller.project.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fatp.domain.UcUser;
import com.fatp.enums.user.IdTypeDesc;
import com.fatp.enums.user.OrgType;
import com.fatp.enums.user.UserIdentityDesc;
import com.fatp.enums.user.UserStatusDesc;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.interceptor.WithoutAuth;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.po.user.UcUserPubinfoPo;
import com.fatp.service.sys.SysareaCityService;
import com.fatp.service.sys.SysareaDistrictService;
import com.fatp.service.sys.SysareaProvinceService;
import com.fatp.service.sys.SysbizcodeSequenceService;
import com.fatp.service.sys.SystypeCompanyService;
import com.fatp.service.sys.SystypeIndustryService;
import com.fatp.service.user.MemberOperatorService;
import com.fatp.service.user.UcUserPubinfoService;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;

@Controller
@RequestMapping("/user/member")
public class UserMemberController extends UserSupport{

	private String viewPath = super.viewPath + "user/";
	
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
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
	private MemberOperatorService memberOperatorService;
	/**
	 * 客户列表、查询
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		// 传递页面上的查询条件参数到后面的服务
		Map<String, Object> queryMap = paramToMap(request);
		UserStatusDesc[] allowStatus = new UserStatusDesc[]{
				UserStatusDesc.审核不通过,
				UserStatusDesc.审核退回,
				UserStatusDesc.未提交
		};
		listMembers(queryMap, allowStatus);
		return viewPath + "member/list";
	}
	/**
	 * 获取经办人信息
	 * @param ids
	 * @return
	 */
	@RequestMapping("registeragentlist")
	@ResponseBody
	@WithoutAuth
	public Object getRegisterAgentList(String ids) {
		return memberOperatorService.getRegisterAgentList(ids, getExchangeId());
	}
	/**
	 * 添加新会员
	 * @param request
	 * @return
	 */
	@RequestMapping("toAddPage")
	public String toAddPage(HttpServletRequest request) {
		request.setAttribute("userChildType", request.getParameter("userChildType"));
		return viewPath + "member/add";
	}
	/**
	 * 添加
	 * @param ucUser
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Object add(@ModelAttribute UcUser ucUser) {
		try {
			if(StringUtils.isBlank(ucUser.getCompanyOrgCode()) || StringUtils.isBlank(ucUser.getCompanyName())
					|| StringUtils.isBlank(ucUser.getRealName()) || StringUtils.isBlank(ucUser.getUserName())) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			UcUserPubinfoPo ucUserPubinfo = this.ucUserPubinfoService.getByCompanyOrgCode(ucUser.getCompanyOrgCode());
			if(ucUserPubinfo != null) {
				if(!ucUserPubinfo.getCompanyName().equals(ucUser.getCompanyName())) {
					return resultError(ErrorCode.COMPANY_ORGNAME_NOT_MACTH.getMessage());
				}
				ucUser.setCompanyInfoId(ucUserPubinfo.getId());
			}
			//验证真实姓名是否已经存在
			UcUser memberUser = ucUserService.getMemberByEqualRealName(ucUser.getRealName(), UserIdentityDesc.发行人.value, getExchangeId());
			if(memberUser != null){
				return resultError(ErrorCode.MEMBER_REALNAME_ALREADY_EXIST.getMessage());
			}
			ucUser.setUserCode(sysbizcodeSequenceService.getMemberSequence());
			ucUser.setUserIdentity(UserIdentityDesc.发行人.value);
			ucUser.setOrgTypeId(OrgType.法人机构.type);
			int id = ucUserService.insertUser(ucUser,getMemberId(),getSelfId(),getExchangeId(),getSelfName());
			JSONObject result = resultSuccess();
			result.put("id", id);
			return result;
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "add", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "add", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 验证用户名
	 * @param userName
	 * @param isUpdate
	 * @param userId
	 * @return
	 */
	@RequestMapping("/validusername")
	@ResponseBody
	public Map<String,Object> validUserName(@RequestParam String userName,@RequestParam int isUpdate,@RequestParam int userId){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			UcUser user = ucUserService.getUserNameByEqualUserName(userName,getExchangeId(),getMemberId());
			if(user == null) {
				retMap.put("ok", "");
			} else if(isUpdate == 1 && userId == user.getId().intValue()) {
				retMap.put("ok", "");
			} else {
				retMap.put("error",ErrorCode.MEMBER_NAME_ALREADY_EXIST.getMessage());
			}
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "add", e));
			retMap.put("error", "校验失败。");
		}
		return retMap;
	}
	/**
	 * 修改投资者
	 * @param id
	 * @return
	 * @return String
	 * @author zhiya.chai
	 * 2015年6月29日 下午2:52:37
	 */
	@RequestMapping("toEditPage")
	public String toEditPage(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		UcUser obj = ucUserService.getAllById(id, super.getExchangeId());
		request.setAttribute("user", obj);
		UcUser userExt = ucUserService.getUserExtById(id);
		request.setAttribute("userExt", userExt);
		request.setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return viewPath + "member/editindex2";
	}
	
	/**
	 * 修改基本信息
	 * @param ucUser
	 * @return
	 * @return Object
	 * @author zhiya.chai
	 * 2015年6月29日 下午2:52:45
	 */
	@RequestMapping("updateBase")
	@ResponseBody
	public Object userUpdateBaseInfo(@ModelAttribute UcUser ucUser) {
		try {
			UcUser oldUser = ucUserService.getAllById(ucUser.getId(), super.getExchangeId());
			if(oldUser == null) {
				return resultError(ErrorCode.MEMBER_NOT_EXIST.getMessage());
			}
			oldUser.setUserName(ucUser.getUserName());
			oldUser.setCompanyName(ucUser.getCompanyName());
			oldUser.setCompanyShortName(ucUser.getCompanyShortName());
			oldUser.setCompanyRepresentative(ucUser.getCompanyRepresentative());
			oldUser.setCompanyOrgCode(ucUser.getCompanyOrgCode());
			oldUser.setCompanyBusinessLicense(ucUser.getCompanyBusinessLicense());
			oldUser.setCompanyRegAddress(ucUser.getCompanyRegAddress());
			oldUser.setCompanyTypeId(ucUser.getCompanyTypeId());
			oldUser.setIndustryId(ucUser.getIndustryId());
			oldUser.setPostalCode(ucUser.getPostalCode());
			oldUser.setFaxPhone(ucUser.getFaxPhone());
			oldUser.setTelePhone(ucUser.getTelePhone());
			oldUser.setWebUrl(ucUser.getWebUrl());
			oldUser.setMainBuisness(ucUser.getMainBuisness());
			oldUser.setIdTypeId(IdTypeDesc.统一社会信用代码.getIdType());
			oldUser.setProvinceId(ucUser.getProvinceId());
			oldUser.setCityId(ucUser.getCityId());
			oldUser.setDisId(ucUser.getDisId());
			oldUser.setMainDesc(ucUser.getMainDesc());
			oldUser.setUpdateOperatorId(getSelfId());
			oldUser.setFinancialGroupBusinessIncome(ucUser.getFinancialGroupBusinessIncome());
			oldUser.setBirthDate(ucUser.getBirthDate());
			ucUserService.updateUserAll(oldUser,getSelfName());
			return resultSuccess();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "userUpdateBaseInfo", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 修改财务信息
	 * @param ucUser
	 * @return
	 * @return Object
	 */
	@RequestMapping("updateFinance")
	@ResponseBody
	public Object userUpdateFinanceInfo(@ModelAttribute UcUser ucUser) {
		try {
			UcUser oldUser = ucUserService.getAllById(ucUser.getId(), super.getExchangeId());
			if(oldUser == null) {
				return resultError(ErrorCode.MEMBER_NOT_EXIST.getMessage());
			}
			oldUser.setMoneyUnit(ucUser.getMoneyUnit());
			oldUser.setMoneyUnitFinance(ucUser.getMoneyUnitFinance());
			oldUser.setFinancialBusinessIncome(ucUser.getFinancialBusinessIncome());
			oldUser.setFinancialGroupBusinessIncome(ucUser.getFinancialGroupBusinessIncome());
			oldUser.setFinancialGroupNetAsset(ucUser.getFinancialGroupNetAsset());
			oldUser.setFinancialNetAsset(ucUser.getFinancialNetAsset());
			oldUser.setFinancialNetProfit(ucUser.getFinancialNetProfit());
			oldUser.setFinancialTotalAsset(ucUser.getFinancialTotalAsset());
			oldUser.setFinancialTotalProfit(ucUser.getFinancialTotalProfit());
			oldUser.setUpdateOperatorId(getSelfId());
			ucUserService.updateUserAll(oldUser,getSelfName());
			return resultSuccess();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "userUpdateFinanceInfo", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 修改部门信息
	 * @param ucUser
	 * @return
	 * @return Object
	 */
	@RequestMapping("updateLink")
	@ResponseBody
	public Object userUpdateLink(@ModelAttribute UcUser ucUser) {
		try {
			UcUser oldUser = ucUserService.getAllById(ucUser.getId(), super.getExchangeId());
			if(oldUser == null) {
				return resultError(ErrorCode.MEMBER_NOT_EXIST.getMessage());
			}
			UcUser userExt = ucUserService.getUserExtById(ucUser.getId());
			userExt.setDeparmentAddress(ucUser.getDeparmentAddress());
			userExt.setDeparmentName(ucUser.getDeparmentName());
			userExt.setDeparmentProvinceId(ucUser.getDeparmentProvinceId());
			userExt.setDeparmentCityId(ucUser.getDeparmentCityId());
			userExt.setDeparmentDisId(ucUser.getDeparmentDisId());
			userExt.setDeparmentPhone(ucUser.getDeparmentPhone());
			userExt.setDeparmentFax(ucUser.getDeparmentFax());
			userExt.setDeparmentPostCode(ucUser.getDeparmentPostCode());
			userExt.setLinkMan(ucUser.getLinkMan());
			userExt.setLinkSex(ucUser.getLinkSex());
			userExt.setLinkIdnumber(ucUser.getLinkIdnumber());
			userExt.setLinkCareer(ucUser.getLinkCareer());
			userExt.setLinkPhone(ucUser.getLinkPhone());
			userExt.setLinkEmail(ucUser.getLinkEmail());
			userExt.setLinkIdTypeId(ucUser.getLinkIdTypeId());
			userExt.setUpdateOperatorId(getSelfId());
			ucUserService.updateUserExt(userExt);
			return resultSuccess();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "userUpdateLink", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 检查是否可以提交审核
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkAuditConditions")
	public Object checkAuditConditions(int id) {
		try {
			ucUserService.checkMemberAuditConditions(id,getExchangeId());
			return resultSuccess();
		} catch (FatpException e) {
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "checkAuditConditions", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping("/audit_reg/submit")
	public JSONObject auditRegSubmit(int memberId) {
		try {
			UcUser member = ucUserService.getAllById(memberId, super.getExchangeId());
			if(member == null) {
				return resultError(ErrorCode.MEMBER_NOT_EXIST.getMessage());
			}
			member.setUpdateOperatorId(getSelfId());
			member.setUserStatus(UserStatusDesc.待审核.value);
			member.setExchangeId(super.getExchangeId());
			ucUserService.updateUserStatus(member,getSelfName());
			return resultSuccess();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "auditRegSubmit", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
		
	}
	
	/**
	 * 会员审核列表
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("checklist")
	public String checklist(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		UserStatusDesc[] allowStatus = new UserStatusDesc[]{
				UserStatusDesc.待审核
		};
		listMembers(map, allowStatus);
		return viewPath + "member/checklist";
	}
	
	/**
	 * 审核查看
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("checkview")
	public String checkview(int id) {
		UcUser obj = ucUserService.getAllById(id, getExchangeId());
		obj.setCityName(sysareaCityService.getById(obj.getCityId()).getCityName());
		obj.setProName(sysareaProvinceService.getById(obj.getProvinceId()).getProName());
		obj.setDisName(sysareaDistrictService.getById(obj.getDisId()).getDisName());
		obj.setIndustryName(systypeIndustryService.getById(obj.getIndustryId()).getIndustryName());
		obj.setCompanyTypeName(SystypeCompanyService.getById(obj.getCompanyTypeId()).getCompanyTypeName());
		request().setAttribute("user", obj);
		request().setAttribute("userId", id);
		if(obj.getOrgTypeId() == OrgType.其他组织.type) {
			request().setAttribute("userPub", ucUserPubinfoService.getById(obj.getCompanyParentId()));
		}
		return viewPath + "check/infoindex2";
	}
	
	/**
	 * 经办人信息
	 * @param userId
	 * @return
	 * @return String
	 */
	@RequestMapping("/registeragentinfo")
	public String registerAgentInfo(@RequestParam Integer userId){
		MemberOperatorPo o  = memberOperatorService.getRegisterAgentByMemberId(userId, getExchangeId());
		request().setAttribute("operator", o);
		request().setAttribute("memberId", userId);
		request().setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return viewPath + "/info/registerAgentInfo";
	}
	
	/**
	 * 前往管理员页面
	 * @param userId
	 * @return void
	 */
	@RequestMapping("advisor-rz/to-advisor-admin-info")
	public String to_advisor_admin_info(@RequestParam Integer userId){
		MemberOperatorPo o  = memberOperatorService.getSuperAdmin(userId, getExchangeId());
		request().setAttribute("operator", o);
		request().setAttribute("memberId", userId);
		request().setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return viewPath + "/advisor-rz/advisor-admin-info";
	}
	/**
	 * 银行信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/view/bankinfo")
	public String bankinfo(int id) {
		request().setAttribute("cardList", userBankCardService.getUserBankcards(id, getExchangeId()));
		request().setAttribute("userId", id);
		return viewPath + "info/info_bank2";
	}
	/**
	 * 公司信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/view/linkinfo")
	public String linkinfo(int id) {
		UcUser obj = ucUserService.getUserExtById(id);
		obj.setDeparmentCity(sysareaCityService.getById(obj.getDeparmentCityId()).getCityName());
		obj.setDeparmentProvince(sysareaProvinceService.getById(obj.getDeparmentProvinceId()).getProName());
		obj.setDeparmentDis(sysareaDistrictService.getById(obj.getDeparmentDisId()).getDisName());
		request().setAttribute("user", obj);
		request().setAttribute("userId", id);
		request().setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return viewPath + "info/info_link2";
	}
	
	/**
	 * 审核
	 * @param id
	 * @return
	 * @return String
	 */
	@RequestMapping("check")
	@ResponseBody
	public Object check(int id, int type, String auditRemark) {
		try {
			ucUserService.checkMember(id, type, getExchangeId(), getSelfId(), auditRemark,getSelfName());
			return super.resultSuccess();
		} catch (FatpException e) {
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "check", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 会员
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("search")
	public String search(HttpServletRequest request) {
		Map<String, Object> queryMap = paramToMap(request);
		UserStatusDesc[] allowStatus = UserStatusDesc.getAllNormalUserStatus();
		listMembers(queryMap, allowStatus);
		return viewPath + "member/search";
	}
	
}
