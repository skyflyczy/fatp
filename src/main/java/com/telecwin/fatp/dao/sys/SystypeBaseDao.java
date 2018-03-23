package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SystypeBasePo;

/**
 * SystypeBase
 */
@MyBatisDao
public interface SystypeBaseDao {
	
	public List<SystypeBasePo> select(Map<String, Object> map);

}
