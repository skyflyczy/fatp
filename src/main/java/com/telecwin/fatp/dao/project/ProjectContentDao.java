package com.telecwin.fatp.dao.project;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ProjectContentPo;

/**
 * 
 * ProjectContent
 * 
 */
@MyBatisDao
public interface ProjectContentDao {
	int updateByProjectId(ProjectContentPo projectContentPo);

	void insert(ProjectContentPo contentPo);
	
	void deleteByProjectId(int id);
	
	ProjectContentPo getByProjectId(int projectId);
}
