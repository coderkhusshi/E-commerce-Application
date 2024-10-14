package com.fd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.ImageFood;

@Repository
public interface ImageFoodRepository extends JpaRepository<ImageFood, Long> {

	Optional<ImageFood> findByFoodId(long foodId);
}
