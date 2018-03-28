package com.telecwin.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SystypeCompanyDao;
import com.telecwin.fatp.po.sys.SystypeCompanyPo;

/**
 * SystypeCompany
 */
@Service
public class SystypeCompanyDataSupportService {

	@Autowired
	private SystypeCompanyDao systypeCompanyDao;
	
	public SystypeCompanyPo getById(Integer id) {
		return systypeCompanyDao.getById(id);
	}

	public List<SystypeCompanyPo> select(Map<String, Object> map) {
		return systypeCompanyDao.select(map);
	}
}
