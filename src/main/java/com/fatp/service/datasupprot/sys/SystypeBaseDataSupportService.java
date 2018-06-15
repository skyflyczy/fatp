package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SystypeBaseDao;
import com.fatp.po.sys.SystypeBasePo;

/**
 * SystypeBase
 * @author zhiya.chai
 * 2016年8月2日 上午9:34:29
 */
@Service
public class SystypeBaseDataSupportService {

	@Autowired
	private SystypeBaseDao systypeBaseDao;
	
	/**
	 * 根据分类获取类型
	 * @param categoryId
	 * @return
	 */
	public List<SystypeBasePo> getSystypeByCategory(int categoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		return systypeBaseDao.select(map);
	}
	

}
