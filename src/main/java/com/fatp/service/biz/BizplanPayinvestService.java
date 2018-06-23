package com.fatp.service.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.enums.biz.PayinvestStatus;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.exception.FatpException;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;

@Service
public class BizplanPayinvestService extends BaseService{

	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	/**
	 * 查找兑付计划
	 * @param map
	 * @return
	 */
	public List<BizplanPayinvest> findPlanPayinvest(Map<String, Object> map) {
		List<BizplanPayinvest> list = bizplanPayinvestDataSupportService.findPlanPayinvest(map);
		return list;
	}
	/**
	 * 得到兑付状态
	 * @param map
	 * @return 
	 * @return
	 */
	public PayinvestStatus getPayinvestStatus(Map<String,Object> map) {
		List<BizplanPayinvest> list = bizplanPayinvestDataSupportService.findPlanPayinvest(map);
		for(BizplanPayinvest payinvest : list) {
			if(payinvest.getPayinvestStatus() == PayinvestStatus.未兑付.status) {
				return PayinvestStatus.未兑付;
			}
		}
		return PayinvestStatus.兑付完成;
	}
	/**
	 * 兑付完成
	 * @param repayPlanGuid
	 * @param operatorId
	 */
	public void payinvestCompleted(String repayPlanGuid,int operatorId) {
		BizplanRepay planRepay = bizplanRepayDataSupportService.getPlanRepayByGuid(repayPlanGuid);
		if(planRepay == null) {
			throw new FatpException("没有还款计划");
		}
		if(planRepay.getRepayStatus() != RepayStatus.还款完成.status) {
			throw new FatpException("请先还款，后兑付");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("payinvestStatus", PayinvestStatus.兑付完成.status);
		map.put("updateOperatorId", operatorId);
		map.put("repayPlanId", planRepay.getId());
		bizplanPayinvestDataSupportService.updatePayinvestStatusByPlanRepayId(map);
	}
}
