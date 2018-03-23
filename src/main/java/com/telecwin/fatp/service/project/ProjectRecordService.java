package com.telecwin.fatp.service.project;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.controller.param.project.ProjectRecordinfoVo;
import com.telecwin.fatp.convert.ProjectRecordConvertor;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.ProjectRecordComplex;
import com.telecwin.fatp.enums.project.FlowFeedTypeDesc;
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
	 * @param recordinfoVo
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addRecord(ProjectRecordinfoVo recordinfoVo,MemberOperatorPo operator) {
		// 1、新增备案
		ProjectRecordinfoPo recordinfoPo = ProjectRecordConvertor.convertRecordPo(recordinfoVo);
		recordinfoPo.setMemberId(operator.getMemberId());
		recordinfoPo.setExchangeId(operator.getExchangeId());
		recordinfoPo.setCreateOperatorId(operator.getId());
		recordinfoPo.setUpateOperatorId(operator.getId());
		recordinfoPo.setRecordCode(sysbizcodeSequenceService.getRecordSequence());
		projectRecordDataSupportService.addRecord(recordinfoPo);
		// 2、新增项目内容
		recordinfoVo.setRecordGuid(recordinfoPo.getRecordGuid());
		recordinfoVo.setId(recordinfoPo.getId());
		projectContentService.insert(ProjectRecordConvertor.convertRecordContent(recordinfoVo));
		//3、新增动态
		timelineDetailDataSupportService.createRecordTimeLine(recordinfoPo, FlowFeedTypeDesc.创建, "", operator.getRealName());
		return recordinfoPo.getId();
	}
	
}
