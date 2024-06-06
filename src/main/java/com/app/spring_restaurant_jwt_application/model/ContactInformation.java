package com.app.spring_restaurant_jwt_application.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {
    private String email;
    private String mobile;
    private String twitter;
    private String instagram;

   
}

