package com.telecwin.fatp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.po.TimelineDetailPo;
import com.telecwin.fatp.service.datasupprot.TimelineDetailDataSupportService;

@Service
public class TimelineDetailService extends BaseService{
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;

	public List<TimelineDetailPo> getListByEntityType(int entityType,int entityId) {
		return timelineDetailDataSupportService.getListByEntityType(entityType,entityId);
	}
}
