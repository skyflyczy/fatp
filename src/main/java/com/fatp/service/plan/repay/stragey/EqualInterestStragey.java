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
 * 等额本息
 * 
 * @author zhiya.chai
 * @date 2018年7月27日 下午12:50:21
 */
@Service("equalInterestStragey")
public class EqualInterestStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;

	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,BizImportApply apply) {
		List<PeriodResult> list = new ArrayList<>();
		//计息开始日期
		Date interestStartDate = apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate();
		//计息结束日期
		Date interestEndDate = getInterestEndDate(listingInfoPo, interestStartDate);
		int month[] = DateUtil.getDiffByMonth(interestEndDate, interestStartDate);
		int monthCount = month[0];
		int dayCount = month[1];
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
		// 月利率=年利率/12
		BigDecimal monthInterestProfit = monthInterestProfit(investProfit);
		// n-1期还款本金之和
		BigDecimal residualPrincipa = calcResidualPrincipa(totalPrincipal, monthInterestProfit, periodNum, totalPeriodNum);
		// 每月偿还的本金=贷款本金*月利率*(1+月利率)^(当前期次-1)/((1+月利率)^总期次-1)
		BigDecimal payPrincipal = calcPrincipal(totalPrincipal, monthInterestProfit, periodNum, totalPeriodNum);
		// 每月计息本金(剩余本金) = 贷款本金-已还本金
		BigDecimal interestPrincipal = totalPrincipal.subtract(residualPrincipa);
		// 修正尾期还款本金，尾期还款本金=剩余本金
		if (periodNum == totalPeriodNum) {
			payPrincipal = interestPrincipal;
		}
		return new BigDecimal[] { payPrincipal, interestPrincipal };
	}
	
	/**
	 * 计算月利率
	 * 
	 * @param investProfit
	 * @return
	 */
	private BigDecimal monthInterestProfit(BigDecimal investProfit) {
		return investProfit.divide(new BigDecimal(12), 20, BigDecimal.ROUND_DOWN);
	}
	
	/**
	 * 计算等额本息的n-1期还款本金之和
	 * 
	 * @param totalPrincipal
	 * @param monthInterestProfit
	 * @param periodNum
	 * @param totalPeriodNum
	 * @return
	 */
	private BigDecimal calcResidualPrincipa(BigDecimal totalPrincipal, BigDecimal monthInterestProfit, int periodNum,
			int totalPeriodNum) {
		// 已还本金缺省值为0
		BigDecimal residualPrincipa = BigDecimal.ZERO;

		// 已还本金=每次还款本金之和
		for (int i = 1; i < periodNum; i++) {
			residualPrincipa = residualPrincipa.add(calcPrincipal(totalPrincipal, monthInterestProfit, i, totalPeriodNum));
		}

		return residualPrincipa;
	}
	/**
	 * 计算等额本息的每期还款本金
	 * 
	 * @param totalPrincipal
	 * @param monthInterestProfit
	 * @param periodNum
	 * @param totalPeriodNum
	 * @return
	 */
	private BigDecimal calcPrincipal(BigDecimal totalPrincipal, BigDecimal monthInterestProfit, int periodNum,
			int totalPeriodNum) {
		if (monthInterestProfit.compareTo(BigDecimal.ZERO) > 0) {
			// 每月偿还的本金=贷款本金*月利率*(1+月利率)^(当前期次-1)/((1+月利率)^总期次-1)
			return totalPrincipal.multiply(monthInterestProfit)
					.multiply(monthInterestProfit.add(BigDecimal.ONE).pow(periodNum - 1))
					.divide((monthInterestProfit.add(BigDecimal.ONE).pow(totalPeriodNum).subtract(BigDecimal.ONE)), 2,
							BigDecimal.ROUND_DOWN);
		} else {
			// 本金/期数
			return totalPrincipal.divide(new BigDecimal(totalPeriodNum), 2, BigDecimal.ROUND_DOWN);
		}
	}

}
