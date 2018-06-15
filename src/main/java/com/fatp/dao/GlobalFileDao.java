package com.fatp.dao;

import com.fatp.domain.GlobalFile;
import com.fatp.po.GlobalFilePo;
import com.huajin.baymax.db.annotation.MyBatisDao;
/**
 * globalFile
 */
@MyBatisDao
public interface GlobalFileDao {

	public GlobalFile getGlobalFileById(int id);
	
	public int insert(GlobalFilePo po);
	
	public void deleteById(int id);
}
