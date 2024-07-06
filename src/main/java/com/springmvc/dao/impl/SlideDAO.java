package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.ISlideDAO;
import com.springmvc.mapper.MapperSlide;
import com.springmvc.model.Slide;

@Repository
public class SlideDAO extends AbstractDAO<Slide> implements ISlideDAO{
	
	@Override
	public int insert(Slide slide) {
		String sql = "INSERT INTO `slide` (`thumbnailId`, `caption`, `content`, `link`, `createdBy`) "
						+ "VALUES (?, ?, ?, ?, ?)";
		
		int slideId = executeInsert(sql, slide.getThumbnailId(),
										 slide.getCaption(),
										 slide.getContent(),
										 slide.getLink(),
										 slide.getCreatedBy());
		return slideId;
	}

	@Override
	public int update(Slide slide) {
		String sql = "UPDATE `slide` SET "
						+ "`thumbnailId` = ?, "
						+ "`caption` = ?, "
						+ "`content` = ?, "
						+ "`link` = ?, "
						+ "`modifiedBy` = ? WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, slide.getThumbnailId(),
											  slide.getCaption(),
											  slide.getContent(),
											  slide.getLink(),
											  slide.getModifiedBy(),
											  slide.getId());
		return affectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `slide` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}
	
	@Override
	public Slide getById(int id) {
		String sql = "SELECT * FROM `slide` WHERE (`id` = ?)";
		List<Slide> listSlides = executeQuery(sql, new MapperSlide(), id);
		
		return listSlides.isEmpty() ? null : listSlides.get(0);
	}

	@Override
	public List<Slide> getAllSlides() {
		String sql = "SELECT * FROM `slide` ORDER BY `createdDate` DESC";
		List<Slide> listSlides = executeQuery(sql, new MapperSlide());
		
		return listSlides;
	}
}