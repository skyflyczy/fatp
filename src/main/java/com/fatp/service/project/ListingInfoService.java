package com.fatp.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.listing.ListingTrade;
import com.fatp.enums.FlowFeedTypeDesc;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
import com.fatp.service.sys.SysbizcodeSequenceService;
import com.fatp.util.StringUtil;

@Service
public class ListingInfoService extends BaseService{

	@Autowired
	private ListingInfoDataSupportService listingInfoDataSupportService;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
	@Autowired
	private TimelineDetailDataSupportService timelineDetailDataSupportService;
	/**
	 * 分页查找信息
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ListingInfo> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize) {
		return listingInfoDataSupportService.pageFindByCondition(map, pageNo, pageSize);
	}
	/**
	 * 根据条件查找挂牌信息
	 * @param map
	 * @return
	 */
	public List<ListingInfo> findByCondition(Map<String,Object> map) {
		return listingInfoDataSupportService.findByCondition(map);
	}
	/**
	 * 根据Guid查找挂牌信息
	 * @param listingGuid
	 * @return
	 */
	public ListingInfo getByListingGuid(String listingGuid) {
		return listingInfoDataSupportService.getByListingGuid(listingGuid);
	}
	/**
	 * 根据Id查找挂牌信息
	 * @param id
	 * @return
	 */
	public ListingInfo getLisingInfoById(Integer id) {
		return listingInfoDataSupportService.getLisingInfoById(id);
	}
	/**
	 * 根据挂牌Id查找挂牌交易信息
	 * @param listingInfoId
	 * @return
	 */
	public List<ListingTrade> getTradeByListingInfoId(Integer listingInfoId) {
		return listingInfoDataSupportService.getTradeByListingInfoId(listingInfoId);
	}
	/**
	 * 更新挂牌信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateListingInfo(ListingInfo listingInfoVo) {
		FlowFeedTypeDesc flowFeedType = FlowFeedTypeDesc.创建;
		ListingInfoPo listingInfoPo = null;
		if(listingInfoVo.getId() == null) {
			//新增
			listingInfoVo.setListingCode(sysbizcodeSequenceService.getListingInfoSequence());
			listingInfoPo = listingInfoDataSupportService.addListingInfo(listingInfoVo);
			listingInfoVo.setId(listingInfoPo.getId());
		} else {
			//更新挂牌信息
			listingInfoPo = listingInfoDataSupportService.updateListingInfo(listingInfoVo);
			flowFeedType = FlowFeedTypeDesc.编辑保存;
		}
		listingInfoVo.setVersionNo(listingInfoPo.getVersionNo());
		//更新交易信息，收益率
		listingInfoDataSupportService.updateListingTrade(listingInfoVo);
		//新增动态
		timelineDetailDataSupportService.createListingInfoTimeLine(listingInfoPo, flowFeedType, "", listingInfoVo.getCreateOperatorName());
	}
	/***
	 * 删除挂牌信息
	 * @param ListingGuid
	 */
	public void deleteListingInfo(String listingGuid) {
		if(StringUtil.isBlank(listingGuid)) {
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		ListingInfo listingVo = listingInfoDataSupportService.getByListingGuid(listingGuid);
		if(listingVo == null) {
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		listingInfoDataSupportService.deleteListingInfo(listingVo.getId(), listingVo.getVersionNo());
	}
	/***
	 * 挂牌产品交易导入
	 * @param fileInfo
	 */

	public String listingRecords(List<ListingInfoPo> listing,String createOperatorName)throws Exception  {

		
		//导入挂牌产品信息
		String result =listingInfoDataSupportService.listingRecords(listing);
		
		//新增动态
		for(ListingInfoPo po : listing) {
			timelineDetailDataSupportService.createListingInfoTimeLine(po, FlowFeedTypeDesc.创建, "", createOperatorName);
		}
		return result;
	}	
}
