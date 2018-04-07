package com.telecwin.fatp.service.datasupprot.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.project.ProjectContentDao;
import com.telecwin.fatp.po.project.ProjectContentPo;

@Service
public class ProjectContentDataSupportService {

	@Autowired
	private ProjectContentDao projectContentDao;
	
	public int updateByProjectId(ProjectContentPo projectContentPo) {
		return projectContentDao.updateByProjectId(projectContentPo);
	}

	public void insert(ProjectContentPo contentPo) {
		projectContentDao.insert(contentPo);
	}
	
	public void deleteByProjectId(int id) {
		projectContentDao.deleteByProjectId(id);
	}
	public ProjectContentPo getByProjectId(int projectId) {
		return projectContentDao.getByProjectId(projectId);
	}
}
