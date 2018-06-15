package com.fatp.service.datasupprot.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.dao.sys.SysbizcodeSequenceDao;
import com.fatp.po.sys.SysbizcodeSequencePo;

/**
 * SysbizcodeSequence
 * @author zhiya.chai
 * 2016年8月1日 下午7:56:48
 */
@Service
public class SysbizcodeSequenceDataSupportService {

	@Autowired
	private SysbizcodeSequenceDao sysbizcodeSequenceDao;
	
	public SysbizcodeSequencePo getById(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return sysbizcodeSequenceDao.getById(map);
	}
	
	public int update(SysbizcodeSequencePo sequence){
		return sysbizcodeSequenceDao.update(sequence);
	}
	
	public List<SysbizcodeSequencePo> select(Map<String,Object> map){
		return sysbizcodeSequenceDao.select(map);
	}
}
