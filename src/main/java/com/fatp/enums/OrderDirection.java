package com.fatp.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderDirection {
	asc,
	desc;
	
	public static String getOrderDirection(String orderDirection) {
		if(StringUtils.isBlank(orderDirection))
			return desc.name();
		OrderDirection[] array = OrderDirection.values();
    	for(int i=0; i<array.length; i++) {
    		if(orderDirection.equalsIgnoreCase(array[i].name())) {
    			return array[i].name();
    		}
    	}
    	return desc.name();
	}
}
