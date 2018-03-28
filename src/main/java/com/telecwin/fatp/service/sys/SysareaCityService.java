package com.telecwin.fatp.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.po.sys.SysareaCityPo;
import com.telecwin.fatp.service.datasupprot.sys.SysareaCityDataSupportService;
import com.telecwin.fatp.util.CacheUtil;

/**
 * 
 * SysareaCity
 * @author auto-generator
 * 2015-06-30 17:10:10
 */
@Service
public class SysareaCityService{
	@Autowired
	private SysareaCityDataSupportService sysareaCityDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SysareaCityPo getById(Integer cityId) {
		String key = CacheUtil.CACHEKEY_SYSAREACITY + cityId;
		SysareaCityPo o = (SysareaCityPo)this.memcachedCache.get(key);
		if(o == null) {
			o = sysareaCityDataSupportService.getById(cityId);
			this.memcachedCache.set(key, o, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return o;
	}
	/**
	 * 获取银行的城市列表
	 * @return List<SysareaCity>
	 */
	public List<SysareaCityPo> selectBankCity(Map<String,Object> map) {
		return sysareaCityDataSupportService.selectBankCity(map);
	}
	
	public List<SysareaCityPo> getSystypeCitysByProId(int proId) {
		return sysareaCityDataSupportService.getSystypeCitysByProId(proId);
	}
}
