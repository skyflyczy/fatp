package com.fatp.service.plan.repay.result;

import java.util.Date;

/**
 * 分期结果
 * 
 * @author zhiya.chai
 * @date 2018年6月22日 上午10:22:16
 */
public class PeriodResult {

	private int period;//第几期
	private Date interestStartDate;//起息日
	private Date interestEndDate;//计算计息截止日
	private Date repayDate;//当期还款日
	private Date investDate;//当期兑付日
	
	public static PeriodResult build(){
		return new PeriodResult();
	}

	public int getPeriod() {
		return period;
	}

	public PeriodResult setPeriod(int period) {
		this.period = period;
		return this;
	}

	public Date getInterestStartDate() {
		return interestStartDate;
	}

	public PeriodResult setInterestStartDate(Date interestStartDate) {
		this.interestStartDate = interestStartDate;
		return this;
	}

	public Date getInterestEndDate() {
		return interestEndDate;
	}

	public PeriodResult setInterestEndDate(Date interestEndDate) {
		this.interestEndDate = interestEndDate;
		return this;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public PeriodResult setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
		return this;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public PeriodResult setInvestDate(Date investDate) {
		this.investDate = investDate;
		return this;
	}
	
}
