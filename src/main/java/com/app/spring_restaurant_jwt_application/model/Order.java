package com.app.spring_restaurant_jwt_application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User customer;

	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;

	private Long totalAmount;
	
	private String orderStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne
	private Address deliveryAddress;

//	@JsonIgnore
	@OneToMany
	private List<OrderItem> items;

	@OneToOne
	private Payment payment;
	
	private int totalItem;
	
	private int totalPrice;

}
