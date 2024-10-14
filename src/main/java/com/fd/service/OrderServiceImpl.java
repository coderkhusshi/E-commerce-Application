package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Order;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public Order addOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public Order editOrder(Order order) {
		orderRepo.findById(order.getId()).orElseThrow(() -> new NotFoundException("Order Not Found."));
		return orderRepo.save(order);
	}

	@Override
	public String deleteOrder(long id) {
		orderRepo.findById(id).orElseThrow(() -> new NotFoundException("No Order Found."));
		orderRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public Order getOrderById(long id) {
		return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("Order Not Found."));
	}

	@Override
	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	@Override
	public List<Order> getOrderByCustomer(long customerId) {
		return orderRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("No Orders Found."));
	}

	@Override
	public List<Order> getOrderByStatus(String status) {
		return orderRepo.findAllByStatus(status).orElseThrow(() -> new NotFoundException("No Orders Found."));
	}

	@Override
	public List<Order> getOrderByCollected(boolean isCollected) {
		return orderRepo.findAllByCollected(isCollected).orElseThrow(() -> new NotFoundException("No Orders Found."));
	}

}
