package com.springmvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.MediaDAO;
import com.springmvc.model.Media;
import com.springmvc.service.IMediaService;

@Service
public class MediaService implements IMediaService{
	@Autowired
	private MediaDAO mediaDAO;

	@Override
	public int insertMedia(Media media) {
		int mediaId = mediaDAO.insert(media);
		
		return mediaId;
	}

	@Override
	public boolean updateMedia(Media media) {
		int affectedRows = mediaDAO.update(media);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteMedia(int id) {
		int affectedRows = mediaDAO.delete(id);
		
		return affectedRows > 0;
	}
	
	@Override
	public Media getMediaById(int id) {
		return mediaDAO.getById(id);
	}
}