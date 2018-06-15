package com.fatp.enums.project;

public enum ListingLimitType {
	天(1),
	月(2),
	年(3);
	
	public int type;
	
	private ListingLimitType(int type){
		this.type = type;
	}
	
	public static ListingLimitType getProjectLimitType(Integer type){
		if(type == null) {
			return ListingLimitType.天;
		}
		for(ListingLimitType desc : ListingLimitType.values()){
			if(desc.type == type){
				return desc;
			}
		}
		return ListingLimitType.天;
	}
	
	public int getType() {
		return type;
	}
}
