package com.fatp.dao.offsite;

import java.util.List;
import java.util.Map;

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
	
	public List<BizimportTradeDetail> pageFindTradeDetail(Map<String,Object> map);
	
	public List<BizimportTradeDetail> findTradeDetailByApplyId(int bizImportApplyId);
}
