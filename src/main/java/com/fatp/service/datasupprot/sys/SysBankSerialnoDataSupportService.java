package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysBankSerialnoDao;
import com.fatp.domain.SysBankSerialno;
import com.fatp.enums.YesNo;

/**
 * SysBankSerialno
 */
@Service
public class SysBankSerialnoDataSupportService {

	@Autowired
	private SysBankSerialnoDao sysBankSerialnoDao;
	
	public List<SysBankSerialno> select(Map<String, Object> map){
		return sysBankSerialnoDao.select(map);
	}
	/**
	 * 获取联行号信息
	 * @param bankId
	 * @param cityId
	 * @return
	 */
	public List<SysBankSerialno> getBankSerialnosByBank(Integer bankId, Integer cityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bankId", bankId);
		map.put("cityId", cityId);
		map.put("useEnabled", YesNo.是.value);
		map.put("isOpen", 1);
		return sysBankSerialnoDao.select(map);
	}
	
	public SysBankSerialno getById(Integer id) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sysBankSerialnoDao.getById(map);
	}
	
	public void insert(SysBankSerialno sysBankSerialno){
		sysBankSerialnoDao.insert(sysBankSerialno);
	}
	
	public int update(SysBankSerialno sysBankSerialno){
		return sysBankSerialnoDao.update(sysBankSerialno);
	}
}
