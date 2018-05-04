package com.telecwin.fatp.dao;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.GlobalFile;
import com.telecwin.fatp.po.GlobalFilePo;
/**
 * globalFile
 */
@MyBatisDao
public interface GlobalFileDao {

	public GlobalFile getGlobalFileById(int id);
	
	public int insert(GlobalFilePo po);
	
	public void deleteById(int id);
}
