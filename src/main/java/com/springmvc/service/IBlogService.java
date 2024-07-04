package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Blog;

public interface IBlogService {
	public List<Blog> getAllBlogs();
	public List<Blog> getNewestBlog();
}
