package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.BlogDAO;
import com.springmvc.model.Blog;
import com.springmvc.service.IBlogService;

@Service
public class BlogService implements IBlogService{

	@Autowired
	private BlogDAO blogDAO;
	
	@Override
	public List<Blog> getAllBlogs() {
		return blogDAO.getAllBlogs();
	}

	@Override
	public List<Blog> getNewestBlog() {
		return blogDAO.getNewBlogs();
	}
}
