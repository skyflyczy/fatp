package com.telecwin.fatp.service.datasupprot.offsite;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.telecwin.fatp.dao.offsite.InvestApplyDao;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.InvestApply;

@Service
public class InvestApplyDataService {

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
}
