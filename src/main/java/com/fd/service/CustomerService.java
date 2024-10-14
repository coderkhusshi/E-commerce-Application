package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Customer;

@Service
public interface CustomerService {

	Customer addCustomer(Customer customer);
	
	Customer editCustomer(Customer customer);
	
	String deleteCustomer(long id);
	
	Customer getCustomerById(long id);
	
	List<Customer> getAllCustomers();

	List<Customer> getAllCustomersByPincode(String pincode);

	List<Customer> getAllCustomersByCity(String city);
	
	Customer login(String username, String password);
}
