package com.fatp.service.plan.repay;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.YesNo;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.huajin.baymax.util.DateUtils;

/**
 * 一次性还本付息
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:02:46
 */
@Service("oneTimeStragey")
public class OneTimeStragey extends PlanGenStragey{
	
	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList, 
			BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList,
			int operatorId) {
		//获取分期结果
		PeriodResult periodResult = getPeriodResult(listingInfoPo, apply);
		//生成还款
		BizplanRepayPo repay = super.genBaseRepay(operatorId, listingInfoPo);
		//得到计息天数
		int interestDay = getInterestDays(listingInfoPo, periodResult);
//		Map<Integer>
//		tradeDetailList.stream().forEach(tradeDetail -> {
//			
//		});
		
	}
	/**
	 * 获取计息天数
	 * @param listingInfoPo
	 * @param periodResult
	 * @return
	 */
	private int getInterestDays(ListingInfoPo listingInfoPo,PeriodResult periodResult) {
		if(listingInfoPo.getExpireDateInterest().intValue() == YesNo.是.value) {
			return (int)DateUtils.getDistanceOfTwoDate(periodResult.getInterestStartDate(), periodResult.getInterestEndDate()) + 1;
		} else {
			return (int)DateUtils.getDistanceOfTwoDate(periodResult.getInterestStartDate(), periodResult.getInterestEndDate());
		}
	}
	/**
	 * 获取分期信息
	 * @param listingInfoPo
	 * @param apply
	 * @return
	 */
	private PeriodResult getPeriodResult(ListingInfoPo listingInfoPo,BizImportApply apply){
		PeriodResult result = PeriodResult.build();
		result.setPeriod(1);
		result.setInterestStartDate(apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate());
		result.setInterestEndDate(listingInfoPo.getExpireDate());
		result.setInterestType(listingInfoPo.getPayInterestType());
		return result;
	}


}
