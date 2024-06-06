package com.app.spring_restaurant_jwt_application.repository;

import com.app.spring_restaurant_jwt_application.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
