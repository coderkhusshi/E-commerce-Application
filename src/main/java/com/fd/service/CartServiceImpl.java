package com.fd.service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fd.entities.Cart;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.CartRepositoy;

@Transactional
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepositoy cartRepo;

	@Override
	public Cart addCart(Cart cart) {
		return cartRepo.save(cart);
	}

	@Override
	public String deleteCart(long id) {
		cartRepo.findById(id).orElseThrow(() -> new NotFoundException("Cart Not Found."));
		cartRepo.deleteById(id);
		return "Deleted Successfully.";
	}
	
	@Override
	public String deleteCartByCustomerId(long customerId) {
		cartRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("Cart Not Found."));
		cartRepo.deleteCartItemByCustomerId(customerId);
		return "Deleted";
	}

	@Override
	public Cart editCart(Cart cart) {
		cartRepo.findById(cart.getId()).orElseThrow(() -> new NotFoundException("Cart Not Found."));
		return cartRepo.save(cart);
	}

	@Override
	public Cart getCartById(long id) {
		return cartRepo.findById(id).orElseThrow(() -> new NotFoundException("Cart Not Found."));
	}

	@Override
	public List<Cart> getCartAll() {
		return cartRepo.findAll();
	}

	@Override
	public List<Cart> getCartByCustomerId(long customerId) {
		return cartRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("Cart Not Found."));
	}
	

	@Override
	public List<Cart> getCartByRestaurant(String restaurant) {
		return cartRepo.findAllByRestaurant(restaurant).orElseThrow(() -> new NotFoundException("Cart Not Found."));
	}
	

	@Override
	public long totalPrice(long customerId) {
		
		Optional<Long> value = cartRepo.total(customerId);
		if(!value.isEmpty()) {
			return value.get();
		}
		
		return 0;
	}
	
}
