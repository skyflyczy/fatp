package com.telecwin.fatp.dao.sys;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.sys.SysareaCityPo;

/**
 * 
 * SysareaCity
 * @author auto-generator
 * 2015-06-30 17:10:10
 */
@MyBatisDao
public interface SysareaCityDao {
	
	public SysareaCityPo getById(int cityId);
	
	public List<SysareaCityPo> select(Map<String, Object> map);
	
	public List<SysareaCityPo> selectBankCity(Map<String, Object> map);
}
