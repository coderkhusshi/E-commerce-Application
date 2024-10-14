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

import com.fd.entities.Customer;
import com.fd.model.AuthRequest;
import com.fd.service.CustomerServiceImpl;


@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/customer")
@RestController
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;


	@PostMapping("/add")
	public ResponseEntity<Object> saveCustomer(@RequestBody Customer customer) {
		Customer cust = customerService.addCustomer(customer);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer) {
		Customer cust = customerService.editCustomer(customer);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeCustomer(@PathVariable long id) {
		String msg = customerService.deleteCustomer(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Object> CustomerById(@PathVariable long id) {
		Customer cust = customerService.getCustomerById(id);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<Object> customerAll() {
		List<Customer> cust = customerService.getAllCustomers();
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@GetMapping("/city/{city}")
	public ResponseEntity<Object> customerAllByCity(@PathVariable String city) {
		List<Customer> cust = customerService.getAllCustomersByCity(city);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@GetMapping("/pincode/{pincode}")
	public ResponseEntity<Object> customerAllByPincode(@PathVariable String pincode) {
		List<Customer> cust = customerService.getAllCustomersByPincode(pincode);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}

	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Object> customerLogin(@PathVariable String username, @PathVariable String password) {
		Customer cust = customerService.login(username, password);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
}
