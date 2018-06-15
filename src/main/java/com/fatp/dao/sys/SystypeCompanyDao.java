package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SystypeCompanyPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * SystypeCompany
 */
@MyBatisDao
public interface SystypeCompanyDao {
	
	public SystypeCompanyPo getById(int id);
	
	public List<SystypeCompanyPo> select(Map<String, Object> map);
}
