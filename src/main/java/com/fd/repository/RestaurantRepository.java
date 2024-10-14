package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findByUsername(String username);
	
	Optional<List<Restaurant>> findAllByCity(String city);
	
	Optional<List<Restaurant>> findAllByPincode(String pincode);
}
