package com.telecwin.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SystypeRepayDao;
import com.telecwin.fatp.po.sys.SystypeRepayPo;

/**
 * SystypeRepay
 */
@Service
public class SystypeRepayDataSupportService {

	@Autowired
	private SystypeRepayDao systypeRepayDao;
	
	public SystypeRepayPo getById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return systypeRepayDao.getById(map);
	}
	
	public List<SystypeRepayPo> select(Map<String, Object> map) {
		return systypeRepayDao.select(map);
	}
}
