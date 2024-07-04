package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Blog;

public interface IBlogService {
	public List<Blog> getAllBlogs();
	public List<Blog> getNewestBlog();
	public int insertBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(int blogId);
	public Blog getById(int blogId);
	public List<Blog> searchByName(String key);
}
