package com.fd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fd.entities.Order;
import com.fd.entities.OrderDetails;
import com.fd.service.OrderDetailsService;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/orderdetails")
@RestController
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService detailsService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveOrderDetails(@RequestBody OrderDetails order) {
		OrderDetails ord = detailsService.addOrderDetails(order);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateOrderDetails(@RequestBody OrderDetails order) {
		OrderDetails ord = detailsService.editOrderDetails(order);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeOrderDetails(@PathVariable long id) {
		String msg = detailsService.deleteOrderDetails(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> orderDetailsById(@PathVariable long id) {
		OrderDetails ord = detailsService.getOrderDetailsById(id);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> orderDetailsAll() {
		List<OrderDetails> ord = detailsService.getAllOrderDetails();
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Object> orderDetailsByOrderId(@PathVariable long orderId) {
		List<OrderDetails> ord = detailsService.getAllOrderDetailsByOrderId(orderId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/order/status/restaurant/{restaurant}/{status}")
	public ResponseEntity<Object> orderDetailsByRestaurantStatus(@PathVariable String restaurant ,@PathVariable String status) {
		List<OrderDetails> ord = detailsService.getOrderDetailsByRestaurantStatus(restaurant, status);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/order/status/customer/{customerId}/{status}")
	public ResponseEntity<Object> orderDetailsByCustomerStatus(@PathVariable long customerId ,@PathVariable String status) {
		List<OrderDetails> ord = detailsService.getOrderDetailsByCustomerStatus(customerId, status);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/order/status/notdelivered/customer/{customerId}")
	public ResponseEntity<Object> orderDetailsByStatusNotDelivered(@PathVariable long customerId) {
		List<OrderDetails> ord = detailsService.getOrderDetailsByStatusNotDelivered(customerId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{res}")
	public ResponseEntity<Object> OrderByCustomerId(@PathVariable String res) {
		List<OrderDetails> ord = detailsService.getOrderDetailsByRestaurant(res);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Object> orderDetailsByCustomerId(@PathVariable long customerId) {
		List<OrderDetails> ord = detailsService.getAllOrderDetailsByCustomerId(customerId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}

}
