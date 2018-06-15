package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SysareaProvincePo;
import com.fatp.service.datasupprot.sys.SysareaProvinceDataSupportService;
import com.fatp.util.CacheUtil;
import com.huajin.baymax.memcache.client.MemcachedCache;

/**
 * SysareaProvince
 */
@Service
public class SysareaProvinceService{
	@Autowired
	private SysareaProvinceDataSupportService sysareaProvinceDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SysareaProvincePo getById(Integer proId) {
		String key = CacheUtil.CACHEKEY_SYSAREAPROVINCE + proId;
		SysareaProvincePo o = (SysareaProvincePo)this.memcachedCache.get(key);
		if(o == null) {
			o = sysareaProvinceDataSupportService.getById(proId);
			this.memcachedCache.set(key, o, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return o;
	}
	
	public List<SysareaProvincePo> getAll(){
		return sysareaProvinceDataSupportService.select(null);
	}
}
