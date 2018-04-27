package com.telecwin.fatp.service.offsite;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.domain.offsite.InvestRecordsResult;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.service.datasupprot.offsite.InvestApplyDataSupportService;

@Service
public class InvestApplyService {

	@Autowired
	private InvestApplyDataSupportService investApplyDataSupportService;
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
	 * @param listing
	 * @param list
	 * @return
	 */
	public InvestRecordsResult assumInvestRecords(ListingComplex listing,List<BizimportTradeDetail> list){
		BigDecimal totalMoney = BigDecimal.ZERO;
		Set<String> idNumberSet = new HashSet<String>();
		Map<String, Integer> duplicateMap = new HashMap<String, Integer>();
		InvestRecordsResult result = new InvestRecordsResult();
		for(int i = 0; i < list.size(); i++){
			BizimportTradeDetail detail = list.get(i);
			totalMoney = totalMoney.add(detail.getTradeMoney());
			idNumberSet.add(detail.getIdNumber() + "_" + detail.getIdTypeId());
			if(detail.getTradeTime().before(listing.getBuyTimeStart()) || detail.getTradeTime().after(listing.getBuyTimeEnd())) {
				result.setOverBuyTime(result.getOverBuyTime()+1);
			}
			if(detail.getTradeMoney().compareTo(listing.getInvestAmountMax()) > 0) {
				result.setOverInvestLimit(result.getOverInvestLimit()+1);
			}
			if(detail.getTradeMoney().compareTo(listing.getInvestAmountMin()) < 0) {
				result.setLessBuy(result.getLessBuy()+1);
			}
			if(detail.getTradeMoney().subtract(listing.getInvestAmountMin()).divideAndRemainder(listing.getInvestAmountAppend())[1].compareTo(BigDecimal.ZERO) !=0){
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
	
	private String getJudgeSameInvestRecordsKey(BizimportTradeDetail vo) {
		return vo.getIdTypeId() + "_" + vo.getIdNumber() + "_" + vo.getUserRealName() + "_" + DateUtils.formatDateTime(vo.getTradeTime()) + "_" + vo.getTradeMoney();
	}
}
