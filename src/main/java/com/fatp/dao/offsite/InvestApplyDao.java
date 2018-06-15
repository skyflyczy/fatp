package com.fatp.dao.offsite;

import java.util.List;
import java.util.Map;

import com.fatp.vo.InvestApplyVo;
import com.huajin.baymax.db.annotation.MyBatisDao;

@MyBatisDao
public interface InvestApplyDao {
	/**
	 * 获取可申请的挂牌集合
	 * @param map
	 * @return
	 */
	List<InvestApplyVo> getCanApplyListingList(Map<String, Object> map);
}
