package com.fatp.service.datasupprot.project;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.dao.project.ListingInfoDao;
import com.fatp.dao.project.ListingTradeDao;
import com.fatp.domain.PageData;
import com.fatp.enums.project.ListingStatus;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.po.project.ListingTradePo;
import com.fatp.util.UUIDUtil;
import com.fatp.vo.ListingInfoVo;
import com.fatp.vo.ListingTradeVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 挂牌数据支持服务
 * @author zhiya.chai
 * @date 2018年6月12日 下午3:37:53
 */
@Service
public class ListingInfoDataSupportService {

	@Autowired
	private ListingInfoDao listingInfoDao;
	@Autowired
	private ListingTradeDao listingTradeDao;
	/**
	 * 根据条件分页查找
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<ListingInfoVo> pageFindByCondition(Map<String,Object> map,int pageNo, int pageSize){
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<ListingInfoVo> list = listingInfoDao.pageFindByCondition(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据Guid查找挂牌信息
	 * @param listingGuid
	 * @return
	 */
	public ListingInfoVo getByListingGuid(String listingGuid) {
		return listingInfoDao.getByListingGuid(listingGuid);
	}
	/**
	 * 根据挂牌Id查找挂牌交易信息
	 * @param listingInfoId
	 * @return
	 */
	public List<ListingTradeVo> getTradeByListingInfoId(Integer listingInfoId) {
		return listingTradeDao.getTradeByListingInfoId(listingInfoId);
	}
	/**
	 * 添加信息
	 * @param po
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public ListingInfoPo addListingInfo(ListingInfoVo listingInfoVo){
		ListingInfoPo listingInfoPo = new ListingInfoPo();
		BeanUtils.copyProperties(listingInfoVo, listingInfoPo);
		listingInfoPo.setListingGuid(UUIDUtil.getUUID());
		listingInfoPo.setListingName(listingInfoVo.getListingFullName().trim());
		listingInfoPo.setListingStatus(ListingStatus.正常.status);
		listingInfoDao.insert(listingInfoPo);
		return listingInfoPo;
	}
	/**
	 * 更新挂牌信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public ListingInfoPo updateListingInfo(ListingInfoVo listingInfoVo) {
		ListingInfoPo listingInfoPo = listingInfoDao.getPoById(listingInfoVo.getId());
		if(listingInfoPo == null) {
			//挂牌信息不存在
			throw new FatpException(ErrorCode.LISTING_NOT_EXIST);
		}
		if(listingInfoPo.getVersionNo().intValue() != listingInfoVo.getVersionNo().intValue()) {
			throw new FatpException(ErrorCode.LISTING_ALREADY_UPDATE);
		}
		ListingInfoPo newPo = new ListingInfoPo();
		BeanUtils.copyProperties(listingInfoVo, newPo);
		newPo.setId(listingInfoVo.getId());
		newPo.setListingName(listingInfoVo.getListingFullName());
		newPo.setUpateOperatorId(listingInfoVo.getUpateOperatorId());
		int row = listingInfoDao.updateByVersion(newPo);
		if(row < 1){
			throw new FatpException(ErrorCode.LISTING_SAVE_ERROR);
		}
		return newPo;
	}
	/**
	 * 更新挂牌交易信息
	 * @param listingInfoVo
	 */
	@Transactional(rollbackFor=Exception.class)
	public void updateListingTrade(ListingInfoVo listingInfoVo) {
		List<ListingTradeVo> tradeList = listingInfoVo.getListingTradeList();
		if(CollectionUtils.isEmpty(tradeList)) {
			listingTradeDao.deleteByListingInfoId(listingInfoVo.getId());
			return;
		}
		//获取原有交易信息Id集合
		Set<Integer> tradeIdSet = new HashSet<>();
		//更新交易信息
		tradeList.stream().forEach(trade ->{
			ListingTradePo newPo = new ListingTradePo();
			BeanUtils.copyProperties(trade, newPo);
			newPo.setCreateOperatorId(listingInfoVo.getUpateOperatorId());
			newPo.setUpateOperatorId(listingInfoVo.getUpateOperatorId());
			newPo.setListingInfoId(listingInfoVo.getId());
			newPo.setId(trade.getId());
			newPo.setInvestProfit(newPo.getInvestProfit().divide(new BigDecimal("100"), 5, BigDecimal.ROUND_HALF_UP));
			if(trade.getId() == null) {
				//新插入一条记录
				listingTradeDao.insert(newPo);
			} else {
				//更新记录
				listingTradeDao.update(newPo);
			}
			tradeIdSet.add(newPo.getId());
		});
		//获取原有的，要删除哪些去掉的交易信息
		List<ListingTradePo> tradePoList = listingTradeDao.getPoByListingInfoId(listingInfoVo.getId());
		tradePoList.stream().forEach(po ->{
			//如果不包含更新的，删除掉
			if(!tradeIdSet.contains(po.getId())) {
				listingTradeDao.deleteById(po.getId());
			}
		});
	}
	/**
	 * 删除挂牌信息
	 * @param id
	 * @param versionNo
	 */
	public void deleteListingInfo(int id,int versionNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("versionNo", versionNo);
		map.put("listingStatus", ListingStatus.删除.status);
		int n = listingInfoDao.delete(map);
		if(n < 1) {
			throw new FatpException(ErrorCode.LISTING_DELELT_ERROR);
		}
	}
	
}
