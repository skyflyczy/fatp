package com.fatp.enums.project;

import com.fatp.util.StringUtil;

public enum ListingLimitType {
	天(1),
	月(2),
	年(3);
	
	public int type;
	
	private ListingLimitType(int type){
		this.type = type;
	}
	
	public static ListingLimitType getListingLimitTypeByName(String name) {
		if(StringUtil.isBlank(name)) {
			return ListingLimitType.天;
		}
		for(ListingLimitType desc : ListingLimitType.values()){
			if(desc.name().equals(name)){
					return desc;
			}
		}
		return ListingLimitType.天;
	}
	
	public int getType() {
		return type;
	}
}
