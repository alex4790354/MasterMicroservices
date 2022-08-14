package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	  	/**
	  	We should get an Error  "This application has not explicit mapping" or
		.HttpMediaTypeNotAcceptableException: Could not find acceptable representation]
		 without getMessage() */

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
