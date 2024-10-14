package com.fd.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fd.entities.Cart;

@Transactional
@Service
public interface CartService {

	Cart addCart(Cart cart);
	
	String deleteCart(long id);
	
	String deleteCartByCustomerId(long customerId);
	
	Cart editCart(Cart cart);
	
	Cart getCartById(long id);
	
	List<Cart> getCartAll();
	
	List<Cart> getCartByCustomerId(long customerId);
	
	List<Cart> getCartByRestaurant(String restaurant);
	
	long totalPrice(long customerId);
}
