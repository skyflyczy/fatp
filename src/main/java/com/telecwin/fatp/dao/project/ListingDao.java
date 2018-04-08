package com.telecwin.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.project.ListingComplex;

@MyBatisDao
public interface ListingDao {

	public List<ListingComplex> pageFindByCondition(Map<String,Object> map);
}
