package com.fatp.service.offsite;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.controller.param.InvestRecordsParam;
import com.fatp.domain.PageData;
import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.domain.offsite.InvestApply;
import com.fatp.domain.offsite.InvestRecordsResult;
import com.fatp.enums.FlowFeedTypeDesc;
import com.fatp.enums.YesNo;
import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.GlobalFilePo;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.offsite.BizimportSummaryPo;
import com.fatp.service.GlobalFileService;
import com.fatp.service.ImportFileService;
import com.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;
import com.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
import com.fatp.service.plan.PlanService;
import com.huajin.baymax.util.DateUtils;

@Service
public class InvestApplyService {

	@Autowired
	private InvestApplyDataSupportService investApplyDataSupportService;
	@Autowired
	private ListingInfoDataSupportService listingInfoDataSupportService;
	@Autowired
	private ImportFileService importFileService;
	@Autowired
	private GlobalFileService globalFileService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	@Autowired
	private PlanService planService;
	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	
	/**
	 * 分页查找可进行登记的挂牌产品列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanApplyListingList(Map<String,Object> map,int pageNo, int pageSize) {
		return investApplyDataSupportService.getCanApplyListingList(map, pageNo, pageSize);
	}
	/**
	 * 组装投资明细
	 * @param list
	 * @return
	 */
	public InvestRecordsResult assumInvestRecords(List<BizimportTradeDetail> list){
		BigDecimal totalMoney = BigDecimal.ZERO;
		Set<String> idNumberSet = new HashSet<String>();
		Map<String, Integer> duplicateMap = new HashMap<String, Integer>();
		InvestRecordsResult result = new InvestRecordsResult();
		for(int i = 0; i < list.size(); i++){
			BizimportTradeDetail detail = list.get(i);
			totalMoney = totalMoney.add(detail.getTradeMoney());
			idNumberSet.add(detail.getIdNumber() + "_" + detail.getIdTypeId());
			String key = getJudgeSameInvestRecordsKey(detail);
			if(duplicateMap.containsKey(key)) {
				//有相同的
				result.setDuplicateMap(key, duplicateMap.get(key));
				result.setDuplicateMap(key, (i+2));
			}else {
				duplicateMap.put(key, (i+2));
			}
		}
		result.setTotalMoney(totalMoney);
		result.setTotalNum(list.size());
		result.setTotalInvesters(idNumberSet.size());
		if(result.getTotalNum() == 0) {
			throw new FatpException("文件中没有投资明细数据。");
		}
		return result;
	}
	/**
	 * 新增投资明细登记
	 * @param param
	 * @param memberId
	 * @param exchangeId
	 * @param operatorId
	 * @param operatorName
	 * @param status
	 * @throws IOException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addInvestRecords(InvestRecordsParam param,int memberId,int exchangeId,int operatorId,String operatorName) throws IOException{
		//1、查找项目
		ListingInfo listingVo = listingInfoDataSupportService.getByListingGuid(param.getListingGuid());
		if(listingVo == null) {
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		//2、解析文件内容
		String filePath = param.getExcelFilePath() + File.separator + param.getLinkFileName();
		List<BizimportTradeDetail> detailList = importFileService.readInvestRecordsForFile(filePath, listingVo.getListingCode(), exchangeId);
		//3、生成申请信息 bizimport_apply
		BizimportApplyPo apply = investApplyDataSupportService.insertBizimportApplyForInvest(listingVo.getId(), memberId, operatorId, param.getValueDate());
		//4、生成文件信息 global_file
		GlobalFilePo globalFile = globalFileService.insertGlobalFile(filePath, param.getOriginalFileName(), memberId, operatorId);
		//5、生成汇总信息 bizimport_summary
		BizimportSummaryPo summary = investApplyDataSupportService.insertBizimportSummaryForInvest(apply.getId(), globalFile.getId(), detailList);
		//6、导入交易明细信息 bizimport_trade_detail
		investApplyDataSupportService.insertTradeDetails(apply, summary, detailList);
		//7、写入动态
		timelineDetailDataSupportService.createInvestRecordsTimeLine(apply, FlowFeedTypeDesc.保存投资明细申请, "", operatorName);
		//异步执行生成还款兑付计划
		planService.asynGenPlan(listingVo.getId(), apply.getId(), operatorId);
	}
	/**
	 * 获取挂牌产品登记成功的列表
	 * @param listingInfoId
	 * @return
	 */
	public List<BizImportApply> getListingApplyList(int listingInfoId) {
		return investApplyDataSupportService.getListingApplyList(listingInfoId);
	}
	/**
	 * 根据投资明细申请查找交易明细
	 * @param applyGuid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<BizimportTradeDetail> pageFindTradeDetailByApply(String applyGuid,int pageNo, int pageSize) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("applyGuid", applyGuid);
		return investApplyDataSupportService.pageFindTradeDetail(map, pageNo, pageSize);
	}
	
	/**
	 * 更新投资明细申请信息
	 * @param param
	 * @param memberId
	 * @param exchangeId
	 * @param operatorId
	 * @param operatorName
	 * @param status
	 * @throws IOException
	 */
	@Transactional(rollbackFor = Exception.class)
	public void updateInvestRecords(InvestRecordsParam param,int memberId,int exchangeId,int operatorId,String operatorName,ApplyStatus status) throws IOException {
		//1、查出申请
//		BizimportApplyPo apply = bizimportApplyDataSupportService.getApplyByGuidForUpdate(param.getApplyGuid());
//		if(apply == null) {
//			throw new FatpException("此申请不存在。");
//		}
//		if(apply.getApplyStatus() != ApplyStatus.待提交.status) {
//			throw new FatpException("此申请已经提交审核或已审核，不允许更改。");
//		}
//		//2、解析文件
//		ListingBasePo listingBase = null;//listingDataSupportService.findBasePoById(apply.getProjectId());
//		String filePath = param.getExcelFilePath() + File.separator + param.getLinkFileName();
//		List<BizimportTradeDetail> detailList = importFileService.readInvestRecordsForFile(filePath, listingBase.getProjectName(), listingBase.getProjectCode(), memberId, exchangeId);
//		//3、获取汇总信息
//		BizimportSummaryPo summary = bizimportSummaryDataSupportService.getSummaryByApplyGuid(apply.getApplyGuid());
//		//4、获取文件信息
//		GlobalFile globalFile = globalFileService.getGlobalFileById(summary.getGlobalFileId());
//		//5、删除原有的投资明细
//		bizimportTradeDetailDataSupportService.deleteByApplyId(apply.getId());
//		//6、删除汇总信息
//		bizimportSummaryDataSupportService.deleteById(summary.getId());
//		//7、删除文件信息
//		globalFileService.deleteGlobalFileById(globalFile.getId());
//		//8、更新登记信息
//		investApplyDataSupportService.updateBizimportApplyForInvest(apply, param.getValueDate(), operatorId, status);
//		//9、生成文件信息 global_file
//		GlobalFilePo newGlobalFile = globalFileService.insertGlobalFile(filePath, param.getOriginalFileName(), memberId, operatorId);
//		//10、生成汇总信息 bizimport_summary
//		BizimportSummaryPo newSummary = investApplyDataSupportService.insertBizimportSummaryForInvest(apply.getId(), newGlobalFile.getId(), detailList);
//		//11、导入交易明细信息 bizimport_trade_detail
//		investApplyDataSupportService.insertTradeDetails(apply, newSummary, detailList);
//		//12、删除原来文件
//		File file = new File(globalFile.getFilePath());
//		if(file.exists()) {
//			file.delete();
//		}
//		//13、写入动态
//		FlowFeedTypeDesc flowFeedTypeDesc = status == ApplyStatus.待提交 ? FlowFeedTypeDesc.保存投资明细申请 : FlowFeedTypeDesc.投资明细申请提交审核;
//		timelineDetailDataSupportService.createInvestRecordsTimeLine(apply, flowFeedTypeDesc, "", operatorName);
	}
	/**
	 * 删除登记申请
	 * @param applyGuid
	 * @param operatorId
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteApply(String applyGuid,int operatorId) {
		if(StringUtils.isBlank(applyGuid)) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		BizImportApply apply = investApplyDataSupportService.getApplyByApplyGuid(applyGuid);
		if(apply == null) {
			throw new FatpException("此申请不存在");
		}
		if(apply.getIsDelete() == YesNo.是.value) {
			return;
		}
		//设置登记申请为删除状态
		apply.setIsDelete(YesNo.是.value);
		investApplyDataSupportService.updateApplyDeleteStatus(apply);
		//分期获取此次申请所获取兑付总额和总利息
		List<BizplanPayinvest> payinvestList = bizplanPayinvestDataSupportService.findPlanPayinvestByApplyId(apply.getId());
		if(CollectionUtils.isEmpty(payinvestList)) {
			return;
		}
		Map<Integer, BigDecimal> principalMap = new HashMap<>();//本金期数金额Map
		Map<Integer,BigDecimal> interestMap = new HashMap<>();//利息期数金额Map
		for(BizplanPayinvest payinvest : payinvestList) {
			Integer key = payinvest.getPeriodNumber();
			BigDecimal principal = principalMap.get(key);
			if(principal == null) {
				principalMap.put(key, payinvest.getPrincipal());
			} else {
				principalMap.put(key, principal.add(payinvest.getPrincipal()));
			}
			BigDecimal interest = interestMap.get(key);
			if(interest == null) {
				interestMap.put(key, payinvest.getInterest());
			} else {
				interestMap.put(key, interest.add(payinvest.getInterest()));
			}
		}
		//更新此次兑付数据为删除状态
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("isDelete", YesNo.是.value);
		map.put("updateOperatorId", operatorId);
		map.put("bizImportApplyId", apply.getId());
		bizplanPayinvestDataSupportService.updatePayinvestDeleteStatusByApplyId(map);
		//更新还款计划还款金额和还款利息，分期更新
		map.clear();
		map.put("listingInfoId", apply.getListingInfoId());
		List<BizplanRepay> planRepayList = bizplanRepayDataSupportService.findRepayPlanByCondition(map);
		if(CollectionUtils.isEmpty(planRepayList)) {
			return;
		}
		planRepayList.stream().forEach(planRepay ->{
			BigDecimal principal = principalMap.get(planRepay.getPeriodNumber());
			if(principal != null) {
				planRepay.setPrincipal(planRepay.getPrincipal().subtract(principal));
				planRepay.setPrincipal(planRepay.getPrincipal().compareTo(BigDecimal.ZERO) > 0 ? planRepay.getPrincipal() : BigDecimal.ZERO);
				planRepay.setInterestPrincipal(planRepay.getPrincipal());
				planRepay.setUpdateOperatorId(operatorId);
			}
			BigDecimal interest = interestMap.get(planRepay.getPeriodNumber());
			if(interest != null) {
				planRepay.setInterest(planRepay.getInterest().subtract(interest));
				planRepay.setInterest(planRepay.getInterest().compareTo(BigDecimal.ZERO) > 0 ? planRepay.getInterest() : BigDecimal.ZERO);
			}
		});
		bizplanRepayDataSupportService.updateBatch(planRepayList);
	}
	
	/**
	 * 根据申请Guid查找汇总信息
	 * @param map
	 * @return
	 */
	public BizimportSummaryPo getBizimportSummaryByApplyGuid(String applyGuid) {
		return investApplyDataSupportService.getBizimportSummaryByApplyGuid(applyGuid);
	}
	/**
	 * 根据Guid获取项目申请登记信息
	 * @param applyId
	 * @return
	 */
	public BizImportApply getApplyByApplyGuid(String applyGuid){
		return investApplyDataSupportService.getApplyByApplyGuid(applyGuid);
	}
	
	private String getJudgeSameInvestRecordsKey(BizimportTradeDetail vo) {
		return vo.getIdTypeId() + "_" + vo.getIdNumber() + "_" + vo.getUserRealName() + "_" + DateUtils.formatDateTime(vo.getTradeTime()) + "_" + vo.getTradeMoney();
	}
}
