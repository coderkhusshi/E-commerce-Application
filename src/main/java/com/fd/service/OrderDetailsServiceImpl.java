package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Order;
import com.fd.entities.OrderDetails;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.OrderDetailsRepository;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository detailsRepo;

	@Override
	public OrderDetails addOrderDetails(OrderDetails orderDetails) {
		return detailsRepo.save(orderDetails);
	}

	@Override
	public OrderDetails editOrderDetails(OrderDetails orderDetails) {
		detailsRepo.findById(orderDetails.getId()).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
		return detailsRepo.save(orderDetails);
	}

	@Override
	public String deleteOrderDetails(long id) {
		detailsRepo.findById(id).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
		detailsRepo.deleteById(id);
		return "Deleted Successfully";
	}

	@Override
	public OrderDetails getOrderDetailsById(long id) {
		return detailsRepo.findById(id).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		return detailsRepo.findAll();
	}

	@Override
	public List<OrderDetails> getAllOrderDetailsByOrderId(long id) {
		return detailsRepo.findAllByOrderId(id).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsByRestaurant(String restaurant) {
		return detailsRepo.findAllByRestaurant(restaurant).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}

	@Override
	public List<OrderDetails> getOrderDetailsByRestaurantStatus(String restaurant, String status) {
		return detailsRepo.findAllByRestaurantOrderStatus(restaurant, status).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}
	
	@Override
	public List<OrderDetails> getOrderDetailsByCustomerStatus(long customerId, String status) {
		return detailsRepo.findAllByCustomerOrderStatus(customerId, status).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}

	@Override
	public List<OrderDetails> getAllOrderDetailsByCustomerId(long customerId) {
		return detailsRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}

	@Override
	public List<OrderDetails> getOrderDetailsByStatusNotDelivered(long customerId) {
		return detailsRepo.findAllByStatusNotDelivered(customerId).orElseThrow(() -> new NotFoundException("Order Details Not Found."));
	}
}
