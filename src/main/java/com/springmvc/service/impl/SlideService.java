package com.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.impl.SlideDAO;
import com.springmvc.model.Slide;
import com.springmvc.service.ISlideService;

@Service
public class SlideService implements ISlideService{

	@Autowired
	private SlideDAO slideDAO;
	
	@Autowired
	private MediaFileService mediaFileService;
	
	@Autowired
	private MediaService mediaService;
	
	@Override
	public List<Slide> getAllSlides() {
		return slideDAO.getAllSlides();
	}

	@Override
	public int insertSlide(Slide slide) {
		return slideDAO.insert(slide);
	}

	@Override
	public boolean updateSlide(Slide slide) {
		int affectedRows = slideDAO.update(slide);
		
		return affectedRows > 0;
	}

	@Override
	public boolean deleteSlide(int slideId) {
		Slide slide = getById(slideId);
		
		if (slide != null) {
			mediaFileService.deleteFile(mediaService.getMediaById(slide.getThumbnailId()).getPath());
			mediaService.deleteMedia(slide.getThumbnailId());
		}
		
		int affectedRows = slideDAO.delete(slideId);
		
		return affectedRows > 0;
	}

	@Override
	public Slide getById(int slideId) {
		return slideDAO.getById(slideId);
	}

}
