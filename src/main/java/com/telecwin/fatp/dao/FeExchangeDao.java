package com.telecwin.fatp.dao;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.FeExchange;

/**
 * 
 * FeExchange
 * @author auto-generator
 * 2015-10-09 23:09:05
 */
@MyBatisDao
public interface FeExchangeDao {
	public List<FeExchange> select(Map<String, Object> map);
	
	public FeExchange getById(Map<String, Object> map);
}
