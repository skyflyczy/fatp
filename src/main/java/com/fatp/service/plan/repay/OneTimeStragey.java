package com.fatp.service.plan.repay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.YesNo;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.exception.FatpException;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.po.biz.BizplanRepayPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;
import com.fatp.service.datasupprot.sys.SysWorkdateDataSupportService;
import com.fatp.service.plan.repay.param.CalInterestParam;
import com.fatp.service.plan.repay.result.PeriodResult;
import com.fatp.util.DateUtil;
import com.fatp.util.UUIDUtil;

/**
 * 一次性还本付息
 * @author zhiya.chai
 * @date 2018年6月22日 上午9:02:46
 */
@Service("oneTimeStragey")
public class OneTimeStragey extends PlanGenStragey{
	
	@Autowired
	private SysWorkdateDataSupportService sysWorkdateDataSupportService;
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	/**
	 * 生成还款兑付计划
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void genRepayAndPayInvesetPlan(ListingInfoPo listingInfoPo,List<ListingTrade> listingTradeList, 
			BizImportApply apply,
			List<BizimportTradeDetail> tradeDetailList,
			int operatorId) {
		//获取分期结果
		PeriodResult periodResult = getPeriodResult(listingInfoPo, apply);
		//组装计算利息参数,生成利息
		CalInterestParam param = genCalInterestParam(periodResult, listingInfoPo);
		//得到计息数量
		param.setInterestCount(getInterestCount(listingInfoPo, periodResult));
		List<BizplanPayinvestPo> payinvestList = tradeDetailList.stream().map(tradeDetail ->{
			param.setPrincipal(tradeDetail.getTradeMoney());
			param.setInvestProfitParamList(super.genInvestProfitParamList(listingTradeList,tradeDetail.getAddInvestProfit()));
			param.setAddInvestProfitDays(tradeDetail.getAddInvestProfitDays() == null ? 0 : tradeDetail.getAddInvestProfitDays().intValue());
			//计算利息
			BigDecimal interest = super.calProfit(param);
			BizplanPayinvestPo payinvest = super.genBasePayinvest(operatorId, tradeDetail);
			payinvest.setPeriodNumber(periodResult.getPeriod());
			payinvest.setPrincipal(tradeDetail.getTradeMoney());
			payinvest.setInterest(interest);
			payinvest.setBizImportApplyId(apply.getId());
			payinvest.setIsDelete(YesNo.否.value);
			return payinvest;
		}).collect(Collectors.toList());
		//计算还款兑付日，为到期日所在的工作日 TODO 目前规则
		Date repayDate = sysWorkdateDataSupportService.getBelongWorkDate(periodResult.getInterestEndDate());
		//生成还款对象
		BizplanRepayPo repay = this.genBizplanRepay(operatorId, listingInfoPo, periodResult, repayDate,apply.getId());
		BigDecimal principal = repay.getPrincipal(); //应还本金
		BigDecimal interest = repay.getInterest(); //应还利息
		for(BizplanPayinvestPo payInvest : payinvestList) {
			principal = principal.add(payInvest.getPrincipal());
			interest = interest.add(payInvest.getInterest());
		}
		repay.setPrincipal(principal);
		repay.setInterest(interest);
		repay.setInterestPrincipal(principal);
		
		//插入数据库
		int id = bizplanRepayDataSupportService.insert(repay);
		if(id <= 0) {
			throw new FatpException("更新还款计划失败。");
		}
		payinvestList.stream().forEach(payInvest ->{
			payInvest.setRepayPlanId(repay.getId());
		});
		bizplanPayinvestDataSupportService.insertBatch(payinvestList);
	}
	/**
	 * 生成计算利息参数
	 * @param periodResult
	 * @param listingInfoPo
	 * @return
	 */
	private CalInterestParam genCalInterestParam(PeriodResult periodResult,ListingInfoPo listingInfoPo) {
		CalInterestParam param = new CalInterestParam();
		param.setExpireDate(periodResult.getInterestEndDate());
		param.setValueDate(periodResult.getInterestStartDate());
		param.setInterestRate(InterestRate.getInterestRateByValue(listingInfoPo.getInterestRate()));
		param.setInterestBase(InterestBase.getInterestBaseByValue(listingInfoPo.getInterestBase()));
		return param;
	}
	/**
	 * 生成还款对象
	 * @param operatorId
	 * @param listingInfoPo
	 * @param periodResult
	 * @param repayDate
	 * @return
	 */
	private BizplanRepayPo genBizplanRepay(int operatorId,ListingInfoPo listingInfoPo
			,PeriodResult periodResult,Date repayDate,int applyId) {
		BizplanRepayPo repay = new BizplanRepayPo();
		repay.setCreateOperatorId(operatorId);
		repay.setListingInfoId(listingInfoPo.getId());
		repay.setRepayPlanGuid(UUIDUtil.getUUID());
		repay.setPeriodNumber(periodResult.getPeriod());
		repay.setPrincipal(BigDecimal.ZERO);
		repay.setInterest(BigDecimal.ZERO);
		//repay.setInterestDay(interestDay);
		repay.setInterestEndDate(periodResult.getInterestEndDate());
		repay.setInterestStartDate(periodResult.getInterestStartDate());
		repay.setPlanRepayDate(repayDate);
		repay.setRepayStatus(RepayStatus.未还款.status);
		repay.setUpdateOperatorId(operatorId);
		repay.setBizImportApplyId(applyId);
		repay.setIsDelete(YesNo.否.value);
		return repay;
	}
	/**
	 * 获取计息数量
	 * @param listingInfoPo
	 * @param periodResult
	 * @return
	 */
	private int getInterestCount(ListingInfoPo listingInfoPo,PeriodResult periodResult) {
		if(listingInfoPo.getExpireDateStyle().intValue() ==  ExpireDateStyle.固定期限.style) {
			return listingInfoPo.getListingLimit();
		}
		//固定到期日
		if(listingInfoPo.getListingLimitType().intValue() == ListingLimitType.年.type 
				|| listingInfoPo.getListingLimitType().intValue() == ListingLimitType.月.type) {
			return listingInfoPo.getListingLimit();
		}
		//如果按天计算
		if(listingInfoPo.getExpireDateInterest().intValue() == YesNo.是.value) {
			return (int)DateUtil.getDiffByDate(periodResult.getInterestEndDate(), periodResult.getInterestStartDate()) + 1;
		} else {
			return (int)DateUtil.getDiffByDate(periodResult.getInterestEndDate(), periodResult.getInterestStartDate());
		}
	}
	/**
	 * 获取分期信息
	 * @param listingInfoPo
	 * @param apply
	 * @return
	 */
	private PeriodResult getPeriodResult(ListingInfoPo listingInfoPo,BizImportApply apply){
		PeriodResult result = PeriodResult.build();
		result.setPeriod(1);
		result.setInterestStartDate(apply.getValueDate() == null ? listingInfoPo.getValueDate() : apply.getValueDate());
		result.setInterestType(listingInfoPo.getPayInterestType());
		result.setInterestEndDate(getInterestEndDate(listingInfoPo, result.getInterestStartDate()));
		return result;
	}

}
