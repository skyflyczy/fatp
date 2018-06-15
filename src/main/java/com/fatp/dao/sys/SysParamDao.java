package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysParamPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysParam
 * @author auto-generator
 * 2015-06-26 31:09:13
 */
@MyBatisDao
public interface SysParamDao {
	
	public SysParamPo getByParamKey(String paramKey);
	
	public List<SysParamPo> select(Map<String, Object> map);
	
}
