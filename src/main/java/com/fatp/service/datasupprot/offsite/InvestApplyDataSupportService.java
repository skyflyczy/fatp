package com.fatp.service.datasupprot.offsite;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.dao.offsite.BizimportApplyDao;
import com.fatp.dao.offsite.BizimportSummaryDao;
import com.fatp.dao.offsite.BizimportTradeDetailDao;
import com.fatp.dao.offsite.InvestApplyDao;
import com.fatp.domain.PageData;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.enums.offsite.ApplyType;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.offsite.BizimportSummaryPo;
import com.fatp.util.DateUtil;
import com.fatp.util.UUIDUtil;
import com.fatp.vo.InvestApplyVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;

@Service
public class InvestApplyDataSupportService {

	@Autowired
	private InvestApplyDao investApplyDao;
	@Autowired
	private BizimportApplyDao bizimportApplyDao;
	@Autowired
	private BizimportSummaryDao bizimportSummaryDao;
	@Autowired
	private BizimportTradeDetailDao bizimportTradeDetailDao;
	
	/**
	 * 获取可申请的挂牌集合
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApplyVo> getCanApplyListingList(Map<String,Object> map, int pageNo, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<InvestApplyVo> list = investApplyDao.getCanApplyListingList(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 插入投资明细登记申请
	 * @param param
	 * @param projectMemeberId
	 * @param applyMemberId
	 * @param operatorId
	 * @param status
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public BizimportApplyPo insertBizimportApplyForInvest(int listingInfoId, int applyMemberId,int operatorId,String valueDate) {
		BizimportApplyPo po = new BizimportApplyPo();
		po.setApplyMemberId(applyMemberId);
		po.setApplyOperatorId(operatorId);
		po.setApplyStatus(ApplyStatus.登记成功.status);
		po.setApplyType(ApplyType.投资登记申请.type);
		po.setListingInfoId(listingInfoId);
		po.setCreateTime(new Date());
		po.setUpdateTime(new Date());
		po.setValueDate(DateUtil.convertDate(valueDate, "yyyy-MM-dd"));
		po.setApplyGuid(UUIDUtil.getUUID());
		int id =bizimportApplyDao.insert(po);
		if(id <= 0) {
			throw new FatpException(ErrorCode.LISTING_INVESTRECORDS_IMPORT_FAIL);
		}
		return po;
	}
	/**
	 * 插入投资明细汇总信息
	 * @param bizImportApplyId
	 * @param globalFileId
	 * @param detailList
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public BizimportSummaryPo insertBizimportSummaryForInvest(Integer bizImportApplyId, Integer globalFileId,List<BizimportTradeDetail> detailList) {
		BizimportSummaryPo summary = new BizimportSummaryPo();
		Date startDate = null;// 开始日期
		Date endDate = null;// 结束日期
		BigDecimal totalMoney = BigDecimal.ZERO;// 总金额
		Set<String> sellerNum = new HashSet<String>();// 卖方人数
		Set<String> buyerNum = new HashSet<String>();// 买方人数
		for (BizimportTradeDetail detail : detailList) {
			if (startDate == null || startDate.compareTo(detail.getTradeTime()) <= 0) {
				startDate = detail.getTradeTime();
			}
			if (endDate == null || endDate.compareTo(detail.getTradeTime()) >= 0) {
				endDate = detail.getTradeTime();
			}
			totalMoney = totalMoney.add(detail.getTradeMoney());
			buyerNum.add(detail.getIdNumber() + "_" + detail.getIdTypeId());
		}
		summary.setBizImportApplyId(bizImportApplyId);
		summary.setBuyerNum(buyerNum.size());
		summary.setEndDate(endDate);
		summary.setStartDate(startDate);
		summary.setGlobalFileId(globalFileId);
		summary.setSellerNum(sellerNum.size());
		summary.setBuyerNum(buyerNum.size());
		summary.setTotalMoney(totalMoney);
		summary.setTotalNum(detailList.size());
		summary.setCreateOperatorId(bizImportApplyId);
		int id = bizimportSummaryDao.insert(summary);
		if(id <= 0) {
			throw new FatpException(ErrorCode.LISTING_INVESTRECORDS_IMPORT_FAIL);
		}
		return summary;
	}
	/**
	 * 插入交易明细
	 * @param apply
	 * @param summary
	 * @param detailList
	 */
	@Transactional(rollbackFor = Exception.class)
	public void insertTradeDetails(BizimportApplyPo apply,BizimportSummaryPo summary,List<BizimportTradeDetail> detailList) {
		if(CollectionUtils.isEmpty(detailList)) {
			return;
		}
		for(BizimportTradeDetail detail : detailList) {
			detail.setBizImportApplyId(apply.getId());
			detail.setBizImportSummaryId(summary.getId());
			detail.setListingInfoId(apply.getListingInfoId());
		}
		try {
			bizimportTradeDetailDao.insertBatch(detailList);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "insertTradeDetails", e));
			throw new FatpException("投资明细中流水号重复。");
		}
	}
	
}
