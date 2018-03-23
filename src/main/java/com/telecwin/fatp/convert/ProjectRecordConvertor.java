package com.telecwin.fatp.convert;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.huajin.baymax.exception.ExceptionThrowUtil;
import com.telecwin.fatp.controller.param.project.ProjectRecordinfoVo;
import com.telecwin.fatp.po.project.ProjectContentPo;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;
/**
 * 产品备案实体转换器
 */
public class ProjectRecordConvertor {

	public static final ProjectRecordinfoPo convertRecordPo(ProjectRecordinfoVo recordinfoVo){
		if(recordinfoVo == null){
			throw ExceptionThrowUtil.emptyParameterException(null);
		}
		ProjectRecordinfoPo recordInfoPo = new ProjectRecordinfoPo();
		BeanUtils.copyProperties(recordinfoVo, recordInfoPo);
		return recordInfoPo;
	}
	
	/**
	 * 转换备案内容
	 * @param projectRecordinfoVo
	 * @return
	 */
	public static final ProjectContentPo convertRecordContent(ProjectRecordinfoVo projectRecordinfoVo) {
		ProjectContentPo projectContent = new ProjectContentPo();
		projectContent.setProjectId(projectRecordinfoVo.getId());
		if(StringUtils.isNotBlank(projectRecordinfoVo.getProjectInfo())) {
			projectContent.setProjectInfo(projectRecordinfoVo.getProjectInfo().replace("<pre>", "<p>").replace("</pre>", "</p>"));
		}
		projectContent.setBasicAssetNote(projectRecordinfoVo.getBasicAssetNote());
		return projectContent;
	}
	
}
