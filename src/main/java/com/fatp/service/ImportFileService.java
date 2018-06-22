package com.fatp.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.enums.user.IdTypeDesc;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.service.sys.SysParamService;
import com.fatp.util.BigDecimalUtil;
import com.fatp.util.DateUtil;
import com.huajin.pdfconvertor.PdfProcessSupport;

/**
 * 导入文件服务
 */
@Service
public class ImportFileService {

	@Autowired
	private SysParamService sysParmService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 导入投资明细文件地址
	 * 
	 * @return
	 */
	public String importInvestRecordsFilePath(int projectId) {
		return sysParmService.getProjectUploadAddress() + File.separator + projectId + File.separator + "invest_records"
				+ File.separator + getRandomNum() + File.separator;
	}
	/**
	 * 导入挂牌产品文件地址
	 * 
	 * @return
	 */
	public String importListingRecordsFilePath(int projectId) {
		return sysParmService.getProjectUploadAddress() + File.separator + projectId + File.separator + "listing_records"
				+ File.separator + getRandomNum() + File.separator;
	}
	/**
	 * 读取挂牌产品的投资明细
	 * 
	 * @param filePath
	 * @param projectName
	 * @param projectCode
	 * @param exchangeId
	 * @return
	 * @throws IOException
	 */
	public List<BizimportTradeDetail> readInvestRecordsForFile(String filePath, String projectCode, Integer exchangeId)
			throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new FatpException(ErrorCode.FILE_NOT_EXIST);
		}
		List<String[]> resultList = PdfProcessSupport.readFirstSheetFromExcelFile(file);
		if (CollectionUtils.isEmpty(resultList)) {
			throw new FatpException(ErrorCode.FILE_READ_DATA_NULL);
		}
		List<BizimportTradeDetail> list = new ArrayList<BizimportTradeDetail>();
		for (int i = 0; i < resultList.size(); i++) {
			String[] strArray = resultList.get(i);
			BizimportTradeDetail detail = new BizimportTradeDetail();
			if (StringUtils.isBlank(strArray[0]) || !strArray[0].trim().equals(projectCode)) {
				throw new FatpException("投资明细文件第" + (i + 2) + "行产品信息错误，请检查后重新上传");
			}
			detail.setListingCode(strArray[0].trim());
			detail.setListingFullName(strArray[1]);
			detail.setUserRealName(strArray[2].trim());
			detail.setTradeTime(DateUtil.autoParseDate(strArray[3]));
			detail.setTradeMoney(BigDecimalUtil.convertDefaultZero(strArray[4]));
			if (detail.getTradeTime() == null || detail.getTradeMoney().compareTo(BigDecimal.ZERO) <= 0) {
				throw new FatpException("投资明细文件第" + (i + 2) + "行交易信息错误，请检查后重新上传");
			}
			detail.setAddInvestProfit(BigDecimalUtil.convertDefaultZero(strArray[5]).divide(new BigDecimal("100"), 5,
					BigDecimal.ROUND_HALF_UP));
			detail.setAddInvestProfitDays(
					StringUtils.isBlank(strArray[6]) ? null : new BigDecimal(strArray[6].trim()).intValue());
			IdTypeDesc idType = getIdTypeDesc(strArray[7]);
			detail.setIdTypeId(idType == null ? null : idType.getIdType());
			detail.setIdNumber(strArray[8]);
			detail.setPhoneNumber(strArray[9]);
			detail.setCardAccount(strArray[10]);
			detail.setSubBankName(strArray[11]);
			detail.setExchangeId(exchangeId);
			if (detail.getIdTypeId() == null || StringUtils.isBlank(detail.getIdNumber())
					|| detail.getIdNumber().indexOf(".") >= 0 || StringUtils.isBlank(detail.getUserRealName())
					|| (detail.getIdTypeId() == IdTypeDesc.身份证.getIdType()
							&& detail.getIdNumber().split(" ").length > 1)) {
				throw new FatpException("投资明细文件第" + (i + 2) + "行客户信息错误，请检查后重新上传");
			}
			list.add(detail);
		}
		return list;
	}


	/**
	 * 读取挂牌产品信息
	 * @param filePath
	 * @param projectCode
	 * @param exchangeId
	 * @return
	 * @throws IOException
	 */
	public List<ListingInfoPo> importListingInfo(String filePath, String projectCode, Integer exchangeId)
			throws IOException {
		logger.debug(">>>>>ImportFileService.importListingInfo()-filePath="+filePath);
		File file = new File(filePath);

		if (!file.exists()) {		
			throw new FatpException(ErrorCode.FILE_NOT_EXIST);
		}
		List<String[]> resultList = PdfProcessSupport.readFirstSheetFromExcelFile(file);
		if (CollectionUtils.isEmpty(resultList)) {
			throw new FatpException(ErrorCode.FILE_READ_DATA_NULL);
		}
		List<ListingInfoPo> list = new ArrayList<ListingInfoPo>();
		for (int i = 0; i < resultList.size(); i++) {
			String[] strArray = resultList.get(i);
			if (strArray != null)
				for (int j = 0; j < strArray.length; j++)
					logger.debug(i+"  strArray["+j + "] = " + strArray[j]);
			
			ListingInfoPo listingInfo = new ListingInfoPo();
//			if (StringUtils.isBlank(strArray[0]) || !strArray[0].trim().equals(projectCode)) {
//				throw new FatpException("投资明细文件第" + (i + 2) + "行产品信息错误，请检查后重新上传");
//			}
			
			try{
			listingInfo.setId( Integer.valueOf(strArray[0].trim()));
			listingInfo.setExchangeId(Integer.valueOf(strArray[1].trim()));
			listingInfo.setIssuer(strArray[2].trim());
			listingInfo.setListingManager(strArray[3].trim());
			listingInfo.setInvestManager(strArray[4].trim());
			listingInfo.setListingCode(strArray[5].trim());
			listingInfo.setListingGuid(strArray[6].trim());
			listingInfo.setPartnerBizCode(strArray[7].trim());
			listingInfo.setPartnerBiz(strArray[8].trim());
			listingInfo.setListingName(strArray[9].trim());
			listingInfo.setListingFullName(strArray[10].trim());
			listingInfo.setListingMoney(new BigDecimal(strArray[11].trim()));
			listingInfo.setListingLimit(Integer.valueOf(strArray[12].trim()));
			listingInfo.setListingLimitType(Integer.valueOf(strArray[13].trim()));
			listingInfo.setInvestProfitType(Integer.valueOf(strArray[14].trim()));
			listingInfo.setExpireDateStyle(Integer.valueOf(strArray[15].trim()));
			listingInfo.setValueDate(Date.valueOf(strArray[16].trim()));
			listingInfo.setExpireDate(Date.valueOf(strArray[17].trim()));
			listingInfo.setPayInterestType(Integer.valueOf(strArray[18].trim()));
			listingInfo.setInterestType(Integer.valueOf(strArray[19].trim()));
			listingInfo.setInterestRate(Integer.valueOf(strArray[20].trim()));
			listingInfo.setInterestBase(Integer.valueOf(strArray[21].trim()));
			listingInfo.setInterestBaseDays(Integer.valueOf(strArray[22].trim()));
			listingInfo.setExpireDateInterest(Integer.valueOf(strArray[23].trim()));
			listingInfo.setListingStatus(Integer.valueOf(strArray[24].trim()));
			listingInfo.setCreateTime(Date.valueOf(strArray[25].trim()));
			listingInfo.setCreateOperatorId(Integer.valueOf(strArray[26].trim()));
			listingInfo.setUpdateTime(Date.valueOf(strArray[27].trim()));
			listingInfo.setUpateOperatorId(Integer.valueOf(strArray[28].trim()));
			listingInfo.setVersionNo(Integer.valueOf(strArray[29].trim()));
			}catch(Exception e)
			{e.printStackTrace();}
			list.add(listingInfo);
			logger.debug("listingInfo["+i + "] = " + listingInfo);
			}
		return list;
	}

	private IdTypeDesc getIdTypeDesc(String key) {
		IdTypeDesc idType = IdTypeDesc.fromIdTypeName(key);
		if (idType != null) {
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
	 * 
	 * @return
	 */
	private String getRandomNum() {
		Random random = new Random(System.currentTimeMillis());
		return Math.abs(random.nextInt()) + "";
	}
}
