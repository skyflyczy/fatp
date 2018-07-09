package com.fatp.service.plan.repay.stragey;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.po.project.ListingInfoPo;

/**
 * 按半年息到期还本
 * @author zhiya.chai
 * @date 2018年7月6日 下午12:58:26
 */
@Service("halfAYearInterestStragey")
public class HalfAYearInterestStragey extends PlanGenStragey{

	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,
			List<ListingTrade> listingTradeList, BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList, int operatorId) {
		
	}

}
