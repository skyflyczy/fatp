package com.fatp.dao;

import java.util.List;
import java.util.Map;

import com.fatp.domain.FeExchange;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * FeExchange
 */
@MyBatisDao
public interface FeExchangeDao {
	public List<FeExchange> select(Map<String, Object> map);
	
	public FeExchange getById(Map<String, Object> map);
}
