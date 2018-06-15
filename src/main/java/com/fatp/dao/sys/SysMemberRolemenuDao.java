package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysMemberRolemenuPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * SysMemberRolemenu
 * @author auto-generator
 * 2015-06-30 40:12:11
 */
@MyBatisDao
public interface SysMemberRolemenuDao {
	
	public int delete(Map<String, Object> map);
	
	public List<SysMemberRolemenuPo> select(Map<String, Object> map);
	
	public void insertBatch(List<SysMemberRolemenuPo> sysMemberRolemenus);
	
	public int deleteByRoleOrMenu(Map<String, Object> queryMap);
	
	public int updateByRoleOrMenu(Map<String,Object> queryMap);
}
