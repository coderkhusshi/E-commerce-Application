package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Rating;


@Service
public interface RatingService {

	Rating addRating(Rating rating);
	
	String deleteRating(long id);
	
	Rating getRatingById(long id);
	
	List<Rating> getAllRatings();
	
	List<Rating> getRatingsByCustomer(long customerId);
	
	List<Rating> getRatingByRestaurant(long restaurantId);

	int getRatingCountByRestaurant(long restaurantId);
}
