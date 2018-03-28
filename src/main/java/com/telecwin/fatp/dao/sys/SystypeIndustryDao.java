package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SystypeIndustryPo;

/**
 * SystypeIndustry
 */
@MyBatisDao
public interface SystypeIndustryDao {
	
	public SystypeIndustryPo getById(int id);
	
	public List<SystypeIndustryPo> select(Map<String, Object> map);
}
