package com.fatp.dao.offsite;

import java.util.List;
import java.util.Map;

import com.fatp.domain.listing.InvestApply;
import com.huajin.baymax.db.annotation.MyBatisDao;

@MyBatisDao
public interface InvestApplyDao {
	/**
	 * 获取可申请的挂牌集合
	 * @param map
	 * @return
	 */
	List<InvestApply> getCanApplyListingList(Map<String, Object> map);
}
