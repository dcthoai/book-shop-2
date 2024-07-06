package com.springmvc.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.BlogService;
import com.springmvc.service.impl.CustomUserDetailsService;
import com.springmvc.service.impl.MediaService;

@Controller
public class UserBlogController {
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@GetMapping(value = "/blog")
	public ModelAndView blog(){
		ModelAndView mav = new ModelAndView("user/blog");
		mav.addObject("blogs", blogService.getAllBlogs());
		mav.addObject("mediaService", mediaService);
		
		return mav;
	}
	
	@GetMapping(value = "/blog/view")
	public ModelAndView showBlog(@RequestParam("id") Integer blogId) {
		ModelAndView mav = new ModelAndView("user/blog-detail");
		
		mav.addObject("blog", blogService.getById(blogId));
		mav.addObject("mediaService", mediaService);
		mav.addObject("userService", userService);
		
		return mav;
	}
}