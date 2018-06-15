package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SysAppsPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SysAppsDataSupportService;

/**
 * SysApps
 */
@Service
public class SysAppsService extends BaseService {
	@Autowired
	private SysAppsDataSupportService sysAppsDataSupportService;
	
	/**
	 *  获取应用列表
	 * @return
	 * @author zhiya.chai
	 */
	public List<SysAppsPo> getAppList() {
		return sysAppsDataSupportService.select(null);
	}
}
