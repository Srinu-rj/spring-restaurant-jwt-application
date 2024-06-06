package com.app.spring_restaurant_jwt_application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User customer;
    
    @ManyToOne
    private Restaurant restaurant;
    
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;
    
    private boolean readStatus;
    
    
}

