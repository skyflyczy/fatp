package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.po.sys.SysMemberRolePo;

/**
 * 
 * SysMemberRole
 * @author auto-generator
 * 2015-06-29 02:18:26
 */
@MyBatisDao
public interface SysMemberRoleDao {
	public int insert(SysMemberRolePo o);
	@NoRowAffectException
	public int update(SysMemberRolePo o);
	
	public SysMemberRolePo getById(Map<String, Object> map);
	
	public List<SysMemberRolePo> select(Map<String, Object> map);
	@NoRowAffectException
	public void delete(int id);
}
