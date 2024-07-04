package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Slide;

public interface ISlideDAO extends IGenericDAO<Slide>{
	public List<Slide> getAllSlides();
}