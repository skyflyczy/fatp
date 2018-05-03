package com.telecwin.fatp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.po.GlobalFilePo;
import com.telecwin.fatp.service.datasupprot.GlobalFileDataSupportService;

@Service
public class GlobalFileService {

	@Autowired
	private GlobalFileDataSupportService globalFileDataSupportService;
	
	public GlobalFilePo getGlobalFileById(int id) {
		return globalFileDataSupportService.getGlobalFileById(id);
	}
}
