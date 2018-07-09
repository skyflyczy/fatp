package com.fatp.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatp.dao.offsite.BizimportSummaryDao;
import com.fatp.exception.FatpException;
import com.fatp.po.GlobalFilePo;
import com.fatp.po.offsite.BizimportSummaryPo;
import com.fatp.service.datasupprot.GlobalFileDataSupportService;

@Service
public class DemoService {
	@Autowired
	private GlobalFileDataSupportService globalFileDataSupportService;
	@Autowired
	private BizimportSummaryDao bizimportSummaryDao;
	@Transactional(rollbackFor=Exception.class)
	public void testTransactional(){
			test();
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void test() {
		GlobalFilePo po = new GlobalFilePo();
		po.setContentType("1111");
		po.setCreateOperatorId(555);
		po.setCreateUserId(555);
		po.setDisplayName("dis");
		po.setFileGuid("1123123123");
		po.setFilePath("sdfasdfasdf");
		po.setOriginalFileName("qweqwe");
		int id = globalFileDataSupportService.insertGlobalFile(po);
		BizimportSummaryPo spo = new BizimportSummaryPo();
		spo.setBizImportApplyId(1);
		spo.setBuyerNum(111);
		spo.setCreateOperatorId(555);
		spo.setEndDate(new Date());
		spo.setGlobalFileId(id);
		spo.setSellerNum(123);
		spo.setStartDate(new Date());
		spo.setTotalMoney(new BigDecimal("100000"));
		spo.setTotalNum(111);
		bizimportSummaryDao.insert(spo);
		throw new FatpException("增加失败，事务回滚");
	}
}
