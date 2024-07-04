package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.IBlogDAO;
import com.springmvc.mapper.MapperBlog;
import com.springmvc.model.Blog;

@Repository
public class BlogDAO extends AbstractDAO<Blog> implements IBlogDAO{
	
	@Override
	public int insert(Blog blog) {
		String sql = "INSERT INTO `bookstore`.`blog` (`authorId`, `thumbnailId`, `title`, `content`, `createdBy`) "
					+ "VALUES (?, ?, ?, ?, ?)";
		
		int blogId = executeInsert(sql, blog.getAuthorId(),
										blog.getThumbnailId(),
										blog.getTitle(),
										blog.getContent(),
										blog.getCreatedBy());
		return blogId;
	}

	@Override
	public int update(Blog blog) {
		String sql = "UPDATE `bookstore`.`blog` SET "
						+ "`authorId` = ?, "
						+ "`thumbnailId` = ?, "
						+ "`title` = ?, "
						+ "`content` = ?, "
						+ "`modifiedBy` = ? WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, blog.getAuthorId(),
											  blog.getThumbnailId(),
											  blog.getTitle(),
											  blog.getContent(),
											  blog.getModifiedBy(),
											  blog.getId());
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `bookstore`.`blog` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}
	
	@Override
	public Blog getById(int id) {
		String sql = "SELECT * FROM `bookstore`.`blog` WHERE (`id` = ?)";
		
		List<Blog> listBlogs = executeQuery(sql, new MapperBlog(), id);
		return listBlogs.isEmpty() ? null : listBlogs.get(0);
	}
	
	@Override
	public List<Blog> getNewBlogs() {
		// Returns the 3 newest blogs
		String sql = "SELECT * FROM `bookstore`.`blog` ORDER BY `createdDate` DESC LIMIT 3";
		
		List<Blog> listBlogs = executeQuery(sql, new MapperBlog());
		return listBlogs;
	}

	@Override
	public List<Blog> getAllBlogs() {
		String sql = "SELECT * FROM `bookstore`.`blog`";
		
		List<Blog> listBlogs = executeQuery(sql, new MapperBlog()); 
		return listBlogs;
	}
}