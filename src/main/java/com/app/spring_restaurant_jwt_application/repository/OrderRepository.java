package com.app.spring_restaurant_jwt_application.repository;

import com.app.spring_restaurant_jwt_application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//	@Query("SELECT o FROM Order o WHERE o.customer.id = :userId")
//	List<Order> findAllUserOrders(@Param("userId")Long userId);
//
//	@Query("SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId")
//	List<Order> findOrdersByRestaurantId(@Param("restaurantId") Long restaurantId);
}
