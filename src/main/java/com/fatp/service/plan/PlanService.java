package com.fatp.service.plan;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
import com.fatp.service.plan.repay.PlanFactory;
import com.fatp.service.plan.repay.PlanGenStragey;

/**
 * 生成还款兑付计划入口
 * 
 * @author zhiya.chai
 * @date 2018年6月21日 上午11:39:15
 */
@Service
public class PlanService extends BaseService{
	
	@Autowired
	private ListingInfoDataSupportService listingInfoDataSupportService;
	
	@Autowired
	private InvestApplyDataSupportService investApplyDataSupportService;
	
	
	public void genPlan(int listingInfoId,int applyId) {
		//获取挂牌产品信息
		ListingInfoPo listingInfoPo = listingInfoDataSupportService.getLisingInfoPoById(listingInfoId);
		if(listingInfoPo == null) {
			logger.error("生成还款兑付计划，获取挂牌信息为空：listingInfoId：" + listingInfoId);
			return;
		}
		List<ListingTrade> listingTradeList =  listingInfoDataSupportService.getTradeByListingInfoId(listingInfoId);
		if(CollectionUtils.isEmpty(listingTradeList)){
			logger.error("生成还款兑付计划，获取挂牌交易信息为空：listingInfoId：" + listingInfoId);
			return;
		}
		//获取本次上传的交易明细和汇总信息
		BizImportApply apply = investApplyDataSupportService.getApplyById(applyId);
		if(apply == null) {
			logger.error("生成还款兑付计划，获取挂牌产品的登记信息为空：applyId：" + applyId);
			return;
		}
		List<BizimportTradeDetail> tradeDetailList = investApplyDataSupportService.findTradeDetailByApplyId(applyId);
		if(CollectionUtils.isEmpty(tradeDetailList)) {
			logger.error("生成还款兑付计划，获取客户交易信息为空：applyId：" + applyId);
			return;
		}
		PlanGenStragey planGenStagey = PlanFactory.getInstance().chooseStrategy(listingInfoPo.getPayInterestType().intValue());
		if(planGenStagey == null) {
			logger.error("获取计划生成策略为空，不能生成还款计划，listingInfoId：" + listingInfoPo.getId());
			return;
		}
	}

}
