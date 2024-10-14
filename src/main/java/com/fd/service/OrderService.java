package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Order;


@Service
public interface OrderService {

	Order addOrder(Order order);
	
	Order editOrder(Order order);
	
	String deleteOrder(long id);
	
	Order getOrderById(long id);
	
	List<Order> getAllOrder();
	
	List<Order> getOrderByCustomer(long customerId);
	
	List<Order> getOrderByStatus(String status);
	
	List<Order> getOrderByCollected(boolean isCollected);
}
