package com.telecwin.fatp.dao;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.GlobalFilePo;
/**
 * globalFile
 */
@MyBatisDao
public interface GlobalFileDao {

	public GlobalFilePo getGlobalFileById(int id);
}
