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

import com.fd.entities.Cart;
import com.fd.service.CartService;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/cart")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveCart(@RequestBody Cart cart) {
		Cart car = cartService.addCart(cart);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateCart(@RequestBody Cart cart) {
		Cart car = cartService.editCart(cart);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeCart(@PathVariable long id) {
		String msg = cartService.deleteCart(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/customer/{customerId}")
	public ResponseEntity<Object> removeCartByCustomer(@PathVariable long customerId) {
		String msg = cartService.deleteCartByCustomerId(customerId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> CartById(@PathVariable long id) {
		Cart car = cartService.getCartById(id);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> CartAll() {
		List<Cart> cartList = cartService.getCartAll();
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Object> CartAllByCustomer(@PathVariable long customerId) {
		List<Cart> cartList = cartService.getCartByCustomerId(customerId);
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{res}")
	public ResponseEntity<Object> CartAllByRestaurant(@PathVariable String res) {
		List<Cart> cartList = cartService.getCartByRestaurant(res);
		return new ResponseEntity<>(cartList, HttpStatus.OK);
	}
	
	
	@GetMapping("/customer/total/{customerId}")
	public ResponseEntity<Object> cartTotal(@PathVariable long customerId) {
		long total = cartService.totalPrice(customerId);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
}
