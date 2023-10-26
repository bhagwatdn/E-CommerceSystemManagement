package com.app.globalexceptionhandler;

public class OrderNotFoundException extends RuntimeException {
	
	public OrderNotFoundException(String msg){
		super(msg);
	}
}
