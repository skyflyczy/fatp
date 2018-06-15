package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SystypeBasePo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SystypeBaseDataSupportService;

@Service
public class SystypeService extends BaseService {
	
	@Autowired
	private SystypeBaseDataSupportService systypeBaseDataSupportService;
	/**
	 * 根据分类Id获取类型信息
	 * @param categoryId
	 * @return
	 */
	public List<SystypeBasePo> getSystypeByCategory(int categoryId) {
		return systypeBaseDataSupportService.getSystypeByCategory(categoryId);
	}
	
}
