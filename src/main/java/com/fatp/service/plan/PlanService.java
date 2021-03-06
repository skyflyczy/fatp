package com.fatp.service.plan;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.listing.ListingTrade;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.exception.FatpException;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;

/**
 * 生成还款兑付计划入口
 * 
 * @author zhiya.chai
 * @date 2018年6月21日 上午11:39:15
 */
@Service
public class PlanService extends BaseService{
	
	@Autowired
	private ListingInfoDataSupportService listingInfoDataSupportService;
	@Autowired
	private InvestApplyDataSupportService investApplyDataSupportService;
	@Autowired
	private GenPlanService genPlanService;
	
	/**
	 * 异步生成计划
	 * @param listingInfoId
	 * @param applyId
	 * @param operatorId
	 */
	public void asynGenPlan(int listingInfoId,int applyId,int operatorId) {
		Runnable processThread = () -> {
        	try {
        		genPlan(listingInfoId, applyId, operatorId);;
        	} catch(Exception e) {
        		logger.error("生成还款兑付计划失败",e);
        	}
        };
        Thread task = new Thread(processThread);
        task.setDaemon(true);
        task.start();
	}
	
	/**
	 * 生成计划
	 * @param listingInfoId
	 * @param applyId
	 * @param operatorId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void genPlan(int listingInfoId,int applyId,int operatorId) {
		//获取挂牌产品信息
		ListingInfoPo listingInfoPo = listingInfoDataSupportService.getLisingInfoPoById(listingInfoId);
		if(listingInfoPo == null) {
			logger.error("生成还款兑付计划，获取挂牌信息为空：listingInfoId：" + listingInfoId);
			return;
		}
		//获取本次上传的交易明细和汇总信息
		BizImportApply apply = investApplyDataSupportService.getApplyById(applyId);
		if(apply == null) {
			logger.error("生成还款兑付计划，获取挂牌产品的登记信息为空：applyId：" + applyId);
			return;
		}
		List<ListingTrade> listingTradeList =  listingInfoDataSupportService.getTradeByListingInfoId(listingInfoId);
		if(CollectionUtils.isEmpty(listingTradeList)){
			logger.error("生成还款兑付计划，获取挂牌交易信息为空：listingInfoId：" + listingInfoId);
			updateApplyStatus(apply, ApplyStatus.登记成功,"登记成功。收益率为空，未生成还款兑付计划");
			return;
		}
		List<BizimportTradeDetail> tradeDetailList = investApplyDataSupportService.findTradeDetailByApplyId(applyId);
		if(CollectionUtils.isEmpty(tradeDetailList)) {
			logger.error("生成还款兑付计划，获取客户交易信息为空：applyId：" + applyId);
			updateApplyStatus(apply, ApplyStatus.登记失败,"客户交易信息为空");
			return;
		}
		try {
			genPlanService.genRepayAndPayInvesetPlan(listingInfoPo, listingTradeList, apply, tradeDetailList, operatorId);
			updateApplyStatus(apply, ApplyStatus.登记成功,"成功");
		} catch (FatpException e) {
			logger.error("生成还款兑付计划失败,applyId：{}",applyId,e);
			updateApplyStatus(apply, ApplyStatus.登记失败,e.getMessage());
		} catch (Exception e) {
			logger.error("生成还款兑付计划失败,applyId：{}",applyId,e);
			updateApplyStatus(apply, ApplyStatus.登记失败,"生成还款兑付计划异常");
		}
	}

	/**
	 * 更新登记状态
	 * @param apply
	 * @param applyStatus
	 */
	private void updateApplyStatus(BizImportApply apply,ApplyStatus applyStatus,String statusMsg) {
		BizimportApplyPo po = new BizimportApplyPo();
		po.setApplyStatus(applyStatus.status);
		po.setId(apply.getId());
		po.setStatusMsg(statusMsg);
		investApplyDataSupportService.updateApplyStatus(po);
	}
}
