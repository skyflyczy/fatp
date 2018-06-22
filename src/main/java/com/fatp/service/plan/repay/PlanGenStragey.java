package com.fatp.service.plan.repay;

import java.util.Date;
import java.util.List;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.util.UUIDUtil;


/**
 * 还款计划生成策略
 * 
 * @author zhiya.chai
 * @date 2018年6月21日 下午3:46:23
 */
public abstract class PlanGenStragey {

	/**
	 * 生成还款兑付计划
	 * @param listingInfoPo
	 * @param listingTradeList
	 * @param apply
	 * @param tradeDetailList
	 */
	public abstract void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList,BizImportApply apply,List<BizimportTradeDetail> tradeDetailList,int operatorId);
	
	protected BizplanRepayPo genBaseRepay(int operatorId,ListingInfoPo listingInfoPo) {
		BizplanRepayPo repay = new BizplanRepayPo();
		repay.setCreateOperatorId(operatorId);
		repay.setCreateTime(new Date());
		repay.setListingInfoId(listingInfoPo.getId());
		repay.setUpdateOperatorId(operatorId);
		repay.setUpdateTime(new Date());
		repay.setRepayPlanGuid(UUIDUtil.getUUID());
		//TODO 暂时为空 repay.setLoanUserId(project.getLoanUserId());
		repay.setVersionNo(1);
		return repay;
	}
	
}
