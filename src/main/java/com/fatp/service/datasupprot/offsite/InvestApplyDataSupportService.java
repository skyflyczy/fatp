package com.fatp.service.datasupprot.offsite;

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

import com.fatp.dao.offsite.BizimportApplyDao;
import com.fatp.dao.offsite.BizimportSummaryDao;
import com.fatp.dao.offsite.BizimportTradeDetailDao;
import com.fatp.dao.offsite.InvestApplyDao;
import com.fatp.dao.user.UcUserDao;
import com.fatp.dao.user.UcUserPubinfoDao;
import com.fatp.domain.PageData;
import com.fatp.domain.UcUser;
import com.fatp.domain.offsite.BizImportApply;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.domain.offsite.InvestApply;
import com.fatp.enums.offsite.ApplyStatus;
import com.fatp.enums.offsite.ApplyType;
import com.fatp.enums.user.IdTypeDesc;
import com.fatp.enums.user.OrgType;
import com.fatp.enums.user.UserIdentityDesc;
import com.fatp.enums.user.UserRegisterSource;
import com.fatp.enums.user.UserStatusDesc;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.offsite.BizimportApplyPo;
import com.fatp.po.offsite.BizimportSummaryPo;
import com.fatp.po.user.UcUserPubinfoPo;
import com.fatp.service.sys.SysbizcodeSequenceService;
import com.fatp.util.DateUtil;
import com.fatp.util.StringUtil;
import com.fatp.util.UUIDUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huajin.baymax.encrypt.SymmetricEncrypt;

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
	@Autowired
	private UcUserDao ucUserDao;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private UcUserPubinfoDao ucUserPubinfoDao;
	
	/**
	 * 获取可申请的挂牌集合
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanApplyListingList(Map<String,Object> map, int pageNo, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<InvestApply> list = investApplyDao.getCanApplyListingList(map);
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
		po.setApplyStatus(ApplyStatus.登记中.status);
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
			//敏感数据加密
			detail.setPhoneNumber(StringUtil.isBlank(detail.getPhoneNumber()) ? "" : SymmetricEncrypt.encryptStr(detail.getPhoneNumber()));
			detail.setIdNumber(StringUtil.isBlank(detail.getIdNumber()) ? "" : SymmetricEncrypt.encryptStr(detail.getIdNumber()));
			detail.setCardAccount(StringUtil.isBlank(detail.getCardAccount()) ? "" : SymmetricEncrypt.encryptStr(detail.getCardAccount()));
			//加密后插入用户信息
			handleTradeDetailUser(detail);
		}
		bizimportTradeDetailDao.insertBatch(detailList);
	}
	/**
	 * 插入用户信息
	 * @param detail
	 */
	private void handleTradeDetailUser(BizimportTradeDetail detail){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exchangeId", detail.getExchangeId());
		UcUser user = null;
		if(detail.getIdTypeId().intValue() == IdTypeDesc.统一社会信用代码.getIdType()) {
			//机构
			map.put("companyOrgCode", detail.getIdNumber());
			List<UcUser> list = ucUserDao.select(map);
			if(CollectionUtils.isNotEmpty(list)) {
				for(UcUser u : list) {
					if(u.getCompanyName().equals(detail.getUserRealName())) {
						user = u;
						break;
					}
				}
			}
		} else {
			map.put("idNumber",detail.getIdNumber());
			map.put("idTypeId", detail.getIdTypeId());
			List<UcUser> list = ucUserDao.select(map);
			if(CollectionUtils.isNotEmpty(list)) {
				user = list.get(0);
				if(!user.getRealName().equals(detail.getUserRealName())) {
					throw new FatpException("客户" + detail.getUserRealName() + "与系统中登记的不符，请检查。");
				}
			}
		}
		if (user == null) {
			//新增用户
			detail.setIsNewUser(true);
			user = new UcUser();
			user.setPhone(detail.getPhoneNumber());
			user.setUserGuid(UUIDUtil.getUUID());
			user.setExchangeId(detail.getExchangeId());
			user.setUserIdentity(UserIdentityDesc.投资者.value);
			user.setUserStatus(UserStatusDesc.待激活.value);
			user.setUserCode(this.sysbizcodeSequenceService.getCustomerSequence());
			user.setOwnerUserId(0);//TODO  现在先统一为0
			user.setRealName(detail.getUserRealName());
			user.setUserName(detail.getUserRealName());
			user.setRegisterSource(UserRegisterSource.交易明细导入.getValue());
			user.setIdTypeId(detail.getIdTypeId());
			user.setIdNumber(detail.getIdNumber());
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setIsSendSms(false);
			if(user.getIdTypeId() == IdTypeDesc.统一社会信用代码.getIdType()) {
				user.setOrgTypeId(OrgType.法人机构.type);
				UcUserPubinfoPo pubinfo = ucUserPubinfoDao.getByCompanyOrgCode(detail.getIdNumber());
				if(pubinfo == null) {
					pubinfo = ucUserPubinfoDao.getByCompanyNameEqual(detail.getUserRealName());
					if(pubinfo != null) {
						throw new FatpException("客户" + detail.getUserRealName() + "与系统中登记的不符，请检查。");
					}
					pubinfo = new UcUserPubinfoPo();
					pubinfo.setCompanyGuid(UUIDUtil.getUUID());
					pubinfo.setCompanyOrgCode(detail.getIdNumber());
					pubinfo.setCompanyName(detail.getUserRealName());
					this.ucUserPubinfoDao.insertPub(pubinfo);
				}
				user.setCompanyInfoId(pubinfo.getId());
			} else {
				user.setOrgTypeId(OrgType.自然人.type);
			}
			ucUserDao.insert(user);
			ucUserDao.insertExt(user);
		} else {
			detail.setIsNewUser(false);
		}
		detail.setUserId(user.getId());
	}
	/**
	 * 获取挂牌产品登记成功的列表
	 * @param listingInfoId
	 * @return
	 */
	public List<BizImportApply> getListingApplyList(int listingInfoId) {
		return investApplyDao.getListingApplyList(listingInfoId);
	}
	/**
	 * 分页查找交易明细
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<BizimportTradeDetail> pageFindTradeDetail(Map<String,Object> map, int pageNo, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<BizimportTradeDetail> list = bizimportTradeDetailDao.pageFindTradeDetail(map);
		decryptStr(list);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据申请Id查找相应的交易明细
	 * @param bizImportApplyId
	 * @return
	 */
	public List<BizimportTradeDetail> findTradeDetailByApplyId(int bizImportApplyId) {
		List<BizimportTradeDetail> list = bizimportTradeDetailDao.findTradeDetailByApplyId(bizImportApplyId);
		decryptStr(list);
		return list;
	}
	/**
	 * 根据Id获取项目申请登记信息
	 * @param applyId
	 * @return
	 */
	public BizImportApply getApplyById(int applyId) {
		return bizimportApplyDao.getApplyById(applyId);
	}
	/**
	 * 更新登记状态
	 * @param po
	 * @return
	 */
	public int updateApplyStatus(BizimportApplyPo po) {
		return bizimportApplyDao.updateApplyStatus(po);
	}
	/**
	 * 根据申请Guid查找汇总信息
	 * @param map
	 * @return
	 */
	public BizimportSummaryPo getBizimportSummaryByApplyGuid(String applyGuid) {
		return bizimportSummaryDao.getBizimportSummaryByApplyGuid(applyGuid);
	}
	/**
	 * 解密
	 * @param list
	 */
	private void decryptStr(List<BizimportTradeDetail> list){
		if(CollectionUtils.isNotEmpty(list)) {
			//解密
			list.stream().forEach(detail ->{
				detail.setPhoneNumber(StringUtil.isBlank(detail.getPhoneNumber()) ? "" : SymmetricEncrypt.decryptStr(detail.getPhoneNumber()));
				detail.setIdNumber(StringUtil.isBlank(detail.getIdNumber()) ? "" : SymmetricEncrypt.decryptStr(detail.getIdNumber()));
				detail.setCardAccount(StringUtil.isBlank(detail.getCardAccount()) ? "" : SymmetricEncrypt.decryptStr(detail.getCardAccount()));
			});
		}
	}
}
