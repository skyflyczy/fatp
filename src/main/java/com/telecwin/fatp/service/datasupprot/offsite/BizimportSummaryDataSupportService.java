package com.telecwin.fatp.service.datasupprot.offsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.offsite.BizimportSummaryDao;
import com.telecwin.fatp.po.offsite.BizimportSummaryPo;

@Service
public class BizimportSummaryDataSupportService {

	@Autowired
	private BizimportSummaryDao bizimportSummaryDao;
	
	public BizimportSummaryPo getSummaryByApplyGuid(String applyGuid){
		return bizimportSummaryDao.getSummaryByApplyGuid(applyGuid);
	}
}
