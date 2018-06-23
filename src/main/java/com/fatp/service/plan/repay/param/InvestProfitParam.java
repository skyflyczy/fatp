package com.fatp.service.plan.repay.param;

import java.math.BigDecimal;
/**
 * 收益利息阶梯
 * @author zhiya.chai
 */
public class InvestProfitParam implements Comparable<InvestProfitParam>{
	/**
	 * 最低投资金额，元（包含此值）
	 */
	private BigDecimal minInvestMoney;
	/**
	 * 最高投资金额，元（不包含此值）
	 */
	private BigDecimal maxInvestMoney;
	/**
	 * 拟定年化收益率,没有乘以%
	 */
	private BigDecimal investProfit;
	public BigDecimal getMinInvestMoney() {
		return minInvestMoney;
	}
	public void setMinInvestMoney(BigDecimal minInvestMoney) {
		this.minInvestMoney = minInvestMoney;
	}
	public BigDecimal getMaxInvestMoney() {
		return maxInvestMoney;
	}
	public void setMaxInvestMoney(BigDecimal maxInvestMoney) {
		this.maxInvestMoney = maxInvestMoney;
	}
	public BigDecimal getInvestProfit() {
		return investProfit;
	}
	public void setInvestProfit(BigDecimal investProfit) {
		this.investProfit = investProfit;
	}
	@Override
	public int compareTo(InvestProfitParam o) {
		return this.getMinInvestMoney().compareTo(o.getMinInvestMoney());
	}
}
