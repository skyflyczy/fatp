package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SystypeRepayPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * SystypeRepay
 */
@MyBatisDao
public interface SystypeRepayDao {
	
	public SystypeRepayPo getById(Map<String, Object> map);
	
	public List<SystypeRepayPo> select(Map<String, Object> map);
}
