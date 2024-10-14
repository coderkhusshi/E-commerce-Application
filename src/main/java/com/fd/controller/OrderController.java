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
import com.fd.service.OrderService;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/order")
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveOrder(@RequestBody Order order) {
		Order ord = orderService.addOrder(order);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateOrder(@RequestBody Order order) {
		Order ord = orderService.editOrder(order);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeOrder(@PathVariable long id) {
		String msg = orderService.deleteOrder(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> OrderById(@PathVariable long id) {
		Order ord = orderService.getOrderById(id);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> OrderAll() {
		List<Order> ord = orderService.getAllOrder();
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Object> OrderByCustomerId(@PathVariable long customerId) {
		List<Order> ord = orderService.getOrderByCustomer(customerId);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}

	@GetMapping("/status/{sts}")
	public ResponseEntity<Object> OrderByStatus(@PathVariable String status) {
		List<Order> ord = orderService.getOrderByStatus(status);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}

	@GetMapping("/collected/{isCollected}")
	public ResponseEntity<Object> OrderByCollected(@PathVariable boolean isCollected) {
		List<Order> ord = orderService.getOrderByCollected(isCollected);
		return new ResponseEntity<>(ord, HttpStatus.OK);
	}
}
