package com.telecwin.fatp.service.datasupprot.project;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.telecwin.fatp.convert.ListingConvertor;
import com.telecwin.fatp.dao.project.ListingBaseDao;
import com.telecwin.fatp.dao.project.ListingClearingDao;
import com.telecwin.fatp.dao.project.ListingContentDao;
import com.telecwin.fatp.dao.project.ListingDao;
import com.telecwin.fatp.dao.project.ListingSaleagentDao;
import com.telecwin.fatp.dao.project.ListingTradeDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.domain.project.ListingSaleagent;
import com.telecwin.fatp.enums.project.InterestBaseType;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.enums.project.ProjectLimitType;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ListingBasePo;
import com.telecwin.fatp.po.project.ListingClearingPo;
import com.telecwin.fatp.po.project.ListingContentPo;
import com.telecwin.fatp.po.project.ListingSaleagentPo;
import com.telecwin.fatp.po.project.ListingTradePo;
import com.telecwin.fatp.util.UUIDUtil;

@Service
public class ListingDataSupportService {

	@Autowired
	private ListingDao listingDao;
	@Autowired
	private ListingBaseDao listingBaseDao;
	@Autowired
	private ListingTradeDao listingTradeDao;
	@Autowired
	private ListingClearingDao listingClearingDao;
	@Autowired
	private ListingContentDao listingContentDao;
	@Autowired
	private ListingSaleagentDao listingSaleagentDao;
	
	public static final int INTEREST_BASE_DAYS_DEBT = 360;
	public static final int INTEREST_BASE_DAYS_INCOME = 365;
	/**
	 * 分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ListingComplex> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize){
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<ListingComplex> list = listingDao.pageFindByCondition(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 设置挂牌基础信息
	 * @param po
	 * @return
	 */
	public int addListingBase(ListingBasePo po){
		if (po == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		po.setProjectStatus(ListingStatusDesc.待提交.value);
		po.setProjectGuid(UUIDUtil.getUUID());
		po.setProjectFullName(po.getProjectFullName().trim());
		po.setProjectMoney(BigDecimal.ZERO);
		po.setProjectAmountMin(BigDecimal.ZERO);
		po.setProjectLimit(1);
		po.setProjectLimitTypeId(ProjectLimitType.天.type);
		listingBaseDao.insert(po);
		return po.getId();
	}
	
	/**
	 * 更新基本信息
	 * @param listingBasePo
	 * @return
	 */
	public ListingBasePo updateListingBase(ListingComplex infoVo) {
		if(infoVo == null || infoVo.getId() == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		ListingBasePo basePo = listingBaseDao.findById(infoVo.getId());
		//TODO 暂时去掉应该没有用
		//checkReceiveAccount(basePo,infoVo);
		ListingBasePo listingBasePo = ListingConvertor.convertListingBasePo(infoVo);
		listingBasePo.setProjectFullName(listingBasePo.getProjectFullName().trim());
		listingBasePo.setProjectCost(infoVo.getInvestProfit().divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP));
		if(listingBasePo.getProjectStatus() == null){
			listingBasePo.setProjectStatus(ListingStatusDesc.待提交.value);
		}
		listingBasePo.setVersionNo(basePo.getVersionNo());
		int row = listingBaseDao.updateByVersion(listingBasePo);
		if(row < 1){
			throw new FatpException(ErrorCode.LISTING_SAVE_ERROR);
		}
		return listingBasePo;
	}
	
	/**
	 * 新增挂牌交易信息
	 * @param listingTradePo
	 * @param listId
	 * @return
	 */
	public int addListingTrade(ListingTradePo listingTradePo, int listId) {
		if(listingTradePo == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		listingTradePo.setProjectId(listId);
		listingTradeDao.insert(listingTradePo);
		return listingTradePo.getId();
	}
	/**
	 * 设置信息并且更新
	 * @param infoVo
	 * @return
	 */
	public void updateListingTrade(ListingComplex infoVo) {
		if(infoVo == null || infoVo.getId() == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		//根据listingId获取交易信息
		ListingTradePo tradePo = listingTradeDao.findByProjectId(infoVo.getId());
		//获取挂牌交易信息
		ListingTradePo listingTradePo = ListingConvertor.convertListingTradePo(infoVo);
		listingTradePo.setId(tradePo.getId());
		listingTradePo.setProjectId(tradePo.getProjectId());
		listingTradePo.setInvestProfit(infoVo.getInvestProfit().divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP));
		listingTradePo.setVersionNo(tradePo.getVersionNo());
		int row = listingTradeDao.updateByVersion(listingTradePo);
		if(row < 1){
			throw new FatpException(ErrorCode.LISTING_SAVE_ERROR);
		}
	}
	
	/**
	 * 设置信息且插入结算信息
	 * @param listingClearingPo
	 * @param listId
	 * @param productTypeId
	 * @return
	 */
	public int addListingClear(ListingClearingPo listingClearingPo, int listId, Integer productTypeId){
		if(listingClearingPo == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		listingClearingPo.setProjectId(listId);
		if(ProductTypeDesc.定向融资计划.value == productTypeId) {
			listingClearingPo.setInterestBaseDays(INTEREST_BASE_DAYS_DEBT);
		} else {
			listingClearingPo.setInterestBaseDays(INTEREST_BASE_DAYS_INCOME);
		}
		listingClearingPo.setInterestBaseType(InterestBaseType.Custom.type);
		listingClearingDao.insert(listingClearingPo);
		return listingClearingPo.getId();
	}
	/**
	 * 设置信息并更新信息
	 * @param infoVo
	 * @return
	 */
	public void updateListingClear(ListingComplex infoVo) {
		if(infoVo == null || infoVo.getId() == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		BigDecimal bigDecimal100 = new BigDecimal("100");
		ListingClearingPo po = listingClearingDao.findByProjectId(infoVo.getId());
		ListingClearingPo listingClearingPo = ListingConvertor.convertListingClearingPo(infoVo);
		listingClearingPo.setId(po.getId());
		listingClearingPo.setProjectId(po.getProjectId());
		listingClearingPo.setAdvioserFee(infoVo.getAdvioserFee().divide(bigDecimal100, 5, BigDecimal.ROUND_HALF_UP));
		listingClearingPo.setOverduePayFee(infoVo.getOverduePayFee().divide(bigDecimal100, 8, BigDecimal.ROUND_HALF_UP));
		listingClearingPo.setPlatformFee(infoVo.getPlatformFee().divide(bigDecimal100, 5, BigDecimal.ROUND_HALF_UP));
		BigDecimal investAdvioserFee = infoVo.getInvestAdvioserFee();
		if (investAdvioserFee == null) {
			investAdvioserFee = BigDecimal.ZERO;
		}
		listingClearingPo.setInvestAdvioserFee(investAdvioserFee.divide(bigDecimal100, 5,BigDecimal.ROUND_HALF_UP));
		listingClearingPo.setVersionNo(po.getVersionNo());
		int row = listingClearingDao.updateByVersion(listingClearingPo);
		if(row < 1){
			throw new FatpException(ErrorCode.LISTING_SAVE_ERROR);
		}
	}
	/**
	 * 增加挂牌信息内容表数据
	 * @param listingContent
	 * @param listingId
	 * @return
	 */
	public int addListingContent(ListingContentPo listingContent,int listingId) {
		listingContent.setProjectId(listingId);
		listingContentDao.insert(listingContent);
		return listingContent.getId();
	}
	/**
	 * 根据项目Id更新挂牌信息内容表数据
	 * @param listingContent
	 * @param listingId
	 * @return
	 */
	public int updateContentByProjectId(ListingContentPo listingContent,int listingId) {
		listingContent.setProjectId(listingId);
		return listingContentDao.updateContentByProjectId(listingContent);
	}
	/**
	 * 获取详细信息
	 * @param id
	 * @param exchangeId
	 * @return
	 */
	public ListingComplex getListingDetailsById(int id,int exchangeId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("exchangeId", exchangeId);
		map.put("id", id);
		return listingDao.getListingDetailsById(map);
	}
	/**
	 * 获取项目的承销列表，不包括无承销人的部分
	 * @param projectId
	 * @return
	 */
	public List<ListingSaleagent> findByProjectIdExcludeNoAgent(int projectId) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("projectId", projectId);
		map.put("memberIdNotEqual", 0);
		return listingSaleagentDao.select(map);
	}
	/**
	 * 获取内容信息
	 * @param listingId
	 * @return
	 */
	public ListingContentPo findContentByListingId(int listingId){
		Map<String,Object> map = new HashMap<>();
		map.put("projectId", listingId);
		return listingContentDao.findContentByProjectId(map);
	}
	
	/**
	 * 处理承销机构信息
	 * @param id
	 * @param productTypeId
	 * @param saleAgentList
	 */
	public void handleSaleagent(Integer listingId, Integer productTypeId, List<ListingSaleagentPo> saleAgentList) {
		if(listingId == null || productTypeId == null || productTypeId == ProductTypeDesc.定向投资工具.value){
			return;
		}
		Map<String, Object> map = new HashMap<>(1);
		map.put("projectId", listingId);
		List<ListingSaleagent> dbList = listingSaleagentDao.select(map);
		Set<Integer> updateIds = new HashSet<>();
		for(ListingSaleagentPo saleAgent : saleAgentList){
			if(saleAgent.getId() == null){
				listingSaleagentDao.insert(saleAgent);
			}else{
				listingSaleagentDao.update(saleAgent);
				updateIds.add(saleAgent.getId());
			}
		}
		for(ListingSaleagent agent : dbList){
			if(!updateIds.contains(agent.getId())){
				listingSaleagentDao.delete(agent.getMemberId(),agent.getId());
			}
		}
	}
}
