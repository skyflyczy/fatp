package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SysParamPo;

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
