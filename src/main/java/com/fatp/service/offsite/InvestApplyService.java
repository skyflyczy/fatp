package com.fatp.service.offsite;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.controller.param.InvestRecordsParam;
import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.domain.offsite.InvestApply;
import com.fatp.domain.offsite.InvestRecordsResult;
import com.fatp.enums.FlowFeedTypeDesc;
import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.GlobalFilePo;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.offsite.BizimportSummaryPo;
import com.fatp.service.GlobalFileService;
import com.fatp.service.ImportFileService;
import com.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
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
		//TODO 异步执行生成还款兑付计划
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
	
	private String getJudgeSameInvestRecordsKey(BizimportTradeDetail vo) {
		return vo.getIdTypeId() + "_" + vo.getIdNumber() + "_" + vo.getUserRealName() + "_" + DateUtils.formatDateTime(vo.getTradeTime()) + "_" + vo.getTradeMoney();
	}
}
