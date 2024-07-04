package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Blog;

public interface IBlogDAO extends IGenericDAO<Blog>{
	public List<Blog> getNewBlogs();
	public List<Blog> getAllBlogs();
	public List<Blog> searchByName(String key);
}