package com.springmvc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.dao.IMediaDAO;
import com.springmvc.mapper.MapperMedia;
import com.springmvc.model.Media;

@Repository
public class MediaDAO extends AbstractDAO<Media> implements IMediaDAO{

	@Override
	public int insert(Media media) {
		String sql = "INSERT INTO `media` (`path`, `type`, `createdBy`)"
						+ " VALUES (?, ?, ?)";
		
		int mediaId = executeInsert(sql, media.getPath(),
										 media.getType(),
										 media.getCreatedBy());
										 
		return mediaId;
	}

	@Override
	public int update(Media media) {
		String sql = "UPDATE `media` "
						+ "SET `path` = ?, `type` = ?, `modifiedBy` = ? WHERE (`id` = ?)";
		
		int effectedRows = executeUpdate(sql, media.getPath(),
											  media.getType(),
											  media.getModifiedBy(),
											  media.getId());
		
		return effectedRows;
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM `media` WHERE (`id` = ?)";
		
		int affectedRows = executeUpdate(sql, id);
		
		if (affectedRows == 0)
			if (getById(id) != null)
				return 0;
			else
				return 1;
		
		return affectedRows;
	}
	
	@Override
	public Media getById(int id) {
		String sql = "SELECT * FROM `media` WHERE (`id` = ?)";
		List<Media> listMedias = executeQuery(sql, new MapperMedia(), id);
		
		return listMedias.isEmpty() ? null : listMedias.get(0);
	}

	@Override
	public List<Media> getBookMedias(int bookId) {
		String sql = "SELECT * FROM `media` as m "
						+ "JOIN `bookMedia` bm ON m.id = bm.mediaId "
						+ "JOIN `book` b ON b.id = bm.bookId "
						+ "WHERE b.id = ?";
		
		List<Media> listMedias = executeQuery(sql, new MapperMedia(), bookId);
		return listMedias;
	}
}