package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.domain.SysBankSerialno;
import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;

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
