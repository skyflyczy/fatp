package com.telecwin.fatp.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.project.ListingDataSupportService;

@Service
public class ListingService extends BaseService{

	@Autowired
	private ListingDataSupportService listingDataSupportService;
	
}
