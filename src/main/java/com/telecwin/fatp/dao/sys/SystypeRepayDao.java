package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SystypeRepayPo;

/**
 * SystypeRepay
 */
@MyBatisDao
public interface SystypeRepayDao {
	
	public SystypeRepayPo getById(Map<String, Object> map);
	
	public List<SystypeRepayPo> select(Map<String, Object> map);
}
