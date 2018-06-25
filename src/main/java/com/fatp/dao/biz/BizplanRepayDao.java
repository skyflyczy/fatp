package com.fatp.dao.biz;

import java.util.List;
import java.util.Map;

import com.fatp.po.biz.BizplanRepayPo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * BizplanRepay
 * @author zhiya.chai
 * 2018-06-23 55:17:20
 */
@MyBatisDao
public interface BizplanRepayDao {
	
	public int insert(BizplanRepayPo o);
	
	public int updateByVersion(BizplanRepayPo o);
	
	public List<BizplanRepayPo> select(Map<String, Object> map);
	
	public BizplanRepayPo getPlanRepayPoByUniqueKey(Map<String,Object> map);
}