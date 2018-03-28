package com.telecwin.fatp.dao.user;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.domain.UcUser;

/**
 * UcUser
 */
@MyBatisDao
public interface UcUserDao {
	public List<UcUser> select(Map<String, Object> map);
	public UcUser getAllById(Map<String, Object> map);
	public UcUser getUserExtById(Integer id);
	
	@NoRowAffectException
	public int update(UcUser o);
	@NoRowAffectException
	public int updateExt(UcUser o);
	
	public int insert(UcUser o);
	
	public void insertExt(UcUser o);
	@NoRowAffectException
	public int updateUserStatus(UcUser o);
}
