package com.selenium.model;

import java.io.InputStream;

//Model used in jasper report template (.jrxml file)
public class ImageBean {

	//The attributes must have the same name and type of the jasper report
	private InputStream image;
	private String imageName;
	
	public InputStream getImage() {
		return image;
	}
	
	public void setImage(InputStream image) {
		this.image = image;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
