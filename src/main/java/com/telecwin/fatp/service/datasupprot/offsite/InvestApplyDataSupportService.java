package com.telecwin.fatp.service.datasupprot.offsite;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.param.InvestRecordsParam;
import com.telecwin.fatp.dao.offsite.BizimportApplyDao;
import com.telecwin.fatp.dao.offsite.BizimportSummaryDao;
import com.telecwin.fatp.dao.offsite.BizimportTradeDetailDao;
import com.telecwin.fatp.dao.offsite.InvestApplyDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.enums.offsite.ApplyStatus;
import com.telecwin.fatp.enums.offsite.ApplyType;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.offsite.BizimportApplyPo;
import com.telecwin.fatp.po.offsite.BizimportSummaryPo;
import com.telecwin.fatp.util.DateUtil;
import com.telecwin.fatp.util.UUIDUtil;

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
	 * 分页查找可进行登记的挂牌产品列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanRegList(Map<String,Object> map,int pageNo, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<InvestApply> list = investApplyDao.getCanRegList(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据projectGuid查找信息
	 * @param projectGuid
	 * @param exchangeId
	 * @return
	 */
	public InvestApply getCanRegByProjectGuid(String projectGuid,int exchangeId){
		Map<String,Object> map = new HashMap<>();
		map.put("projectGuid", projectGuid);
		map.put("exchangeId", exchangeId);
		List<InvestApply> list = investApplyDao.getCanRegList(map);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
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
	public BizimportApplyPo insertBizimportApplyForInvest(InvestRecordsParam param,int projectMemeberId,int applyMemberId,int operatorId,ApplyStatus status) {
		BizimportApplyPo po = new BizimportApplyPo();
		po.setApplyMemberId(applyMemberId);
		po.setMemberId(projectMemeberId);
		po.setApplyOperatorId(operatorId);
		po.setApplySource((short)0);
		po.setApplyStatus(status.status);
		po.setApplyType(ApplyType.投资登记申请.type);
		po.setProjectId(param.getProjectId());
		po.setCreateTime(new Date());
		po.setUpdateTime(new Date());
		po.setValueDate(DateUtil.convertDate(param.getValueDate(), "yyyy-MM-dd"));
		po.setApplyGuid(UUIDUtil.getUUID());
		if(status == ApplyStatus.登记中) {
			po.setSubmitTime(new Date());
		}
		int id =bizimportApplyDao.insert(po);
		if(id <= 0) {
			throw new FatpException(ErrorCode.LISTING_INVESTRECORDS_IMPORT_FAIL);
		}
		return po;
	}
	/**
	 * 更新投资明细申请
	 * @param po
	 * @param valueDate
	 * @param operatorId
	 * @param status
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public BizimportApplyPo updateBizimportApplyForInvest(BizimportApplyPo po,String valueDate,int operatorId,ApplyStatus status) {
		po.setApplyOperatorId(operatorId);
		po.setApplyStatus(status.status);
		po.setUpdateTime(new Date());
		po.setValueDate(DateUtil.convertDate(valueDate, "yyyy-MM-dd"));
		po.setApplyGuid(UUIDUtil.getUUID());
		if(status == ApplyStatus.登记中) {
			po.setSubmitTime(new Date());
		}
		int id =bizimportApplyDao.update(po);
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
			detail.setBizImportFileSummaryId(summary.getId());
			detail.setProjectId(apply.getProjectId());
		}
		try {
			bizimportTradeDetailDao.insertBatch(detailList);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "insertTradeDetails", e));
			throw new FatpException("投资明细中流水号重复。");
		}
	}
	
}
