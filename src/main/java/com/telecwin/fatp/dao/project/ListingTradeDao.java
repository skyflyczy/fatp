package com.telecwin.fatp.dao.project;

import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ListingTradePo;

/**
 * 
 * ListingTrade
 */
@MyBatisDao
public interface ListingTradeDao {
	/**
	 * 插入
	 * @param listingTradePo
	 * @return
	 */
	int insert(ListingTradePo listingTradePo);
	
	/**
	 * 根据版本号更新
	 * @param listingTradePo
	 * @return
	 */
	int updateByVersion(ListingTradePo listingTradePo);
	
	/**
	 * 根据版本号删除
	 * @param map
	 * @return
	 */
	int deleteByVersion(Map<String, Object> map);
	
	ListingTradePo findByProjectId(Integer projectId);
}
