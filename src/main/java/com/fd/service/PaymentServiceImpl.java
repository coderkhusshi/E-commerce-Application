package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.Payment;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public Payment addPayment(Payment payment) {
		return paymentRepo.save(payment);
	}

	@Override
	public Payment editPayment(Payment payment) {
		paymentRepo.findById(payment.getId()).orElseThrow(() -> new NotFoundException("Payment Not Found."));
		return paymentRepo.save(payment);
	}

	@Override
	public String deletePayment(long id) {
		paymentRepo.findById(id).orElseThrow(() -> new NotFoundException("Payment Not Found."));
		paymentRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public Payment getPaymentById(long id) {
		return paymentRepo.findById(id).orElseThrow(() -> new NotFoundException("Payment Not Found."));
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepo.findAll();
	}

	@Override
	public List<Payment> getAllPaymentByCustomerId(long customerId) {
		return paymentRepo.findAllByCustomerId(customerId).orElseThrow(() -> new NotFoundException("Payment Not Found."));
	}
}
