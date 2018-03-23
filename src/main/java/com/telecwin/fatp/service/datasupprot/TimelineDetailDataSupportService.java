package com.telecwin.fatp.service.datasupprot;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.TimelineDetailDao;
import com.telecwin.fatp.enums.EntityType;
import com.telecwin.fatp.enums.project.FlowFeedTypeDesc;
import com.telecwin.fatp.po.TimelineDetailPo;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;

@Service
public class TimelineDetailDataSupportService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private TimelineDetailDao timelineDetailDao;
	/**
	 * 创建备案流程
	 * @param recordInfoPo
	 * @param flowFeedTypeDesc
	 * @param remark
	 * @param operatorName
	 */
	public void createRecordTimeLine(ProjectRecordinfoPo recordInfoPo,FlowFeedTypeDesc flowFeedTypeDesc,String remark,String operatorName){
		try {
			TimelineDetailPo timelineDetailPo = new TimelineDetailPo();
			Date eventTime = recordInfoPo.getUpdateTime();
			if (eventTime == null) {
				eventTime = new Date();
			}
			timelineDetailPo.setEntityType(EntityType.备案.getValue());
			timelineDetailPo.setEntityId(recordInfoPo.getId());
			timelineDetailPo.setMemberId(recordInfoPo.getMemberId());
			timelineDetailPo.setEventId(flowFeedTypeDesc==null?null:flowFeedTypeDesc.getType());
			timelineDetailPo.setEventName(flowFeedTypeDesc==null?"":flowFeedTypeDesc.name());
			timelineDetailPo.setEventTime(new Date());
			timelineDetailPo.setEventOperatorId(recordInfoPo.getUpateOperatorId());
			timelineDetailPo.setEventOperatorName(operatorName);
			timelineDetailPo.setEventRemark(remark);
			timelineDetailPo.setStatusChangeDesc(flowFeedTypeDesc==null?"":flowFeedTypeDesc.getText());
			timelineDetailDao.insert(timelineDetailPo);
		} catch (Exception e) {
			logger.error("创建备案动态错误。" , e);
		}
		
	}
}
