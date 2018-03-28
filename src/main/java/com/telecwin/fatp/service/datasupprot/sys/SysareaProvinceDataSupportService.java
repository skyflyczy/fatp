package com.telecwin.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SysareaProvinceDao;
import com.telecwin.fatp.po.sys.SysareaProvincePo;

/**
 * SysareaProvince
 */
@Service
public class SysareaProvinceDataSupportService {

	@Autowired
	private SysareaProvinceDao sysareaProvinceDao;
	
	public SysareaProvincePo getById(Integer proId){
		return sysareaProvinceDao.getById(proId);
	}
	
	public List<SysareaProvincePo> select(Map<String, Object> map){
		return sysareaProvinceDao.select(map);
	}
}
