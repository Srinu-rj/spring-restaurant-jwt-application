package com.app.spring_restaurant_jwt_application.request;

import com.app.spring_restaurant_jwt_application.model.Address;
import lombok.Data;

@Data
public class CreateOrderRequest {
 
	private Long restaurantId;
	
	private Address deliveryAddress;
	
    
}
