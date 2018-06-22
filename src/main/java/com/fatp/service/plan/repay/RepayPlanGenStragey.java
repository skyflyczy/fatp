package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.Date;

import com.fatp.domain.listing.ListingInfo;

/**
 * 还款计划生成策略
 * 
 * @author zhiya.chai
 * @date 2018年6月21日 下午3:46:23
 */
public interface RepayPlanGenStragey {

	/**
	 * 统计期数
	 * @param valueDate
	 * @param expireDate
	 * @return
	 */
	public int countPeriods(Date valueDate,Date expireDate);
	/**
	 * 获取计息方式
	 * @return
	 */
	public int getInterestType(ListingInfo listingInfo);
	
	/**
	 * 获取应还本金和收益
	 * @param totalPrincipal
	 * @param investProfit
	 * @param period
	 * @param periods
	 * @return
	 */
	public BigDecimal[] getPrincipal(BigDecimal totalPrincipal, BigDecimal investProfit,int period,int periods);
	
	
}
