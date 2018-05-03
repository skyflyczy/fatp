package com.telecwin.fatp.dao.offsite;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.offsite.BizimportSummaryPo;

@MyBatisDao
public interface BizimportSummaryDao {

	/**
	 * 根据申请Guid查找导入信息
	 * @param applyGuid
	 * @return
	 */
	public BizimportSummaryPo getSummaryByApplyGuid(String applyGuid);
	
	
}
