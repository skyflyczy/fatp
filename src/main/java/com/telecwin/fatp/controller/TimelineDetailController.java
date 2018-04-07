package com.telecwin.fatp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.telecwin.fatp.enums.EntityType;
import com.telecwin.fatp.po.TimelineDetailPo;
import com.telecwin.fatp.service.TimelineDetailService;

/**
 * 动态
 * 
 */
@Controller
@RequestMapping("/timeline")
public class TimelineDetailController extends BaseController{

	private String viewPath = super.viewPath + "timeline/timelinelist";
	@Autowired
	private TimelineDetailService timelineDetailService;
	
	@RequestMapping("/member")
	public String getMemberTimeline(Integer entityId){
		List<TimelineDetailPo> list = timelineDetailService.getListByEntityType(EntityType.发行人.value,entityId);
		request().setAttribute("list", list);
		return viewPath;
	}
	@RequestMapping("/record")
	public String getRecordTimeline(Integer entityId) {
		List<TimelineDetailPo> list = timelineDetailService.getListByEntityType(EntityType.备案.value,entityId);
		request().setAttribute("list", list);
		return viewPath;
	}
}
