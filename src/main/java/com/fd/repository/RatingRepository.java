package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fd.entities.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

	Optional<List<Rating>> findAllByRestaurantId(long restaurantId);
	
	Optional<List<Rating>> findAllByCustomerId(long customerId);
	
	Optional<List<Rating>> findAllByRate(long rate);
	
	@Query(value = "SELECT COUNT(*) FROM RATING WHERE RESTAURANT_ID = :restaurantId", nativeQuery = true)
	int countOfRatingsByRestaurantId(long restaurantId);
}
