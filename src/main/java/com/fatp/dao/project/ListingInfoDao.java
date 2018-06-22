package com.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.fatp.domain.listing.ListingInfo;
import com.fatp.po.project.ListingInfoPo;
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
	
	public ListingInfo getByListingGuid(String listingGuid);
	
	public ListingInfoPo getPoById(Integer id);
	
	public List<ListingInfo> pageFindByCondition(Map<String, Object> map);
	
	public int listInfoImport(String fileInfo);

	public int importListing(List<ListingInfoPo> listing);

}
