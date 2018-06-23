package com.fatp.service.datasupprot.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.biz.BizplanRepayDao;
import com.fatp.po.biz.BizplanRepayPo;
/**
 * 还款计划
 * @author zhiya.chai
 * @date 2018年6月23日 下午6:00:14
 */
@Service
public class BizplanRepayDataSupportService {

	@Autowired
	private BizplanRepayDao bizplanRepayDao;
	
	/**
	 * 插入还款计划
	 * @param o
	 * @return
	 */
	public int insert(BizplanRepayPo o) {
		return bizplanRepayDao.insert(o);
	}
	
	public int updateByVersion(BizplanRepayPo o) {
		return bizplanRepayDao.updateByVersion(o);
	}
	/**
	 * 根据唯一键查找还款信息
	 * @param listingInfoId
	 * @param periodNumber
	 * @return
	 */
	public BizplanRepayPo getPlanRepayPoByUniqueKey(int listingInfoId,int periodNumber) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("listingInfoId", listingInfoId);
		map.put("periodNumber", periodNumber);
		return bizplanRepayDao.getPlanRepayPoByUniqueKey(map);
	}
}
