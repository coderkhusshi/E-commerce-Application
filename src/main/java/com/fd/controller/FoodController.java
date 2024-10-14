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

import com.fd.entities.FoodItem;
import com.fd.service.FoodService;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/food")
@RestController
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveFoodItem(@RequestBody FoodItem foodItem) {
		FoodItem item = foodService.addFoodItem(foodItem);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Object> updateFoodItem(@RequestBody FoodItem foodItem) {
		FoodItem item = foodService.editFoodItem(foodItem);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeFoodItem(@PathVariable long id) {
		String msg = foodService.deleteFoodItem(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> foodItemById(@PathVariable long id) {
		FoodItem item = foodService.getFoodItemById(id);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> foodItemAll() {
		List<FoodItem> item = foodService.getAllFoodItem();
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@GetMapping("/all/{restaurant}")
	public ResponseEntity<Object> foodItemAllByRestaurant(@PathVariable String restaurant) {
		List<FoodItem> item = foodService.getByRestaurant(restaurant);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
}
