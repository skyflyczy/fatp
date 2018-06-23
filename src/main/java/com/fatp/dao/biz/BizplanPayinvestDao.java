package com.fatp.dao.biz;

import java.util.List;

import com.fatp.po.biz.BizplanPayinvestPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizplanPayinvest
 * @author zhiya.chai
 * 2018-06-23 39:14:44
 */
@MyBatisDao
public interface BizplanPayinvestDao {
	
	public void insertBatch(List<BizplanPayinvestPo> list);
}
