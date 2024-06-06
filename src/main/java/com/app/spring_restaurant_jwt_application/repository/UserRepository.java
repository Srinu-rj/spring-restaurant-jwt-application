package com.app.spring_restaurant_jwt_application.repository;

import com.app.spring_restaurant_jwt_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByEmail(String username);



//
//	@Query("SELECT u FROM User u Where u.status='PENDING'")
//	public List<User> getPenddingRestaurantOwners();
//
//	public User findByEmail(String username);

}
