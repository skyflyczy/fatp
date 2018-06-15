package com.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.fatp.po.sys.SystypeProjectPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

@MyBatisDao
public interface SystypeProjectDao {

	public List<SystypeProjectPo> select(Map<String, Object> map);
}
