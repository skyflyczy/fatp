package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SystypeIndustryPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SystypeIndustryDataSupportService;
import com.fatp.util.CacheUtil;
import com.huajin.baymax.memcache.client.MemcachedCache;

/**
 * 
 * SystypeIndustry
 * @author auto-generator
 * 2015-06-30 40:09:00
 */
@Service
public class SystypeIndustryService extends BaseService {
	@Autowired
	private SystypeIndustryDataSupportService systypeIndustryDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SystypeIndustryPo getById(Integer id) {
		String key = CacheUtil.CACHEKEY_SYSTYPEINDUSTRY + id;
		SystypeIndustryPo o = (SystypeIndustryPo)this.memcachedCache.get(key);
		if(o == null) {
			o = systypeIndustryDataSupportService.getById(id);
			this.memcachedCache.set(key, o, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return o;
	}
	
	public List<SystypeIndustryPo> getAll() {
		return systypeIndustryDataSupportService.select(null);
	}
}
