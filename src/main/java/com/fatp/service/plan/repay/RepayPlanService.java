package com.fatp.service.plan.repay;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fatp.domain.offsite.BizImportApply;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.plan.repay.result.RepayStageyResult;

@Service
public class RepayPlanService {
	
	protected Logger log = LogManager.getLogger(getClass());
	
	public void genRepayPlan(ListingInfoPo listingInfoPo,BizImportApply apply){
		//1、根据计息方式获取生成策略
		RepayPlanGenStragey planGenStagey = RepayPlanFactory.getInstance().chooseStrategy(listingInfoPo.getPayInterestType().intValue());
		if(planGenStagey == null) {
			log.error("获取计划生成策略为空，不能生成还款计划，listingInfoId：" + listingInfoPo.getId());
			return;
		}
		//获取期数
		int periods = planGenStagey.countPeriods(apply.getValueDate(),listingInfoPo.getExpireDate());
		
		
	}
	
	private List<RepayStageyResult> genInterestPeriod(RepayPlanGenStragey planGenStagey,Date valueDate,Date expireDate){
		return null;
	}
}
