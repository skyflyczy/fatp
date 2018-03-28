package com.telecwin.fatp.service.datasupprot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.TimelineDetailDao;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.enums.EntityType;
import com.telecwin.fatp.enums.FlowFeedTypeDesc;
import com.telecwin.fatp.po.TimelineDetailPo;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;

@Service
public class TimelineDetailDataSupportService {
	protected Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private TimelineDetailDao timelineDetailDao;
	
	public List<TimelineDetailPo> getListByEntityType(int entityType,int entityId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("entityType", entityType);
		map.put("entityId", entityId);
		return timelineDetailDao.getListByEntityType(map);
	}
	
	/**
	 * 创建备案动态
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
	
	/**
	 * 创建会员动态
	 * @param recordInfoPo
	 * @param flowFeedTypeDesc
	 * @param remark
	 * @param operatorName
	 */
	public void createMemberTimeLine(UcUser user,FlowFeedTypeDesc flowFeedTypeDesc,String remark,String operatorName){
		try {
			TimelineDetailPo timelineDetailPo = new TimelineDetailPo();
			Date eventTime = user.getUpdateTime();
			if (eventTime == null) {
				eventTime = new Date();
			}
			timelineDetailPo.setEntityType(EntityType.发行人.getValue());
			timelineDetailPo.setEntityId(user.getId());
			timelineDetailPo.setMemberId(user.getOwnerUserId());
			timelineDetailPo.setEventId(flowFeedTypeDesc==null?null:flowFeedTypeDesc.getType());
			timelineDetailPo.setEventName(flowFeedTypeDesc==null?"":flowFeedTypeDesc.name());
			timelineDetailPo.setEventTime(new Date());
			timelineDetailPo.setEventOperatorId(user.getUpdateOperatorId());
			timelineDetailPo.setEventOperatorName(operatorName);
			timelineDetailPo.setEventRemark(remark);
			timelineDetailPo.setStatusChangeDesc(flowFeedTypeDesc==null?"":flowFeedTypeDesc.getText());
			timelineDetailDao.insert(timelineDetailPo);
		} catch (Exception e) {
			logger.error("创建会员动态错误。" , e);
		}
	}
}
