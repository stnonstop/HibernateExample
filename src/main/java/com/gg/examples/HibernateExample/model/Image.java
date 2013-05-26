package com.gg.examples.HibernateExample.model;

import java.io.Serializable;

public class Image implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String filename;
	
	private int width;
	
	private int height;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
