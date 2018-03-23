package com.telecwin.fatp.dao;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.TimelineDetailPo;

@MyBatisDao
public interface TimelineDetailDao {

	int insert(TimelineDetailPo timelineDetailPo);
}
