package com.telecwin.fatp.service.offsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.po.offsite.BizimportSummaryPo;
import com.telecwin.fatp.service.datasupprot.offsite.BizimportSummaryDataSupportService;

@Service
public class BizimportSummaryService {

	@Autowired
	private BizimportSummaryDataSupportService bizimportSummaryDataSupportService;
	/**
	 * 根据申请Guid获取总信息
	 * @param applyGuid
	 * @return
	 */
	public BizimportSummaryPo getSummaryByApplyGuid(String applyGuid){
		return bizimportSummaryDataSupportService.getSummaryByApplyGuid(applyGuid);
	}
}
