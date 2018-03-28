package com.telecwin.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.po.sys.SysareaDistrictPo;
import com.telecwin.fatp.service.datasupprot.sys.SysareaDistrictDataSupportService;
import com.telecwin.fatp.util.CacheUtil;

/**
 * 
 * SysareaDistrict
 * @author auto-generator
 * 2015-06-30 17:10:15
 */
@Service
public class SysareaDistrictService{
	@Autowired
	private SysareaDistrictDataSupportService sysareaDistrictDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SysareaDistrictPo getById(Integer disId) {
		String key = CacheUtil.CACHEKEY_SYSAREADISTRICT + disId;
		SysareaDistrictPo o = (SysareaDistrictPo)this.memcachedCache.get(key);
		if(o == null) {
			o = sysareaDistrictDataSupportService.getById(disId);
			this.memcachedCache.set(key, o, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return o;
	}
	
	public List<SysareaDistrictPo> getListByCityId(int cityId) {
		return sysareaDistrictDataSupportService.getListByCityId(cityId);
	}
}
