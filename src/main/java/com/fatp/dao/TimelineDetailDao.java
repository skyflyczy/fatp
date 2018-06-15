package com.fatp.dao;

import java.util.List;
import java.util.Map;

import com.fatp.po.TimelineDetailPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

@MyBatisDao
public interface TimelineDetailDao {

	int insert(TimelineDetailPo timelineDetailPo);
	
	List<TimelineDetailPo> getListByEntityType(Map<String,Object> map);
}
