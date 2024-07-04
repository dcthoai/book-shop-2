package com.springmvc.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.CustomUserDetailsService;

@Controller
@RequestMapping(value = "/admin/account")
public class AdminAccountController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@GetMapping(value = "")
	public ModelAndView userAccount(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/account");
		mav.addObject("users", customUserDetailsService.getAllUsers());
		
		return mav;
	}
}
