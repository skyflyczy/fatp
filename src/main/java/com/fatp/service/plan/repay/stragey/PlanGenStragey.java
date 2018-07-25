package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.YesNo;
import com.fatp.enums.biz.PayinvestStatus;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.CalInterestUtil;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.param.InvestProfitParam;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.util.DateUtil;
import com.fatp.util.UUIDUtil;


/**
 * 还款计划生成策略
 * 
 * @author zhiya.chai
 * @date 2018年6月21日 下午3:46:23
 */
public abstract class PlanGenStragey {
	
	/**
	 * 生成基础的兑付信息
	 * @param operatorId
	 * @param tradeDetail
	 * @return
	 */
	public BizplanPayinvestPo genBasePayinvest(int operatorId,BizimportTradeDetail tradeDetail) {
		BizplanPayinvestPo payinvest = new BizplanPayinvestPo();
		payinvest.setCardAccount(tradeDetail.getCardAccount());
		payinvest.setCreateOperatorId(operatorId);
		payinvest.setInvestUserId(tradeDetail.getUserId());
		payinvest.setListingInfoId(tradeDetail.getListingInfoId());
		payinvest.setPayinvestPlanGuid(UUIDUtil.getUUID());
		payinvest.setSubBankName(tradeDetail.getSubBankName());
		payinvest.setUpdateOperatorId(operatorId);
		payinvest.setPayinvestStatus(PayinvestStatus.未兑付.status);
		payinvest.setIsDelete(YesNo.否.value);
		return payinvest;
	}
	/**
	 * 生成还款对象
	 * @param operatorId
	 * @param listingInfoPo
	 * @param periodResult
	 * @param repayDate
	 * @return
	 */
	public BizplanRepayPo genBizplanRepay(int operatorId,ListingInfoPo listingInfoPo,PeriodResult periodResult,int applyId,int totalPeriodNum) {
		BizplanRepayPo repay = new BizplanRepayPo();
		repay.setCreateOperatorId(operatorId);
		repay.setListingInfoId(listingInfoPo.getId());
		repay.setRepayPlanGuid(UUIDUtil.getUUID());
		repay.setPeriodNumber(periodResult.getPeriod());
		repay.setPrincipal(BigDecimal.ZERO);
		repay.setInterest(BigDecimal.ZERO);
		//repay.setInterestDay(interestDay);
		//计息截止日：是指这一日期也算利息
		if(periodResult.getPeriod() == totalPeriodNum 
				&& listingInfoPo.getExpireDateInterest().intValue() == YesNo.是.value) {
			//最后一期 并且到期日计息
			repay.setInterestEndDate(periodResult.getInterestEndDate());
		} else {
			repay.setInterestEndDate(DateUtil.add(periodResult.getInterestEndDate(),Calendar.DATE, -1));
		}
		repay.setInterestStartDate(periodResult.getInterestStartDate());
		repay.setPlanRepayDate(periodResult.getRepayDate());
		repay.setRepayStatus(RepayStatus.未还款.status);
		repay.setUpdateOperatorId(operatorId);
		repay.setBizImportApplyId(applyId);
		repay.setIsDelete(YesNo.否.value);
		return repay;
	}
	/**
	 * 计算收益
	 * @param param
	 */
	public BigDecimal calProfit(CalInterestParam param){
		return calProfit(param, 2, BigDecimal.ROUND_DOWN);
	}
	/**
	 * 计算收益
	 * @param param
	 * @param zeroplace
	 * @param paramInt
	 * @return
	 */
	public BigDecimal calProfit(CalInterestParam param, int zeroplace, int paramInt){
		BigDecimal interest = BigDecimal.ZERO;//利息
		InterestRate interestRate = param.getInterestRate();//计息方式
		switch (interestRate) {
		case 按日计息:
			interest = CalInterestUtil.calDaysProfit(param, zeroplace, paramInt);
			break;
		case 按月计息:
			interest = CalInterestUtil.calMonthProfit(param, zeroplace, paramInt);
			break;
		case 按年计息:
			interest = CalInterestUtil.calYearProfit(param, zeroplace, paramInt);
			break;
		case 按季计息:
			interest = CalInterestUtil.calSeasonProfit(param, zeroplace, paramInt);
			break;
		case 按半年计息:
			interest = CalInterestUtil.calHalfAYearProfit(param, zeroplace, paramInt);
			break;
		default:
			break;
		}
		return interest;
	} 
	/**
	 * 生成阶梯利息参数
	 * @param listingTradeList
	 * @param addInvestProfit  加息
	 * @return
	 */
	public List<InvestProfitParam> genInvestProfitParamList(List<ListingTrade> listingTradeList,BigDecimal addInvestProfit) {
		List<InvestProfitParam> list = listingTradeList.stream().map(listingTrade -> {
			InvestProfitParam param = new InvestProfitParam();
			param.setMinInvestMoney(listingTrade.getMinInvestMoney());
			param.setMaxInvestMoney(listingTrade.getMaxInvestMoney());
			if(addInvestProfit != null) {
				param.setInvestProfit(listingTrade.getInvestProfit().add(addInvestProfit));
			} else {
				param.setInvestProfit(listingTrade.getInvestProfit());
			}
			return param;
		}).collect(Collectors.toList());
		return list;
	}
	/**
	 * 生成计息参数
	 * @param valueDate
	 * @param expireDate
	 * @param interestRate
	 * @param interestBase
	 * @param interestCount
	 * @return
	 */
	public CalInterestParam genCalInterestParam(Date valueDate,Date expireDate
			,InterestRate interestRate,InterestBase interestBase,int interestCount) {
		CalInterestParam param = new CalInterestParam();
		param.setValueDate(valueDate);
		param.setExpireDate(expireDate);
		param.setInterestRate(interestRate);
		param.setInterestBase(interestBase);
		param.setInterestCount(interestCount);
		return param;
	}
	/**
	 * 获取计息结束日期
	 * @param listingInfoPo
	 * @return
	 */
	public Date getInterestEndDate(ListingInfoPo listingInfoPo,Date interestStartDate) {
		if(listingInfoPo.getExpireDateStyle().intValue() == ExpireDateStyle.固定到期日.style) {
			return listingInfoPo.getExpireDate();
		}
		//如果是固定期限
		if(listingInfoPo.getListingLimitType().intValue() == ListingLimitType.天.type) {
			return DateUtil.add(interestStartDate, Calendar.DATE, listingInfoPo.getListingLimit().intValue());
		} else if(listingInfoPo.getListingLimitType().intValue() == ListingLimitType.月.type) {
			return DateUtil.add(interestStartDate, Calendar.MONTH, listingInfoPo.getListingLimit().intValue());
		} else if(listingInfoPo.getListingLimitType().intValue() == ListingLimitType.年.type) {
			return DateUtil.add(interestStartDate, Calendar.YEAR, listingInfoPo.getListingLimit().intValue());
		}
		return listingInfoPo.getExpireDate();
	}
	/**
	 * 生成计算利息参数集合
	 * @param listingInfoPo
	 * @param interestStartDate 计息开始日期
	 * @param interestEndDate 计息结束日期
	 * @param isEndPeriod 是否是最后一期
	 * @return
	 */
	public List<CalInterestParam> genCalInterestParamList(ListingInfoPo listingInfoPo,Date interestStartDate,Date interestEndDate,boolean isEndPeriod) {
		List<CalInterestParam> list = new ArrayList<>(); 
		InterestRate interestRate = InterestRate.getInterestRateByValue(listingInfoPo.getInterestRate().intValue());
		InterestBase interestBase = InterestBase.getInterestBaseByValue(listingInfoPo.getInterestBase().intValue());
		//计算利息结束日期，根据到期日是否计息判断是否加一天
		Date calInterestEndDate = interestEndDate;
		if(isEndPeriod && listingInfoPo.getExpireDateInterest().intValue() == YesNo.是.value) {
			//如果是最后一期，并且到期日计息，加一天
			calInterestEndDate = DateUtil.add(interestEndDate, Calendar.DATE, 1);
		}
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
	 * 获取期数集合信息
	 * @param listingInfoPo
	 * @param apply
	 * @return
	 */
	public abstract List<PeriodResult> periodResultList(ListingInfoPo listingInfoPo,BizImportApply apply);
	/**
	 * 获取本期的本金
	 * @param totalPrincipal //总本金
	 * @param periodNum 期数
	 * @param totalPeriodNum 总期数
	 * @return
	 */
	public abstract BigDecimal periodPrincipal(BigDecimal totalPrincipal,int periodNum,int totalPeriodNum);
}
