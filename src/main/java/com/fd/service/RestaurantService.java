package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Restaurant;

@Service
public interface RestaurantService {

	Restaurant addRestaurant(Restaurant restaurant);
	
	Restaurant editRestaurant(Restaurant restaurant);
	
	String deleteRestaurant(long id);
	
	Restaurant getRestaurantById(long id);
	
	List<Restaurant> getAllRestaurants();	
	
	List<Restaurant> getAllRestaurantsByPincode(String pincode);

	List<Restaurant> getAllRestaurantsByCity(String city);
	
	
	Restaurant login(String username, String password);
}
