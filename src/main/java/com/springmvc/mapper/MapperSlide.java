package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.model.Slide;

public class MapperSlide implements RowMapper<Slide>{

	@Override
	public Slide mapRow(ResultSet rs, int rowNum) throws SQLException {
		Slide slide = new Slide();
		
		slide.setId(rs.getInt("id"));
		
        slide.setThumbnailId(rs.getInt("thumbnailId"));
        slide.setCaption(rs.getString("caption"));
        slide.setContent(rs.getString("content"));
        slide.setLink(rs.getString("link"));
        
        slide.setCreatedDate(rs.getTimestamp("createdDate"));
        slide.setModifiedDate(rs.getTimestamp("modifiedDate"));
        slide.setCreatedBy(rs.getString("createdBy"));
        slide.setModifiedBy(rs.getString("modifiedBy"));
		
		return slide;
	}
}