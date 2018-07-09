package com.fatp.service.plan.repay.stragey;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.util.DateUtil;

/**
 * 按月付息到期还本
 * @author zhiya.chai
 * @date 2018年7月6日 下午12:55:24
 */
@Service("monthInterestStragey")
public class MonthInterestStragey extends PlanGenStragey{

	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,
			List<ListingTrade> listingTradeList, BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList, int operatorId) {
		
	}
	
	private List<PeriodResult> getPeriodResult(ListingInfoPo listingInfoPo,BizImportApply apply){
		List<PeriodResult> list = new ArrayList<>();
		//计息开始日期
		Date interestStartDate = apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate();
		//计息结束日期
		Date interestEndDate = getInterestEndDate(listingInfoPo, interestStartDate);
		int month[] = DateUtil.getDiffByMonth(interestEndDate, interestStartDate);
		int monthCount = month[0];
		int dayCount = month[1];
		int period = 1 ;
		
		if(monthCount > 0) {
			for(int i = 0 ; i < monthCount ; i ++) {
				PeriodResult pr = PeriodResult.build();
				pr.setInterestStartDate(interestStartDate);
				Date calResultDate = DateUtil.add(interestStartDate, Calendar.MONTH, 1);
				
				list.add(pr);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		Date date = DateUtil.convertDate("2017-02-28", "yyyy-MM-dd");
		System.out.println(DateUtil.formatDate(DateUtil.add(date, Calendar.MONTH, 1),"yyyy-MM-dd"));
	}
}
