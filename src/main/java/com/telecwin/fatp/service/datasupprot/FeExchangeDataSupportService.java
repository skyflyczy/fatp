package com.telecwin.fatp.service.datasupprot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huajin.baymax.encrypt.SymmetricEncrypt;
import com.telecwin.fatp.dao.FeExchangeDao;
import com.telecwin.fatp.domain.FeExchange;

/**
 * 交易所
 * @author zhiya.chai
 * 2016年7月28日 下午3:37:38
 */
@Service
public class FeExchangeDataSupportService {

	@Autowired
	private FeExchangeDao feExchangeDao;
	
	private void doDecrypt(FeExchange o) {
		o.setCardAccount(StringUtils.isNotBlank(o.getCardAccount()) ? SymmetricEncrypt.decryptStr(o.getCardAccount()) : o.getCardAccount());
		o.setAccountName(StringUtils.isNotBlank(o.getAccountName()) ? SymmetricEncrypt.decryptStr(o.getAccountName()) : o.getAccountName());
		o.setBusinessLicense(StringUtils.isNotBlank(o.getBusinessLicense()) ? SymmetricEncrypt.decryptStr(o.getBusinessLicense()) : o.getBusinessLicense());
		o.setLegalRepName(StringUtils.isNotBlank(o.getLegalRepName()) ? SymmetricEncrypt.decryptStr(o.getLegalRepName()) : o.getLegalRepName());
		o.setLegalRepIdNumber(StringUtils.isNotBlank(o.getLegalRepIdNumber()) ? SymmetricEncrypt.decryptStr(o.getLegalRepIdNumber()) : o.getLegalRepIdNumber());
	}
	
	public List<FeExchange> select(Map<String,Object> map){
		List<FeExchange> list = feExchangeDao.select(map);
		if(CollectionUtils.isNotEmpty(list)){
			for(FeExchange o :list){
				doDecrypt(o);
			}
		}
		return list;
	}
	
	public FeExchange getById(Integer id){
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("id", id);
		FeExchange o = feExchangeDao.getById(map);
		if(o != null){
			doDecrypt(o);
		}
		return o;
	}
}