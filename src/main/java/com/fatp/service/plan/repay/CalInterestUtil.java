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
        List<InvestProfitParam> investProfitParamList = param.getInvestProfitParamList();//阶梯利率
        BigDecimal surplusPrincipal = param.getPrincipal();//剩余本金
        BigDecimal interest = BigDecimal.ZERO;
        for(InvestProfitParam investProfitParam : investProfitParamList) {
        	BigDecimal calPrincipal = investProfitParam.getMaxInvestMoney().subtract(investProfitParam.getMinInvestMoney());
			if(surplusPrincipal.compareTo(calPrincipal) >= 0) {
				interest = interest.add(calPrincipal.multiply(investProfitParam.getInvestProfit()).setScale(zeroplace, paramInt));
				surplusPrincipal = surplusPrincipal.subtract(calPrincipal);
			} else {
        		interest = interest.add(surplusPrincipal.multiply(investProfitParam.getInvestProfit()).setScale(zeroplace, paramInt));
        		surplusPrincipal = BigDecimal.ZERO;
        	}
        }
        if(surplusPrincipal.compareTo(BigDecimal.ZERO) > 0) {
        	interest = interest.add(surplusPrincipal.multiply(investProfitParamList.get(investProfitParamList.size()-1).getInvestProfit()).setScale(zeroplace, paramInt));
        }
        return interest;
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
        int interestDay = param.getInterestDay();//计息天数
        switch (interestBase) {
            case ACT_365:
            	return calProfitByLadder(param.getInvestProfitParamList(), principal, interestDay, 365, zeroplace, paramInt);
            case ACT_360:
            	return calProfitByLadder(param.getInvestProfitParamList(), principal, interestDay, 360, zeroplace, paramInt);
            case ACT_ACT:
                return calProfitByACT(param, zeroplace, paramInt);
            default:
                return BigDecimal.ZERO;
        }
    }

    /**
     * 分闰平年计算利息
     *
     * @param param
     * @param zeroplace
     * @param paramInt
     * @return
     * @author zhiya.chai
     */
    private static BigDecimal calProfitByACT(CalInterestParam param, int zeroplace, int paramInt) {
        BigDecimal principal = param.getPrincipal();//本金
        Date valueDate = param.getValueDate();//计息起息日
        Date expireDate = param.getExpireDate();//计息到期日
        int firstYear = DateUtil.getYear(valueDate);
        int lastYear = DateUtil.getYear(expireDate);
        BigDecimal interest = BigDecimal.ZERO;//利息
        int interestDay = param.getInterestDay(); // 计息天数
        List<InvestProfitParam> investProfitParamList = param.getInvestProfitParamList();//阶梯利率集合
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
        return calProfitByLadder(param.getInvestProfitParamList(), param.getPrincipal(), 1, 12, zeroplace, paramInt);
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
    	BigDecimal surplusPrincipal = principal;//剩余本金
        BigDecimal interest = BigDecimal.ZERO;
        for(InvestProfitParam investProfitParam : investProfitParamList) {
        	BigDecimal calPrincipal = investProfitParam.getMaxInvestMoney().subtract(investProfitParam.getMinInvestMoney());
			if(surplusPrincipal.compareTo(calPrincipal) >= 0) {
				BigDecimal i = calPrincipal.multiply(investProfitParam.getInvestProfit()).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
				interest = interest.add(i);
				surplusPrincipal = surplusPrincipal.subtract(calPrincipal);
			} else {
				BigDecimal i = surplusPrincipal.multiply(investProfitParam.getInvestProfit()).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
        		interest = interest.add(i);
        		surplusPrincipal = BigDecimal.ZERO;
        	}
        }
        //如果剩余本金大于0，按照阶梯利率最后一个算
        if(surplusPrincipal.compareTo(BigDecimal.ZERO) > 0) {
        	BigDecimal i = surplusPrincipal.multiply(investProfitParamList.get(investProfitParamList.size()-1).getInvestProfit()).multiply(new BigDecimal(interestDay)).divide(new BigDecimal(interestBaseDay),zeroplace,paramInt).setScale(zeroplace, paramInt);
        	interest = interest.add(i);
        }
        return interest;
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
    	param.setInterestDay(32);
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
