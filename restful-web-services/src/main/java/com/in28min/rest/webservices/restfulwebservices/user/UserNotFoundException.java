package com.in28min.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4595126928231043210L;

	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
