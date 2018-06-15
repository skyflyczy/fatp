
package com.fatp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fatp.util.SessionUtil;
import com.huajin.baymax.controller.interceptor.ControllerLogInterceptor;
import com.huajin.baymax.memcache.client.MemcachedCache;
import com.huajin.baymax.session.SessionFactory;


/**
 * 全局拦截器
 * @author zhiya.chai
 * 2015年7月29日 下午3:33:47
 */
@Component
public class FatpControllerLogInterceptor extends ControllerLogInterceptor  {
	@Autowired
	protected MemcachedCache memcachedCache;
	
	@Override
	protected String getOperatorId(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = SessionFactory.getCurrentSessionId(request, response);
		Integer userId = SessionUtil.getOperatorId(memcachedCache, sessionId);
		if(userId == null)
			return null;
		return userId.toString();
	}

	@Override
	protected String getUserId(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
