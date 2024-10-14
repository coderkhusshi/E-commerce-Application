package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.FoodItem;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem, Long>{

	Optional<List<FoodItem>> findAllByRestaurant(String restaurant);
}
