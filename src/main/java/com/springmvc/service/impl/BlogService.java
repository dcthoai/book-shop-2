package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.BlogDAO;
import com.springmvc.model.Blog;
import com.springmvc.model.Book;
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

	@Override
	public int insertBlog(Blog blog) {
		return blogDAO.insert(blog);
	}

	@Override
	public boolean updateBlog(Blog blog) {
		int affectedRows = blogDAO.update(blog);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteBlog(int blogId) {
		int affectedRows = blogDAO.delete(blogId);
		
		return affectedRows > 0;
	}

	@Override
	public Blog getById(int blogId) {
		return blogDAO.getById(blogId);
	}

	@Override
	public List<Blog> searchByName(String key) {
		String s = "%" + key.trim() + "%";

		List<Blog> listBlogs = blogDAO.searchByName(s);
		return listBlogs;
	}
}
