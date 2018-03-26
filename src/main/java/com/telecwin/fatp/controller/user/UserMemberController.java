package com.telecwin.fatp.controller.user;

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
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.enums.user.UserIdentityDesc;
import com.telecwin.fatp.enums.user.UserStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.WithoutAuth;
import com.telecwin.fatp.po.user.UcUserPubinfoPo;
import com.telecwin.fatp.service.sys.SysbizcodeSequenceService;
import com.telecwin.fatp.service.user.UcUserPubinfoService;

@Controller
@RequestMapping("/user/member")
public class UserMemberController extends UserSupport{

	private String viewPath = super.viewPath + "user/";
	
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private UcUserPubinfoService ucUserPubinfoService;
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
				UserStatusDesc.待处理,
				UserStatusDesc.不通过,
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
			int id = ucUserService.insertUser(ucUser,getMemberId(),getSelfId(),getExchangeId());
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
	 * 
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
}
