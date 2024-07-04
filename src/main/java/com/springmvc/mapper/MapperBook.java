package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Book;

public class MapperBook implements RowMapper<Book>{

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		
		book.setId(rs.getInt("id"));
		
        book.setTitle(rs.getString("title"));
        book.setDescription(rs.getString("description"));
        book.setSize(rs.getString("size"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        
        book.setLanguageId(rs.getInt("languageId"));
        book.setCategoryId(rs.getInt("categoryId"));
        book.setThumbnailId(rs.getInt("thumbnailId"));
        book.setPages(rs.getInt("pages"));
        book.setWeight(rs.getInt("weight"));
        book.setPurchases(rs.getInt("purchases"));
        book.setRate(rs.getInt("rate"));
        book.setStock(rs.getInt("stock"));
        
        book.setPrice(rs.getLong("price"));
        book.setDiscount(rs.getFloat("discount"));
        book.setReleaseDate(rs.getDate("releaseDate"));
        
        book.setCreatedDate(rs.getTimestamp("createdDate"));
        book.setModifiedDate(rs.getTimestamp("modifiedDate"));
        book.setCreatedBy(rs.getString("createdBy"));
        book.setModifiedBy(rs.getString("modifiedBy"));

		return book;
	}
}