package com.fatp.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.po.sys.SystypeProjectPo;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.sys.SystypeProjectDataSupportService;

@Service
public class SystypeProjectService extends BaseService{
	
	@Autowired
	private SystypeProjectDataSupportService systypeProjectDataSupportService;
	
	/**
	 * 根据产品ID查找项目类型
	 * @param productTypeId
	 * @return
	 */
	public List<SystypeProjectPo> findByProductTypeId(int productTypeId){
		return systypeProjectDataSupportService.findByProductTypeId(productTypeId);
	}

}
