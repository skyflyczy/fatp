package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SystypeProjectDao;
import com.fatp.po.sys.SystypeProjectPo;

@Service
public class SystypeProjectDataSupportService {

	@Autowired
	private SystypeProjectDao systypeProjectDao;
	
	public List<SystypeProjectPo> findByProductTypeId(int productTypeId) {
		Map<String, Object> map = new HashMap<>(1);
		map.put("productTypeId", productTypeId);
		return systypeProjectDao.select(map);
	}
}
