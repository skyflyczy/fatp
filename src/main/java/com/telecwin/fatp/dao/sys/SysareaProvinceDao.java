package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SysareaProvincePo;

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
