package com.fatp.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

@ControllerAdvice
public class ResponsebodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {
	public final static String _ARG_NAME = "__RESPONSEBODY_RESULT";
	
	@Override
	protected void beforeBodyWriteInternal(
			MappingJacksonValue paramMappingJacksonValue,
			MediaType paramMediaType, MethodParameter paramMethodParameter,
			ServerHttpRequest paramServerHttpRequest,
			ServerHttpResponse paramServerHttpResponse) {
		HttpServletRequest servletRequest = ((ServletServerHttpRequest)paramServerHttpRequest).getServletRequest();
		servletRequest.setAttribute(_ARG_NAME, paramMappingJacksonValue.getValue());
	}

}
