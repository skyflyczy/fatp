package com.fatp.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class ExceptionAdvice {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public Object handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
		logger.error("Handle request fail!", exception);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("statusCode", Integer.valueOf(300));
		jsonObj.put("message", "上传文件过大");
		return jsonObj;
	}
}
