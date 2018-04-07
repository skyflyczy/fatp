package com.telecwin.fatp.convert;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.huajin.baymax.exception.ExceptionThrowUtil;
import com.telecwin.fatp.domain.project.ProjectRecordinfo;
import com.telecwin.fatp.po.project.ProjectContentPo;
import com.telecwin.fatp.po.project.ProjectRecordinfoPo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
/**
 * 产品备案实体转换器
 */
public class ProjectRecordConvertor {

	public static final ProjectRecordinfoPo convertRecordPo(ProjectRecordinfo recordinfoVo){
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
	public static final ProjectContentPo convertRecordContent(ProjectRecordinfo projectRecordinfoVo) {
		ProjectContentPo projectContent = new ProjectContentPo();
		projectContent.setProjectId(projectRecordinfoVo.getId());
		if(StringUtils.isNotBlank(projectRecordinfoVo.getProjectInfo())) {
			projectContent.setProjectInfo(projectRecordinfoVo.getProjectInfo().replace("<pre>", "<p>").replace("</pre>", "</p>"));
		}
		projectContent.setBasicAssetNote(projectRecordinfoVo.getBasicAssetNote());
		return projectContent;
	}
	/**
	 * 备案数据转移
	 * @param newRecordinfo
	 * @param oldRecordInfo
	 * @param operator
	 */
	public static final void recordNewToOld(ProjectRecordinfo newRecordinfo,ProjectRecordinfo oldRecordInfo,MemberOperatorPo operator) {
		oldRecordInfo.setRecordName(newRecordinfo.getRecordName());
		oldRecordInfo.setRecordFullName(newRecordinfo.getRecordFullName());
		oldRecordInfo.setProjectUsing(newRecordinfo.getProjectUsing());
		oldRecordInfo.setProjectMoney(newRecordinfo.getProjectMoney());
		oldRecordInfo.setProjectLimitTypeId(newRecordinfo.getProjectLimitTypeId());
		oldRecordInfo.setProjectLimit(newRecordinfo.getProjectLimit());
		oldRecordInfo.setInvestProfit(newRecordinfo.getInvestProfit().divide(BigDecimal.valueOf(100)));
		oldRecordInfo.setRepayTypeId(newRecordinfo.getRepayTypeId());
		oldRecordInfo.setIsGuarantee(newRecordinfo.getIsGuarantee());
		oldRecordInfo.setIsPledge(newRecordinfo.getIsPledge());
		oldRecordInfo.setCreditNote(newRecordinfo.getCreditNote());
		oldRecordInfo.setExchangeId(operator.getExchangeId());
		oldRecordInfo.setUpateOperatorId(operator.getId());
		oldRecordInfo.setUpdateTime(new Date());
		oldRecordInfo.setBasicAssetNote(newRecordinfo.getBasicAssetNote());
		oldRecordInfo.setRecordStatus(newRecordinfo.getRecordStatus());
		oldRecordInfo.setLoanUserId(newRecordinfo.getLoanUserId());
		oldRecordInfo.setProjectInfo(newRecordinfo.getProjectInfo());
		if(!StringUtils.isEmpty(newRecordinfo.getRaisesFileName())) {
			oldRecordInfo.setRaisesFileName(newRecordinfo.getRaisesFileName());
		}
		if(!StringUtils.isEmpty(newRecordinfo.getRisksFileName())) {
			oldRecordInfo.setRisksFileName(newRecordinfo.getRisksFileName());
		}
		oldRecordInfo.setRatingOrgName(newRecordinfo.getRatingOrgName());
		oldRecordInfo.setCreditRating(newRecordinfo.getCreditRating());
		oldRecordInfo.setDebtLevel(newRecordinfo.getDebtLevel());
		oldRecordInfo.setAgentLinkman(newRecordinfo.getAgentLinkman());
		oldRecordInfo.setLoanLinkman(newRecordinfo.getLoanLinkman());
	}
	
}
