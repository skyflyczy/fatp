package com.fatp.dao.offsite;

import com.fatp.po.offsite.BizimportApplyPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizimportApply
 * @author zhiya.chai
 * 2018-06-14 23:16:21
 */
@MyBatisDao
public interface BizimportApplyDao {
	public int insert(BizimportApplyPo o);
}
