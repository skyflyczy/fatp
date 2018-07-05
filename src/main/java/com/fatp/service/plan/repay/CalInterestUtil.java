package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fatp.enums.project.InterestBase;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.param.InvestProfitParam;
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
    	BigDecimal interest = calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), param.getInterestCount(), 1, zeroplace, paramInt);
    	BigDecimal addInterest = calAddInterest(param, zeroplace, paramInt);;
        return interest.add(addInterest);
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
    	BigDecimal interest = calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), param.getInterestCount(), 4, zeroplace, paramInt);
    	BigDecimal addInterest = calAddInterest(param, zeroplace, paramInt);;
        return interest.add(addInterest);
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
    	BigDecimal interest = calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), param.getInterestCount(), 2, zeroplace, paramInt);
    	BigDecimal addInterest = calAddInterest(param, zeroplace, paramInt);;
        return interest.add(addInterest);
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
        int interestDay = (param.getInterestCount() + param.getAddInvestProfitDays());//计息天数
        switch (interestBase) {
            case ACT_365:
            	return calProfitByLadder(param.getInvestProfitParamList(), principal, interestDay, 365, zeroplace, paramInt);
            case ACT_360:
            	return calProfitByLadder(param.getInvestProfitParamList(), principal, interestDay, 360, zeroplace, paramInt);
            case ACT_ACT:
                return calProfitByACT(param.getValueDate(),param.getExpireDate(),interestDay, principal,param.getInvestProfitParamList(),zeroplace, paramInt);
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
    		BigDecimal principal, List<InvestProfitParam> investProfitParamList, int zeroplace, int paramInt) {
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
            	interest = calProfitByLadder(investProfitParamList, principal, firstYearDays, LEAP_YEAR_DAYS, zeroplace, paramInt);
            } else {
            	interest = calProfitByLadder(investProfitParamList, principal, firstYearDays, PLAIN_YEAR_DAYS, zeroplace, paramInt);
            }
            if (DateUtil.isLeapYear(lastYear)) {//是否是闰年
            	interest = calProfitByLadder(investProfitParamList, principal, lastYearDays, LEAP_YEAR_DAYS, zeroplace, paramInt);
            } else {
            	interest = calProfitByLadder(investProfitParamList, principal, lastYearDays, PLAIN_YEAR_DAYS, zeroplace, paramInt);
            }
            int diffYeay = lastYear - firstYear - 1;//相差年份
            interest = interest.add(calProfitByLadder(investProfitParamList, principal, diffYeay, 1, zeroplace, paramInt));
        } else {
            if (DateUtil.isLeapYear(firstYear)) {//是否是闰年
                interest = calProfitByLadder(investProfitParamList, principal, interestDay, LEAP_YEAR_DAYS, zeroplace, paramInt);
            } else {
                interest = calProfitByLadder(investProfitParamList, principal, interestDay, PLAIN_YEAR_DAYS, zeroplace, paramInt);
            }
        }
        //获取尾年天数

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
    	BigDecimal interest = calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), param.getInterestCount(),12, zeroplace, paramInt);
    	BigDecimal addInterest = calAddInterest(param, zeroplace, paramInt);
        return interest.add(addInterest);
    }
    /**
     * 计算加息部分
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     */
    private static BigDecimal calAddInterest(CalInterestParam param, int zeroplace, int paramInt){
    	if(param.getAddInvestProfitDays() <= 0) {
    		return BigDecimal.ZERO;
    	}
    	InterestBase interestBase = param.getInterestBase();
    	int interestDay = param.getAddInvestProfitDays();
    	switch (interestBase) {
	        case ACT_365:
	        	return calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), interestDay, 365, zeroplace, paramInt);
	        case ACT_360:
	        	return calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), interestDay, 360, zeroplace, paramInt);
	        case ACT_ACT:
	       	 	return calProfitByACT(param.getExpireDate(),param.getExpireDate(),interestDay, param.getPrincipal(),param.getInvestProfitParamList(),zeroplace, paramInt);
	        default:
	       	 return BigDecimal.ZERO;
    	}
    }
    /**
     * 阶梯利率计算
     * @param investProfitParamList 阶梯利率集合
     * @param principal 计算本金
     * @param interestDay 计息天数
     * @param interestBaseDay 计息基准天数
     * @param zeroplace 
     * @param paramInt
     * @return
     */
    private static BigDecimal calProfitByLadder(List<InvestProfitParam> investProfitParamList,
    		BigDecimal principal,int interestDay,int interestBaseDay,int zeroplace, int paramInt) {
    	Collections.sort(investProfitParamList);  //阶梯利率排序,金额从小到大排序
        for(InvestProfitParam investProfitParam : investProfitParamList) {
        	if(principal.compareTo(investProfitParam.getMinInvestMoney()) >= 0 
        			&& principal.compareTo(investProfitParam.getMaxInvestMoney()) < 0) {
        		//如果本金在这个区间内，按照此区间计算
        		return principal.multiply(investProfitParam.getInvestProfit()).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
        	}
        }
        //如果都不在此区间，则本金证明大于等于最后一个阶梯，则按照最后一个阶梯计算
        InvestProfitParam investProfitParam = investProfitParamList.get(investProfitParamList.size()-1);
        return principal.multiply(investProfitParam.getInvestProfit()).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
    }
    
    public static void main(String[] args) {
    	List<InvestProfitParam> list = new ArrayList<InvestProfitParam>();
    	InvestProfitParam p = new InvestProfitParam();
    	p.setMinInvestMoney(new BigDecimal("1000"));
    	p.setMaxInvestMoney(new BigDecimal("3000"));
    	p.setInvestProfit(new BigDecimal("0.2"));
    	InvestProfitParam p1 = new InvestProfitParam();
    	p1.setMinInvestMoney(new BigDecimal("0"));
    	p1.setMaxInvestMoney(new BigDecimal("1000"));
    	p1.setInvestProfit(new BigDecimal("0.1"));
    	list.add(p);
    	list.add(p1);
    	CalInterestParam param = new CalInterestParam();
    	param.setInterestCount(32);
    	param.setExpireDate(DateUtil.autoParseDate("2018-06-31"));
    	param.setValueDate(DateUtil.autoParseDate("2018-06-01"));
    	param.setPrincipal(new BigDecimal("4000"));
    	param.setInterestBase(InterestBase.ACT_ACT);
    	param.setInvestProfitParamList(list);
    	int zeroplace = 2;
    	System.out.println(CalInterestUtil.calMonthProfit(param, zeroplace,  BigDecimal.ROUND_DOWN));
    	System.out.println(CalInterestUtil.calDaysProfit(param, zeroplace,  BigDecimal.ROUND_DOWN));
    	System.out.println(CalInterestUtil.calYearProfit(param, zeroplace,  BigDecimal.ROUND_DOWN));
	}
}
