package com.fatp.service.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.PageData;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;

@Service
public class BizplanRepayService extends BaseService{

	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	/**
	 * 分页查找还款计划
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<BizplanRepay> pageFindRepayList(Map<String,Object> map,int pageNo, int pageSize) {
		return bizplanRepayDataSupportService.pageFindRepayList(map, pageNo, pageSize);
	}
	/**
	 * 获取还款计划
	 * @param map
	 * @return
	 */
	public List<BizplanRepay> findRepayPlan(Map<String,Object> map) {
		return bizplanRepayDataSupportService.findRepayPlanByCondition(map);
	}
	
	/**
	 * 根据Guid获取还款计划
	 * @param repayPlanGuid
	 * @return
	 */
	public BizplanRepay getPlanRepayByGuid(String repayPlanGuid){
		return bizplanRepayDataSupportService.getPlanRepayByGuid(repayPlanGuid);
	}
	/**
	 * 更新还款状态
	 * @param planRepay
	 * @param repayStatus
	 * @param operatorId
	 */
	public void updateRepayStatus(BizplanRepay planRepay,RepayStatus repayStatus,int operatorId) {
		BizplanRepayPo po = new BizplanRepayPo();
		po.setId(planRepay.getId());
		po.setVersionNo(planRepay.getVersionNo());
		po.setRepayStatus(repayStatus.status);
		po.setUpdateOperatorId(operatorId);
		bizplanRepayDataSupportService.updateRepayStatus(po);
	}
}
