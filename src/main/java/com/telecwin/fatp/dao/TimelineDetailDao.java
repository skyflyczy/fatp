package com.telecwin.fatp.dao;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.TimelineDetailPo;

@MyBatisDao
public interface TimelineDetailDao {

	int insert(TimelineDetailPo timelineDetailPo);
	
	List<TimelineDetailPo> getListByEntityType(Map<String,Object> map);
}
