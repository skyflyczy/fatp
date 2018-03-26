package com.telecwin.fatp.controller.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.controller.param.OperatorQueryParam;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.enums.user.IdTypeDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.WithoutAuth;
import com.telecwin.fatp.po.sys.SysMemberRolePo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.sys.SysMemberOperatorRoleService;
import com.telecwin.fatp.service.sys.SysMemberRoleService;
import com.telecwin.fatp.service.user.MemberOperatorService;

@Controller
@RequestMapping("/member/operator")
public class MemberOperatorController extends BaseController{

	@Autowired
	private MemberOperatorService memberOperatorService;
	@Autowired
	private SysMemberOperatorRoleService sysMemberOperatorRoleService;
	@Autowired
	private SysMemberRoleService sysMemberRoleService;
	/**
	 * 操作员信息
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping("/myinfo")
	@WithoutAuth
	public String myinfo(HttpServletRequest request) {
		int operatorId = getSelfId();
		MemberOperatorPo memberOperator = memberOperatorService.getMemberOperatorById(operatorId);
		request.setAttribute("memberOperator", memberOperator);
		request.setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return viewPath + "myinfo";
	}
	
	/**
	 * 更新操作员信息
	 * @param memberOperator
	 * @return
	 */
	@RequestMapping("/updateMyinfo")
	@ResponseBody
	@WithoutAuth
	public Object updateMyinfo(@ModelAttribute MemberOperatorPo memberOperator){
		try {
			if(memberOperator.getId().intValue() != super.getSelfId().intValue()) {
				return resultError("登录信息已变更。");
			}
			memberOperatorService.updateMemberOperator(memberOperator,false);
			return resultSuccess();
		} catch (FatpException e){
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateMyinfo", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	/**
	 * 验证登录名
	 * @param loginName
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/validloginname")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> validLoginName(@RequestParam String loginName,@RequestParam(required=false) Integer memberId){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			if(memberId == null) {
				memberId = getMemberId();
			}
			memberOperatorService.validLoginName(memberId, loginName);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validPhone", e));
			retMap.put("error", "校验失败");
		}
		return retMap;
	}
	/**
	 * 验证手机号
	 * @param phone
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/validphone")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> validPhone(@RequestParam(required = true) String phone,@RequestParam(required=false) Integer memberId){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			if(memberId == null) {
				memberId = getMemberId();
			}
			memberOperatorService.validPhone(memberId,phone,null);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validPhone", e));
			retMap.put("error", "校验失败");
		}
		return retMap;
	}
	/**
	 * 验证身份证号
	 * @param idType
	 * @param idNumber
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/valididnumber")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> validIdNumber(@RequestParam int idType,@RequestParam String idNumber,@RequestParam(required = false) Integer memberId){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			if(memberId == null) {
				memberId = getMemberId();
			}
			memberOperatorService.validIdNumber(memberId, idType, null, idNumber, null);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validPhone", e));
			retMap.put("error", "校验失败");
		}
		return retMap;
	}
	/**
	 * 验证Email
	 * @param email
	 * @param memberId
	 * @return
	 */
	@RequestMapping("/validemail")
	@ResponseBody
	public Map<String,Object> validEmail(@RequestParam String email,@RequestParam(required=false) Integer memberId){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			if(memberId == null) {
				memberId = getMemberId();
			}
			memberOperatorService.validEmail(memberId, email, null);
			retMap.put("ok", "");
		} catch (FatpException e) {
			retMap.put("error",e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "validPhone", e));
			retMap.put("error", "校验失败");
		}
		return retMap;
	}
	/**
	 * 操作员列表
	 * @return
	 */
	@RequestMapping("/list")
	@WithoutAuth
	public ModelAndView list(@ModelAttribute OperatorQueryParam operatorParam){
		ModelAndView mv = init("sys/operator/list");
		try {
			operatorParam.setMemberId(getMemberId());
			operatorParam.setExchangeId(getExchangeId());
			PageData<MemberOperatorPo> pageData = memberOperatorService.pageFindByQueryParam(operatorParam);
			mv.addObject("operatorlist", pageData.getList());
			mv.addObject("total", pageData.getTotalsize());
		} catch (Exception e) {
			mv.addObject("operatorlist", Collections.EMPTY_LIST);
			mv.addObject("total",0);
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "list", e));
		}
		mv.addObject("operatorParam", operatorParam);
		mv.addObject("pageSize",operatorParam.getPageSize());
		mv.addObject("pageCurrent", operatorParam.getPageCurrent());
		mv.addObject("search", operatorParam);
		return mv;
	}
	
	/**
	 * 新增页面
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView viewAdd(){
		ModelAndView mv = init("sys/operator/add");
		request().setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		return mv;
	}
	
	/**
	 * 新增操作员
	 * @param memberOperator
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> add(@ModelAttribute MemberOperatorPo memberOperator){
		Map<String,Object> retMap = new HashMap<>();
		memberOperator.setMemberId(getMemberId());
		memberOperator.setExchangeId(getExchangeId());
		try {
			memberOperatorService.insertMemberOperator(memberOperator);
			retMap.put("statusCode", 200);
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "save", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "save", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "保存失败。");
		}
		return retMap;
	}
	
	/**
	 * 进入编辑页面
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年6月24日 下午5:34:19
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(Integer id){
		ModelAndView mv = init("sys/operator/edit");
		try {
			MemberOperatorPo memberOperator = memberOperatorService.getMemberOperatorById(id);
			mv.addObject("memberOperator", memberOperator);
			request().setAttribute("idTypeList", IdTypeDesc.getPersonalTypes());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "edit", e));
		}
		return mv;
	}
	
	/**
	 * 更新操作员信息
	 * @param memberOperator
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(@ModelAttribute MemberOperatorPo memberOperator){
		memberOperator.setMemberId(getMemberId());
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			memberOperatorService.updateMemberOperator(memberOperator, true);
			retMap.put("statusCode", 200);
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "更新失败。");
		}
		return retMap;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			memberOperatorService.deleteMemberOperator(id);
			retMap.put("statusCode", 200);
			retMap.put("message", "删除成功。");
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "删除失败。");
		}
		
		return retMap;
	}
	
	/**
	 * 更新操作员的状态 （解冻/冻结）
	 * @param id
	 * @param operatorStatus
	 * @return
	 */
	@RequestMapping("/updateopestatu")
	@ResponseBody
	@WithoutAuth
	public Map<String,Object> updateopestatu(@RequestParam(value = "id",required = true) Integer id,
											 @RequestParam(value = "operatorStatus",required = true) Integer operatorStatus){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			memberOperatorService.updateMemberOperatorStatu(id,operatorStatus);
			retMap.put("statusCode", 200);
			retMap.put("message", "操作成功");
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateopestatu", e));
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateopestatu", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "操作失败");
		}
		return retMap;
	}
	
	/**
	 * 设置权限
	 * @param id
	 * @return
	 */
	@RequestMapping("/setrole")
	@WithoutAuth
	public ModelAndView setRole(Integer id){
		ModelAndView mv = init("sys/operator/setroles");
		try {
			MemberOperatorPo memberOperator = memberOperatorService.getMemberOperatorById(id);
			mv.addObject("memberOperator", memberOperator);
			//根据操作员类型获取相关角色列表
			List<SysMemberRolePo> memberRoles = sysMemberRoleService.getMemberRolesByRoleType(getMemberId(),memberOperator.getOperatorType());
			mv.addObject("memberRoles", memberRoles);
			//拥有的角色ids
			List<Integer> roleIds = sysMemberOperatorRoleService.getRoleIdsByMemberOperatorId(id);
			mv.addObject("roleIds", roleIds);
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "setRole", e));
		}
		return mv;
	}
	
	/**
	 * 更新角色
	 * @param id
	 * @return
	 * @author zhiya.chai
	 * 2015年6月24日 下午5:48:06
	 */
	@RequestMapping("/updaterole")
	@ResponseBody
	public Map<String,Object> updateRole(Integer id,
										 @RequestParam(value="roleIds",required=false) String roleIds){
		Map<String,Object> retMap = new HashMap<String, Object>();
		try {
			sysMemberOperatorRoleService.addOperatorRoles(id, roleIds, getMemberId(), getSelfId());;
			retMap.put("statusCode", 200);
			retMap.put("message", "操作成功");
		} catch (FatpException e) {
			retMap.put("statusCode", 300);
			retMap.put("message", e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "updateRole", e));
			retMap.put("statusCode", 300);
			retMap.put("message", "操作失败");
		}
		return retMap;
	}
	
}
