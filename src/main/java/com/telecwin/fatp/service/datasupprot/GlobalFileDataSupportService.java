package com.telecwin.fatp.service.datasupprot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.GlobalFileDao;
import com.telecwin.fatp.po.GlobalFilePo;

@Service
public class GlobalFileDataSupportService {

	@Autowired
	private GlobalFileDao globalFileDao;
	
	public GlobalFilePo getGlobalFileById(int id) {
		return globalFileDao.getGlobalFileById(id);
	}
}
