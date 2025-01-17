package com.fd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fd.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Optional<List<Payment>> findAllByCustomerId(long customerId);
}
