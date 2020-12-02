package com.in28min.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {
	
	private String name;

	public String getName() {
		return name;
	}
	
	public PersonV1(String s) {
		this.name= s;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
