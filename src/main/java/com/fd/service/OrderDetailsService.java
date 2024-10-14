package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Order;
import com.fd.entities.OrderDetails;

@Service
public interface OrderDetailsService {

	OrderDetails addOrderDetails(OrderDetails orderDetails);
	
	OrderDetails editOrderDetails(OrderDetails orderDetails);
	
	String deleteOrderDetails(long id);
	
	OrderDetails getOrderDetailsById(long id);
	
	List<OrderDetails> getAllOrderDetails();
	
	List<OrderDetails> getAllOrderDetailsByOrderId(long orderId);
	
	List<OrderDetails> getOrderDetailsByRestaurant(String restaurant);

	List<OrderDetails> getAllOrderDetailsByCustomerId(long customerId);

	List<OrderDetails> getOrderDetailsByRestaurantStatus(String restaurant, String status);
	
	List<OrderDetails> getOrderDetailsByCustomerStatus(long customerId, String status);
	
	List<OrderDetails> getOrderDetailsByStatusNotDelivered(long customerId);
}
