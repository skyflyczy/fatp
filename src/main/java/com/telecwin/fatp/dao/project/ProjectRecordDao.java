package com.telecwin.fatp.dao.project;

import java.util.List;
import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.domain.project.ProjectRecordComplex;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
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
	
	public ProjectRecordinfo getById(int id);
	
	public int update(ProjectRecordinfoPo o);
	
	public void delete(Map<String, Object> map);
	
	public ProjectRecordinfo getByRecordGuid(String recordGuid);
	
	public List<ProjectRecordComplex> getCanQuotedRecordList(Map<String,Object> map);
}
