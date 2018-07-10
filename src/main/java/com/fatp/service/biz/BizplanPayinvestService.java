package com.fatp.service.biz;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatp.domain.biz.BizplanPayinvest;
import com.fatp.domain.biz.BizplanRepay;
import com.fatp.domain.listing.ListingInfo;
import com.fatp.enums.biz.PayinvestStatus;
import com.fatp.enums.biz.RepayStatus;
import com.fatp.exception.FatpException;
import com.fatp.service.BaseService;
import com.fatp.service.datasupprot.biz.BizplanPayinvestDataSupportService;
import com.fatp.service.datasupprot.biz.BizplanRepayDataSupportService;
import com.fatp.util.StringUtil;

@Service
public class BizplanPayinvestService extends BaseService{

	@Autowired
	private BizplanPayinvestDataSupportService bizplanPayinvestDataSupportService;
	@Autowired
	private BizplanRepayDataSupportService bizplanRepayDataSupportService;
	/**
	 * 查找兑付计划
	 * @param map
	 * @return
	 */
	public List<BizplanPayinvest> findPlanPayinvest(Map<String, Object> map) {
		List<BizplanPayinvest> list = bizplanPayinvestDataSupportService.findPlanPayinvest(map);
		return list;
	}
	/**
	 * 得到兑付状态
	 * @param map
	 * @return 
	 * @return
	 */
	public PayinvestStatus getPayinvestStatus(Map<String,Object> map) {
		List<BizplanPayinvest> list = bizplanPayinvestDataSupportService.findPlanPayinvest(map);
		for(BizplanPayinvest payinvest : list) {
			if(payinvest.getPayinvestStatus() == PayinvestStatus.未兑付.status) {
				return PayinvestStatus.未兑付;
			}
		}
		return PayinvestStatus.兑付完成;
	}
	/**
	 * 兑付完成
	 * @param repayPlanGuid
	 * @param operatorId
	 */
	public void payinvestCompleted(String repayPlanGuid,int operatorId) {
		BizplanRepay planRepay = bizplanRepayDataSupportService.getPlanRepayByGuid(repayPlanGuid);
		if(planRepay == null) {
			throw new FatpException("没有还款计划");
		}
		if(planRepay.getRepayStatus() != RepayStatus.还款完成.status) {
			throw new FatpException("请先还款，后兑付");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("payinvestStatus", PayinvestStatus.兑付完成.status);
		map.put("updateOperatorId", operatorId);
		map.put("repayPlanId", planRepay.getId());
		bizplanPayinvestDataSupportService.updatePayinvestStatusByPlanRepayId(map);
	}
	/**
	 * 生成兑付明细Excel
	 * @param list
	 * @param os
	 * @throws IOException
	 */
	public void genPayinvestListExcel(List<BizplanPayinvest> list, OutputStream os,ListingInfo listingInfo) throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook workbook = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet = workbook.createSheet("兑付明细");
		if(CollectionUtils.isNotEmpty(list)) {
			for(int i = 0 ; i < list.size() ; i ++) {
				BizplanPayinvest payinvest = list.get(0);
				HSSFRow currentRow = sheet.createRow(i);
				//TODO 转账类型？行内转帐填1；同城跨行转帐填2；异地汇款填3；超级网银实时转账4；超级网银有5万元/笔限制
				currentRow.createCell(0).setCellValue("1");
				//付款人清分子账号：必填，最大14位长度
				currentRow.createCell(1).setCellValue(StringUtil.isNotBlank(listingInfo.getSettleCardAccount()) ? listingInfo.getSettleCardAccount() : "");
				//留空
				currentRow.createCell(2).setCellValue("");
				//收款人账号栏：必填，最大32位
				currentRow.createCell(3).setCellValue(payinvest.getCardAccount());
				//收款人名称栏：必填，最大90个汉字
				currentRow.createCell(4).setCellValue(payinvest.getInvestUserRealName());
				//收款方所在省：异地汇款必填，行内转帐和同城跨行不必填，填入视为空，最大10个汉字
				currentRow.createCell(5).setCellValue("");
				//收款方所在市县：异地汇款必填，行内转帐和同城跨行不必填，最大10个汉字填入视为空
				currentRow.createCell(6).setCellValue("");
				//收款方开户行名称：同城跨行、异地汇款必填，行内转帐不必填，填入视为空，最大90个汉字
				currentRow.createCell(7).setCellValue(payinvest.getSubBankName());
				//金额栏：必填，最大百亿位，可以有2位小数位，也可没有小数位
				currentRow.createCell(8).setCellValue(payinvest.getPrincipal().add(payinvest.getInterest()).doubleValue());
				//预约转账标志，非必填，不填留空，要预约转账时填Y
				currentRow.createCell(9).setCellValue("");
				//TODO 用途，必输，请输入款项真实用途，企业对用途的真实性负责，最大50位
				currentRow.createCell(10).setCellValue("兑付");
				//预约转账日期：如预约转账标志设为Y则必填，没有设为Y不必填，格式为YYYYMMDD,暂不支持预约转账
				currentRow.createCell(11).setCellValue("");
				//币种：留空
				currentRow.createCell(12).setCellValue("");
				//钞汇标志：留空
				currentRow.createCell(13).setCellValue("");
				//联行号：跨行交易收款方联行号，非必填，如填写将加快到账速度。不填写则落单，银行作业人员添加行号后再发出。最大12位
				currentRow.createCell(14).setCellValue("");
			}
		}
		workbook.write(os);
		workbook.close();
	}
}
