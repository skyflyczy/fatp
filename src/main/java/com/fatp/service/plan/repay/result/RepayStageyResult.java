package com.fatp.service.plan.repay.result;

import java.util.Date;

/**
 * 还款分期结果
 * 
 * @author zhiya.chai
 * @date 2018年6月22日 上午10:22:16
 */
public class RepayStageyResult {

	private int period;//第几期
	
	private Date interestStartDate;//起息日
	
	private Date interestEndDate;//计息截止日
	
	private Date repayDate;//当期还款日
	
	private Date investDate;//当期兑付日
	
	private int interestType;//计息方式 
	
	public static RepayStageyResult build(){
		return new RepayStageyResult();
	}

	public int getPeriod() {
		return period;
	}

	public RepayStageyResult setPeriod(int period) {
		this.period = period;
		return this;
	}

	public Date getInterestStartDate() {
		return interestStartDate;
	}

	public RepayStageyResult setInterestStartDate(Date interestStartDate) {
		this.interestStartDate = interestStartDate;
		return this;
	}

	public Date getInterestEndDate() {
		return interestEndDate;
	}

	public RepayStageyResult setInterestEndDate(Date interestEndDate) {
		this.interestEndDate = interestEndDate;
		return this;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public RepayStageyResult setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
		return this;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public RepayStageyResult setInvestDate(Date investDate) {
		this.investDate = investDate;
		return this;
	}

	public int getInterestType() {
		return interestType;
	}

	public RepayStageyResult setInterestType(int interestType) {
		this.interestType = interestType;
		return this;
	}
}
