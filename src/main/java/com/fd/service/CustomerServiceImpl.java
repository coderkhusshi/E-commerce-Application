package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Customer;
import com.fd.exceptions.AlreadyPresentException;
import com.fd.exceptions.InvalidPasswordException;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.CustomerRepository;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepo;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws NotFoundException {
//		
//		Customer customer = customerRepo.findByUsername(username).get();
//		
//		return new User(customer.getUsername(), customer.getPassword(), new ArrayList<>());
//
//		//return new User("tanmay21","tanmay21", new ArrayList<>());
//
//	}

	@Override
	public Customer addCustomer(Customer customer) {
		if(customerRepo.existsByUsername(customer.getUsername())) {
			throw new AlreadyPresentException("Username not available");
		}
		return customerRepo.save(customer);
	}

	@Override
	public Customer editCustomer(Customer customer) {
		customerRepo.findById(customer.getId()).orElseThrow(() -> new NotFoundException("User Not Found."));
		return customerRepo.save(customer);
	}

	@Override
	public String deleteCustomer(long id) {
		customerRepo.findById(id).orElseThrow(() -> new NotFoundException("customer Not Found."));
		customerRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerRepo.findById(id).orElseThrow(() -> new NotFoundException("customer Not Found."));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer login(String username, String password) {
		Customer user = customerRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("Invalid Username or Password."));
		if(!user.getPassword().equals(password)) {
			throw new InvalidPasswordException("Invalid Username or Password.");
		}
		return user;
	}

	@Override
	public List<Customer> getAllCustomersByPincode(String pincode) {
		return customerRepo.findAllByPincode(pincode).orElseThrow(() -> new NotFoundException("Customer Not Found."));
	}

	@Override
	public List<Customer> getAllCustomersByCity(String city) {
		return customerRepo.findAllByCity(city).orElseThrow(() -> new NotFoundException("Customer Not Found."));
	}
}
