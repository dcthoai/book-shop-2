package com.springmvc.model;

public class Media extends AbstractModel {
	private String path, type;
	
	public Media() {
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}