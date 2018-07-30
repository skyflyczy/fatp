package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.Date;

import com.fatp.enums.project.InterestBase;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.util.DateUtil;

/**
 * 计算收益帮助类
 * @author zhiya.chai
 * @date 2018年6月22日 下午10:27:04
 */
public class CalInterestUtil {
	
	private static final int PLAIN_YEAR_DAYS = 365;
    private static final int LEAP_YEAR_DAYS = 366;
	/**
     * 计算年利息
     *
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     * @author zhiya.chai
     */
    public static BigDecimal calYearProfit(CalInterestParam param, int zeroplace, int paramInt) {
    	return coreCal(param.getInvestProfit(), param.getPrincipal(), param.getInterestCount(), 1, zeroplace, paramInt);
    }
    
    /**
     * 计算季利息
     *
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     * @author zhiya.chai
     */
    public static BigDecimal calSeasonProfit(CalInterestParam param, int zeroplace, int paramInt) {
    	return coreCal(param.getInvestProfit(), param.getPrincipal(), param.getInterestCount(), 4, zeroplace, paramInt);
    }
    
    /**
     * 计算半年利息
     *
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     * @author zhiya.chai
     */
    public static BigDecimal calHalfAYearProfit(CalInterestParam param, int zeroplace, int paramInt) {
    	return coreCal(param.getInvestProfit(), param.getPrincipal(), param.getInterestCount(), 2, zeroplace, paramInt);
    }

    /**
     * 计算日利息
     *
     * @param param
     * @param zeroplace 保留小数位数
     * @param paramInt  保留尾数算法
     * @return
     * @author zhiya.chai
     */
    public static BigDecimal calDaysProfit(CalInterestParam param, int zeroplace, int paramInt) {
        InterestBase interestBase = param.getInterestBase();
        BigDecimal principal = param.getPrincipal();//本金
        int interestDay = param.getInterestCount();//计息天数
        switch (interestBase) {
            case ACT_365:
            	return coreCal(param.getInvestProfit(), principal, interestDay, 365, zeroplace, paramInt);
            case ACT_360:
            	return coreCal(param.getInvestProfit(), principal, interestDay, 360, zeroplace, paramInt);
            case ACT_ACT:
                return calProfitByACT(param.getValueDate(),param.getExpireDate(),interestDay, principal,param.getInvestProfit(),zeroplace, paramInt);
            default:
                return BigDecimal.ZERO;
        }
    }

    /**
     * 分平闰年计算利息
     * @param valueDate
     * @param expireDate
     * @param interestDay
     * @param principal
     * @param investProfitParamList
     * @param zeroplace
     * @param paramInt
     * @return
     */
    private static BigDecimal calProfitByACT(Date valueDate,Date expireDate,int interestDay,
    		BigDecimal principal, BigDecimal investProfit, int zeroplace, int paramInt) {
        int firstYear = DateUtil.getYear(valueDate);
        int lastYear = DateUtil.getYear(expireDate);
        BigDecimal interest = BigDecimal.ZERO;//利息
        if (firstYear != lastYear) { //如果首年与尾年不是同一年
            //获取首年天数
            int firstYearDays = DateUtil.getDiffByDate(DateUtil.getYearLast(firstYear), valueDate) + 1;
            int lastYearDays = DateUtil.getDiffByDate(expireDate, DateUtil.getYearFirst(lastYear));
            int calInterestDay = DateUtil.getDiffByDate(expireDate, valueDate);
            if (interestDay > calInterestDay) {
                lastYearDays = (interestDay - calInterestDay) + lastYearDays;
            }
            if (DateUtil.isLeapYear(firstYear)) {//是否是闰年
            	interest = coreCal(investProfit, principal, firstYearDays, LEAP_YEAR_DAYS, zeroplace, paramInt);
            } else {
            	interest = coreCal(investProfit, principal, firstYearDays, PLAIN_YEAR_DAYS, zeroplace, paramInt);
            }
            if (DateUtil.isLeapYear(lastYear)) {//是否是闰年
            	interest = interest.add(coreCal(investProfit, principal, lastYearDays, LEAP_YEAR_DAYS, zeroplace, paramInt));
            } else {
            	interest = interest.add(coreCal(investProfit, principal, lastYearDays, PLAIN_YEAR_DAYS, zeroplace, paramInt));
            }
            int diffYeay = lastYear - firstYear - 1;//相差年份
            interest = interest.add(coreCal(investProfit, principal, diffYeay, 1, zeroplace, paramInt));
        } else {
            if (DateUtil.isLeapYear(firstYear)) {//是否是闰年
                interest = coreCal(investProfit, principal, interestDay, LEAP_YEAR_DAYS, zeroplace, paramInt);
            } else {
                interest = coreCal(investProfit, principal, interestDay, PLAIN_YEAR_DAYS, zeroplace, paramInt);
            }
        }
        return interest;
    }

    /**
     * 计算月收益
     *
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     * @author zhiya.chai
     */
    public static BigDecimal calMonthProfit(CalInterestParam param, int zeroplace, int paramInt) {
    	return coreCal(param.getInvestProfit(), param.getPrincipal(), param.getInterestCount(),12, zeroplace, paramInt);
    }
    /**
     * 核心计算
     * @param investProfit 利率
     * @param principal 计算本金
     * @param interestDay 计息天数
     * @param interestBaseDay 计息基准天数
     * @param zeroplace 
     * @param paramInt
     * @return
     */
    private static BigDecimal coreCal(BigDecimal investProfit,BigDecimal principal,int interestDay,int interestBaseDay,int zeroplace, int paramInt) {
        return principal.multiply(investProfit).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
    }
}
