package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.datasupprot.sys.SysWorkdateDataSupportService;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.util.DateUtil;

/**
 * 按月付息到期还本
 * @author zhiya.chai
 * @date 2018年7月6日 下午12:55:24
 */
@Service("monthInterestStragey")
public class MonthInterestStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;

	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,BizImportApply apply){
		List<PeriodResult> list = new ArrayList<>();
		//计息开始日期
		Date interestStartDate = apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate();
		//计息结束日期
		Date interestEndDate = getInterestEndDate(listingInfoPo, interestStartDate);
		int months[] = DateUtil.getDiffByMonth(interestEndDate, interestStartDate);
		int monthCount = months[0];
		int dayCount = months[1];
		Date nextStartDate = interestStartDate;
		if(monthCount > 0) {
			//按月计息
			for(int i = 1 ; i <= monthCount ; i ++) {
				PeriodResult pr = PeriodResult.build();
				pr.setPeriod(i);//期数
				pr.setInterestStartDate(nextStartDate);
				Date calResultDate = DateUtil.add(interestStartDate, Calendar.MONTH, i);
				pr.setInterestEndDate(calResultDate);
				//获取还款日期
				Date repayDate = sysWorkdateDataSupportService.getBeforeWorkDate(pr.getInterestEndDate(), 1);
				pr.setRepayDate(repayDate);
				list.add(pr);
				nextStartDate = calResultDate;
			}
		}
		if(dayCount > 0) {
			//按日计息
			int period = list.size() + 1; //期数
			PeriodResult pr = PeriodResult.build();
			pr.setPeriod(period);
			pr.setInterestStartDate(nextStartDate);
			pr.setInterestEndDate(interestEndDate);
			Date repayDate = sysWorkdateDataSupportService.getBeforeWorkDate(pr.getInterestEndDate(), 1);
			pr.setRepayDate(repayDate);
			list.add(pr);
		}
		return list;
	}

	@Override
	public BigDecimal[] periodPrincipal(BigDecimal totalPrincipal, int periodNum,int totalPeriodNum,BigDecimal investProfit) {
		BigDecimal payPrincipal = BigDecimal.ZERO;
		if(periodNum == totalPeriodNum) {
			payPrincipal = totalPrincipal;
		}
		return new BigDecimal[]{payPrincipal,totalPrincipal};
	}

}
