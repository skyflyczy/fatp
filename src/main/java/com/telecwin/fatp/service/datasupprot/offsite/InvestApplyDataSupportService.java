package com.telecwin.fatp.service.datasupprot.offsite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.telecwin.fatp.dao.offsite.InvestApplyDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.InvestApply;

@Service
public class InvestApplyDataSupportService {

	@Autowired
	private InvestApplyDao investApplyDao;
	
	/**
	 * 分页查找可进行登记的挂牌产品列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanRegList(Map<String,Object> map,int pageNo, int pageSize) {
		Page<?> page = PageHelper.startPage(pageNo, pageSize, true);
		List<InvestApply> list = investApplyDao.getCanRegList(map);
		return new PageData<>(page.getTotal(), page.getPages(), list);
	}
	/**
	 * 根据projectGuid查找信息
	 * @param projectGuid
	 * @param exchangeId
	 * @return
	 */
	public InvestApply getCanRegByProjectGuid(String projectGuid,int exchangeId){
		Map<String,Object> map = new HashMap<>();
		map.put("projectGuid", projectGuid);
		map.put("exchangeId", exchangeId);
		List<InvestApply> list = investApplyDao.getCanRegList(map);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
}
