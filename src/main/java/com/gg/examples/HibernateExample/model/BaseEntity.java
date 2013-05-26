package com.gg.examples.HibernateExample.model;

import java.io.Serializable;


public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
