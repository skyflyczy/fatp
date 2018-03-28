package com.telecwin.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.sys.SysareaDistrictDao;
import com.telecwin.fatp.po.sys.SysareaDistrictPo;

/**
 * SysareaDistrict
 * @author zhiya.chai
 * 2016年8月1日 下午5:31:53
 */
@Service
public class SysareaDistrictDataSupportService {

	@Autowired
	private SysareaDistrictDao sysareaDistrictDao;
	
	public SysareaDistrictPo getById(Integer disId){
		return sysareaDistrictDao.getById(disId);
	}
	
	public List<SysareaDistrictPo> select(Map<String, Object> map){
		return sysareaDistrictDao.select(map);
	}
	
	public List<SysareaDistrictPo> getListByCityId(int cityId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityId", cityId);
		return sysareaDistrictDao.select(map);
	}
}
