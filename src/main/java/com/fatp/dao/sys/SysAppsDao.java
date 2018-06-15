package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysAppsPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysApps
 * @author auto-generator
 * 2015-06-29 45:13:55
 */
@MyBatisDao
public interface SysAppsDao {
	
	public List<SysAppsPo> select(Map<String, Object> map);
}
