package com.springmvc.model;

public class Blog extends AbstractModel {
	private int authorId, thumbnailId;
	private String title, content;

	public Blog() {
		super();
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(int thumbnailId) {
		this.thumbnailId = thumbnailId;
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
}