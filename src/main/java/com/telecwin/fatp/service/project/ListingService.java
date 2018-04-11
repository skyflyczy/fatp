package com.telecwin.fatp.service.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecwin.fatp.controller.param.ListingCheckParam;
import com.telecwin.fatp.convert.ListingConvertor;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.domain.project.ListingSaleagent;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.enums.FlowFeedTypeDesc;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ListingBasePo;
import com.telecwin.fatp.po.project.ListingClearingPo;
import com.telecwin.fatp.po.project.ListingContentPo;
import com.telecwin.fatp.po.project.ListingSaleagentPo;
import com.telecwin.fatp.po.project.ListingTradePo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.telecwin.fatp.service.datasupprot.project.ListingDataSupportService;
import com.telecwin.fatp.service.sys.SysbizcodeSequenceService;
import com.telecwin.fatp.service.user.UcUserService;

@Service
public class ListingService extends BaseService{

	@Autowired
	private ListingDataSupportService listingDataSupportService;
	@Autowired
	private ProjectRecordService projectRecordService;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	@Autowired
	private UcUserService ucUserService;
	/**
	 * 分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ListingComplex> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize){
		return listingDataSupportService.pageFindByCondition(map, pageNo, pageSize);
	}
	/**
	 * 新增挂牌信息
	 * @param infoVo
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addListing(ListingComplex infoVo){
		//设置备案信息
		setRecordData(infoVo);
		//新增挂牌基本信息
		ListingBasePo listingBasePo = ListingConvertor.convertListingBasePo(infoVo);
		listingBasePo.setProjectCode(sysbizcodeSequenceService.getProjectSequence());
		listingBasePo.setProjectSettleCode(sysbizcodeSequenceService.getProjectSettleCode());
		int listingId = listingDataSupportService.addListingBase(listingBasePo);
		//新增挂牌交易信息
		ListingTradePo listingTradePo = ListingConvertor.convertListingTradePo(infoVo);
		listingDataSupportService.addListingTrade(listingTradePo,listingId);
		//新增挂牌结算信息
		ListingClearingPo listingClearingPo = ListingConvertor.convertListingClearingPo(infoVo);
		listingDataSupportService.addListingClear(listingClearingPo,listingId,listingBasePo.getProductTypeId());
		//新增项目内容
		ListingContentPo contentPo = ListingConvertor.convertListingContentPo(infoVo);
		listingDataSupportService.addListingContent(contentPo, listingId);
		//新增动态
		timelineDetailDataSupportService.createListingTimeLine(listingBasePo, FlowFeedTypeDesc.创建, "", infoVo.getCreateOperatorName());
		return listingId;
	}
	/**
	 * 更新挂牌信息
	 * @param infoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateListing(ListingComplex infoVo) {
		//设置备案信息
		setRecordData(infoVo);
		//补充承销商信息
		fillSaleAgent(infoVo);
		//更新基本信息
		ListingBasePo listingBasePo = listingDataSupportService.updateListingBase(infoVo);
		//更新交易信息
		listingDataSupportService.updateListingTrade(infoVo);
		//更新结算信息
		listingDataSupportService.updateListingClear(infoVo);
		//更新挂牌内容信息
		ListingContentPo contentPo = ListingConvertor.convertListingContentPo(infoVo);
		listingDataSupportService.updateContentByProjectId(contentPo, infoVo.getId());
		//更新承销机构信息
		List<ListingSaleagentPo> saleAgentList = ListingConvertor.convertSaleagentList(infoVo);
		listingDataSupportService.handleSaleagent(infoVo.getId(),infoVo.getProductTypeId(),saleAgentList);
		//新增动态
		timelineDetailDataSupportService.createListingTimeLine(listingBasePo, FlowFeedTypeDesc.编辑保存, "", infoVo.getCreateOperatorName());
	}
	/**
	 * 挂牌产品审核
	 * @param param
	 * @param listingStatus
	 * @param exchangeId
	 */
	@Transactional(rollbackFor=Exception.class)
	public void checkListing(ListingCheckParam param,ListingStatusDesc listingStatus){
		//验证挂牌产品信息
		ListingBasePo listing = validForCheck(param.getId());
		//更新挂牌状态
		listing.setProjectStatus(listingStatus.value);
		listing.setAuditOperatorId(param.getOperatorId());
		listing.setAuditRemark(param.getAuditRemark());
		listing.setAuditTime(new Date());
		listing.setValueDateChangeStyle(param.getValueDateChangeStyle());
		listing.setExpireDateChangeStyle(param.getExpireDateChangeStyle());
		listingDataSupportService.updateBasePoByVersion(listing);
		
		//更新结算信息
		ListingClearingPo clearPo = listingDataSupportService.getClearPoByProjectId(listing.getId());
		clearPo.setMultipleRelease(param.getMultipleRelease());
		clearPo.setMaxReleaseNum(param.getMaxReleaseNum());
		listingDataSupportService.updateClearPoByVersion(clearPo);
		
		//TODO 暂时不用设置，这个是在认购时使用的，设置默认的承销信息
//		if(listingStatus == ListingStatusDesc.待发布 
//				&& listing.getProductTypeId().intValue() != ProductTypeDesc.定向投资工具.value){
//			listingBaseRootService.addDefaultSaleAgent(listingVo);
//		}
		//新增动态
		FlowFeedTypeDesc flowFeed = FlowFeedTypeDesc.审核通过;
		if(listingStatus == ListingStatusDesc.审核不通过) {
			flowFeed = FlowFeedTypeDesc.审核不通过;
		} else if(listingStatus == ListingStatusDesc.审核退回) {
			flowFeed = FlowFeedTypeDesc.审核退回;
		}
		timelineDetailDataSupportService.createListingTimeLine(listing, flowFeed, param.getAuditRemark(), param.getOperatorName());
	}
	/**
	 * 审核时验证信息
	 * @param id
	 * @param exchangeId
	 * @return
	 */
	private ListingBasePo validForCheck(Integer id){
		if(id == null) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		ListingBasePo listing = listingDataSupportService.findBasePoById(id);
		if(listing == null) {
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		if(listing.getProjectStatus().intValue() != ListingStatusDesc.待审核.value) {
			throw new FatpException(ErrorCode.LISTING_STATUS_ERROR);
		}
		return listing;
	}
	/**
	 * 补充承销商信息
	 * @param infoVo
	 */
	private void fillSaleAgent(ListingComplex infoVo) {
		List<ListingSaleagent> projectSaleagent = infoVo.getProjectSaleagent();
		if(CollectionUtils.isEmpty(projectSaleagent)) {
			return;
		}
		List<ListingSaleagent> filteredAgent = new ArrayList<>();
		for(ListingSaleagent agent : projectSaleagent) {
			if(agent.formDataAllEmpty()) {
				continue;
			}
			if(agent.getSaleMemberId() == null) {
				UcUser user = ucUserService.getUserByCompanyName(agent.getSaleMemberName(), infoVo.getExchangeId());
				if(user == null) {
					throw new FatpException(agent.getSaleMemberName() + "不存在。");
				}
				agent.setSaleMemberId(user.getId());
			}
			agent.setProjectId(infoVo.getId());
			agent.setCreateOperatorId(infoVo.getCreateOperatorId());
			agent.setUpdateOperatorId(infoVo.getUpateOperatorId());
			filteredAgent.add(agent);
		}
		infoVo.setProjectSaleagent(filteredAgent);
	}
	
	/**
	 * 设置备案信息
	 * @param infoVo
	 */
	private void setRecordData(ListingComplex infoVo) {
		if(infoVo.getProjectRecordId() == null) {
			return;
		}
		ProjectRecordinfo record = projectRecordService.getById(infoVo.getProjectRecordId());
		infoVo.setProjectTypeId(record.getProjectTypeId());
		infoVo.setLoanUserId(record.getLoanUserId());
		infoVo.setProjectUnitPrice(record.getProjectUnitPrice());
		infoVo.setProjectUsing(record.getProjectUsing());
		infoVo.setIsGuarantee(record.getIsGuarantee());
		infoVo.setIsPledge(record.getIsPledge());
		if(infoVo.getId() == null) {
			infoVo.setInvestProfit(record.getInvestProfit());
			infoVo.setProjectLimit(record.getProjectLimit());
			infoVo.setProjectLimitTypeId(record.getProjectLimitTypeId());
			infoVo.setRepayTypeId(record.getRepayTypeId());
		}
	}
	/**
	 * 获取挂牌详细信息
	 * @param id
	 * @param exchangeId
	 * @return
	 */
	public ListingComplex getListingDetailsById(int id,int exchangeId) {
		return listingDataSupportService.getListingDetailsById(id, exchangeId);
	}
	/**
	 * 获取挂牌详细信息
	 * @param guid
	 * @param exchangeId
	 * @return
	 */
	public ListingComplex getListingDetailsByGuid(String guid,int exchangeId) {
		return listingDataSupportService.getListingDetailsByGuid(guid, exchangeId);
	}
	
	/**
	 * 获取项目的承销列表，不包括无承销人的部分
	 * @param projectId
	 * @return
	 */
	public List<ListingSaleagent> findByProjectIdExcludeNoAgent(int projectId) {
		return listingDataSupportService.findByProjectIdExcludeNoAgent(projectId);
	}
	
	/**
	 * 获取内容信息
	 * @param listingId
	 * @return
	 */
	public ListingContentPo findContentByListingId(int listingId){
		return listingDataSupportService.findContentByListingId(listingId);
	}
}
