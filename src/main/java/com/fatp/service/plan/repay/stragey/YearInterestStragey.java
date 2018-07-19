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
 * 按年付息到期还本
 * @author zhiya.chai
 * 2016年5月10日 上午9:52:02
 */
@Service("yearInterestStragey")
public class YearInterestStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;

	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,BizImportApply apply) {
		List<PeriodResult> list = new ArrayList<>();
		//计息开始日期
		Date interestStartDate = apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate();
		//计息结束日期
		Date interestEndDate = getInterestEndDate(listingInfoPo, interestStartDate);
		int years[] = DateUtil.getDiffByMonth(interestEndDate, interestStartDate);
		int yearCount = years[0];
		int dayCount = years[1];
		Date nextStartDate = interestStartDate;
		if(yearCount > 0) {
			for(int i = 1 ; i <= yearCount/12 ; i ++) {
				PeriodResult pr = PeriodResult.build();
				pr.setPeriod(i);//期数
				pr.setInterestStartDate(nextStartDate);
				Date calResultDate = DateUtil.add(interestStartDate, Calendar.MONTH, i * 12);
				pr.setInterestEndDate(calResultDate);
				//获取还款日期
				Date repayDate = sysWorkdateDataSupportService.getBeforeWorkDate(pr.getInterestEndDate(), 1);
				pr.setRepayDate(repayDate);
				list.add(pr);
				nextStartDate = calResultDate;
			}
		}
		if( yearCount % 12 != 0 || dayCount > 0) {
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
	public BigDecimal periodPrincipal(BigDecimal totalPrincipal, int periodNum,int totalPeriodNum) {
		if(periodNum == totalPeriodNum) {
			return totalPrincipal;
		}
		return BigDecimal.ZERO;
	}
	
	public static void main(String[] args) {
		Date minDay = DateUtil.convertDate("2016-02-29", "yyyy-MM-dd");
		Date maxDay = DateUtil.convertDate("2019-02-28", "yyyy-MM-dd");
		Date nextStartDate = minDay;
		DateUtil.getDiffByMonth(maxDay, minDay);
		int years[] = DateUtil.getDiffByYear(maxDay, minDay);
		int y = years[0];
		for(int i = 1 ; i <= y ; i ++) {
			Date calResultDate = DateUtil.add(minDay, Calendar.MONTH, i*12);
			System.out.println(DateUtil.formatDate(nextStartDate, "yyyy-MM-dd") + "-------" + DateUtil.formatDate(calResultDate, "yyyy-MM-dd"));
			nextStartDate = calResultDate;
		}
	}
	
}
