package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SystypeBasePo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * SystypeBase
 */
@MyBatisDao
public interface SystypeBaseDao {
	
	public List<SystypeBasePo> select(Map<String, Object> map);

}
