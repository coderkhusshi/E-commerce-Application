package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fd.entities.Cart;

@Repository
public interface CartRepositoy extends JpaRepository<Cart, Long> {

	Optional<List<Cart>> findAllByCustomerId(long customerId);
	
	Optional<List<Cart>> findAllByRestaurant(String restaurant);
	
	@Modifying
	@Query(value="DELETE FROM CART c where c.customer_id = :customerId", nativeQuery = true)
	Optional<Integer> deleteCartItemByCustomerId(@Param("customerId") long customerId);
	
	@Query(value="SELECT SUM(c.price) FROM CART c where c.customer_id = ?", nativeQuery = true)
	Optional<Long> total(long customerId);
}
