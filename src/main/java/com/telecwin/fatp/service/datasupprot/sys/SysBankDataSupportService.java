package com.telecwin.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SysBankDao;
import com.telecwin.fatp.po.sys.SysBankPo;

/**
 * SysBank
 */
@Service
public class SysBankDataSupportService {
	
	@Autowired
	private SysBankDao sysBankDao;
	
	public SysBankPo getById(Integer id) {
		return sysBankDao.getById(id);
	}
	
	public List<SysBankPo> select(Map<String,Object> map){
		
		return sysBankDao.select(map);
	}
	
	public int addBank(SysBankPo sysBank) {
		return sysBankDao.insert(sysBank);
	}
	
	public int updateBank(SysBankPo sysBank) {
		return sysBankDao.update(sysBank);
	}
	
}
