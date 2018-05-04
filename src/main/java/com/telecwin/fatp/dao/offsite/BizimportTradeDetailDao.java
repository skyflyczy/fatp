package com.telecwin.fatp.dao.offsite;

import java.util.List;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;

@MyBatisDao
public interface BizimportTradeDetailDao {

	public void insertBatch(List<BizimportTradeDetail> list);
	
	public void deleteByApplyId(Integer applyId);
}
