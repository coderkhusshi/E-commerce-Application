package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.DeliveryBoy;
import com.fd.exceptions.AlreadyPresentException;
import com.fd.exceptions.InvalidPasswordException;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.DeliveryBoyRepository;


@Service
public class DeliveryBoyServiceImpl implements DeliveryBoyService {

	@Autowired
	private DeliveryBoyRepository deliveryBoyRepo;

	@Override
	public DeliveryBoy addDeliveryBoy(DeliveryBoy deliveryBoy) {
		if(deliveryBoyRepo.existsByUsername(deliveryBoy.getUsername())) {
			throw new AlreadyPresentException("Username not available");
		}
		return deliveryBoyRepo.save(deliveryBoy);
	}

	@Override
	public DeliveryBoy editDeliveryBoy(DeliveryBoy deliveryBoy) {
		deliveryBoyRepo.findById(deliveryBoy.getId()).orElseThrow(() -> new NotFoundException("User Not Found."));
		return deliveryBoyRepo.save(deliveryBoy);
	}

	@Override
	public String deleteDeliveryBoy(long id) {
		deliveryBoyRepo.findById(id).orElseThrow(() -> new NotFoundException("customer Not Found."));
		deliveryBoyRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public DeliveryBoy getDeliveryBoyById(long id) {
		return deliveryBoyRepo.findById(id).orElseThrow(() -> new NotFoundException("customer Not Found."));
	}

	@Override
	public List<DeliveryBoy> getAllDeliveryBoys() {
		return deliveryBoyRepo.findAll();
	}

	@Override
	public DeliveryBoy login(String username, String password) {
		DeliveryBoy user = deliveryBoyRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("User Not Found."));
		if(!user.getPassword().equals(password)) {
			throw new InvalidPasswordException("Invalid Password.");
		}
		return user;
	}

	@Override
	public List<DeliveryBoy> getAllDeliveryBoysByPincode(String pincode) {
		return deliveryBoyRepo.findAllByPincode(pincode).orElseThrow(() -> new NotFoundException("Customer Not Found."));
	}

	@Override
	public List<DeliveryBoy> getAllDeliveryBoysByCity(String city) {
		return deliveryBoyRepo.findAllByCity(city).orElseThrow(() -> new NotFoundException("Customer Not Found."));
	}
}
