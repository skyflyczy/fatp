package com.fatp.service.datasupprot.biz;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.biz.BizplanPayinvestDao;
import com.fatp.po.biz.BizplanPayinvestPo;
import com.fatp.util.StringUtil;
import com.huajin.baymax.encrypt.SymmetricEncrypt;
/**
 * 兑付计划
 * @author zhiya.chai
 * @date 2018年6月23日 下午6:03:54
 */
@Service
public class BizplanPayinvestDataSupportService {
	
	@Autowired
	private BizplanPayinvestDao bizplanPayinvestDao;
	/**
	 * 批量插入
	 * @param list
	 */
	public void insertBatch(List<BizplanPayinvestPo> list) {
		if(CollectionUtils.isNotEmpty(list)) {
			encryptStr(list);
			bizplanPayinvestDao.insertBatch(list);
		}
	}
	/**
	 * 加密
	 * @param list
	 */
	private void encryptStr(List<BizplanPayinvestPo> list){
		list.stream().forEach(payinvest ->{
			payinvest.setCardAccount(StringUtil.isBlank(payinvest.getCardAccount()) ? "" : SymmetricEncrypt.encryptStr(payinvest.getCardAccount()));
		});
	}

}
