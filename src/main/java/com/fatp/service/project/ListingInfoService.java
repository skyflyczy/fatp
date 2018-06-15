package com.fatp.service.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.domain.PageData;
import com.fatp.enums.FlowFeedTypeDesc;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.TimelineDetailDataSupportService;
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
import com.fatp.service.sys.SysbizcodeSequenceService;
import com.fatp.util.StringUtil;
import com.fatp.vo.ListingInfoVo;
import com.fatp.vo.ListingTradeVo;

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
	public PageData<ListingInfoVo> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize) {
		return listingInfoDataSupportService.pageFindByCondition(map, pageNo, pageSize);
	}
	/**
	 * 根据Guid查找挂牌信息
	 * @param listingGuid
	 * @return
	 */
	public ListingInfoVo getByListingGuid(String listingGuid) {
		return listingInfoDataSupportService.getByListingGuid(listingGuid);
	}
	/**
	 * 根据挂牌Id查找挂牌交易信息
	 * @param listingInfoId
	 * @return
	 */
	public List<ListingTradeVo> getTradeByListingInfoId(Integer listingInfoId) {
		return listingInfoDataSupportService.getTradeByListingInfoId(listingInfoId);
	}
	/**
	 * 更新挂牌信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateListingInfo(ListingInfoVo listingInfoVo) {
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
		ListingInfoVo listingVo = listingInfoDataSupportService.getByListingGuid(listingGuid);
		if(listingVo == null) {
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		listingInfoDataSupportService.deleteListingInfo(listingVo.getId(), listingVo.getVersionNo());
	}
	
}
