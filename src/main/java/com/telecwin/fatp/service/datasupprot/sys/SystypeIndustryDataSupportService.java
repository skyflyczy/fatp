package com.telecwin.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SystypeIndustryDao;
import com.telecwin.fatp.po.sys.SystypeIndustryPo;

/**
 * SystypeIndustry
 */
@Service
public class SystypeIndustryDataSupportService {

	@Autowired
	private SystypeIndustryDao systypeIndustryDao;
	
	public SystypeIndustryPo getById(Integer id){
		return systypeIndustryDao.getById(id);
	}
	
	public List<SystypeIndustryPo> select(Map<String, Object> map){
		return systypeIndustryDao.select(map);
	}
}
