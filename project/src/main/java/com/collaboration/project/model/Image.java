package com.collaboration.project.model;

import org.springframework.web.multipart.MultipartFile;

public class Image {
	
	private String name;
	private MultipartFile image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
