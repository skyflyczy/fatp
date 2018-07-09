package com.fatp.service.plan.repay.stragey;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.biz.PayinvestStatus;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.CalInterestUtil;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.param.InvestProfitParam;
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
	 * 生成还款兑付计划
	 * @param listingInfoPo
	 * @param listingTradeList
	 * @param apply
	 * @param tradeDetailList
	 */
	public abstract void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList,BizImportApply apply,List<BizimportTradeDetail> tradeDetailList,int operatorId);
	
	/**
	 * 生成基础的兑付信息
	 * @param operatorId
	 * @param tradeDetail
	 * @return
	 */
	protected BizplanPayinvestPo genBasePayinvest(int operatorId,BizimportTradeDetail tradeDetail) {
		BizplanPayinvestPo payinvest = new BizplanPayinvestPo();
		payinvest.setCardAccount(tradeDetail.getCardAccount());
		payinvest.setCreateOperatorId(operatorId);
		payinvest.setInvestUserId(tradeDetail.getUserId());
		payinvest.setListingInfoId(tradeDetail.getListingInfoId());
		payinvest.setPayinvestPlanGuid(UUIDUtil.getUUID());
		payinvest.setSubBankName(tradeDetail.getSubBankName());
		payinvest.setUpdateOperatorId(operatorId);
		payinvest.setPayinvestStatus(PayinvestStatus.未兑付.status);
		return payinvest;
	}
	/**
	 * 计算收益
	 * @param param
	 */
	protected BigDecimal calProfit(CalInterestParam param){
		return calProfit(param, 2, BigDecimal.ROUND_DOWN);
	}
	/**
	 * 计算收益
	 * @param param
	 * @param zeroplace
	 * @param paramInt
	 * @return
	 */
	public static BigDecimal calProfit(CalInterestParam param, int zeroplace, int paramInt){
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
	protected List<InvestProfitParam> genInvestProfitParamList(List<ListingTrade> listingTradeList,BigDecimal addInvestProfit) {
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
	protected CalInterestParam genCalInterestParam(Date valueDate,Date expireDate
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
	protected Date getInterestEndDate(ListingInfoPo listingInfoPo,Date interestStartDate) {
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
}
