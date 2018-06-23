package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysWorkdatePo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysWorkdate
 * @author zhiya.chai
 * 2018-06-23 24:15:21
 */
@MyBatisDao
public interface SysWorkdateDao {
	public List<SysWorkdatePo> select(Map<String, Object> map);
	
	/**
	 * 当期日期所属工作日
	 * @return SysWorkdate
	 */
	public SysWorkdatePo getBelongWorkDate(Map<String, Object> map);
}
