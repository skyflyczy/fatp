package com.telecwin.fatp.dao.offsite;

import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.offsite.BizimportApplyPo;

@MyBatisDao
public interface BizimportApplyDao {

	public int insert(BizimportApplyPo po);
	
	public int update(BizimportApplyPo po);
	
	public BizimportApplyPo getApplyByGuid(String applyGuid);
	
	public BizimportApplyPo getApplyByGuidForUpdate(String applyGuid);
	
	public BizimportApplyPo getUnSubmitInvestApplyByProject(Map<String,Object> map);
}
