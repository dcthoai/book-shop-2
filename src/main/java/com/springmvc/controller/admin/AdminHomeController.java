package com.springmvc.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.service.impl.BookService;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/dashboard");
		mav.addObject("books", bookService.getNewestBooks());
		mav.addObject("bookService", bookService);	
		
		return mav;
	}
}
