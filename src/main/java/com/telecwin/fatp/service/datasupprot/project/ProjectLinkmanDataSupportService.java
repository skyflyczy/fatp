package com.telecwin.fatp.service.datasupprot.project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.dao.project.ProjectLinkmanDao;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ProjectLinkmanPo;
/**
 * 联系人 
 */
@Service
public class ProjectLinkmanDataSupportService {

	@Autowired
	private ProjectLinkmanDao projectLinkmanDao;
	/**
	 * 获取唯一联系人
	 * @param po
	 */
	public ProjectLinkmanPo getUniq(ProjectLinkmanPo po){
		if(po == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("realName", po.getRealName());
		map.put("phone", po.getPhone());
		map.put("email", po.getEmail());
		map.put("address", po.getAddress());
		return projectLinkmanDao.getUniq(map);
	}
	
	public int insert(ProjectLinkmanPo projectLinkMan) {
		return projectLinkmanDao.insert(projectLinkMan);
	}
	
	public ProjectLinkmanPo getById(Integer id) {
		return projectLinkmanDao.getById(id);
	}
}
