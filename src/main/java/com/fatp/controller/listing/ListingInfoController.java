package com.fatp.controller.listing;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fatp.controller.BaseController;
import com.fatp.domain.PageData;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.listing.ListingTrade;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestBase;
import com.fatp.enums.project.InterestRate;
import com.fatp.enums.project.InterestType;
import com.fatp.enums.project.InvestProfitType;
import com.fatp.enums.project.ListingLimitType;
import com.fatp.enums.project.ListingStatus;
import com.fatp.enums.project.PayInterestType;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.interceptor.FlashUpload;
import com.fatp.po.GlobalFilePo;
import com.fatp.po.project.ListingInfoPo;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.GlobalFileService;
import com.fatp.service.ImportFileService;
import com.fatp.service.offsite.InvestApplyService;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
import com.fatp.util.StringUtil;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;

@Controller
@RequestMapping("/project/listinginfo/")
public class ListingInfoController extends BaseController {

	private String viewPath = super.viewPath + "project/listing";
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ListingInfoService listingInfoService;
	@Autowired
	private ImportFileService importFileService;
	@Autowired
	private InvestApplyService investApplyService;
	@Autowired
	private GlobalFileService globalFileService;

	/**
	 * 查询挂牌产品信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("infolist")
	public String infoList(HttpServletRequest request) {
		Map<String, Object> map = paramToMap(request);
		map.put("exchangeId", super.getExchangeId());
		map.put("listingStatus", ListingStatus.正常.status);
		map.put("sortColumns", " CreateTime desc ");
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ListingInfo> pageData = listingInfoService.pageFindByCondition(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("projectLimitTypeList", ListingLimitType.values());
		return viewPath + "/infolist";
	}

	/**
	 * 修改项目
	 * 
	 * @return
	 * @return String
	 */
	@RequestMapping("edit")
	public String edit(String id) {
		logger.debug("id=" + id);
		if(StringUtil.isNotBlank(id)) {
			ListingInfo listingInfoVo = listingInfoService.getByListingGuid(id);
			if(listingInfoVo != null) {
				List<ListingTrade> tradeVoList = listingInfoService.getTradeByListingInfoId(listingInfoVo.getId());
				request().setAttribute("listingTradeList", tradeVoList);
			}
			request().setAttribute("obj", listingInfoVo);
			logger.debug("listingInfoVo=" + listingInfoVo);
		}
		request().setAttribute("todayForJudge", DateUtils.getDate());
		request().setAttribute("investProfitTypeList", InvestProfitType.values());
		request().setAttribute("listingLimitTypeList", ListingLimitType.values());
		request().setAttribute("expireDateStyleList", ExpireDateStyle.values());
		request().setAttribute("payInterestTypeList", PayInterestType.values());
		request().setAttribute("interestTypeList", InterestType.values());
		request().setAttribute("interestRateList", InterestRate.values());
		request().setAttribute("interestBaseList", InterestBase.values());
		return viewPath + "/editindex";
	}

	/**
	 * 更新挂牌信息
	 * 
	 * @param listingInfoVo
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Object update(@ModelAttribute ListingInfo listingInfoVo) {
		try {
			setOperatorData(listingInfoVo);
			listingInfoService.updateListingInfo(listingInfoVo);
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "update", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			XMsgError error = XMsgError.buildSimple(getClass().getName(), "update", e);
			error.setMoreMsg(new Object[] { "id=" + listingInfoVo.getId() });
			Xlogger.error(error);
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}

	/**
	 * 删除挂牌产品
	 * 
	 * @param request
	 * @return
	 * @return Object
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Object delete(String id) {
		try {
			listingInfoService.deleteListingInfo(id);
			return resultSuccess();
		} catch (FatpException e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delete", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 挂牌产品导入页面
	 * 
	 * @return
	 */

	@RequestMapping("import")
	public String importIndex(String id) {
		logger.debug("importIndex() id=" + id);
		return viewPath + "/importIndex";
	}

	/**
	 * 导入挂牌产品信息
	 * 
	 * @param listingInfoVo
	 * @return
	 */
	@FlashUpload
	@RequestMapping("listInfoImport")
	@ResponseBody
	private Object listInfoImport(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
		try {
			logger.debug("<---------------enter listInfoImport------------------------>");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			logger.debug(">>>>>fileMap=" + fileMap);
			logger.debug(">>>>>fileMap.size()=" + fileMap.size());
			String listingGuid = request.getParameter("listingGuid");
			String projectCode = request.getParameter("projectCode");
			String memberId = request.getParameter("memberId");
			String operatorId = request.getParameter("operatorId");
			String fileName = file.getOriginalFilename();
			logger.debug(">>>>>listingGuid=" + listingGuid);
			logger.debug(">>>>>projectCode=" + projectCode);
			logger.debug(">>>>>fileName=" + fileName);
			logger.debug(">>>>>file.getName()=" + file.getName());


			// 获取上传地址
			String importRecordsPath = importFileService.importListingRecordsFilePath(Integer.valueOf(listingGuid));
			logger.debug(">>>>>importRecordsPath=" + importRecordsPath);


			// 上传产品文件到服务器
			String fileNames = upload(fileMap, importRecordsPath);
			String[] fileNameArray = fileNames.split(":");
			String originalFilename = fileNameArray[0];
			String linkFileName = fileNameArray[1];
			String filePath = importRecordsPath + File.separator + linkFileName;
			
			//上传服务器成功后，记录挂牌产品文件的信息 global_file
			GlobalFilePo globalFile = globalFileService.insertGlobalFile(filePath, originalFilename, Integer.parseInt(memberId), Integer.parseInt(operatorId));
			logger.debug(">>>>>globalFile=" + globalFile);			
			
			// 解析产品信息
			List<ListingInfoPo> list = importFileService.importListingInfo(filePath, projectCode,super.getExchangeId());
			logger.debug(">>>>>List<ListingInfoPo>=" + list);
			int  recordsNumbers =-1;
			try{
			//把产品信息记录到数据库中
			recordsNumbers = listingInfoService.listingRecords(list);
			logger.debug("recordsNumbers=" + recordsNumbers);
			}catch(Exception e)
			{
				e.printStackTrace();
				globalFileService.deleteGlobalFileById(globalFile.getId());
				Xlogger.error(XMsgError.buildSimple(getClass().getName(), "listInfoImport", e));
				return resultError(ErrorCode.SYSTEM_ERROR.getMessage()).toJSONString();
			}
			logger.debug("<---------------out listInfoImport------------------------>");
			return resultSuccess();


		} catch (FatpException e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "listInfoImport", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage())
					.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "listInfoImport", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage()).toJSONString();
		}
	}



	private void setOperatorData(ListingInfo listingInfoVo) {
		MemberOperatorPo operator = super.getMemberOperator();
		listingInfoVo.setExchangeId(operator.getExchangeId());
		listingInfoVo.setCreateOperatorId(operator.getId());
		listingInfoVo.setUpateOperatorId(operator.getId());
		listingInfoVo.setCreateOperatorName(operator.getRealName());
	}
}
