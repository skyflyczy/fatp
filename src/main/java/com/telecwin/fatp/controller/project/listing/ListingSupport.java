package com.telecwin.fatp.controller.project.listing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.huajin.baymax.util.DateUtils;
import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.domain.UcUserBankcard;
import com.telecwin.fatp.domain.project.ListingComplex;
import com.telecwin.fatp.domain.project.ProjectRecordComplex;
import com.telecwin.fatp.enums.project.ExpireDateType;
import com.telecwin.fatp.enums.project.ListingSortColumns;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.enums.project.PlatformFeeType;
import com.telecwin.fatp.enums.project.ProductTypeDesc;
import com.telecwin.fatp.enums.project.ProjectLimitType;
import com.telecwin.fatp.enums.project.ProjectSaleType;
import com.telecwin.fatp.enums.project.ReceiveUserType;
import com.telecwin.fatp.enums.project.SettleTypeDesc;
import com.telecwin.fatp.enums.project.ValueDateType;
import com.telecwin.fatp.enums.repay.RepayTypeDesc;
import com.telecwin.fatp.exception.ErrorCode;
import com.telecwin.fatp.exception.FatpException;
import com.telecwin.fatp.po.user.MemberOperatorPo;
import com.telecwin.fatp.service.project.ListingService;
import com.telecwin.fatp.service.project.ProjectRecordService;
import com.telecwin.fatp.service.sys.SysareaCityService;
import com.telecwin.fatp.service.sys.SysareaDistrictService;
import com.telecwin.fatp.service.sys.SysareaProvinceService;
import com.telecwin.fatp.service.sys.SystypeProjectService;
import com.telecwin.fatp.service.user.UcUserService;
import com.telecwin.fatp.service.user.UserBankCardService;
import com.telecwin.fatp.util.Constant;
import com.telecwin.fatp.util.SortUtil;

@Controller
public class ListingSupport extends BaseController{
	
	private static final ListingStatusDesc[] NEED_EDIT_STATUS = new ListingStatusDesc[]{ListingStatusDesc.待提交,ListingStatusDesc.审核退回,ListingStatusDesc.审核不通过};

	@Autowired
	private ProjectRecordService projectRecordService;
	@Autowired
	private ListingService listingService;
	@Autowired
	private UcUserService ucUserService;
	@Autowired
	private SysareaCityService sysareaCityService;
	@Autowired
	private SysareaProvinceService sysareaProvinceService;
	@Autowired
	private SysareaDistrictService sysareaDistrictService;
	@Autowired
	private UserBankCardService userBankCardService;
	@Autowired
	private SystypeProjectService systypeProjectService;
	
	
	protected String handleSortColumn(ListingSortColumns defaultListingSortColumns) {
		String orderField = request().getParameter("orderField");
		String orderDirection = request().getParameter("orderDirection");
		return SortUtil.getSortColumns(orderField, orderDirection, defaultListingSortColumns);
	}
	/**
	 * 获取需要编辑的挂牌项目
	 * @param map
	 */
	public void getProjectNeedEditList(Map<String,Object> map) {
		//可修改的备案信息状态
		int[] searchStatus = new int[NEED_EDIT_STATUS.length];
		for(int i=0; i<NEED_EDIT_STATUS.length; i++) {
			searchStatus[i] = NEED_EDIT_STATUS[i].value;
		}
		map.put("searchStatusList", searchStatus);
		map.put("memberId", super.getMemberId());
		Object createTimeEnd = map.get("createTimeEnd");
		if(createTimeEnd != null) {
			map.put("createTimeEnd", createTimeEnd.toString() + " 23:59:59");
		}
		Object expireDateEnd = map.get("expireDateEnd");
		if(expireDateEnd != null) {
			map.put("expireDateEnd", expireDateEnd.toString() + " 23:59:59");
		}
		Object projectStatus =  map.get("projectStatus");
		if(projectStatus == null || Integer.parseInt(projectStatus.toString()) == 0){
			map.remove("projectStatus");
		}
		map.put("sortColumns", handleSortColumn(ListingSortColumns.projectId));
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ListingComplex> pageData = listingService.pageFindByCondition(map, pageNo, pageSize);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("total", pageData.getTotalsize());
		map.put("createTimeEnd", createTimeEnd);
		map.put("expireDateEnd", expireDateEnd);
		map.put("projectStatus", projectStatus);
		map.put("projectStatusDesc", NEED_EDIT_STATUS);
		request().setAttribute("search", map);
	}
	/**
	 * 获取可挂牌备案列表
	 * @param map
	 * @return
	 */
	public List<ProjectRecordComplex> getCanQuotedRecordList(Map<String, Object> map){
		Object updateTimeEnd = map.get("updateTimeEnd");
		if(updateTimeEnd != null){
			map.put("updateTimeEnd", updateTimeEnd.toString() + " 23:59:59");
		}
		map.put("memberId", super.getMemberId());
		map.put("exchangeId", super.getExchangeId());
		map.put("sortColumns", "project_recordinfo.updateTime desc");
		int pageNo = Integer.parseInt(String.valueOf(map.get(Constant._PAGEINDEX)));
		int pageSize = Integer.parseInt(String.valueOf(map.get(Constant._PAGESIZE)));
		PageData<ProjectRecordComplex> pageData = projectRecordService.getCanQuotedRecordList(map, pageNo, pageSize);
		map.put("updateTimeEnd", updateTimeEnd);
		request().setAttribute("list", pageData.getList());
		request().setAttribute("search", map);
		request().setAttribute("total", pageData.getTotalsize());
		request().setAttribute("pageCurrent", pageNo);
		request().setAttribute("pageSize", pageSize);
		return pageData.getList();
	}
	
	/**
	 * 添加项目
	 * @param listing
	 * @return
	 * @return Object
	 * 
	 */
	public Object doCreate(ListingComplex listing) {
		try {
			setOperatorData(listing);
			listing.setProjectCost(listing.getInvestProfit()); //融资成本
			if(listing.getProductTypeId() == ProductTypeDesc.定向投资工具.value){
				listing.setPlatformFeeType(PlatformFeeType.年化.type);
			}else{
				listing.setValueDateChangeStyle(ValueDateType.放款日.type);
				listing.setExpireDateChangeStyle(ExpireDateType.固定期限.type);
				listing.setPlatformFeeType(PlatformFeeType.次.type);
			}
			if(listing.getProductTypeId() == ProductTypeDesc.定向融资计划.value){
				listing.setProjectUnitPrice(new BigDecimal("100"));
			}
			int id = listingService.addListing(listing);
			if(id <= 0) {
				return resultError(ErrorCode.LISTING_SAVE_ERROR.getMessage());
			} else {
				JSONObject obj = resultSuccess();
				obj.put("id", listing.getId());
				return obj;
			}
		} catch (FatpException e) {
			return resultError(e.getErrorCode().getMessage());
		}
	}
	
	private void setOperatorData(ListingComplex listing) {
		MemberOperatorPo operator = super.getMemberOperator();
		listing.setMemberId(operator.getMemberId());
		listing.setExchangeId(operator.getExchangeId());
		listing.setCreateOperatorId(operator.getId());
		listing.setUpateOperatorId(operator.getId());
		listing.setCreateOperatorName(operator.getRealName());
	}
	
	private ListingComplex listingView(int id){
		ListingComplex listingInfo = listingService.getListingDetailsById(id, super.getExchangeId());
		if(listingInfo.getProvinceId() != null) {
			listingInfo.setProName(sysareaProvinceService.getById(listingInfo.getProvinceId()).getProName());
		}
		if(listingInfo.getCityId() != null) {
			listingInfo.setCityName(sysareaCityService.getById(listingInfo.getCityId()).getCityName());
		}
		if(listingInfo.getIndustryId() != null) {
			listingInfo.setIndustryName(sysareaDistrictService.getById(listingInfo.getIndustryId()).getDisName());
		}
		request().setAttribute("obj", listingInfo);
		//兑付中间方的银行卡列表
		request().setAttribute("payInvestBankcard", userBankCardService.getUserBankcardById(listingInfo.getPayinvestId(), listingInfo.getPayinvestBankCardId()));
		UcUser payInvestUser = ucUserService.getAllById(listingInfo.getPayinvestId() ,super.getExchangeId());
		if(payInvestUser == null){
			request().setAttribute("payInvestName",null );
		}else{
			request().setAttribute("payInvestName",payInvestUser.getCompanyName());
		}
		//取收款银行卡
		Integer userId = listingInfo.getLoanUserId();
		if(listingInfo.getReceiveUserType() != null && listingInfo.getReceiveUserType() == ReceiveUserType.产品管理人.type) {
			userId = listingInfo.getMemberId();
		}
		UcUserBankcard bankcard = userBankCardService.getUserBankcardById(userId, listingInfo.getReceiveAccountId());
		request().setAttribute("bankcard", bankcard);
		request().setAttribute("bankcardList", userBankCardService.getUserBankcards(userId,super.getExchangeId()));
		//取项目类型
		request().setAttribute("systypeProjectList", systypeProjectService.findByProductTypeId(listingInfo.getProductTypeId()));
		//取还款方式
		request().setAttribute("systypeRepayList", RepayTypeDesc.values());
		//取项目承销机构
		request().setAttribute("saleagentList", listingService.findByProjectIdExcludeNoAgent(id));
		request().setAttribute("projectLimitTypeList", ProjectLimitType.values());
		request().setAttribute("saleTypeIdList", ProjectSaleType.values());
		//取备案信息
		if(listingInfo.getProjectRecordId() != null) {
			ProjectRecordComplex record = projectRecordService.getCanQuotedRecord(listingInfo.getProjectRecordId() , listingInfo.getExchangeId());
			request().setAttribute("record", record);
			request().setAttribute("canQuoteMoney", record.getProjectMoney().subtract(record.getQuotedMoney()).add(listingInfo.getProjectMoney()));
		}
		request().setAttribute("content", listingService.findContentByListingId(id));
		request().setAttribute("platformFeeTypeList", PlatformFeeType.values());
		return listingInfo;
	}
	
	/**
	 * 修改项目
	 * @param request
	 * @return
	 * @return String
	 */
	public void beforeEdit(int id) {
		ListingComplex listingInfo = listingView(id);
		if(listingInfo.getGuaranteeMoney() == null) {
			listingInfo.setGuaranteeMoney(BigDecimal.ZERO);
		}
		//兑付中间方的银行卡列表
		request().setAttribute("payInvestBankcardList", userBankCardService.getUserBankcards(listingInfo.getPayinvestId(),super.getExchangeId()));
		request().setAttribute("platformFee", listingInfo.getPlatformFee() == null ? Constant._PLAT_DEFAULT_FEE : listingInfo.getPlatformFee());
		request().setAttribute("receiveUserTypeDesc", ReceiveUserType.values());
		request().setAttribute("todayForJudge", DateUtils.getDate());
		request().setAttribute("settleTypeDesc", SettleTypeDesc.values());
		UcUser loanUser = ucUserService.getAllById(listingInfo.getLoanUserId() , super.getExchangeId());
		request().setAttribute("loanUserName", loanUser.getCompanyName());
	}
	
	/**
	 * 修改项目
	 * @param listing
	 * @return
	 * @return Object
	 * 
	 * 2015年6月29日 下午2:50:37
	 */
	public void doUpdate(ListingComplex listing,boolean toAudit) {
		if(listing.getSettleInvestDay() != null && listing.getSettleInvestDay().intValue() > 28) {
			throw new FatpException(ErrorCode.LISTING_PAYINVEST_DATE_28);
		}
		if(listing.getSettleInvestMonth() != null) {
			listing.setSettleInvestDay(listing.getSettleInvestMonth()*100 + listing.getSettleInvestDay());
		}
		setOperatorData(listing);
		listing.setProjectCost(listing.getInvestProfit()); //融资成本
		listing.setValueDateChangeStyle(ValueDateType.放款日.type);
		listing.setExpireDateChangeStyle(ExpireDateType.固定期限.type);
		listing.setProjectLimitTypeId(ProjectLimitType.天.type);
		if(toAudit) {
			listing.setProjectStatus(ListingStatusDesc.待审核.value);
		}
		listingService.updateListing(listing);
	}
	
}
