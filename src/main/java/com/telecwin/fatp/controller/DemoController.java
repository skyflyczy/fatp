package com.telecwin.fatp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/test")
	public String test(){
		System.out.println("");
		ModelAndView view = new ModelAndView();
		view.setViewName("jsp/index");
		return "WEB-INF/jsp/index";
	}
}
