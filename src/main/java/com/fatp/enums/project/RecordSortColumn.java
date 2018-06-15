package com.fatp.enums.project;

import org.apache.commons.lang3.StringUtils;

import com.fatp.enums.SortColumnsInterface;

public enum RecordSortColumn implements SortColumnsInterface {
	id("project_recordinfo.Id"),
	recordCode("project_recordinfo.RecordCode"),
	projectMoney("project_recordinfo.ProjectMoney"),
	investProfit("project_recordinfo.InvestProfit"),
	updateTime("project_recordinfo.UpdateTime");
	
	private String value;
	
	private RecordSortColumn(String value) {
        this.value = value;
    }

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	public String getOrderField(String orderField) {
		if(StringUtils.isBlank(orderField))
			return null;
		RecordSortColumn[] array = RecordSortColumn.values();
    	for(int i=0; i<array.length; i++) {
    		if(orderField.equalsIgnoreCase(array[i].name())) {
    			return array[i].value;
    		}
    	}
    	return null;
	}
}
