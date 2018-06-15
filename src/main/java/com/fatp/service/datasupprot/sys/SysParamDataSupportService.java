package com.fatp.service.datasupprot.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fatp.dao.sys.SysParamDao;
import com.fatp.po.sys.SysParamPo;
import com.fatp.util.CacheUtil;
import com.huajin.baymax.memcache.client.MemcachedCache;

/**
 * SysParam
 * @author zhiya.chai
 */
@Service
public class SysParamDataSupportService {

	@Autowired
	private SysParamDao sysParamDao;
	@Autowired
	private MemcachedCache memcachedCache;
	/**
	 * 获取设置参数
	 * @param paramKey
	 * @return
	 */
	public SysParamPo getByParamKey(String paramKey){
		String cacheKey = CacheUtil.CACHEKEY_SYSPARAM + paramKey;
		String str = (String)memcachedCache.get(cacheKey);
		SysParamPo sysParam = null;
		if(StringUtils.isBlank(str)){
			sysParam = sysParamDao.getByParamKey(paramKey);
			if(sysParam != null) {
				memcachedCache.set(cacheKey, JSON.toJSONString(sysParam), CacheUtil.CACHE_EXPIRY_24HOUR);
			}
		}else {
			sysParam = JSON.parseObject(str, SysParamPo.class);
		}
		return sysParamDao.getByParamKey(paramKey);
	}
	
	/**
	 * 获取设置列表
	 * @param map
	 * @return
	 */
	public List<SysParamPo> select(Map<String, Object> map) {
		return sysParamDao.select(map);
	}
}
