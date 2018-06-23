package com.fatp.service.datasupprot.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.biz.BizplanRepayDao;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.exception.FatpException;
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
	/**
	 * 根据版本号更新
	 * @param o
	 * @return
	 */
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
	/**
	 * 根据条件查找还款计划
	 * @param map
	 * @return
	 */
	public List<BizplanRepay> findRepayPlanByCondition(Map<String, Object> map) {
		return bizplanRepayDao.select(map);
	}
	/**
	 * 根据Guid获取还款计划
	 * @param repayPlanGuid
	 * @return
	 */
	public BizplanRepay getPlanRepayByGuid(String repayPlanGuid){
		return bizplanRepayDao.getPlanRepayByGuid(repayPlanGuid);
	}
	/**
	 * 更新还款状态
	 * @param po
	 */
	public void updateRepayStatus(BizplanRepayPo po) {
		int n = bizplanRepayDao.updateRepayStatus(po);
		if(n <= 0) {
			throw new FatpException("更新还款状态失败");
		}
	}
}
