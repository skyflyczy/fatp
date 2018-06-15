package com.fatp.controller.offsite;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fatp.controller.BaseController;
import com.fatp.controller.param.InvestRecordsParam;
import com.fatp.domain.PageData;
import com.fatp.domain.offsite.BizimportTradeDetail;
import com.fatp.domain.offsite.InvestRecordsResult;
import com.fatp.exception.ErrorCode;
import com.fatp.exception.FatpException;
import com.fatp.interceptor.FlashUpload;
import com.fatp.po.user.MemberOperatorPo;
import com.fatp.service.ImportFileService;
import com.fatp.service.offsite.InvestApplyService;
import com.fatp.service.project.ListingInfoService;
import com.fatp.util.Constant;
import com.fatp.util.FileUtil;
import com.fatp.util.SessionUtil;
import com.fatp.vo.InvestApplyVo;
import com.fatp.vo.ListingInfoVo;
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.huajin.baymax.util.DateUtils;

/**
 * 投资明细登记
 */
@Controller
@RequestMapping("/offsite/invest")
public class InvestApplyController extends BaseController{

	private String viewPath = super.viewPath + "/offsite/invest";
	
	@Autowired
	private InvestApplyService investApplyService;
	@Autowired
	private ListingInfoService listingInfoService;
	@Autowired
	private ImportFileService importFileService;
	/**
	 * 可登记列表
	 * @return
	 */
	@RequestMapping("reglist")
	public String list(){
		Map<String,Object> map = paramToMap(request());
		//处理查询状态
		map.put("exchangeId", super.getExchangeId());
		//后台调查询list
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<InvestApplyVo> pageData = investApplyService.getCanApplyListingList(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		return viewPath + "/reglist";
	}
	/**
	 * 前往投资明细登记页面
	 * @return
	 */
	@RequestMapping("to_apply_register")
	@ResponseBody
	public Object toApplyRegister() {
		String listingGuid = request().getParameter("id");
		if(StringUtils.isBlank(listingGuid)) {
			return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
		}
		ListingInfoVo listingVo = listingInfoService.getByListingGuid(listingGuid);
		if(listingVo == null) {
			return resultError(ErrorCode.LISTING_NOT_EXIST.getMessage());
		}
		request().setAttribute("listingInfo", listingVo);
		request().setAttribute("jsessionid", SessionUtil.getEncryptSessionId(request(), response()));
		request().setAttribute("todayForJudge", DateUtils.getDate());
		return new ModelAndView(viewPath + "/apply-register-index");
	}
	/**
	 * 上传投资明细
	 * @param request
	 * @return
	 */
	@FlashUpload
	@RequestMapping(value="uploadInvestRecords",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object uploadInvestRecords(HttpServletRequest request) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			String listingGuid = multipartRequest.getParameter("listingGuid");
			if(StringUtils.isBlank(listingGuid)) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			ListingInfoVo listingVo = listingInfoService.getByListingGuid(listingGuid);
			if(listingVo == null) {
				return resultError(ErrorCode.LISTING_NOT_EXIST.getMessage());
			}
			//获取上传地址
			String importRecordsPath = importFileService.importInvestRecordsFilePath(listingVo.getId());
			//上传
			String fileNames = upload(fileMap, importRecordsPath);
			String[] fileNameArray = fileNames.split(":");
			String originalFilename = fileNameArray[0];
			String linkFileName = fileNameArray[1];
			//解析投资记录
			List<BizimportTradeDetail> list = importFileService.readInvestRecordsForFile(importRecordsPath + File.separator + linkFileName, listingVo.getListingCode(),super.getExchangeId());
			InvestRecordsResult recordsResult = investApplyService.assumInvestRecords(list);
			JSONObject retJson = resultSuccess();
			retJson.put("originalFilename", originalFilename);
			retJson.put("linkFileName", linkFileName);
			//TODO 需要访问地址
			retJson.put("accessPath", "");
			retJson.put("excelFilePath", importRecordsPath);
			retJson.put("recordsResult", recordsResult);
			retJson.put("duplicate", recordsResult.getDuplicateMap().values());
			return retJson.toJSONString();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "uploadInvestRecords", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage()).toJSONString();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "uploadInvestRecords", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage()).toJSONString();
		}
	}
	/**
	 * 保存投资明细
	 * @param param
	 * @return
	 */
	@RequestMapping("apply_register")
	@ResponseBody
	public Object applyRegister(InvestRecordsParam param) {
		try {
			if(StringUtils.isBlank(param.getExcelFileName()) || StringUtils.isBlank(param.getExcelFilePath()) ) {
				return resultError("请上传投资明细文件");
			}
			MemberOperatorPo operator = super.getMemberOperator();
			//新增
			investApplyService.addInvestRecords(param, operator.getMemberId(), operator.getExchangeId(), operator.getId(), operator.getRealName());
			return resultSuccess();
		} catch (FatpException e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "applyRegister", e));
			return resultError(StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "applyRegister", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 删除上传的交易明细文件
	 * @return
	 */
	@RequestMapping("/delfile")
	@ResponseBody
	public Object delFile(){
		try {
			String excelFilePath = request().getParameter("excelFilePath");
			String fileName = request().getParameter("fileName");
			FileUtil.fileDelete(excelFilePath,fileName);
			return resultSuccess();
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "delFile", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	/**
	 * 挂牌产品交易申请列表
	 * @return
	 */
	@RequestMapping("tradeapplylist")
	@ResponseBody
	public Object tradeApplyList(){
		String listingGuid = request().getParameter("id");
		if(StringUtils.isBlank(listingGuid)) {
			return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
		}
		ListingInfoVo listingVo = listingInfoService.getByListingGuid(listingGuid);
		if(listingVo == null) {
			return resultError(ErrorCode.LISTING_NOT_EXIST.getMessage());
		}
		request().setAttribute("listingInfo",listingVo);
		return new ModelAndView(viewPath + "/trade-apply-list");
	}

}
