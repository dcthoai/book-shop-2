package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.BookMedia;

public class MapperBookMedia implements RowMapper<BookMedia>{

	@Override
	public BookMedia mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookMedia bookMedia = new BookMedia();
		
		bookMedia.setId(rs.getInt("id"));
		
        bookMedia.setBookId(rs.getInt("bookId"));
        bookMedia.setMediaId(rs.getInt("mediaId"));
        
        bookMedia.setCreatedDate(rs.getTimestamp("createdDate"));
        bookMedia.setModifiedDate(rs.getTimestamp("modifiedDate"));
        bookMedia.setCreatedBy(rs.getString("createdBy"));
        bookMedia.setModifiedBy(rs.getString("modifiedBy"));
		
		return bookMedia;
	}
}