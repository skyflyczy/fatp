package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SysBankPo;
import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;

/**
 * SysBankchannel
 */
@MyBatisDao
public interface SysBankDao {
	
	public int insert(SysBankPo sysBank);
	
	@NoRowAffectException
	public int update(SysBankPo sysBank);
	
	public SysBankPo getById(int id);
	
	public List<SysBankPo> select(Map<String, Object> map);
}
