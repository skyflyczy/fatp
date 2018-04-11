package com.telecwin.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ListingBasePo;

/**
 * 挂牌产品基础信息DAO
 */
@MyBatisDao
public interface ListingBaseDao {

	/**
	 * 新增
	 * @param listingBasePo
	 * @return
	 */
	int insert(ListingBasePo listingBasePo);
	
	/**
	 * 根据版本号更新
	 * @param listingBasePo
	 * @return
	 */
	int updateByVersion(ListingBasePo listingBasePo);
	
	
	int updateProjectLimitByVersion(ListingBasePo listingBasePo);
	
	/**
	 * 根据版本号删除信息
	 * @param map
	 * @return
	 */
	int deleteByVersion(Map<String, Object> map);
	
	
	List<ListingBasePo> select(Map<String, Object> map);
	
	ListingBasePo findById(Integer id);
	
	ListingBasePo findByGuid(String projectGuid);
}
