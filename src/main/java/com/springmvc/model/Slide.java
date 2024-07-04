package com.springmvc.model;

public class Slide extends AbstractModel{
	private int thumbnailId;
	private String caption, content , link;

	public Slide() {
		super();
	}

	public int getThumbnailId() {
		return thumbnailId;
	}

	public void setThumbnailId(int thumbnailId) {
		this.thumbnailId = thumbnailId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}