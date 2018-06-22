package com.fatp.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class FatpContext implements ApplicationContextAware{
	
	/**
	 * spring context
	 */
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

}
