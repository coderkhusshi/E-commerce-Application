package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.DeliveryBoy;

@Service
public interface DeliveryBoyService {

	DeliveryBoy addDeliveryBoy(DeliveryBoy deliveryBoy);
	
	DeliveryBoy editDeliveryBoy(DeliveryBoy deliveryBoy);
	
	String deleteDeliveryBoy(long id);
	
	DeliveryBoy getDeliveryBoyById(long id);
	
	List<DeliveryBoy> getAllDeliveryBoys();

	List<DeliveryBoy> getAllDeliveryBoysByPincode(String pincode);

	List<DeliveryBoy> getAllDeliveryBoysByCity(String city);
	
	DeliveryBoy login(String username, String password);
}
