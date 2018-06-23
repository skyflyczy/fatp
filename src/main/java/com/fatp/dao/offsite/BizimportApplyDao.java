package com.fatp.dao.offsite;

import com.fatp.domain.offsite.BizImportApply;
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
	/**
	 * 根据Id获取项目申请登记信息
	 * @param id
	 * @return
	 */
	BizImportApply getApplyById(int id);
	/**
	 * 更新登记状态
	 * @param po
	 * @return
	 */
	public int updateApplyStatus(BizimportApplyPo po);
}
