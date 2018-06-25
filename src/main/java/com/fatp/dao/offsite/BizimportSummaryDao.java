package com.fatp.dao.offsite;

import com.fatp.po.offsite.BizimportSummaryPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizimportSummary
 * @author zhiya.chai
 * 2018-06-14 21:16:45
 */
@MyBatisDao
public interface BizimportSummaryDao {
	public int insert(BizimportSummaryPo o);
	/**
	 * 根据申请Guid查找汇总信息
	 * @param map
	 * @return
	 */
	public BizimportSummaryPo getBizimportSummaryByApplyGuid(String applyGuid);
}
