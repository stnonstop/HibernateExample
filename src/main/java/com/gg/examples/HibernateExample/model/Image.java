package com.gg.examples.HibernateExample.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="width")
	private int width;
	
	@Column(name="height")
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
