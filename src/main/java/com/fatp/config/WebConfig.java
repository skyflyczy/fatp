package com.fatp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fatp.interceptor.FatpControllerLogInterceptor;
import com.fatp.interceptor.GlobalInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	
	@Autowired
	private GlobalInterceptor globalInterceptor;
	
	@Autowired
	private FatpControllerLogInterceptor logInterceptor;

	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return new ErrorPageRegister();
	}

	@Bean(name = { "multipartResolver" })
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxInMemorySize(4096);
		resolver.setMaxUploadSize(32505856);
		return resolver;
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.addUrlMappings("/","*.do");
		return registration;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(globalInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
	    registry.addInterceptor(logInterceptor).addPathPatterns("/**");
	}
}
