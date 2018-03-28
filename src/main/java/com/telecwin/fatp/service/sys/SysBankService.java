package com.telecwin.fatp.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.enums.YesNo;
import com.telecwin.fatp.po.sys.SysBankPo;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.sys.SysBankDataSupportService;
import com.telecwin.fatp.util.CacheUtil;

/**
 * SysBankchannel
 */
@Service
public class SysBankService extends BaseService {
	@Autowired
	private SysBankDataSupportService sysBankDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	/**
	 * 获取默认银行卡列表
	 * @return
	 */
	public List<SysBankPo> getDefaultBankList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortColumns", "ShowIndex");
		map.put("noShowNull", "1");
		map.put("useEnabled", YesNo.是.value);
		return getBankList(map);
	}
	/**
	 * 获取银行列表
	 * @param map
	 * @return
	 */
	public List<SysBankPo> getBankList(Map<String,Object> map) {
		String cacheKey = CacheUtil.CACHEKEY_SYSBANK + JSON.toJSONString(map);
		String str = (String)memcachedCache.get(cacheKey);
		List<SysBankPo> list = new ArrayList<SysBankPo>();
		if(StringUtils.isBlank(str)){
			list = sysBankDataSupportService.select(map);
			if(CollectionUtils.isNotEmpty(list)) {
				memcachedCache.set(cacheKey, JSON.toJSONString(list), CacheUtil.CACHE_EXPIRY_1HOUR);
			}
		}else {
			list = JSON.parseArray(str, SysBankPo.class);
		}
		return list;
	}
}
