package com.org.model;

import java.io.Serializable;

public class Brand implements Serializable {

	private String id;
	private String name;
	
	public Brand() {
		super();
	}

	public Brand(String name) {
		super();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
