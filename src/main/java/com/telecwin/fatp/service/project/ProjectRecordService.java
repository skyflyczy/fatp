package com.telecwin.fatp.service.project;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.controller.param.RecordCheckParam;
import com.telecwin.fatp.convert.ProjectRecordConvertor;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.project.ProjectRecordComplex;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.FlowFeedTypeDesc;
import com.telecwin.fatp.enums.project.RecordStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ProjectContentPo;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.telecwin.fatp.service.datasupprot.project.ProjectRecordDataSupportService;
import com.telecwin.fatp.service.sys.SysbizcodeSequenceService;

@Service
public class ProjectRecordService extends BaseService{

	@Autowired
	private ProjectRecordDataSupportService projectRecordDataSupportService;
	@Autowired
	private ProjectContentService projectContentService;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	@Autowired
	private ProjectLinkmanService projectLinkmanService;
	
	/**
	 * 分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ProjectRecordComplex> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize) {
		return projectRecordDataSupportService.pageFindByCondition(map, pageNo, pageSize);
	}
	/**
	 * 新增备案
	 * @param recordinfo
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addRecord(ProjectRecordinfo recordinfo,MemberOperatorPo operator) {
		// 1、新增备案
		ProjectRecordinfoPo recordinfoPo = ProjectRecordConvertor.convertRecordPo(recordinfo);
		recordinfoPo.setMemberId(operator.getMemberId());
		recordinfoPo.setExchangeId(operator.getExchangeId());
		recordinfoPo.setCreateOperatorId(operator.getId());
		recordinfoPo.setUpateOperatorId(operator.getId());
		recordinfoPo.setRecordCode(sysbizcodeSequenceService.getRecordSequence());
		projectRecordDataSupportService.addRecord(recordinfoPo);
		// 2、新增项目内容
		recordinfo.setRecordGuid(recordinfoPo.getRecordGuid());
		recordinfo.setId(recordinfoPo.getId());
		projectContentService.insert(ProjectRecordConvertor.convertRecordContent(recordinfo));
		//3、新增动态
		timelineDetailDataSupportService.createRecordTimeLine(recordinfoPo, FlowFeedTypeDesc.创建, "", operator.getRealName());
		return recordinfoPo.getId();
	}
	/**
	 * 根据GuId查找备案信息
	 * @return
	 */
	public ProjectRecordinfo getByRecordGuid(String recordGuid){
		return projectRecordDataSupportService.getByRecordGuid(recordGuid);
	}
	
	public ProjectRecordinfo getById(int id){
		ProjectRecordinfo project =  projectRecordDataSupportService.getById(id);
		projectLinkmanService.setLinkmanInfo(project);
		return project;
	}
	
	/**
	 * 更新备案信息
	 * @param recordinfo
	 * @param operator
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateRecord(ProjectRecordinfo newRecordinfo,MemberOperatorPo operator){
		ProjectRecordinfo oldRecordInfo = projectRecordDataSupportService.getById(newRecordinfo.getId());
		if(oldRecordInfo == null) {
			throw new FatpException(ErrorCode.RECORDINFO_NOT_EXIST);
		}
		if(!oldRecordInfo.canEdit()) {
			throw new FatpException(ErrorCode.RECORDINFO_STATUS_ERROR);
		}
		ProjectRecordConvertor.recordNewToOld(newRecordinfo, oldRecordInfo, operator);
		//更新联系人
		if(newRecordinfo.getLoanLinkman() != null) {
			int loanLinkId = projectLinkmanService.insertLinkman(newRecordinfo.getLoanLinkman());
			oldRecordInfo.setLoanLinkId(loanLinkId);
		}
		if(newRecordinfo.getAgentLinkman() != null) {
			int agentLinkId = projectLinkmanService.insertLinkman(newRecordinfo.getAgentLinkman());
			oldRecordInfo.setUnderwriterLinkId(agentLinkId);
		}
		//更新备案信息
		ProjectRecordinfoPo recordinfoPo = ProjectRecordConvertor.convertRecordPo(oldRecordInfo);
		projectRecordDataSupportService.update(recordinfoPo);
		//---更新内容信息
		ProjectContentPo contentPo = ProjectRecordConvertor.convertRecordContent(newRecordinfo);
		projectContentService.updateByProjectId(contentPo);
		//---新增动态
		FlowFeedTypeDesc flowType = FlowFeedTypeDesc.编辑保存;
		if(recordinfoPo.getRecordStatus().intValue() == RecordStatusDesc.待审核.value) {
			flowType = FlowFeedTypeDesc.提交审核;
		}
		timelineDetailDataSupportService.createRecordTimeLine(recordinfoPo, flowType, "", operator.getRealName());
		return recordinfoPo.getId().intValue();
	}
	/**
	 * 删除备案信息
	 * @param id
	 */
	@Transactional(rollbackFor=Exception.class)
	public void deleteRecord(String recordGuid) {
		ProjectRecordinfo recordInfo = projectRecordDataSupportService.getByRecordGuid(recordGuid);
		if(!recordInfo.canDelete()) {
			throw new FatpException(ErrorCode.RECORDINFO_STATUS_ERROR);
		}
		projectRecordDataSupportService.deleteRecordById(recordInfo.getId(), recordInfo.getVersionNo());
		projectContentService.deleteByRecordId(recordInfo.getId());
		//TODO 删除附件
	}
	/**
	 * 备案审核
	 * @param param
	 * @param recordStatus
	 */
	@Transactional(rollbackFor=Exception.class)
	public void check(RecordCheckParam param,RecordStatusDesc recordStatus){
		ProjectRecordinfo record = projectRecordDataSupportService.getById(param.getId());
		if(record == null) {
			throw new FatpException(ErrorCode.RECORDINFO_NOT_EXIST);
		}
		if(record.getRecordStatus().intValue() != RecordStatusDesc.待审核.value) {
			throw new FatpException(ErrorCode.RECORDINFO_STATUS_ERROR);
		}
		ProjectRecordinfoPo recordinfoPo = ProjectRecordConvertor.convertRecordPo(record);
		recordinfoPo.setRecordStatus(recordStatus.value);
		recordinfoPo.setDeadline(param.getDeadline());
		recordinfoPo.setAuditOperatorId(param.getOperatorId());
		recordinfoPo.setAuditTime(new Date());
		recordinfoPo.setAuditRemark(param.getFlowFeedOpinion());
		recordinfoPo.setUpateOperatorId(param.getOperatorId());
		projectRecordDataSupportService.update(recordinfoPo);
		//增加动态
		FlowFeedTypeDesc flowType = FlowFeedTypeDesc.审核通过;
		if(recordinfoPo.getRecordStatus().intValue() == RecordStatusDesc.审核不通过.value) {
			flowType = FlowFeedTypeDesc.审核不通过;
		} else if(recordinfoPo.getRecordStatus().intValue() == RecordStatusDesc.审核退回.value) {
			flowType = FlowFeedTypeDesc.审核退回;
		}
		timelineDetailDataSupportService.createRecordTimeLine(recordinfoPo, flowType, param.getFlowFeedOpinion(), param.getOperatorName());
	}
	/**
	 * 获取可挂牌列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ProjectRecordComplex> getCanQuotedRecordList(Map<String,Object> map,int pageNo, int pageSize) {
		map.put("recordStatus", RecordStatusDesc.审核通过.value);
		return projectRecordDataSupportService.getCanQuotedRecordList(map, pageNo, pageSize);
	}
	/**
	 * 获取可挂牌备案
	 * @param recordId
	 * @param exchangeId
	 * @return
	 */
	public ProjectRecordComplex getCanQuotedRecord(int recordId,int exchangeId) {
		return projectRecordDataSupportService.getCanQuotedRecord(recordId, exchangeId);
	}
}
