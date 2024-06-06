package com.app.spring_restaurant_jwt_application.request;

import lombok.Data;

@Data
public class CreateIngredientRequest {

    private Long restaurantId;
    private String name;
    private Long ingredientCategoryId;
}
