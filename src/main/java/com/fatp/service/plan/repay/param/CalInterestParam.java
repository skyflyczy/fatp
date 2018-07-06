package com.fatp.service.plan.repay.param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;

/**
 * 计算利息参数
 * @author zhiya.chai
 * @date 2018年6月22日 下午10:32:00
 */
public class CalInterestParam {
	//计息数量  如果是按日，即为计息天数，如果是按月，即为计息月数，如果是按年，则是计息年数
	private int interestCount;
	//本金
	private BigDecimal principal;
	//计息频率：按日、按月、按年
	private InterestRate interestRate;
	//计息基准 365、360、ACT
	private InterestBase interestBase;
	//起息日
	private Date valueDate;
	//到期日
	private Date expireDate;
	/**
	 * 收益利息阶梯
	 */
	private List<InvestProfitParam> investProfitParamList;
	
	
	public BigDecimal getPrincipal() {
		return principal;
	}
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}
	public InterestRate getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(InterestRate interestRate) {
		this.interestRate = interestRate;
	}
	public InterestBase getInterestBase() {
		return interestBase;
	}
	public void setInterestBase(InterestBase interestBase) {
		this.interestBase = interestBase;
	}
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public List<InvestProfitParam> getInvestProfitParamList() {
		return investProfitParamList;
	}
	public void setInvestProfitParamList(
			List<InvestProfitParam> investProfitParamList) {
		this.investProfitParamList = investProfitParamList;
	}
	public int getInterestCount() {
		return interestCount;
	}
	public void setInterestCount(int interestCount) {
		this.interestCount = interestCount;
	}
}
