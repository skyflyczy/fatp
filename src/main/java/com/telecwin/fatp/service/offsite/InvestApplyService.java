package com.telecwin.fatp.service.offsite;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.controller.param.InvestRecordsParam;
import com.telecwin.fatp.domain.GlobalFile;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.domain.offsite.InvestRecordsResult;
import com.telecwin.fatp.enums.FlowFeedTypeDesc;
import com.telecwin.fatp.enums.offsite.ApplyStatus;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.GlobalFilePo;
import com.telecwin.fatp.po.offsite.BizimportApplyPo;
import com.telecwin.fatp.po.offsite.BizimportSummaryPo;
import com.telecwin.fatp.po.project.ListingBasePo;
import com.telecwin.fatp.service.GlobalFileService;
import com.telecwin.fatp.service.ImportFileService;
import com.telecwin.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.telecwin.fatp.service.datasupprot.offsite.BizimportApplyDataSupportService;
import com.telecwin.fatp.service.datasupprot.offsite.BizimportSummaryDataSupportService;
import com.telecwin.fatp.service.datasupprot.offsite.BizimportTradeDetailDataSupportService;
import com.telecwin.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;
import com.telecwin.fatp.service.datasupprot.project.ListingDataSupportService;

@Service
public class InvestApplyService {

	@Autowired
	private InvestApplyDataSupportService investApplyDataSupportService;
	@Autowired
	private ImportFileService importFileService;
	@Autowired
	private ListingDataSupportService listingDataSupportService;
	@Autowired
	private GlobalFileService globalFileService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	@Autowired
	private BizimportApplyDataSupportService bizimportApplyDataSupportService;
	@Autowired
	private BizimportTradeDetailDataSupportService bizimportTradeDetailDataSupportService;
	@Autowired
	private BizimportSummaryDataSupportService bizimportSummaryDataSupportService;
	
	/**
	 * 分页查找可进行登记的挂牌产品列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanRegList(Map<String,Object> map,int pageNo, int pageSize) {
		return investApplyDataSupportService.getCanRegList(map, pageNo, pageSize);
	}
	
	public InvestApply getCanRegByProjectGuid(String projectGuid,int exchangeId) {
		return investApplyDataSupportService.getCanRegByProjectGuid(projectGuid, exchangeId);
	}
	/**
	 * 是否可以投资明细登记
	 * @param listingStatus
	 */
	public boolean canInvestApply(int listingStatus) {
		return listingStatus == ListingStatusDesc.认购中.value 
				|| listingStatus == ListingStatusDesc.认购结束.value 
				|| listingStatus == ListingStatusDesc.发行成功.value;
	}
	/**
	 * 组装上传的投资明细
	 * @param buyTimeStart
	 * @param buyTimeEnd
	 * @param investAmountMax
	 * @param investAmountMin
	 * @param investAmountAppend
	 * @param list
	 * @return
	 */
	public InvestRecordsResult assumInvestRecords(Date buyTimeStart,Date buyTimeEnd,BigDecimal investAmountMax,
			BigDecimal investAmountMin, BigDecimal investAmountAppend,List<BizimportTradeDetail> list){
		BigDecimal totalMoney = BigDecimal.ZERO;
		Set<String> idNumberSet = new HashSet<String>();
		Map<String, Integer> duplicateMap = new HashMap<String, Integer>();
		InvestRecordsResult result = new InvestRecordsResult();
		for(int i = 0; i < list.size(); i++){
			BizimportTradeDetail detail = list.get(i);
			totalMoney = totalMoney.add(detail.getTradeMoney());
			idNumberSet.add(detail.getIdNumber() + "_" + detail.getIdTypeId());
			if(detail.getTradeTime().before(buyTimeStart) || detail.getTradeTime().after(buyTimeEnd)) {
				result.setOverBuyTime(result.getOverBuyTime()+1);
			}
			if(detail.getTradeMoney().compareTo(investAmountMax) > 0) {
				result.setOverInvestLimit(result.getOverInvestLimit()+1);
			}
			if(detail.getTradeMoney().compareTo(investAmountMin) < 0) {
				result.setLessBuy(result.getLessBuy()+1);
			}
			if(detail.getTradeMoney().subtract(investAmountMin).divideAndRemainder(investAmountAppend)[1].compareTo(BigDecimal.ZERO) !=0){
				result.setNotEqAppend(result.getNotEqAppend() + 1);
			}
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
	public void addInvestRecords(InvestRecordsParam param,int memberId,int exchangeId,int operatorId,String operatorName,ApplyStatus status) throws IOException{
		//1、查找项目
		ListingBasePo listingBase = listingDataSupportService.findBaseByIdForUpdate(param.getProjectId());
		if(listingBase == null) {
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		boolean canApply = canInvestApply(listingBase.getProjectStatus().intValue());
		if(!canApply) {
			throw new FatpException("此挂牌产品状态不正确，不能进行投资明细登记。");
		}
		//2、检验是否有此项目的未提交审核的登记申请
		BizimportApplyPo unSubmitApply =  bizimportApplyDataSupportService.getUnSubmitInvestApplyByProject(param.getProjectId());
		if(unSubmitApply != null) {
			throw new FatpException("此挂牌产品投资明细登记信息有变更，请刷新页面重新登记。");
		}
		//3、解析文件内容
		String filePath = param.getExcelFilePath() + File.separator + param.getLinkFileName();
		List<BizimportTradeDetail> detailList = importFileService.readInvestRecordsForFile(filePath, listingBase.getProjectName(), listingBase.getProjectCode(), memberId, exchangeId);
		//4、生成申请信息 bizimport_apply
		BizimportApplyPo apply = investApplyDataSupportService.insertBizimportApplyForInvest(param, listingBase.getMemberId(), memberId, operatorId, status);
		//5、生成文件信息 global_file
		GlobalFilePo globalFile = globalFileService.insertGlobalFile(filePath, param.getOriginalFileName(), memberId, operatorId);
		//6、生成汇总信息 bizimport_summary
		BizimportSummaryPo summary = investApplyDataSupportService.insertBizimportSummaryForInvest(apply.getId(), globalFile.getId(), detailList);
		//7、导入交易明细信息 bizimport_trade_detail
		investApplyDataSupportService.insertTradeDetails(apply, summary, detailList);
		//8、写入动态
		FlowFeedTypeDesc flowFeedTypeDesc = status == ApplyStatus.待提交 ? FlowFeedTypeDesc.保存投资明细申请 : FlowFeedTypeDesc.投资明细申请提交审核;
		timelineDetailDataSupportService.createInvestRecordsTimeLine(apply, flowFeedTypeDesc, "", operatorName);
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
		BizimportApplyPo apply = bizimportApplyDataSupportService.getApplyByGuidForUpdate(param.getApplyGuid());
		if(apply == null) {
			throw new FatpException("此申请不存在。");
		}
		if(apply.getApplyStatus() != ApplyStatus.待提交.status) {
			throw new FatpException("此申请已经提交审核或已审核，不允许更改。");
		}
		//2、解析文件
		ListingBasePo listingBase = listingDataSupportService.findBasePoById(apply.getProjectId());
		String filePath = param.getExcelFilePath() + File.separator + param.getLinkFileName();
		List<BizimportTradeDetail> detailList = importFileService.readInvestRecordsForFile(filePath, listingBase.getProjectName(), listingBase.getProjectCode(), memberId, exchangeId);
		//3、获取汇总信息
		BizimportSummaryPo summary = bizimportSummaryDataSupportService.getSummaryByApplyGuid(apply.getApplyGuid());
		//4、获取文件信息
		GlobalFile globalFile = globalFileService.getGlobalFileById(summary.getGlobalFileId());
		//5、删除原有的投资明细
		bizimportTradeDetailDataSupportService.deleteByApplyId(apply.getId());
		//6、删除汇总信息
		bizimportSummaryDataSupportService.deleteById(summary.getId());
		//7、删除文件信息
		globalFileService.deleteGlobalFileById(globalFile.getId());
		//8、更新登记信息
		investApplyDataSupportService.updateBizimportApplyForInvest(apply, param.getValueDate(), operatorId, status);
		//9、生成文件信息 global_file
		GlobalFilePo newGlobalFile = globalFileService.insertGlobalFile(filePath, param.getOriginalFileName(), memberId, operatorId);
		//10、生成汇总信息 bizimport_summary
		BizimportSummaryPo newSummary = investApplyDataSupportService.insertBizimportSummaryForInvest(apply.getId(), newGlobalFile.getId(), detailList);
		//11、导入交易明细信息 bizimport_trade_detail
		investApplyDataSupportService.insertTradeDetails(apply, newSummary, detailList);
		//12、删除原来文件
		File file = new File(globalFile.getFilePath());
		if(file.exists()) {
			file.delete();
		}
		//13、写入动态
		FlowFeedTypeDesc flowFeedTypeDesc = status == ApplyStatus.待提交 ? FlowFeedTypeDesc.保存投资明细申请 : FlowFeedTypeDesc.投资明细申请提交审核;
		timelineDetailDataSupportService.createInvestRecordsTimeLine(apply, flowFeedTypeDesc, "", operatorName);
	}
	
	private String getJudgeSameInvestRecordsKey(BizimportTradeDetail vo) {
		return vo.getIdTypeId() + "_" + vo.getIdNumber() + "_" + vo.getUserRealName() + "_" + DateUtils.formatDateTime(vo.getTradeTime()) + "_" + vo.getTradeMoney();
	}
}
