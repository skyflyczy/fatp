package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SysMemberOperatorRolePo;

/**
 * 
 * SysMemberOperatorRole
 * @author auto-generator
 * 2015-07-03 44:13:09
 */
@MyBatisDao
public interface SysMemberOperatorRoleDao {
	public List<SysMemberOperatorRolePo> select(Map<String, Object> map);
	
	public int insertBatch(List<SysMemberOperatorRolePo> sysMemberOperatorRoles);
	
	public int updateByOpeartorOrRole(Map<String,Object> queryMap);
	
	public int deleteByOpeartorOrRole(Map<String,Object> queryMap);
}
