package com.springmvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.BlogService;
import com.springmvc.service.impl.MediaService;

@Controller
public class UserBlogController {
	
	@Autowired
	private MediaService mediaService;
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public ModelAndView blog(){
		ModelAndView mav = new ModelAndView("user/blog");
		mav.addObject("blogs", blogService.getAllBlogs());
		mav.addObject("mediaService", mediaService);
		
		return mav;
	}
}