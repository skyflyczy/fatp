package com.telecwin.fatp.controller.offsite;

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
import com.huajin.baymax.logger.XMsgError;
import com.huajin.baymax.logger.Xlogger;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.controller.param.InvestRecordsParam;
import com.telecwin.fatp.domain.GlobalFile;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.offsite.BizimportTradeDetail;
import com.telecwin.fatp.domain.offsite.InvestApply;
import com.telecwin.fatp.domain.offsite.InvestRecordsResult;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.enums.offsite.ApplyStatus;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.interceptor.FlashUpload;
import com.telecwin.fatp.po.offsite.BizimportSummaryPo;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.GlobalFileService;
import com.telecwin.fatp.service.ImportFileService;
import com.telecwin.fatp.service.offsite.BizimportSummaryService;
import com.telecwin.fatp.service.offsite.InvestApplyService;
import com.telecwin.fatp.service.project.ListingService;
import com.telecwin.fatp.util.Constant;
import com.telecwin.fatp.util.SessionUtil;

/**
 * 投资明细登记
 */
@Controller
@RequestMapping("/offsite/invest")
public class InvestApplyController extends BaseController{

	protected static final ListingStatusDesc[] INVEST_REGISTER_STATUS = new ListingStatusDesc[]{ListingStatusDesc.认购中,ListingStatusDesc.认购结束,ListingStatusDesc.发行成功};
	
	private String viewPath = super.viewPath + "/offsite/invest";
	
	@Autowired
	private InvestApplyService investApplyService;
	@Autowired
	private ListingService listingService;
	@Autowired
	private ImportFileService importFileService;
	@Autowired
	private BizimportSummaryService bizimportSummaryService;
	@Autowired
	private GlobalFileService globalFileService;
	/**
	 * 可登记列表
	 * @return
	 */
	@RequestMapping("reglist")
	public String list(){
		Map<String,Object> map = paramToMap(request());
		//处理查询状态
		delSearchStatus(map, INVEST_REGISTER_STATUS);
		map.put("exchangeId", super.getExchangeId());
		//后台调查询list
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<InvestApply> pageData = investApplyService.getCanRegList(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		request().setAttribute("search", map);
		request().setAttribute("projectStatusDesc", INVEST_REGISTER_STATUS);
		return viewPath + "/reglist";
	}
	/**
	 * 前往投资明细登记页面
	 * @return
	 */
	@RequestMapping("apply_register")
	@ResponseBody
	public Object toApplyRegister() {
		String projectGuid = request().getParameter("id");
		InvestApply apply = investApplyService.getCanRegByProjectGuid(projectGuid, getExchangeId());
		if(apply == null) {
			return resultError("此挂牌产品不存在。");
		}
		//验证是否可以进行登记
		boolean canApply = investApplyService.canInvestApply(apply.getProjectStatus().intValue());
		if(!canApply) {
			return resultError("此挂牌产品状态不正确，不能进行投资明细登记。");
		}
		try {
			//如果有申请登记没有提交审核，则拿到次申请登记进行编辑
			if(StringUtils.isNotBlank(apply.getApplyGuid())) {
				BizimportSummaryPo summary = bizimportSummaryService.getSummaryByApplyGuid(apply.getApplyGuid());
				if(summary != null) {
					request().setAttribute("summary", summary);
					GlobalFile golbalFile = globalFileService.getGlobalFileById(summary.getGlobalFileId());
					request().setAttribute("globalFile", golbalFile);
					List<BizimportTradeDetail> list = importFileService.readInvestRecordsForFile(golbalFile.getFilePath(), apply.getProjectName(), apply.getProjectCode(), super.getMemberId(), super.getExchangeId());
					InvestRecordsResult result = investApplyService.assumInvestRecords(apply.getBuyTimeStart(),apply.getBuyTimeEnd(),apply.getInvestAmountMax(),apply.getInvestAmountMin(),apply.getInvestAmountAppend(), list);
					request().setAttribute("investRecordsResult", result);
				}
			}
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "apply_register", e));
		}
		request().setAttribute("jsessionid", SessionUtil.getEncryptSessionId(request(), response()));
		request().setAttribute("project", apply);
		request().setAttribute("valueDate", apply.getValueDate());
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
			String projectId = multipartRequest.getParameter("projectId");
			if(StringUtils.isBlank(projectId)) {
				return resultError(ErrorCode.SYSTEM_PARAMETERS_EMPTY.getMessage());
			}
			ListingComplex listing = listingService.getListingDetailsById(Integer.parseInt(projectId), super.getExchangeId());
			if(listing == null) {
				return resultError("此挂牌产品不存在。");
			}
			boolean canApply = investApplyService.canInvestApply(listing.getProjectStatus().intValue());
			if(!canApply) {
				return resultError("此挂牌产品状态不正确，不能进行投资明细登记。");
			}
			//获取上传地址
			String importRecordsPath = importFileService.importInvestRecordsFilePath(listing.getId());
			//上传
			String fileNames = upload(fileMap, importRecordsPath);
			String[] fileNameArray = fileNames.split(":");
			String originalFilename = fileNameArray[0];
			String linkFileName = fileNameArray[1];
			//解析投资记录
			List<BizimportTradeDetail> list = importFileService.readInvestRecordsForFile(importRecordsPath + File.separator + linkFileName, listing.getProjectName(), listing.getProjectCode(), super.getMemberId(), super.getExchangeId());
			InvestRecordsResult result = investApplyService.assumInvestRecords(listing.getBuyTimeStart(),listing.getBuyTimeEnd(),listing.getInvestAmountMax(),listing.getInvestAmountMin(),listing.getInvestAmountAppend(),list);
			JSONObject retJson = resultSuccess();
			retJson.put("originalFilename", originalFilename);
			retJson.put("linkFileName", linkFileName);
			//TODO 需要访问地址
			retJson.put("accessPath", "");
			retJson.put("excelFilePath", importRecordsPath);
			retJson.put("totalNum", result.getTotalNum());
			retJson.put("totalMoney", result.getTotalMoney());
			retJson.put("totalInvesters", result.getTotalInvesters());
			retJson.put("overBuyTime", result.getOverBuyTime());
			retJson.put("overInvestLimit", result.getOverInvestLimit());
			retJson.put("lessBuy", result.getLessBuy());
			retJson.put("notEqAppend", result.getNotEqAppend());
			retJson.put("duplicate", result.getDuplicateMap().values());
			return retJson.toJSONString();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "uploadInvestRecords", e));
			return resultError(StringUtils.isBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "uploadInvestRecords", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	public Object editBizimportApply(InvestRecordsParam param) {
		try {
			if(StringUtils.isBlank(param.getExcelFileName()) || StringUtils.isBlank(param.getExcelFilePath()) ) {
				return resultError("请上传投资明细文件");
			}
			MemberOperatorPo operator = super.getMemberOperator();
			ApplyStatus status = param.getSubmit() == 0 ? ApplyStatus.待提交 : ApplyStatus.登记中;
			if(StringUtils.isNotBlank(param.getApplyGuid())) {
				//更新
				investApplyService.updateInvestRecords(param, operator.getMemberId(), operator.getExchangeId(), operator.getId(), operator.getRealName(), status);
			} else {
				//新增
				investApplyService.addInvestRecords(param, operator.getMemberId(), operator.getExchangeId(), operator.getId(), operator.getRealName(),status);
			}
			return resultSuccess();
		} catch (FatpException e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "editBizimportApply", e));
			return resultError(StringUtils.isBlank(e.getMessage()) ? e.getMessage() : e.getErrorCode().getMessage());
		} catch (Exception e) {
			Xlogger.error(XMsgError.buildSimple(getClass().getName(), "editBizimportApply", e));
			return resultError(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}
	
	private void delSearchStatus(Map<String,Object> map,ListingStatusDesc[] statusArray){
		int[] searchStatus = new int[statusArray.length];
		for(int i=0; i<statusArray.length; i++) {
			searchStatus[i] = statusArray[i].value;
		}
		map.put("searchStatusList", searchStatus);
	}

}
