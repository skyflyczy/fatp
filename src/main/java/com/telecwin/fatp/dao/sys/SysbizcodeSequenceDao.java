package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.huajin.baymax.db.annotation.NoRowAffectException;
import com.telecwin.fatp.po.sys.SysbizcodeSequencePo;

@MyBatisDao
public interface SysbizcodeSequenceDao {
	@NoRowAffectException
	public int update(SysbizcodeSequencePo o);
	
	public SysbizcodeSequencePo getById(Map<String, Object> map);
	public List<SysbizcodeSequencePo> select(Map<String, Object> map);
	
}
