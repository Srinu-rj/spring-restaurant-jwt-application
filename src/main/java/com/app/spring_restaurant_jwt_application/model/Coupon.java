package com.app.spring_restaurant_jwt_application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    private double discountAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validityPeriod;

    private String termsAndConditions;
    
   
}

