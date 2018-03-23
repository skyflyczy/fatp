package com.telecwin.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.service.AbstractBaseService;
import com.telecwin.fatp.po.sys.SysAppsPo;
import com.telecwin.fatp.service.datasupprot.sys.SysAppsDataSupportService;

/**
 * 
 * SysApps
 * @author auto-generator
 * 2015-06-29 45:13:55
 */
@Service
public class SysAppsService extends AbstractBaseService {
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
