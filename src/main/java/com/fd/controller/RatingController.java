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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Rating;
import com.fd.service.RatingService;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/rating")
@RestController
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveRating(@RequestBody Rating rating) {
		Rating rate = ratingService.addRating(rating);
		return new ResponseEntity<>(rate, HttpStatus.OK);
	}
	
//	@PutMapping("/update")
//	public ResponseEntity<Object> updateRating(@RequestBody Rating Rating) {
//		Rating rate = ratingService.editRating(Rating);
//		return new ResponseEntity<>(rate, HttpStatus.OK);
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeRating(@PathVariable long id) {
		String msg = ratingService.deleteRating(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> ratingById(@PathVariable long id) {
		Rating rate = ratingService.getRatingById(id);
		return new ResponseEntity<>(rate, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> ratingAll() {
		List<Rating> ratings = ratingService.getAllRatings();
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
	
	@GetMapping("/id/{customerId}")
	public ResponseEntity<Object> ratingByCustomerId(@PathVariable long customerId) {
		List<Rating> ratings = ratingService.getRatingsByCustomer(customerId);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> ratingByRestaurantId(@PathVariable long restaurantId) {
		List<Rating> ratings = ratingService.getRatingByRestaurant(restaurantId);
		return new ResponseEntity<>(ratings, HttpStatus.OK);
	}
	
	@GetMapping("/count/restaurant/{restaurantId}")
	public ResponseEntity<Object> ratingCountByRestaurantId(@PathVariable long restaurantId) {
		int count = ratingService.getRatingCountByRestaurant(restaurantId);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
}
