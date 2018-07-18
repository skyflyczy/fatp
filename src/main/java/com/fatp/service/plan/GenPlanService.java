package com.fatp.service.plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.exception.FatpException;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;
import com.fatp.service.plan.repay.PlanFactory;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.param.InvestProfitParam;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.service.plan.repay.stragey.PlanGenStragey;

@Service
public class GenPlanService extends BaseService{
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	/**
	 * 生成还款兑付计划
	 * @param listingInfoPo
	 * @param listingTradeList
	 * @param apply
	 * @param tradeDetailList
	 * @param operatorId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList,BizImportApply apply,List<BizimportTradeDetail> tradeDetailList,int operatorId) {
		PlanGenStragey planGenStagey = PlanFactory.getInstance().chooseStrategy(listingInfoPo.getPayInterestType().intValue());
		if(planGenStagey == null) {
			logger.error("获取计划生成策略为空，不能生成还款计划，listingInfoId：" + listingInfoPo.getId());
			throw new FatpException("付息方式出错");
		}
		List<PeriodResult> periodResultList = planGenStagey.periodResultList(listingInfoPo, apply);
		int totalPeriodNum = periodResultList.size();//总期数
		periodResultList.stream().forEach(periodResult -> {
			//生成兑付列表
			List<BizplanPayinvestPo> payinvestList = genPlanPayInvestList(planGenStagey, tradeDetailList, listingInfoPo, periodResult, listingTradeList, operatorId, apply.getId(),totalPeriodNum);
			//生成还款计划
			BizplanRepayPo repay = planGenStagey.genBizplanRepay(operatorId, listingInfoPo, periodResult, apply.getId());
			BigDecimal principal = repay.getPrincipal(); //应还本金
			BigDecimal interest = repay.getInterest(); //应还利息
			for(BizplanPayinvestPo payInvest : payinvestList) {
				principal = principal.add(payInvest.getPrincipal());
				interest = interest.add(payInvest.getInterest());
			}
			repay.setPrincipal(principal);
			repay.setInterest(interest);
			repay.setInterestPrincipal(principal);
			//插入数据库
			int id = bizplanRepayDataSupportService.insert(repay);
			if(id <= 0) {
				throw new FatpException("更新还款计划失败。");
			}
			payinvestList.stream().forEach(payInvest ->{
				payInvest.setRepayPlanId(repay.getId());
			});
			bizplanPayinvestDataSupportService.insertBatch(payinvestList);
		});
	}
	/**
	 * 生成兑付计划
	 * @param planGenStagey
	 * @param tradeDetailList
	 * @param listingInfoPo
	 * @param periodResult
	 * @param listingTradeList
	 * @param operatorId
	 * @param applyId
	 * @return
	 */
	private List<BizplanPayinvestPo> genPlanPayInvestList(PlanGenStragey planGenStagey,List<BizimportTradeDetail> tradeDetailList,
			ListingInfoPo listingInfoPo,PeriodResult periodResult,List<ListingTrade> listingTradeList,int operatorId,int applyId,int totalPeriodNum) {
		List<BizplanPayinvestPo> payinvestList = tradeDetailList.stream().map(tradeDetail ->{
			//组装计算利息参数,生成利息
			List<CalInterestParam> calInterestParamList = planGenStagey.genCalInterestParamList(listingInfoPo, periodResult.getInterestStartDate(), periodResult.getInterestEndDate(),periodResult.getPeriod() == totalPeriodNum);
			//生成利息参数集合
			List<InvestProfitParam> investProfitParamList = planGenStagey.genInvestProfitParamList(listingTradeList,tradeDetail.getAddInvestProfit());
			//组装计算利息参数,生成利息
			BizplanPayinvestPo payinvest = planGenStagey.genBasePayinvest(operatorId, tradeDetail);
			BigDecimal interest = BigDecimal.ZERO;
			
			for(CalInterestParam param : calInterestParamList) {
				param.setInvestProfitParamList(investProfitParamList);
				param.setPrincipal(tradeDetail.getTradeMoney());
				//计算利息
				interest = interest.add(planGenStagey.calProfit(param));
			}
			if(tradeDetail.getAddInvestProfitDays() != null) {
				//加息
				InterestRate interestRate = InterestRate.getInterestRateByValue(listingInfoPo.getInterestRate().intValue());
				InterestBase interestBase = InterestBase.getInterestBaseByValue(listingInfoPo.getInterestBase().intValue());
				CalInterestParam param = planGenStagey.genCalInterestParam(periodResult.getInterestEndDate(), periodResult.getInterestEndDate(), interestRate, interestBase, tradeDetail.getAddInvestProfitDays().intValue());
				param.setInvestProfitParamList(investProfitParamList);
				param.setPrincipal(tradeDetail.getTradeMoney());
				//计算加息
				interest = interest.add(planGenStagey.calProfit(param));
			}
			payinvest.setPeriodNumber(periodResult.getPeriod());
			payinvest.setPrincipal(planGenStagey.periodPrincipal(tradeDetail.getTradeMoney(),periodResult.getPeriod(),totalPeriodNum));
			payinvest.setInterest(interest);
			payinvest.setBizImportApplyId(applyId);
			return payinvest;
		}).collect(Collectors.toList());
		return payinvestList;
	}
}
