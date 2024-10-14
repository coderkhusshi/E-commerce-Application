package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.Payment;

@Service
public interface PaymentService {

	Payment addPayment(Payment payment);
	
	Payment editPayment(Payment payment);
	
	String deletePayment(long id);
	
	Payment getPaymentById(long id);
	
	List<Payment> getAllPayments();
	
	List<Payment> getAllPaymentByCustomerId(long customerId);
}
