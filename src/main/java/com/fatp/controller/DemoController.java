package com.fatp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fatp.interceptor.WithoutAuth;
import com.fatp.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	
	@Autowired
	private DemoService demoService;

	@RequestMapping("/test")
	public String test(){
		System.out.println("");
		ModelAndView view = new ModelAndView();
		view.setViewName("jsp/index");
		return "WEB-INF/jsp/index";
	}
	@RequestMapping("/testT")
	@ResponseBody
	@WithoutAuth
	public Object testT() {
		try {
			demoService.testTransactional();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSuccess();
	}
}
