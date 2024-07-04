package com.springmvc.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.BlogService;
import com.springmvc.service.impl.CustomUserDetailsService;

@Controller
@RequestMapping(value = "/admin/blog")
public class AdminBlogController {
	
	@Autowired 
	private CustomUserDetailsService userDetailsService;
	@Autowired 
	private BlogService blogService;
	
	@GetMapping
	public ModelAndView blog(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/blog");
		mav.addObject("userService", userDetailsService); 
		mav.addObject("blogs", blogService.getAllBlogs());
		  
		return mav;
	}
	
	@GetMapping(value = "/add")
	public ModelAndView addBlog(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/add-blog");
		
		return mav;
	}
}
