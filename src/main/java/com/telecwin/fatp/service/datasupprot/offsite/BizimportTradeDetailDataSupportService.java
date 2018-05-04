package com.telecwin.fatp.service.datasupprot.offsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.offsite.BizimportTradeDetailDao;

@Service
public class BizimportTradeDetailDataSupportService {

	@Autowired
	private BizimportTradeDetailDao bizimportTradeDetailDao;
	
	public void deleteByApplyId(Integer applyId) {
		bizimportTradeDetailDao.deleteByApplyId(applyId);
	}
}
