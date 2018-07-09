package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.result.PeriodResult;

/**
 * 按半年息到期还本
 * @author zhiya.chai
 * @date 2018年7月6日 下午12:58:26
 */
@Service("halfAYearInterestStragey")
public class HalfAYearInterestStragey extends PlanGenStragey{

	@Override
	public List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,
			BizImportApply apply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal periodPrincipal(BigDecimal totalPrincipal, int periodNum,
			int totalPeriodNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
