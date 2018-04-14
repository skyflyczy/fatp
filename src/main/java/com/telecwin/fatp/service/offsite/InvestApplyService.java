package com.telecwin.fatp.service.offsite;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.service.datasupprot.offsite.InvestApplyDataService;

@Service
public class InvestApplyService {

	@Autowired
	private InvestApplyDataService investApplyDataService;
	/**
	 * 分页查找可进行登记的挂牌产品列表
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageData<InvestApply> getCanRegList(Map<String,Object> map,int pageNo, int pageSize) {
		return investApplyDataService.getCanRegList(map, pageNo, pageSize);
	}
}
