package com.fatp.enums.project;

import org.apache.commons.lang3.StringUtils;

import com.fatp.enums.SortColumnsInterface;

public enum ListingSortColumns implements SortColumnsInterface {
	projectId("listing_base.Id"),
	projectCode("listing_base.ProjectCode"),
	projectMoney("listing_base.ProjectMoney"),
	investProfit("listing_trade.InvestProfit"),
	updateTime("IFNULL(project_bizresults.UpdateTime,listing_base.UpdateTime)");
	
	private String value;
	
	private ListingSortColumns(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
	
	public String getOrderField(String orderField) {
		if(StringUtils.isBlank(orderField))
			return null;
		ListingSortColumns[] array = ListingSortColumns.values();
    	for(int i=0; i<array.length; i++) {
    		if(orderField.equalsIgnoreCase(array[i].name())) {
    			return array[i].value;
    		}
    	}
    	return null;
	}
}
