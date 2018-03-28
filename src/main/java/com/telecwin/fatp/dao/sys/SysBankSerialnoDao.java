package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.domain.SysBankSerialno;

/**
 * SysBankSerialno
 */
@MyBatisDao
public interface SysBankSerialnoDao {
	
	public SysBankSerialno getById(Map<String, Object> map);
	
	public List<SysBankSerialno> select(Map<String, Object> map);
	
	public void insert(SysBankSerialno o);

	@NoRowAffectException
	public int update(SysBankSerialno sysBankSerialno);
}
