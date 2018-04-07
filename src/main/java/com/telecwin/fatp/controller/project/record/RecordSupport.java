package com.telecwin.fatp.controller.project.record;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.domain.project.ProjectRecordComplex;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.project.ProjectLimitType;
import com.telecwin.fatp.enums.project.RecordSortColumn;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.sys.SystypeProjectPo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.project.ProjectContentService;
import com.telecwin.fatp.service.project.ProjectRecordService;
import com.telecwin.fatp.service.sys.FeExchangeService;
import com.telecwin.fatp.service.sys.SystypeProjectService;
import com.telecwin.fatp.service.sys.SystypeRepayService;
import com.telecwin.fatp.service.user.UcUserService;
import com.telecwin.fatp.util.Constant;
import com.telecwin.fatp.util.SortUtil;

@Controller
public class RecordSupport extends BaseController{
	
	@Autowired
	protected ProjectRecordService projectRecordService;
	@Autowired
	private ProjectContentService projectContentService;
	@Autowired
	private SystypeProjectService systypeProjectService;
	@Autowired
	protected UcUserService ucUserService;
	@Autowired
	private SystypeRepayService systypeRepayService;
	@Autowired
	private FeExchangeService feExchangeService;

	//正在备案的产品状态
	private static final RecordStatusDesc[] RECORDING_STATUS = new RecordStatusDesc[]{RecordStatusDesc.待提交,RecordStatusDesc.审核退回,RecordStatusDesc.审核不通过};
	private static final RecordStatusDesc[] RECORD_CHECKING_STATUS = new RecordStatusDesc[]{RecordStatusDesc.待审核};
	
	protected String handleSortColumn(RecordSortColumn defaultRecordSortColumn) {
		String orderField = request().getParameter("orderField");
		String orderDirection = request().getParameter("orderDirection");
		return SortUtil.getSortColumns(orderField, orderDirection, defaultRecordSortColumn);
	}
	/**
	 * 获取正在备案的列表
	 * @param map
	 * @return
	 */
	public List<ProjectRecordComplex> getRecordingList(Map<String, Object> map){
		//待处理的备案列表、全部备案列表
		int[] searchStatus = new int[RECORDING_STATUS.length];
		for(int i=0; i<RECORDING_STATUS.length; i++) {
			searchStatus[i] = RECORDING_STATUS[i].value;
		}
		map.put("recordStatusList", searchStatus);
		map.put("memberId", super.getMemberId());
		map.put("exchangeId", super.getExchangeId());
		map.put("sortColumns", handleSortColumn(RecordSortColumn.updateTime));
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ProjectRecordComplex> pageData = projectRecordService.pageFindByCondition(map, pageNo, pageSize);
		request().setAttribute("recordStatusDesc", RECORDING_STATUS);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("search", map);
		return pageData.getList();
	}
	/**
	 * 获取审核列表
	 * @param map
	 * @return
	 */
	public List<ProjectRecordComplex> getRecordCheckingList(Map<String,Object> map) {
		int[] searchStatus = new int[RECORD_CHECKING_STATUS.length];
		for(int i=0; i<RECORD_CHECKING_STATUS.length; i++) {
			searchStatus[i] = RECORD_CHECKING_STATUS[i].value;
		}
		map.put("recordStatusList", searchStatus);
		map.put("memberId", super.getMemberId());
		map.put("exchangeId", super.getExchangeId());
		map.put("sortColumns", handleSortColumn(RecordSortColumn.updateTime));
		Object updateTimeEnd = map.get("updateTimeEnd");
		if(updateTimeEnd != null) {
			map.put("updateTimeEnd", updateTimeEnd.toString() + " 23:59:59");
		}
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ProjectRecordComplex> pageData = projectRecordService.pageFindByCondition(map, pageNo, pageSize);
		map.put("updateTimeEnd", updateTimeEnd);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("systypeRepayList", systypeRepayService.findAll());
		request().setAttribute("search", map);
		request().setAttribute("projectLimitTypeList", ProjectLimitType.values());
		return pageData.getList();
	}
	
	/**
	 * 添加项目前处理
	 * @param map
	 * @return
	 */
	public void beforeAdd(Map<String, Object> map) {
		//取项目类型
		int productTypeId = Integer.parseInt(String.valueOf(map.get("productTypeId")));
		List<SystypeProjectPo> systypeProjectList = systypeProjectService.findByProductTypeId(productTypeId);
		//取项目来源
		request().setAttribute("systypeProjectList", systypeProjectList);
	}
	/**
	 * 项目录入唯一性验证
	 * @param request
	 * @return Object
	 */
	public Object doValidProject(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			String recordName = request.getParameter("recordName");
			String recordFullName = request.getParameter("recordFullName");
			//校验 备案简称、备案全称
			String regEx = "^[\u4e00-\u9fa5-\\s()\\[\\]{}——_a-zA-Z0-9]{1,100}$";
			if(StringUtils.isNotBlank(recordName)) {
				if(!recordName.matches(regEx)) {
					json.put("error", ErrorCode.RECORD_NAME_NOT_MATCH.getMessage());
					return json;
				}
			}else if(StringUtils.isNotBlank(recordFullName)) {
				if(!recordFullName.matches(regEx)) {
					json.put("error", ErrorCode.RECORD_FULLNAME_NOT_MATCH.getMessage());
					return json;
				}
			}
			json.put("ok", "");
			return json;
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "doValidProject", e));
			json.put("error", ErrorCode.SYSTEM_ERROR);
			return json;
		}
	}
	
	/**
	 * 添加项目
	 * @param projectRrecordinfo
	 * @return
	 * @return Object
	 */
	public Object doCreate(ProjectRecordinfo projectRrecordinfo) {
		try {
			projectRrecordinfo.setRecordName(projectRrecordinfo.getRecordFullName());
			int id = projectRecordService.addRecord(projectRrecordinfo,super.getMemberOperator());
			JSONObject json = resultSuccess();
			json.put("id", id);
			return json;
		} catch (FatpException e) {
			return resultError(e.getMessage());
		} 
	}
	
	/**
	 * 获取备案相关信息（包含页面上需要的数据）
	 * @param id
	 * @return
	 */
	public ProjectRecordinfo getProjectRecordInfo(int id) {
		MemberOperatorPo operator = super.getMemberOperator();
		ProjectRecordinfo record = projectRecordService.getById(id);
		UcUser user = ucUserService.getAllById(record.getLoanUserId(), operator.getExchangeId());
		request().setAttribute("loanUserName", user.getUserName());
		request().setAttribute("loanUserCompanyName", user.getCompanyName());
		request().setAttribute("loanUserId", user.getId());
		request().setAttribute("loanUser", user);
		
		request().setAttribute("obj", record);
		//取项目类型
		request().setAttribute("systypeProjectList", systypeProjectService.findByProductTypeId(record.getProductTypeId()));
		//取还款方式
		request().setAttribute("systypeRepayList", systypeRepayService.findAll());
		request().setAttribute("projectLimitTypeList", ProjectLimitType.values());
		request().setAttribute("todayForJudge", DateUtils.getDate());
		request().setAttribute("content", projectContentService.getByProjectId(record.getId()));
		return record;
	}
}
