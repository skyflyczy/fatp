package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SystypeProjectPo;

@MyBatisDao
public interface SystypeProjectDao {

	public List<SystypeProjectPo> select(Map<String, Object> map);
}
