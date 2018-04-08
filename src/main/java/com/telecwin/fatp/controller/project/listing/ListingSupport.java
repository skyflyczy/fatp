package com.telecwin.fatp.controller.project.listing;

import java.util.Map;

import org.springframework.stereotype.Controller;

import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.enums.project.ListingSortColumns;
import com.telecwin.fatp.enums.project.ListingStatusDesc;
import com.telecwin.fatp.util.SortUtil;

@Controller
public class ListingSupport extends BaseController{
	
	private static final ListingStatusDesc[] NEED_EDIT_STATUS = new ListingStatusDesc[]{ListingStatusDesc.待提交,ListingStatusDesc.审核退回,ListingStatusDesc.审核不通过};

	
	protected String handleSortColumn(ListingSortColumns defaultListingSortColumns) {
		String orderField = request().getParameter("orderField");
		String orderDirection = request().getParameter("orderDirection");
		return SortUtil.getSortColumns(orderField, orderDirection, defaultListingSortColumns);
	}
	
	public void getProjectNeedEditList(Map<String,Object> map) {
		//可修改的备案信息状态
		int[] searchStatus = new int[NEED_EDIT_STATUS.length];
		for(int i=0; i<NEED_EDIT_STATUS.length; i++) {
			searchStatus[i] = NEED_EDIT_STATUS[i].value;
		}
		map.put("searchStatusList", searchStatus);
		map.put("memberId", super.getMemberId());
		Object createTimeEnd = map.get("createTimeEnd");
		if(createTimeEnd != null) {
			map.put("createTimeEnd", createTimeEnd.toString() + " 23:59:59");
		}
		Object expireDateEnd = map.get("expireDateEnd");
		if(expireDateEnd != null) {
			map.put("expireDateEnd", expireDateEnd.toString() + " 23:59:59");
		}
		Object projectStatus =  map.get("projectStatus");
		if(projectStatus == null || Integer.parseInt(projectStatus.toString()) == 0){
			map.remove("projectStatus");
		}
		map.put("sortColumns", handleSortColumn(ListingSortColumns.projectId));
		
		map.put("createTimeEnd", createTimeEnd);
		map.put("expireDateEnd", expireDateEnd);
		map.put("projectStatus", projectStatus);
		map.put("projectStatusDesc", NEED_EDIT_STATUS);
		request().setAttribute("search", map);
	}
}
