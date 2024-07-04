package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.BlogDAO;
import com.springmvc.dao.impl.SlideDAO;
import com.springmvc.model.Blog;
import com.springmvc.model.Slide;
import com.springmvc.service.IHomeService;

@Service
public class HomeService implements IHomeService{
	@Autowired
	private SlideDAO slideDAO;
	@Autowired
	private BlogDAO blogDAO;
	
	@Override
	public List<Slide> getAllSlides(){
		return slideDAO.getAllSlides();
	}

	@Override
	public List<Blog> getNewBlogs(){
		return blogDAO.getNewBlogs();
	}
}