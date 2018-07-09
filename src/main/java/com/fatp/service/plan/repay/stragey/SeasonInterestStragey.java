package com.fatp.service.plan.repay.stragey;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.po.project.ListingInfoPo;

/**
 * 按季付息到期还本
 * @author zhiya.chai
 * 2016年5月10日 上午9:27:33
 */
@Service("seasonInterestStragey")
public class SeasonInterestStragey extends PlanGenStragey{

	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,
			List<ListingTrade> listingTradeList, BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList, int operatorId) {
		
	}
	
}
