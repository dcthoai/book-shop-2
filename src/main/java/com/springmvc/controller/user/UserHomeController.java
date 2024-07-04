package com.springmvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.HomeService;
import com.springmvc.service.impl.MediaService;

@Controller
@RequestMapping(value = "")
public class UserHomeController {

	@Autowired
	private HomeService homeService;
	@Autowired 
	private MediaService mediaService;
	@Autowired
	private CustomUserDetailsService userService;
	
	@GetMapping(value = "/")
	public ModelAndView homepage() {
		
		ModelAndView mav = new ModelAndView("user/index");
		mav.addObject("slides", homeService.getAllSlides());
		mav.addObject("newBlogs", homeService.getNewBlogs());
		mav.addObject("mediaService", mediaService);
		mav.addObject("userService", userService);
		
		return mav;
	}
	
	@GetMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("user/about");
		return mav;
	}
}
