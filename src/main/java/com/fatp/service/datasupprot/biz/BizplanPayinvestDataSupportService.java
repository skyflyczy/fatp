package com.fatp.service.datasupprot.biz;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.biz.BizplanPayinvestDao;
import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.enums.YesNo;
import com.fatp.exception.FatpException;
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
	 * 查找兑付计划
	 * @param map
	 * @return
	 */
	public List<BizplanPayinvest> findPlanPayinvest(Map<String, Object> map) {
		map.put("isDelete", YesNo.否.value);
		List<BizplanPayinvest> list = bizplanPayinvestDao.select(map);
		if(CollectionUtils.isNotEmpty(list)) {
			decryptStr(list);
		}
		return list;
	}
	/**
	 * 根据还款计划Id更新兑付状态
	 * @param map
	 */
	public void updatePayinvestStatusByPlanRepayId(Map<String,Object> map) {
		int n = bizplanPayinvestDao.updatePayinvestStatusByPlanRepayId(map);
		if(n <= 0) {
			throw new FatpException("更新兑付状态失败");
		}
	}
	/**
	 * 更新兑付明细删除状态
	 * @param map
	 */
	public void updatePayinvestDeleteStatusByApplyId(Map<String,Object> map) {
		int n =bizplanPayinvestDao.updatePayinvestDeleteStatusByApplyId(map);
		if(n <= 0) {
			throw new FatpException("更新兑付明细删除状态失败");
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
	/**
	 * 解密
	 * @param list
	 */
	private void decryptStr(List<BizplanPayinvest> list) {
		list.stream().forEach(payinvest -> {
			payinvest.setCardAccount(StringUtil.isBlank(payinvest.getCardAccount()) ? "" : SymmetricEncrypt.decryptStr(payinvest.getCardAccount()));
		});
	}

}
