package com.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.fatp.po.project.ListingInfoPo;
import com.fatp.vo.ListingInfoVo;
import com.huajin.baymax.db.annotation.MyBatisDao;

/**
 * 挂牌产品
 * @author zhiya.chai
 * 2018-06-12 19:15:10
 */
@MyBatisDao
public interface ListingInfoDao {
	
	public int insert(ListingInfoPo o);
	
	public int updateByVersion(ListingInfoPo o);
	
	public int delete(Map<String,Object> map);
	
	public ListingInfoVo getByListingGuid(String listingGuid);
	
	public ListingInfoPo getPoById(Integer id);
	
	public List<ListingInfoVo> pageFindByCondition(Map<String, Object> map);
}
