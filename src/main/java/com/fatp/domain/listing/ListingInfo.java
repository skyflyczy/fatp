package com.fatp.domain.listing;

import java.util.List;

import com.fatp.enums.project.ListingLimitType;
import com.fatp.po.project.ListingInfoPo;

/**
 * 挂牌
 * @author zhiya.chai
 * @date 2018年6月12日 下午3:42:11
 */
public class ListingInfo extends ListingInfoPo{
	
	private static final long serialVersionUID = 1366406720340777769L;
	
	private String createOperatorName;
	
	private List<ListingTrade> listingTradeList;

	/**
	 * 获取投资期限类型
	 * @return
	 */
	public String getListingLimitTypeDesc() {
		if(super.getListingLimitType() == null) {
			return "";
		}
		for(ListingLimitType desc : ListingLimitType.values()){
			if(desc.type == super.getListingLimitType()){
				return desc.toString();
			}
		}
		return "";
	}


	public String getCreateOperatorName() {
		return createOperatorName;
	}
	public void setCreateOperatorName(String createOperatorName) {
		this.createOperatorName = createOperatorName;
	}
	public List<ListingTrade> getListingTradeList() {
		return listingTradeList;
	}
	public void setListingTradeList(List<ListingTrade> listingTradeList) {
		this.listingTradeList = listingTradeList;
	}
	
}
