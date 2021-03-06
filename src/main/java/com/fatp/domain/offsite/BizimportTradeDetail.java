package com.fatp.domain.offsite;

import com.fatp.enums.user.IdTypeDesc;
import com.fatp.po.offsite.BizimportTradeDetailPo;

public class BizimportTradeDetail extends BizimportTradeDetailPo{
	
	private static final long serialVersionUID = 8132784432216035812L;
	private String listingCode;
	private String listingFullName;
	
	public String getListingCode() {
		return listingCode;
	}
	public void setListingCode(String listingCode) {
		this.listingCode = listingCode;
	}
	public String getListingFullName() {
		return listingFullName;
	}
	public void setListingFullName(String listingFullName) {
		this.listingFullName = listingFullName;
	}
	
	public String getIdTypeDesc(){
		if(super.getIdTypeId() == null) {
			return IdTypeDesc.其他.name();
		}
		return IdTypeDesc.fromIdIdType(super.getIdTypeId()).name();
	}
}
