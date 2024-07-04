package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Slide;

public interface ISlideService {
	public List<Slide> getAllSlides();
	public int insertSlide(Slide slide);
	public boolean updateSlide(Slide slide);
	public boolean deleteSlide(int slideId);
	public Slide getById(int slideId);
}
