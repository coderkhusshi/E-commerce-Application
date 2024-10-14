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

import com.fd.entities.Payment;
import com.fd.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/payment")
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> savePayment(@RequestBody Payment payment) {
		Payment pay = paymentService.addPayment(payment);
		return new ResponseEntity<>(pay, HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Object> updatePayment(@RequestBody Payment payment) {
		Payment pay = paymentService.editPayment(payment);
		return new ResponseEntity<>(pay, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removePayment(@PathVariable long id) {
		String msg = paymentService.deletePayment(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> paymentById(@PathVariable long id) {
		Payment pay = paymentService.getPaymentById(id);
		return new ResponseEntity<>(pay, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> paymentAll() {
		List<Payment> pay = paymentService.getAllPayments();
		return new ResponseEntity<>(pay, HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Object> paymentByCustomerId(@PathVariable long customerId) {
		List<Payment> pay = paymentService.getAllPaymentByCustomerId(customerId);
		return new ResponseEntity<>(pay, HttpStatus.OK);
	}
}
