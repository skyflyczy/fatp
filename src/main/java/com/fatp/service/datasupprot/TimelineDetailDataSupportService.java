package com.fatp.service.datasupprot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.TimelineDetailDao;
import com.fatp.domain.UcUser;
import com.fatp.enums.EntityType;
import com.fatp.enums.FlowFeedTypeDesc;
import com.fatp.po.TimelineDetailPo;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.project.ListingInfoPo;

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
	
	/**
	 * 创建挂牌动态
	 * @param listingInfoPo
	 * @param flowFeedTypeDesc
	 * @param remark
	 * @param operatorName
	 */
	public void createListingInfoTimeLine(ListingInfoPo listingInfoPo,FlowFeedTypeDesc flowFeedTypeDesc,String remark,String operatorName){
		try {
			TimelineDetailPo timelineDetailPo = new TimelineDetailPo();
			Date eventTime = listingInfoPo.getUpdateTime();
			if (eventTime == null) {
				eventTime = new Date();
			}
			timelineDetailPo.setEntityType(EntityType.挂牌.getValue());
			timelineDetailPo.setEntityId(listingInfoPo.getId());
			timelineDetailPo.setEventId(flowFeedTypeDesc==null?null:flowFeedTypeDesc.getType());
			timelineDetailPo.setEventName(flowFeedTypeDesc==null?"":flowFeedTypeDesc.name());
			timelineDetailPo.setEventTime(new Date());
			timelineDetailPo.setEventOperatorId(listingInfoPo.getUpateOperatorId());
			timelineDetailPo.setEventOperatorName(operatorName);
			timelineDetailPo.setEventRemark(remark);
			timelineDetailPo.setStatusChangeDesc(flowFeedTypeDesc==null?"":flowFeedTypeDesc.getText());
			timelineDetailDao.insert(timelineDetailPo);
		} catch (Exception e) {
			logger.error("创建挂牌动态错误。" , e);
		}
	}
	/**
	 * 创建投资明细登记动态
	 * @param apply
	 * @param flowFeedTypeDesc
	 * @param remark
	 * @param operatorName
	 */
	public void createInvestRecordsTimeLine(BizimportApplyPo apply,FlowFeedTypeDesc flowFeedTypeDesc,String remark,String operatorName){
		try {
			TimelineDetailPo timelineDetailPo = new TimelineDetailPo();
			Date eventTime = apply.getUpdateTime();
			if (eventTime == null) {
				eventTime = new Date();
			}
			timelineDetailPo.setEntityType(EntityType.投资明细登记.getValue());
			timelineDetailPo.setEntityId(apply.getId());
			timelineDetailPo.setMemberId(timelineDetailPo.getMemberId());
			timelineDetailPo.setEventId(flowFeedTypeDesc==null ? null :flowFeedTypeDesc.getType());
			timelineDetailPo.setEventName(flowFeedTypeDesc==null ? "" :flowFeedTypeDesc.name());
			timelineDetailPo.setEventTime(new Date());
			timelineDetailPo.setEventOperatorId(apply.getApplyOperatorId());
			timelineDetailPo.setEventOperatorName(operatorName);
			timelineDetailPo.setEventRemark(remark);
			timelineDetailPo.setStatusChangeDesc(flowFeedTypeDesc==null?"":flowFeedTypeDesc.getText());
			timelineDetailDao.insert(timelineDetailPo);
		} catch (Exception e) {
			logger.error("创建投资明细登记错误。" , e);
		}
	}
}
