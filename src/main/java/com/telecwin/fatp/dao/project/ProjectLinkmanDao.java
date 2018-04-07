package com.telecwin.fatp.dao.project;

import java.util.Map;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ProjectLinkmanPo;

/**
 * ProjectLinkman
 */
@MyBatisDao
public interface ProjectLinkmanDao {
	int insert(ProjectLinkmanPo projectLinkMan);

	ProjectLinkmanPo getById(Integer id);

	ProjectLinkmanPo getUniq(Map<String, Object> map);
}
