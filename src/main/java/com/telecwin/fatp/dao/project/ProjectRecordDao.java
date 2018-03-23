package com.telecwin.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.ProjectRecordComplex;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;

@MyBatisDao
public interface ProjectRecordDao {
	/**
	 * 根据条件查询备案记录
	 * @param map
	 * @return
	 */
	public List<ProjectRecordComplex> findByCondition(Map<String,Object> map);
	
	public int insert(ProjectRecordinfoPo o);
}
