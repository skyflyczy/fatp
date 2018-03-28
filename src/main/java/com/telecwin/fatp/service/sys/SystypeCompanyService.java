package com.telecwin.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.po.sys.SystypeCompanyPo;
import com.telecwin.fatp.service.datasupprot.sys.SystypeCompanyDataSupportService;
import com.telecwin.fatp.util.CacheUtil;

/**
 * 
 * SystypeCompany
 */
@Service
public class SystypeCompanyService {
	@Autowired
	private SystypeCompanyDataSupportService systypeCompanyDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SystypeCompanyPo getById(Integer id) {
		String key = CacheUtil.CACHEKEY_SYSTYPECOMPANY + id;
		SystypeCompanyPo o = (SystypeCompanyPo)this.memcachedCache.get(key);
		if(o == null) {
			o = systypeCompanyDataSupportService.getById(id);
			this.memcachedCache.set(key, o, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return o;
	}
	
	public List<SystypeCompanyPo> getAll() {
		return systypeCompanyDataSupportService.select(null);
	}
}
