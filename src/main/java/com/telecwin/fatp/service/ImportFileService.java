package com.telecwin.fatp.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.huajin.pdfconvertor.PdfProcessSupport;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;
import com.telecwin.fatp.enums.user.IdTypeDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.service.sys.SysParamService;
import com.telecwin.fatp.util.BigDecimalUtil;
import com.telecwin.fatp.util.DateUtil;

/**
 * 导入文件服务
 */
@Service
public class ImportFileService {

	@Autowired
	private SysParamService sysParmService;
	
	/**
	 * 导入投资明细文件地址
	 * @return
	 */
	public String importInvestRecordsFilePath(int projectId){
		return sysParmService.getProjectUploadAddress() 
				+ File.separator + projectId 
				+ File.separator + "invest_records" 
				+ File.separator + getRandomNum() + File.separator;
	}
	/**
	 * 读取投资明细
	 * @param filePath
	 * @param projectName
	 * @param projectCode
	 * @param memberId
	 * @param exchangeId
	 * @return
	 * @throws IOException
	 */
	public List<BizimportTradeDetail> readInvestRecordsForFile(String filePath,String projectName,String projectCode,int memberId, Integer exchangeId) throws IOException {
		File file = new File(filePath);
		if(!file.exists()) {
			throw new FatpException(ErrorCode.FILE_NOT_EXIST);
		}
		List<String[]> resultList = PdfProcessSupport.readFirstSheetFromExcelFile(file);
		if(CollectionUtils.isEmpty(resultList)) {
			throw new FatpException(ErrorCode.FILE_READ_DATA_NULL);
		}
		List<BizimportTradeDetail> list = new ArrayList<BizimportTradeDetail>();
		for (int i = 0; i < resultList.size(); i++) {
			String[] strArray = resultList.get(i);
			BizimportTradeDetail detail = new BizimportTradeDetail();
			detail.setApplyMemberId(memberId);
			detail.setTradeTime(DateUtil.autoParseDate(strArray[0]));
			detail.setPeriodName(strArray[1]);
			detail.setPeriodCode(strArray[2]);
			detail.setTradeMoney(BigDecimalUtil.convertDefaultZero(strArray[3]));
			detail.setUserRealName(strArray[4]);
			IdTypeDesc idType = getIdTypeDesc(strArray[5]);
			detail.setIdTypeId(idType == null ? null : idType.getIdType());
			detail.setIdNumber(strArray[6]);
			detail.setPhoneNumber(strArray[7]);
			detail.setPartnerTradeSeq(StringUtils.isBlank(strArray[8])? null : strArray[8]);
			if(strArray.length >= 10) {
				detail.setProjectCode(strArray[9]);
				detail.setProjectName(strArray[10]);
			}
			if(strArray.length == 12) {
				detail.setUserName(StringUtils.isBlank(strArray[11])?null:strArray[11]);
			}
			detail.setExchangeId(exchangeId);
			if(detail.getIdTypeId() == null 
					|| StringUtils.isBlank(detail.getIdNumber()) 
					|| detail.getIdNumber().indexOf(".") >= 0
					|| detail.getTradeMoney().compareTo(BigDecimal.ZERO) <= 0
					|| StringUtils.isBlank(detail.getUserRealName())
					|| detail.getTradeTime() == null 
					|| (StringUtils.isNotBlank(detail.getProjectCode()) && !detail.getProjectCode().equals(projectCode))
					|| (StringUtils.isNotBlank(detail.getProjectName()) && !detail.getProjectName().equals(projectName))
					|| ((detail.getIdTypeId() == IdTypeDesc.身份证.getIdType() || detail.getIdTypeId() == IdTypeDesc.临时身份证.getIdType()) && detail.getIdNumber().split(" ").length > 1)) {
				throw new FatpException("投资明细文件第"+(i+2)+"行格式错误，请检查后重新上传");
			}
			list.add(detail);
		}
		return list;
	}
	private IdTypeDesc getIdTypeDesc(String key) {
		IdTypeDesc idType = IdTypeDesc.fromIdTypeName(key);
		if(idType != null) {
			return idType;
		}
		try {
			int type = Integer.parseInt(key);
			idType = IdTypeDesc.fromIdIdType(type);
		} catch (Exception e) {
		}
		return idType;
	}
	
	/**
	 * 获取随机数
	 * @return
	 */
	private String getRandomNum(){
		Random random = new Random(System.currentTimeMillis());
		return Math.abs(random.nextInt()) + "";
	}
}
