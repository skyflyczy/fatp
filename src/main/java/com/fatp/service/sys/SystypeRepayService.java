package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SystypeRepayPo;
import com.fatp.service.datasupprot.sys.SystypeRepayDataSupportService;

/**
 * 
 * SystypeRepay
 */
@Service
public class SystypeRepayService{
	@Autowired
	private SystypeRepayDataSupportService systypeRepayDataSupportService;
	
	public SystypeRepayPo getById(Integer id) {
		return systypeRepayDataSupportService.getById(id);
	}
	
	public List<SystypeRepayPo> findAll() {
		return  systypeRepayDataSupportService.select(null);
	}
	
}
