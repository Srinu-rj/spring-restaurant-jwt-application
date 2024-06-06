package com.app.spring_restaurant_jwt_application.response;


import com.app.spring_restaurant_jwt_application.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
	
	private String message;
	private String jwt;
	private USER_ROLE role;
	


}
