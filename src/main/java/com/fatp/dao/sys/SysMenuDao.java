package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysMenuPo;
import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;

@MyBatisDao
public interface SysMenuDao {

	public int insert(SysMenuPo o);
	
	@NoRowAffectException
	public int update(SysMenuPo o);
	
	public SysMenuPo getById(Map<String, Object> map);
	
	public List<SysMenuPo> select(Map<String, Object> map);
	
	public int updateStatus(Map<String, Object> map);
	
	public List<SysMenuPo> getUserMenus(Map<String,Object> map);
	
	public void delete(List<Integer> list);
}
