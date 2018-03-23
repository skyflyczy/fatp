package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SysAppsPo;

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
