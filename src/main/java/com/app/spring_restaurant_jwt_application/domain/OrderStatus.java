package com.app.spring_restaurant_jwt_application.domain;

public enum OrderStatus {
    RECEIVED,
    PENDING,
    READY_FOR_PICKUP,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED
}
