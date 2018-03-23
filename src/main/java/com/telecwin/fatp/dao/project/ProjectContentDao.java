package com.telecwin.fatp.dao.project;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ProjectContentPo;

/**
 * 
 * ProjectContent
 * 
 * @author zhiya.chai
 * @date 2017-06-01 48:14:09
 */
@MyBatisDao
public interface ProjectContentDao {
	int updateByProjectId(ProjectContentPo projectContentPo);

	void insert(ProjectContentPo contentPo);
	
	void deleteByProjectId(int id);
}
