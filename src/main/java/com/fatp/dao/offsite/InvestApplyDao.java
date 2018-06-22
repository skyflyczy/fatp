package com.fatp.dao.offsite;

import java.util.List;
import java.util.Map;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.InvestApply;
import com.huajin.baymax.db.annotation.MyBatisDao;

@MyBatisDao
public interface InvestApplyDao {
	/**
	 * 获取可申请的挂牌集合
	 * @param map
	 * @return
	 */
	List<InvestApply> getCanApplyListingList(Map<String, Object> map);
	/**
	 * 获取挂牌产品登记成功的列表
	 * @param listingInfoId
	 * @return
	 */
	List<BizImportApply> getListingApplyList(int listingInfoId);
	
}
