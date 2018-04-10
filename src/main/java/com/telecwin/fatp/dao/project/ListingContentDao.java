package com.telecwin.fatp.dao.project;

import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ListingContentPo;

/**
 * 
 * ListingContent
 */
@MyBatisDao
public interface ListingContentDao {
	int insert(ListingContentPo listingContentPo);
	
	int updateContentByProjectId(ListingContentPo listingContentPo);
	
	void deleteContentByProjectId(Map<String,Object> map);
	
	ListingContentPo findContentByProjectId(Map<String,Object> map);
	
}
