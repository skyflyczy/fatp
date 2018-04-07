package com.telecwin.fatp.controller.project.record;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.controller.param.RecordCheckParam;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
/**
 * 备案审核
 */
@Controller
@RequestMapping("/project/record/check/")
public class RecordCheckController extends RecordSupport{
	
	private String viewPath = super.viewPath + "project/record/check";

	/**
	 * 审核列表
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String checkList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		this.getRecordCheckingList(map);
		request().setAttribute("action", "checklist");
		return viewPath + "/list";
	}
	
	/**
	 * 
	 * @param guid
	 * @return
	 */
	@RequestMapping("view")
	@ResponseBody
	public Object view(String id) {
		//项目信息
		ProjectRecordinfo obj = projectRecordService.getByRecordGuid(id);
		if(obj.getRecordStatus().intValue() != RecordStatusDesc.待审核.value) {
			return resultError(ErrorCode.RECORDINFO_STATUS_ERROR.getMessage());
		}
		request().setAttribute("project", obj);
		request().setAttribute("todayForJudge", DateUtils.getDate());
		return new ModelAndView(viewPath + "/check");
	}
	
	/**
	 * 项目审核
	 * @param id
	 * @param projectStatus
	 * @param flowFeedOpinion
	 * @return
	 * @return Object
	 */
	@RequestMapping("check")
	@ResponseBody
	public Object firstCheck(@ModelAttribute RecordCheckParam param, int pass) {
		try {
			RecordStatusDesc recordStatus = RecordStatusDesc.审核通过;
			if(pass == 0) {
				recordStatus = RecordStatusDesc.审核退回;
			} else if(pass == 1){
				recordStatus = RecordStatusDesc.审核通过;
			} else if(pass == 2) {
				recordStatus = RecordStatusDesc.审核不通过;
			}
			param.setOperatorId(super.getSelfId());
			param.setOperatorName(super.getSelfName());
			projectRecordService.check(param, recordStatus);
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "check", e));
			return resultError(e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "check", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
}
