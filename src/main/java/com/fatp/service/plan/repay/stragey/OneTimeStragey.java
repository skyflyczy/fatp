package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.datasupprot.sys.SysWorkdateDataSupportService;
import com.fatp.service.plan.repay.result.PeriodResult;

/**
 * 一次性还本付息
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:02:46
 */
@Service("oneTimeStragey")
public class OneTimeStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;
	
	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,
			BizImportApply apply) {
		PeriodResult result = PeriodResult.build();
		result.setPeriod(1);
		result.setInterestStartDate(apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate());
		result.setInterestEndDate(getInterestEndDate(listingInfoPo, result.getInterestStartDate()));
		//计算还款兑付日，为到期日所在的工作日 TODO 目前规则
		Date repayDate = sysWorkdateDataSupportService.getBeforeWorkDate(result.getInterestEndDate(), 1);
		result.setRepayDate(repayDate);
		List<PeriodResult> periodResultList = new ArrayList<PeriodResult>();
		periodResultList.add(result);
		return periodResultList;
	}

	@Override
	public BigDecimal[] periodPrincipal(BigDecimal totalPrincipal, int periodNum,
			int totalPeriodNum,BigDecimal investProfit) {
		return new BigDecimal[]{totalPrincipal,totalPrincipal};
	}
	
}
