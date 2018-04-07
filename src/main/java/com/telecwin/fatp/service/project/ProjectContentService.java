package com.telecwin.fatp.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.po.project.ProjectContentPo;
import com.telecwin.fatp.service.datasupprot.project.ProjectContentDataSupportService;

/**
 * 
 * @author zhiya.chai
 * 项目内容domain
 */
@Service
public class ProjectContentService {
	
	@Autowired
	private ProjectContentDataSupportService projectContentDataSupportService;
	

	public void updateByProjectId(ProjectContentPo contentPo) {
		projectContentDataSupportService.updateByProjectId(contentPo);
	}
	
	public void insert(ProjectContentPo contentPo) {
		projectContentDataSupportService.insert(contentPo);
	}

	public void deleteByRecordId(Integer id) {
		projectContentDataSupportService.deleteByProjectId(id);
	}
	
	public ProjectContentPo getByProjectId(int projectId) {
		return projectContentDataSupportService.getByProjectId(projectId);
	}

}
