package com.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysAppsDao;
import com.fatp.po.sys.SysAppsPo;

@Service
public class SysAppsDataSupportService {
	
	@Autowired
	private SysAppsDao sysAppsDao;

	public List<SysAppsPo> select(Map<String, Object> map) {
		return sysAppsDao.select(map);
	}
}
