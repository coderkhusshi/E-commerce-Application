package com.fd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Restaurant;
import com.fd.model.AuthRequest;
import com.fd.service.RestaurantServiceImpl;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/restaurant")
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantServiceImpl restaurantService;
	
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant res = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant res = restaurantService.editRestaurant(restaurant);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeRestaurant(@PathVariable long id) {
		String msg = restaurantService.deleteRestaurant(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> restaurantById(@PathVariable long id) {
		Restaurant res = restaurantService.getRestaurantById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> restaurantAll() {
		List<Restaurant> res = restaurantService.getAllRestaurants();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/pincode/{pincode}")
	public ResponseEntity<Object> restaurantByPincode(@PathVariable String pincode) {
		List<Restaurant> res = restaurantService.getAllRestaurantsByPincode(pincode);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<Object> restaurantByCity(@PathVariable String city) {
		List<Restaurant> res = restaurantService.getAllRestaurantsByCity(city);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Object> restaurantLogin(@PathVariable String username, @PathVariable String password) {
		Restaurant res = restaurantService.login(username, password);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}
