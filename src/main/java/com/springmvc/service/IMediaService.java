package com.springmvc.service;

import com.springmvc.model.Media;

public interface IMediaService{
	public int insertMedia(Media media);
	public boolean updateMedia(Media media);
	public boolean deleteMedia(int id);
	public Media getMediaById(int id);
}