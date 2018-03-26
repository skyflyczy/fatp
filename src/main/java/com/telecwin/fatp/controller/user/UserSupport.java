package com.telecwin.fatp.controller.user;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.telecwin.fatp.controller.BaseController;
import com.telecwin.fatp.domain.PageData;
import com.telecwin.fatp.domain.UcUser;
import com.telecwin.fatp.enums.user.OrgType;
import com.telecwin.fatp.enums.user.UserIdentityDesc;
import com.telecwin.fatp.enums.user.UserStatusDesc;
import com.telecwin.fatp.service.user.UcUserService;

@Controller
public class UserSupport extends BaseController{
	
	@Autowired
	protected UcUserService ucUserService;

	/**
	 * 查询用户
	 * @param map
	 * @param allowStatusArray
	 * @return
	 */
	public List<UcUser> listMembers(Map<String, Object> map, UserStatusDesc[] allowStatusArray) {
		map.put("userIdentity", UserIdentityDesc.发行人.value);
		map.put("exchangeId", getExchangeId());
		map.put("sortColumns", "u.Id desc");
		map.put("userStatusList", handleUserStatusArray(allowStatusArray));
		Object createTimeEnd = map.get("createTimeEnd");
		if(createTimeEnd != null) {
			map.put("createTimeEnd", createTimeEnd.toString() + " 23:59:59");
		}
		Object userStatus = map.get("userStatus");
		if(userStatus != null && Integer.parseInt(userStatus.toString()) == UserStatusDesc.UNKNOWN.value) {
			map.remove("userStatus");
		}
		int pageNo = Integer.parseInt(map.get("pageCurrent").toString());
		int pageSize = Integer.parseInt(map.get("pageSize").toString());
		PageData<UcUser> pageData = ucUserService.pageFindUsersByCondition(map, pageNo, pageSize);
		List<UcUser> ucUserList = pageData == null ? null : pageData.getList();
		if(CollectionUtils.isNotEmpty(ucUserList)) {
			StringBuilder sb = new StringBuilder();
			for(UcUser o : ucUserList) {
				sb.append(o.getId()).append(",");
			}
			if(sb.length() > 0)
				sb.deleteCharAt(sb.length()-1);
			request().setAttribute("ids", sb.toString());
		}
		map.put("createTimeEnd", createTimeEnd);
		map.put("userStatus", userStatus);
		request().setAttribute("search", map);
		request().setAttribute("list", ucUserList);
		request().setAttribute("total", pageData == null ? 0 : pageData.getTotalsize());
		request().setAttribute("totalpage", pageData == null ? 0 : pageData.getTotalpage());
		request().setAttribute("pageCurrent", map.get("pageCurrent"));
		request().setAttribute("pageSize", map.get("pageSize"));
		request().setAttribute("OrgType", OrgType.values());
		request().setAttribute("userStatusList", allowStatusArray);
		return ucUserList;
	}
	
	private int[] handleUserStatusArray(UserStatusDesc[] allowStatusArray){
		int [] statusArray = new int[allowStatusArray.length];
		for(int i = 0 ; i < allowStatusArray.length; i++ ){
			statusArray[i] = allowStatusArray[i].value;
		}
		return statusArray;
	}
}
