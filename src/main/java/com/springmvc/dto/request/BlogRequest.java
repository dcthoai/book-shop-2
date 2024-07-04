package com.springmvc.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class BlogRequest {
	private Integer id;
	private String title, content;
	private MultipartFile thumbnail;
	
	public BlogRequest() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}
}
