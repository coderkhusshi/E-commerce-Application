package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByUsername(String username);
	
	Optional<List<Customer>> findAllByCity(String city);
	
	Optional<List<Customer>> findAllByPincode(String pincode);
	
	boolean existsByUsername(String username);
}
