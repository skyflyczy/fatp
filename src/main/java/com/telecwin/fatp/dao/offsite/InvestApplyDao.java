package com.telecwin.fatp.dao.offsite;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.offsite.InvestApply;

@MyBatisDao
public interface InvestApplyDao {
	/**
	 * 获取可登记List
	 * @param map
	 * @return
	 */
	List<InvestApply> getCanRegList(Map<String,Object> map);
}
