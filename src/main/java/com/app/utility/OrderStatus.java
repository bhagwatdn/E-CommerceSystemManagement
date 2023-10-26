package com.app.utility;

public enum OrderStatus {
	 PENDING("PENDING"),
	 CONFIRMED("CONFIRMED"),
	 SHIPPED("SHIPPED"), 
	 DELIVERED("DELIVERED"),
	 CANCELED("CANCELED");
	
	private String name;

	
    private OrderStatus(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	
    
	
   
	
}
