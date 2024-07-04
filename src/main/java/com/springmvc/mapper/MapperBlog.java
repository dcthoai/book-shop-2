package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Blog;

public class MapperBlog implements RowMapper<Blog>{

	@Override
	public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Blog blog = new Blog();

        blog.setId(rs.getInt("id"));
        
        blog.setAuthorId(rs.getInt("authorId"));
        blog.setThumbnailId(rs.getInt("thumbnailId"));
        blog.setTitle(rs.getString("title"));
        blog.setContent(rs.getString("content"));
        
        blog.setCreatedDate(rs.getTimestamp("createdDate"));
        blog.setModifiedDate(rs.getTimestamp("modifiedDate"));
        blog.setCreatedBy(rs.getString("createdBy"));
        blog.setModifiedBy(rs.getString("modifiedBy"));

        return blog;
    }
}