package com.telecwin.fatp.service.datasupprot.offsite;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.offsite.BizimportApplyDao;
import com.telecwin.fatp.enums.offsite.ApplyStatus;
import com.telecwin.fatp.enums.offsite.ApplyType;
import com.telecwin.fatp.po.offsite.BizimportApplyPo;

@Service
public class BizimportApplyDataSupportService {

	@Autowired
	private BizimportApplyDao bizimportApplyDao;
	
	public BizimportApplyPo getApplyByGuid(String applyGuid){
		return bizimportApplyDao.getApplyByGuid(applyGuid);
	}
	
	public BizimportApplyPo getApplyByGuidForUpdate(String applyGuid){
		return bizimportApplyDao.getApplyByGuid(applyGuid);
	}
	/**
	 * 获取未提交审核投资申请
	 * @param projectId
	 * @param exchangeId
	 * @return
	 */
	public BizimportApplyPo getUnSubmitInvestApplyByProject(int projectId) {
		Map<String,Object> map = new HashMap<>();
		map.put("projectId", projectId);
		map.put("applyStatus", ApplyStatus.待提交.status);
		map.put("applyType", ApplyType.投资登记申请.type);
		return bizimportApplyDao.getUnSubmitInvestApplyByProject(map);
	}
}
