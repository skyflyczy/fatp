package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysareaCityDao;
import com.fatp.po.sys.SysareaCityPo;

/**
 * SysareaCity
 * @author zhiya.chai
 * 2016年8月1日 下午4:58:57
 */
@Service
public class SysareaCityDataSupportService {

	@Autowired
	private SysareaCityDao sysareaCityDao;
	
	public SysareaCityPo getById(Integer cityId) {
		return sysareaCityDao.getById(cityId);
	}
	
	public List<SysareaCityPo> select(Map<String,Object> map){
		return sysareaCityDao.select(map);
	}
	
	public List<SysareaCityPo> selectBankCity(Map<String,Object> map){
		return sysareaCityDao.selectBankCity(map);
	}
	/**
	 * 根据省份Id获取城市列表
	 * @param proId
	 * @return
	 */
	public List<SysareaCityPo> getSystypeCitysByProId(int proId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proId", proId);
		return sysareaCityDao.select(map);
	}
}
