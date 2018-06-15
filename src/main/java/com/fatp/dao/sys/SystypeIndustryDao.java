package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SystypeIndustryPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * SystypeIndustry
 */
@MyBatisDao
public interface SystypeIndustryDao {
	
	public SystypeIndustryPo getById(int id);
	
	public List<SystypeIndustryPo> select(Map<String, Object> map);
}
