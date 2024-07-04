package com.springmvc.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.model.UserDetailsCustom;
import com.springmvc.service.impl.CustomUserDetailsService;

@Controller
@RequestMapping(value = "/user")
public class UserAccountController {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@GetMapping
	public ModelAndView accountPage(HttpServletRequest request) {
		ModelAndView mav;
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			String username = (String) session.getAttribute("username");
			UserDetailsCustom user = userService.findUserByUsername(username);
			
			if (user != null) {
				mav = new ModelAndView("user/account");
				mav.addObject("user", user);
				
				return mav;
			}
		}
		
		return new ModelAndView("redirect:/");
	}
}
