package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Media;

public interface IMediaDAO extends IGenericDAO<Media>{
	public List<Media> getBookMedias(int bookId);
}