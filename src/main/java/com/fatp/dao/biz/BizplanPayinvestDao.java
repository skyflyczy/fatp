package com.fatp.dao.biz;

import java.util.List;
import java.util.Map;

import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizplanPayinvest
 * @author zhiya.chai
 * 2018-06-23 39:14:44
 */
@MyBatisDao
public interface BizplanPayinvestDao {
	
	public void insertBatch(List<BizplanPayinvestPo> list);
	
	public List<BizplanPayinvest> select(Map<String, Object> map);
	
	public int updatePayinvestStatusByPlanRepayId(Map<String,Object> map);
	
	public int updatePayinvestDeleteStatusByApplyId(Map<String,Object> map);
	
}
