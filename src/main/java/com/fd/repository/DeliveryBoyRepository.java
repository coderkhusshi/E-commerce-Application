package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fd.entities.DeliveryBoy;

public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, Long> {

	Optional<DeliveryBoy> findByUsername(String username);
	
	Optional<List<DeliveryBoy>> findAllByCity(String city);
	
	Optional<List<DeliveryBoy>> findAllByPincode(String pincode);
	
	boolean existsByUsername(String username);
}