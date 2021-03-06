package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.FeExchange;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.FeExchangeDataSupportService;

/**
 * 
 * FeExchange
 */
@Service
public class FeExchangeService extends BaseService {
	
	@Autowired
	private FeExchangeDataSupportService feExchangeDataSupportService;
	
	/**
	 * 获取默认信息
	 * @return
	 */
	public FeExchange getDefaultExchangeInfo(){
		List<FeExchange> list = feExchangeDataSupportService.select(null);
		return list.get(0);
	}
	
	public FeExchange getExchangeInfo(int id) {
		return feExchangeDataSupportService.getById(id);
	}
	
	
	
	
	
}
