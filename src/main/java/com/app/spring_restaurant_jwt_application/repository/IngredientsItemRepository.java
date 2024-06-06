package com.app.spring_restaurant_jwt_application.repository;

import com.app.spring_restaurant_jwt_application.model.IngredientsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, Long> {

//
//	List<IngredientsItem> findByRestaurantId(Long id);
//	@Query("SELECT e FROM IngredientsItem e "
//			+ "WHERE e.restaurant.id = :restaurantId "
//			+ "AND lower(e.name) = lower(:name)"
//			+ "AND e.category.name = :categoryName")
//	public IngredientsItem findByRestaurantIdAndNameIngoreCase(
//			@Param("restaurantId") Long restaurantId,
//			@Param("name") String name,
//			@Param("categoryName") String categoryName);
}
