package com.fd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.ImageRestaurant;

@Repository
public interface ImageRestaurantRepository extends JpaRepository<ImageRestaurant, Long>{

	Optional<ImageRestaurant> findByRestaurantId(long restaurantId);
}