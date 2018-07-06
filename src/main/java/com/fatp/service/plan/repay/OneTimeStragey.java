package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.YesNo;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.exception.FatpException;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;
import com.fatp.service.datasupprot.sys.SysWorkdateDataSupportService;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.param.InvestProfitParam;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.util.DateUtil;
import com.fatp.util.UUIDUtil;

/**
 * 一次性还本付息
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:02:46
 */
@Service("oneTimeStragey")
public class OneTimeStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	/**
	 * 生成还款兑付计划
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList, 
			BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList,
			int operatorId) {
		//获取分期结果  一次性还本付息，只有一期
		PeriodResult periodResult = getPeriodResult(listingInfoPo, apply);
		List<BizplanPayinvestPo> payinvestList = tradeDetailList.stream().map(tradeDetail ->{
			//组装计算利息参数,生成利息
			List<CalInterestParam> calInterestParamList = genCalInterestParamList(listingInfoPo, periodResult.getInterestStartDate(), periodResult.getInterestEndDate());
			//生成利息参数集合
			List<InvestProfitParam> investProfitParamList = super.genInvestProfitParamList(listingTradeList,tradeDetail.getAddInvestProfit());
			BigDecimal interest = BigDecimal.ZERO;
			for(CalInterestParam param : calInterestParamList) {
				param.setInvestProfitParamList(investProfitParamList);
				param.setPrincipal(tradeDetail.getTradeMoney());
				//计算利息
				interest = interest.add(super.calProfit(param));
			}
			if(tradeDetail.getAddInvestProfitDays() != null) {
				//加息
				InterestRate interestRate = InterestRate.getInterestRateByValue(listingInfoPo.getInterestRate().intValue());
				InterestBase interestBase = InterestBase.getInterestBaseByValue(listingInfoPo.getInterestBase().intValue());
				CalInterestParam param = super.genCalInterestParam(periodResult.getInterestEndDate(), periodResult.getInterestEndDate(), interestRate, interestBase, tradeDetail.getAddInvestProfitDays().intValue());
				param.setInvestProfitParamList(investProfitParamList);
				param.setPrincipal(tradeDetail.getTradeMoney());
				//计算加息
				interest = interest.add(super.calProfit(param));
			}
			BizplanPayinvestPo payinvest = super.genBasePayinvest(operatorId, tradeDetail);
			payinvest.setPeriodNumber(periodResult.getPeriod());
			payinvest.setPrincipal(tradeDetail.getTradeMoney());
			payinvest.setInterest(interest);
			payinvest.setBizImportApplyId(apply.getId());
			payinvest.setIsDelete(YesNo.否.value);
			return payinvest;
		}).collect(Collectors.toList());
		//计算还款兑付日，为到期日所在的工作日 TODO 目前规则
		Date repayDate = sysWorkdateDataSupportService.getBelongWorkDate(periodResult.getInterestEndDate());
		//生成还款对象
		BizplanRepayPo repay = this.genBizplanRepay(operatorId, listingInfoPo, periodResult, repayDate,apply.getId());
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
	}
	/**
	 * 生成还款对象
	 * @param operatorId
	 * @param listingInfoPo
	 * @param periodResult
	 * @param repayDate
	 * @return
	 */
	private BizplanRepayPo genBizplanRepay(int operatorId,ListingInfoPo listingInfoPo
			,PeriodResult periodResult,Date repayDate,int applyId) {
		BizplanRepayPo repay = new BizplanRepayPo();
		repay.setCreateOperatorId(operatorId);
		repay.setListingInfoId(listingInfoPo.getId());
		repay.setRepayPlanGuid(UUIDUtil.getUUID());
		repay.setPeriodNumber(periodResult.getPeriod());
		repay.setPrincipal(BigDecimal.ZERO);
		repay.setInterest(BigDecimal.ZERO);
		//repay.setInterestDay(interestDay);
		repay.setInterestEndDate(periodResult.getInterestEndDate());
		repay.setInterestStartDate(periodResult.getInterestStartDate());
		repay.setPlanRepayDate(repayDate);
		repay.setRepayStatus(RepayStatus.未还款.status);
		repay.setUpdateOperatorId(operatorId);
		repay.setBizImportApplyId(applyId);
		repay.setIsDelete(YesNo.否.value);
		return repay;
	}
	/**
	 * 生成计算利息参数集合
	 * @param listingInfoPo
	 * @param interestStartDate 计息开始日期
	 * @param interestEndDate 计息结束日期
	 * @return
	 */
	private List<CalInterestParam> genCalInterestParamList(ListingInfoPo listingInfoPo,Date interestStartDate,Date interestEndDate) {
		List<CalInterestParam> list = new ArrayList<>(); 
		InterestRate interestRate = InterestRate.getInterestRateByValue(listingInfoPo.getInterestRate().intValue());
		InterestBase interestBase = InterestBase.getInterestBaseByValue(listingInfoPo.getInterestBase().intValue());
		//计算利息结束日期，根据到期日是否计息判断是否加一天
		Date calInterestEndDate = listingInfoPo.getExpireDateInterest().intValue() == YesNo.是.value ? DateUtil.add(interestEndDate, Calendar.DATE, 1) : interestEndDate;
		if(interestRate == InterestRate.按日计息) {
			int dayCount = DateUtil.getDiffByDate(calInterestEndDate, interestStartDate);
			list.add(genCalInterestParam(interestStartDate, interestEndDate, interestRate, interestBase, dayCount));
		} else if(interestRate == InterestRate.按月计息) {
			int [] months = DateUtil.getDiffByMonth(calInterestEndDate, interestStartDate);
			int monthCount = months[0];//月份数量
			int dayCount = months[1];//天数
			Date splitDate = DateUtil.add(interestStartDate, Calendar.MONTH, monthCount);//整月拆分日期
			if(monthCount > 0) {
				list.add(genCalInterestParam(interestStartDate, splitDate, InterestRate.按月计息, interestBase, monthCount));
			}
			if(dayCount > 0) {
				list.add(genCalInterestParam(splitDate, interestEndDate, InterestRate.按日计息, interestBase, dayCount));
			}
		} else if(interestRate == InterestRate.按年计息) {
			int []years = DateUtil.getDiffByYear(calInterestEndDate, interestStartDate);
			int yearCount = years[0];
			int dayCount = years[1];
			Date splitDate = DateUtil.add(interestStartDate, Calendar.YEAR, yearCount);//整年拆分日期
			if(yearCount > 0) {
				list.add(genCalInterestParam(interestStartDate, splitDate, InterestRate.按年计息, interestBase, yearCount));
			}
			if(dayCount > 0) {
				list.add(genCalInterestParam(splitDate, interestEndDate, InterestRate.按日计息, interestBase, dayCount));
			}
		} else if(interestRate == InterestRate.按季计息) {
			int [] seasons = DateUtil.getDiffBySeason(calInterestEndDate, interestStartDate);
			int seasonCount = seasons[0];//季节数量
			int dayCount = seasons[1];//天数
			Date splitDate = DateUtil.add(interestStartDate, Calendar.MONTH, seasonCount*3);//整季拆分日期
			if(seasonCount > 0) {
				list.add(genCalInterestParam(interestStartDate, splitDate, InterestRate.按季计息, interestBase, seasonCount));
			}
			if(dayCount > 0) {
				list.add(genCalInterestParam(splitDate, interestEndDate, InterestRate.按日计息, interestBase, dayCount));
			}
		} else if(listingInfoPo.getInterestRate().intValue() == InterestRate.按半年计息.value) {
			int [] hs = DateUtil.getDiffByHalfAYear(calInterestEndDate,interestStartDate);
			int hsCount = hs[0];//半年数量
			int dayCount = hs[1];//天数
			Date splitDate = DateUtil.add(interestStartDate, Calendar.MONTH, hsCount*6);//整半年拆分日期
			if(hsCount > 0) {
				list.add(genCalInterestParam(interestStartDate, splitDate, InterestRate.按半年计息, interestBase, hsCount));
			}
			if(dayCount > 0) {
				list.add(genCalInterestParam(splitDate, interestEndDate, InterestRate.按日计息, interestBase, dayCount));
			}
		}
		return list;
	}
	/**
	 * 获取分期信息
	 * @param listingInfoPo
	 * @param apply
	 * @return
	 */
	private PeriodResult getPeriodResult(ListingInfoPo listingInfoPo,BizImportApply apply){
		PeriodResult result = PeriodResult.build();
		result.setPeriod(1);
		result.setInterestStartDate(apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate());
		result.setInterestType(listingInfoPo.getPayInterestType());
		result.setInterestEndDate(getInterestEndDate(listingInfoPo, result.getInterestStartDate()));
		return result;
	}
	
}
