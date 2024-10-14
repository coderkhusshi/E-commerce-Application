package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Rating;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepo;

	@Override
	public Rating addRating(Rating rating) {
		return ratingRepo.save(rating);
	}

	@Override
	public String deleteRating(long id) {
		ratingRepo.findById(id).orElseThrow(() -> new NotFoundException("Rating Not Found."));
		ratingRepo.deleteById(id);
		return "Deleted Successfully";
	}

	@Override
	public Rating getRatingById(long id) {
		return ratingRepo.findById(id).orElseThrow(() -> new NotFoundException("Rating Not Found."));
	}

	@Override
	public List<Rating> getAllRatings() {
		return ratingRepo.findAll();
	}

	@Override
	public List<Rating> getRatingsByCustomer(long customerId) {
		return ratingRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("Rating Not Found."));
	}

	@Override
	public List<Rating> getRatingByRestaurant(long restaurantId) {
		return ratingRepo.findAllByRestaurantId(restaurantId).orElseThrow(() -> new NotFoundException("Rating Not Found."));
	}

	@Override
	public int getRatingCountByRestaurant(long restaurantId) {
		return ratingRepo.countOfRatingsByRestaurantId(restaurantId);
	}
}
