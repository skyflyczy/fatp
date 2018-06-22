package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingInfo;

/**
 * 一次性还本付息
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:02:46
 */
@Service("oneTimeRepayStragey")
public class OneTimeRepayStragey implements RepayPlanGenStragey{

	@Override
	public int countPeriods(Date valueDate, Date expireDate) {
		return 1;
	}

	@Override
	public int getInterestType(ListingInfo listingInfo) {
		return 0;
	}

	@Override
	public BigDecimal[] getPrincipal(BigDecimal totalPrincipal,BigDecimal investProfit, int period, int periods) {
		return new BigDecimal[]{totalPrincipal,totalPrincipal};
	}

}
