package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fd.entities.Order;
import com.fd.entities.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

	Optional<List<OrderDetails>> findAllByOrderId(long orderId);
	
	Optional<List<OrderDetails>> findAllByRestaurant(String restaurant);
	
	Optional<List<OrderDetails>> findAllByCustomerId(long customerId);

	@Query(value = "SELECT * FROM ORDER_DETAILS WHERE RESTAURANT = :restaurant and STATUS = :status", nativeQuery = true)
	Optional<List<OrderDetails>> findAllByRestaurantOrderStatus(String restaurant, String status);

	@Query(value = "SELECT * FROM ORDER_DETAILS WHERE CUSTOMER_ID = :customerId and STATUS = :status", nativeQuery = true)
	Optional<List<OrderDetails>> findAllByCustomerOrderStatus(long customerId, String status);

	@Query(value = "SELECT * FROM ORDER_DETAILS WHERE CUSTOMER_ID = :customerId and STATUS != 'Delivered'", nativeQuery = true)
	Optional<List<OrderDetails>> findAllByStatusNotDelivered(long customerId);
}
