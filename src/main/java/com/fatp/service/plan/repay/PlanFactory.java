package com.fatp.service.plan.repay;

import com.fatp.context.FatpContext;
import com.fatp.enums.project.PayInterestType;
import com.fatp.service.plan.repay.stragey.PlanGenStragey;
/**
 * 还款计划工厂
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:46:30
 */
public class PlanFactory {

	private static final PlanFactory factory = new PlanFactory();
	
	private PlanFactory(){};
	
	public static final PlanFactory getInstance(){
		return factory;
	}
	
	public PlanGenStragey chooseStrategy(int payInterestType){
		if(payInterestType == PayInterestType.一次性到期还本付息.type) {
			return (PlanGenStragey)FatpContext.getContext().getBean("oneTimeStragey");
		} else if(payInterestType == PayInterestType.按半年息到期还本.type) {
			return (PlanGenStragey)FatpContext.getContext().getBean("halfAYearInterestStragey");
		} else if(payInterestType == PayInterestType.按季付息到期还本.type){
			return (PlanGenStragey)FatpContext.getContext().getBean("seasonInterestStragey");
		} else if(payInterestType == PayInterestType.按年付息到期还本.type){
			return (PlanGenStragey)FatpContext.getContext().getBean("yearInterestStragey");
		} else if(payInterestType == PayInterestType.按月付息到期还本.type){
			return (PlanGenStragey)FatpContext.getContext().getBean("monthInterestStragey");
		} else if(payInterestType == PayInterestType.等额本息.type){
			
		}
		return null;
	}
}
