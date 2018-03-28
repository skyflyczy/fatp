package com.telecwin.fatp.service.sys;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.exception.ExceptionThrowUtil;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.telecwin.fatp.domain.SysBankSerialno;
import com.telecwin.fatp.service.BaseService;
import com.telecwin.fatp.service.datasupprot.sys.SysBankSerialnoDataSupportService;
import com.telecwin.fatp.util.CacheUtil;

/**
 * SysBankSerialno
 */
@Service
public class SysBankSerialnoService extends BaseService {
	@Autowired
	private SysBankSerialnoDataSupportService sysBankSerialnoDataSupportService;
	@Autowired
	private MemcachedCache memcachedCache;
	
	public SysBankSerialno getById(Integer id) {
		String key = CacheUtil.CACHEKEY_SYSBANKSERIALNO + id;
		SysBankSerialno bankSerialno = (SysBankSerialno)memcachedCache.get(key);
		if(bankSerialno == null){
			bankSerialno = sysBankSerialnoDataSupportService.getById(id);
			memcachedCache.set(key, bankSerialno, CacheUtil.CACHE_EXPIRY_24HOUR);
		}
		return bankSerialno;
	}

	/**
	 * 获取联行号信息
	 * @param bankId
	 * @param cityId
	 * @return
	 */
	public List<SysBankSerialno> getBankSerialnosByBank(Integer bankId, Integer cityId) {
		return sysBankSerialnoDataSupportService.getBankSerialnosByBank(bankId, cityId);
	}
	/**
	 * 新增联行号
	 * @return DataResponse
	 */
	public void addSerinalno(SysBankSerialno sysBankSerialno) {
		if(sysBankSerialno == null || sysBankSerialno.getBankId() == null || sysBankSerialno.getProvinceId() == null
				||sysBankSerialno.getCityId() == null || StringUtils.isBlank(sysBankSerialno.getSubBankName()) 
				|| StringUtils.isBlank(sysBankSerialno.getSubBankNo())){
			throw ExceptionThrowUtil.emptyParameterException(null);
		}
		sysBankSerialno.setOriginBankName(sysBankSerialno.getSubBankName());
		sysBankSerialnoDataSupportService.insert(sysBankSerialno);
	}

	/**
	 * 更新联行号
	 * @return DataResponse
	 */
	public int updateSerinalno(SysBankSerialno sysBankSerialno) {
		if(sysBankSerialno == null ||sysBankSerialno.getId() == null || sysBankSerialno.getBankId() == null || sysBankSerialno.getProvinceId() == null
				||sysBankSerialno.getCityId() == null || StringUtils.isBlank(sysBankSerialno.getSubBankName()) 
				|| StringUtils.isBlank(sysBankSerialno.getSubBankNo())){
			throw ExceptionThrowUtil.emptyParameterException(null);
		}
		sysBankSerialno.setOriginBankName(sysBankSerialno.getSubBankName());
		return sysBankSerialnoDataSupportService.update(sysBankSerialno);
	}
}
