package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Restaurant;
import com.fd.exceptions.InvalidPasswordException;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepo;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Restaurant restaurant = restaurantRepo.findByUsername(username).get();
//		
//		return new User(restaurant.getUsername(), restaurant.getPassword(), new ArrayList<>());
//	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepo.save(restaurant);
	}

	@Override
	public Restaurant editRestaurant(Restaurant restaurant) {
		restaurantRepo.findById(restaurant.getId()).orElseThrow(() -> new NotFoundException("Restaurant Not Found."));
		return restaurantRepo.save(restaurant);
	}

	@Override
	public String deleteRestaurant(long id) {
		restaurantRepo.findById(id).orElseThrow(() -> new NotFoundException("Restaurant Not Found."));
		restaurantRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public Restaurant getRestaurantById(long id) {
		return restaurantRepo.findById(id).orElseThrow(() -> new NotFoundException("Restaurant Not Found."));
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepo.findAll();
	}

	@Override
	public Restaurant login(String username, String password) {
		Restaurant user = restaurantRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User Not Found."));
		if(!user.getPassword().equals(password)) {
			throw new InvalidPasswordException("Invalid Password.");
		}
		return user;
	}

	@Override
	public List<Restaurant> getAllRestaurantsByPincode(String pincode) {
		return restaurantRepo.findAllByPincode(pincode).orElseThrow(() -> new NotFoundException("Restaurant Not Found."));
	}

	@Override
	public List<Restaurant> getAllRestaurantsByCity(String city) {
		return restaurantRepo.findAllByCity(city).orElseThrow(() -> new NotFoundException("Restaurant Not Found."));
	}
	
}
