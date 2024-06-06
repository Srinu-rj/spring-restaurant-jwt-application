package com.app.spring_restaurant_jwt_application.request;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long restaurantId;
    
    private double rating;
    
    private String reviewText;

	
}
