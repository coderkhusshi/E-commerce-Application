package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fd.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<List<Order>> findAllByCustomerId(long id);
	
	Optional<List<Order>> findAllByStatus(String status);
	
	@Query(value = "SELECT * FROM ORDERS WHERE COLLECTED = :isCollected", nativeQuery = true)
	Optional<List<Order>> findAllByCollected(boolean isCollected);
}
