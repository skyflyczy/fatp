package com.fatp.dao.project;

import java.util.List;

import com.fatp.po.project.ListingTradePo;
import com.fatp.vo.ListingTradeVo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 
 * ListingTrade
 * @author zhiya.chai
 * 2018-06-13 56:10:38
 */
@MyBatisDao
public interface ListingTradeDao {
	public int insert(ListingTradePo o);
	
	public int update(ListingTradePo o);
	
	public void deleteById(Integer id);
	public void deleteByListingInfoId(Integer listingInfoId);
	
	public List<ListingTradeVo> getTradeByListingInfoId(Integer listingInfoId);
	
	public List<ListingTradePo> getPoByListingInfoId(Integer listingInfoId);
}
