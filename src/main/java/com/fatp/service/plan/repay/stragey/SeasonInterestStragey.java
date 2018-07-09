package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.result.PeriodResult;

/**
 * 按季付息到期还本
 * @author zhiya.chai
 * 2016年5月10日 上午9:27:33
 */
@Service("seasonInterestStragey")
public class SeasonInterestStragey extends PlanGenStragey{

	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,
			BizImportApply apply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal periodPrincipal(BigDecimal totalPrincipal, int periodNum,
			int totalPeriodNum) {
		return null;
	}
	
}
