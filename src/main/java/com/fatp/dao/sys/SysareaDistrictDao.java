package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysareaDistrictPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysareaDistrict
 * @author auto-generator
 * 2015-06-30 17:10:15
 */
@MyBatisDao
public interface SysareaDistrictDao {
	
	public SysareaDistrictPo getById(int disId);
	
	public List<SysareaDistrictPo> select(Map<String, Object> map);
	
	public void insert(SysareaDistrictPo o);
	
}
