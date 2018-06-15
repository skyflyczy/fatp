package com.fatp.util;

import org.apache.commons.lang3.StringUtils;

import com.fatp.enums.OrderDirection;
import com.fatp.enums.SortColumnsInterface;

public class SortUtil {

	public static String getSortColumns(String orderField, String orderDirection, SortColumnsInterface defaultSortColumns) {
		orderField = defaultSortColumns.getOrderField(orderField);
		if(StringUtils.isBlank(orderField)) {
			orderField = defaultSortColumns.getValue();
		}
		orderDirection = OrderDirection.getOrderDirection(orderDirection);
		return orderField + " " + orderDirection;
	}
}
