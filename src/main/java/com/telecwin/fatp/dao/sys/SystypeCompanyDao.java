package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SystypeCompanyPo;

/**
 * SystypeCompany
 */
@MyBatisDao
public interface SystypeCompanyDao {
	
	public SystypeCompanyPo getById(int id);
	
	public List<SystypeCompanyPo> select(Map<String, Object> map);
}
