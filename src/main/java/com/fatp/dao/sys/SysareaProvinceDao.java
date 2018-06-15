package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysareaProvincePo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysareaProvince
 * @author auto-generator
 * 2015-06-30 17:10:02
 */
@MyBatisDao
public interface SysareaProvinceDao {
	
	public SysareaProvincePo getById(int proId);
	
	public List<SysareaProvincePo> select(Map<String, Object> map);
}
