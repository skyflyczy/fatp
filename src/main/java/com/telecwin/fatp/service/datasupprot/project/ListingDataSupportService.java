package com.telecwin.fatp.service.datasupprot.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.project.ListingDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.project.ListingComplex;

@Service
public class ListingDataSupportService {

	@Autowired
	private ListingDao listingDao;
	
	public PageData<ListingComplex> pageFindByCondition(){
		return null;
	}
}
