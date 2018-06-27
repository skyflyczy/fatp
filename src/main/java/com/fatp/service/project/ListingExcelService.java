package com.fatp.service.project;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fatp.domain.listing.ListingInfo;
import com.fatp.domain.listing.ListingTrade;
import com.fatp.enums.YesNo;
import com.fatp.enums.project.ExpireDateStyle;
import com.fatp.enums.project.InterestType;
import com.fatp.service.BaseService;
import com.fatp.util.BigDecimalUtil;
import com.fatp.util.DateUtil;

@Service
public class ListingExcelService extends BaseService{
	
	private String[] titles = new String []{"产品名称","发行人","管理人","投资顾问","发行渠道","产品规模（元）",
			"产品期限","收益率类型","预期收益率（%）","起息日","到期日规则","到期日",
			"付息方式","单利/复利","计息频率","计息基准","到期日是否计息"};
	
	public void genExcel(List<ListingInfo> list, OutputStream os) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("挂牌产品明细");
		HSSFRow titleRow = genExcelTitle(sheet);
		if(CollectionUtils.isNotEmpty(list)) {
			for(int i = 0 ; i < list.size() ; i ++) {
				ListingInfo listingInfo = list.get(i);
				HSSFRow row = sheet.createRow(i + 1); 
				row.createCell(0).setCellValue(listingInfo.getListingFullName());
				row.createCell(1).setCellValue(listingInfo.getIssuer());
				row.createCell(2).setCellValue(listingInfo.getListingManager());
				row.createCell(3).setCellValue(listingInfo.getInvestManager());
				row.createCell(4).setCellValue(listingInfo.getPartnerBiz());
				row.createCell(5).setCellValue(BigDecimalUtil.formatMoney(listingInfo.getListingMoney()));
				row.createCell(6).setCellValue(listingInfo.getListingLimit() + listingInfo.getListingLimitTypeDesc());
				row.createCell(7).setCellValue(listingInfo.getInvestProfitTypeDesc());
				row.createCell(8).setCellValue(assumTradeMsg(listingInfo.getListingTradeList()));
				row.createCell(9).setCellValue(DateUtil.formatDate(listingInfo.getValueDate(),"yyyy-MM-dd"));
				ExpireDateStyle style = ExpireDateStyle.getStyleByValue(listingInfo.getExpireDateStyle());
				row.createCell(10).setCellValue(style == null ? "" : style.name());
				row.createCell(11).setCellValue(DateUtil.formatDate(listingInfo.getExpireDate(),"yyyy-MM-dd"));
				row.createCell(12).setCellValue(listingInfo.getPayInterestTypeDesc());
				row.createCell(13).setCellValue(listingInfo.getInterestType().intValue() == InterestType.单利.type ? InterestType.单利.name() : InterestType.复利.name());
				row.createCell(14).setCellValue(listingInfo.getInterestRateDesc());
				row.createCell(15).setCellValue(listingInfo.getInterestBaseDesc());
				row.createCell(16).setCellValue(listingInfo.getExpireDateInterest() == YesNo.是.value ? "是" : "否");
			}
		}
		for(int j=0; j< titleRow.getLastCellNum(); j++) {
			sheet.autoSizeColumn(j);
		}
		workbook.write(os);
		workbook.close();
	}
	
	private String assumTradeMsg(List<ListingTrade> list) {
		if(CollectionUtils.isEmpty(list)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(ListingTrade trade : list) {
			sb.append(BigDecimalUtil.formatMoney(trade.getMaxInvestMoney()))
			  .append(BigDecimalUtil.format(trade.getInvestProfit().multiply(new BigDecimal("100")), "0.000"))
			  .append("\r\n");
		}
		return sb.toString();
	}
	
	private HSSFRow genExcelTitle(HSSFSheet sheet) {
		HSSFRow titleRow=sheet.createRow(0); 
		for(int i = 0 ; i < titles.length ; i ++) {
			titleRow.createCell(i).setCellValue(titles[i]);
		}
		return titleRow;
	}

}
