package com.fatp.dao.offsite;

import java.util.List;

import com.fatp.domain.offsite.BizimportTradeDetail;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizimportTradeDetail
 * @author zhiya.chai
 * 2018-06-14 19:16:18
 */
@MyBatisDao
public interface BizimportTradeDetailDao {
	
	public int insertBatch(List<BizimportTradeDetail> o);
	
}
