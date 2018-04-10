package com.telecwin.fatp.dao.project;

import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ListingClearingPo;

/**
 * ListingClearing
 */
@MyBatisDao
public interface ListingClearingDao {
	/**
	 * 新增
	 * @param listingClearingPo
	 * @return
	 */
	int insert(ListingClearingPo listingClearingPo);
	
	/**
	 * 根据版本号更新
	 * @param listingClearingPo
	 * @return
	 */
	int updateByVersion(ListingClearingPo listingClearingPo);
	
	/**
	 * 根据版本号删除挂牌结算信息
	 * @param map
	 * @return
	 */
	int deleteByVersion(Map<String, Object> map);
	
	ListingClearingPo findByProjectId(Integer projectId);

}
