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
import com.fatp.service.datasupprot.project.ListingInfoDataSupportService;
import com.fatp.service.sys.SysParamService;
import com.fatp.service.sys.SysbizcodeSequenceService;
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
	@Autowired
	private ListingInfoDataSupportService listingInfoDataSupportService;
	@Autowired
	private SysbizcodeSequenceService sysbizcodeSequenceService;
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
		for (int i = 1; i < resultList.size(); i++) {
			String[] strArray = resultList.get(i);
			if (strArray != null)
				for (int j = 0; j < strArray.length; j++)
					System.err.println(i+"  strArray["+j + "] = " + strArray[j]);
			
			ListingInfoPo listingInfo = new ListingInfoPo();
//			if (StringUtils.isBlank(strArray[0]) || !strArray[0].trim().equals(projectCode)) {
//				throw new FatpException("投资明细文件第" + (i + 2) + "行产品信息错误，请检查后重新上传");
//			}
			
			try{
				listingInfo.setId((int)System.currentTimeMillis());
				listingInfo.setExchangeId(2012);
				//新增挂牌代码,系统自动生成
				listingInfo.setListingCode(sysbizcodeSequenceService.getListingInfoSequence());
				listingInfo.setListingGuid(strArray[0].trim());
				listingInfo.setListingName(strArray[1].trim()); 
				listingInfo.setIssuer(strArray[2].trim());
				listingInfo.setListingManager(strArray[3].trim());
				listingInfo.setInvestManager(strArray[4].trim());
				listingInfo.setPartnerBiz(strArray[5].trim());
				listingInfo.setListingMoney(new BigDecimal(strArray[6].trim()));
				listingInfo.setListingLimit(Integer.valueOf(strArray[7].trim()));

				//产品期限类型：1天2月3年
				listingInfo.setListingLimitType(1);
				if(strArray[8].trim().equals("月")){
					listingInfo.setListingLimitType(2);
				}else if(strArray[8].trim().equals("年")){
					listingInfo.setListingLimitType(3);
				}					

				
				//收益率类型：1固定收益率；2阶梯收益率，3浮动收益率'
				listingInfo.setInvestProfitType(1);
				if(strArray[9].trim().equals("阶梯收益率")){
					listingInfo.setInvestProfitType(2);
				}else if(strArray[9].trim().equals("浮动收益率")){
					listingInfo.setInvestProfitType(3);
				}					
				listingInfo.setProfitValue(strArray[10].trim());				
				listingInfo.setValueDate(Date.valueOf(strArray[11].trim()));
				
				//到期日规则 1：固定期限 2：固定到期日
				listingInfo.setExpireDateStyle(1);
				if(strArray[12].trim().equals("固定到期日")){
					listingInfo.setExpireDateStyle(2);
				}
				listingInfo.setExpireDate(Date.valueOf(strArray[13].trim()));
				
				//付息方式：一次性还本付息（1），等额本息(2),按月付息到期还本(3)，按季付息到期还本(4),按半年息到期还本(5),按年付息到期还本(6);
				listingInfo.setPayInterestType(1);
				if(strArray[14].trim().equals("等额本息")){
					listingInfo.setPayInterestType(2);
				}else if(strArray[14].trim().equals("按月付息到期还本")){
					listingInfo.setPayInterestType(3);
				}else if(strArray[14].trim().equals("按季付息到期还本")){
					listingInfo.setPayInterestType(4);
				}else if(strArray[14].trim().equals("按半年息到期还本")){
					listingInfo.setPayInterestType(5);
				}else if(strArray[14].trim().equals("按年付息到期还本")){
					listingInfo.setPayInterestType(6);
				}					
				
				//计息方式：单利1，复利2
				listingInfo.setInterestType(1);
				if(strArray[15].trim().equals("复利")){
					listingInfo.setInterestType(2);
				}
				//计息频率：1按日计息，2按月计息，3按年计息,4按季计息，5按半年计息
				listingInfo.setInterestRate(1);
				if(strArray[16].trim().equals("按月计息")){
					listingInfo.setInterestRate(2);
				}else if(strArray[16].trim().equals("按年计息")){
					listingInfo.setInterestRate(3);
				}else if(strArray[16].trim().equals("按季计息")){
					listingInfo.setInterestRate(4);
				}else if(strArray[16].trim().equals("按半年计息")){
					listingInfo.setInterestRate(5);
				}	
				//计息基准：1、ACT/365，2、ACT/360，3、ACT/ACT
				listingInfo.setInterestBase(1);
				if(strArray[17].trim().equals("ACT//360")){
					listingInfo.setInterestBase(2);
				}else if(strArray[17].trim().equals("ACT//ACT")){
					listingInfo.setInterestBase(3);
				}
				
				//到期日是否计息：1是0否
				listingInfo.setExpireDateInterest(strArray[18].trim().equals("是")?1:0);
				
				listingInfo.setListingStatus(1);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw e;
			}
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
