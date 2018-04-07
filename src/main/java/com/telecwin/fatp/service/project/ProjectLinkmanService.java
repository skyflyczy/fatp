package com.telecwin.fatp.service.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.project.ProjectLinkmanPo;
import com.telecwin.fatp.service.datasupprot.project.ProjectLinkmanDataSupportService;

/**
 * 
 * @author zhiya.chai
 * 联系人domain
 */
@Service
public class ProjectLinkmanService {

	@Autowired
	private ProjectLinkmanDataSupportService projectLinkmanDataSupportService;

	/**
	 * 新增联系人
	 * @param linkmanPo
	 */
	 public int insertLinkman(ProjectLinkmanPo linkmanPo) {
		if(linkmanPo == null){
			throw new FatpException(ErrorCode.SYSTEM_PARAMETERS_EMPTY);
		}
		ProjectLinkmanPo dbLinkman = projectLinkmanDataSupportService.getUniq(linkmanPo);
		if(dbLinkman != null){
			return dbLinkman.getId();
		}
		projectLinkmanDataSupportService.insert(linkmanPo);
		return linkmanPo.getId();
	}

	/**
	 * 根据id获取联系人
	 * @param id
	 * @return
	 */
	public ProjectLinkmanPo getById(int id) {
		return projectLinkmanDataSupportService.getById(id);
	}
	
	/**
	 * 设置联系人信息
	 * @param recordinfo
	 */
	public void setLinkmanInfo(ProjectRecordinfo recordinfo) {
		if(recordinfo == null) {
			return;
		}
		if(recordinfo.getUnderwriterLinkId() != null) {
			ProjectLinkmanPo po = projectLinkmanDataSupportService.getById(recordinfo.getUnderwriterLinkId());
			recordinfo.setAgentLinkman(po);
		}
		if(recordinfo.getLoanLinkId() != null) {
			ProjectLinkmanPo po = projectLinkmanDataSupportService.getById(recordinfo.getLoanLinkId());
			recordinfo.setLoanLinkman(po);
		}
		
	}
	
}
