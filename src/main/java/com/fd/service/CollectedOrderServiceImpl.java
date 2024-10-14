package com.fd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.CollectedOrder;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.CollectedOrderRepository;

@Service
public class CollectedOrderServiceImpl implements CollectedOrderService {
	
	@Autowired
	private CollectedOrderRepository collectedOrderRepo;

	@Override
	public CollectedOrder addCollectedOrder(CollectedOrder collectedOrder) {
		return collectedOrderRepo.save(collectedOrder);
	}

	@Override
	public CollectedOrder editCollectedOrder(CollectedOrder collectedOrder) {
		Optional<CollectedOrder> optColOrd = collectedOrderRepo.findById(collectedOrder.getId());
		if(optColOrd.isEmpty()) {
			throw new NotFoundException("Not Found.");		
		}
		return collectedOrderRepo.save(collectedOrder);
	}

	@Override
	public String deleteCollectedOrder(long id) {
		Optional<CollectedOrder> optColOrd = collectedOrderRepo.findById(id);
		if(optColOrd.isEmpty()) {
			throw new NotFoundException("Not Found.");		
		}
		collectedOrderRepo.deleteById(optColOrd.get().getId());
		return "Deleted";
	}

	@Override
	public CollectedOrder getCollectedOrderById(long id) {
		return collectedOrderRepo.findById(id).orElseThrow(() -> new NotFoundException("Not Found."));
	}

	@Override
	public List<CollectedOrder> getAllCollectedOrders() {
		return collectedOrderRepo.findAll();
	}

	@Override
	public CollectedOrder getAllCollectedOrderByOrderId(long orderId) {
		return collectedOrderRepo.findByOrderId(orderId).orElseThrow(() -> new NotFoundException("Not Found."));
	}

	@Override
	public List<CollectedOrder> getAllCollectedOrderByDeliveryBoyId(long deliveryBoyId) {
		return collectedOrderRepo.findByDeliveryBoyId(deliveryBoyId).orElseThrow(() -> new NotFoundException("Not Found."));
	}

	@Override
	public CollectedOrder getCollectedOrderByDeliveryBoyIdAndNotDelivered(long deliveryBoyId) {
		return collectedOrderRepo.findByDeliveryBoyIdAndNotDelivered(deliveryBoyId).orElseThrow(() -> new NotFoundException("Not Found."));
	}

	@Override
	public List<CollectedOrder> getCollectedOrderByDeliveryBoyIdAndDelivered(long deliveryBoyId) {
		return collectedOrderRepo.findByDeliveryBoyIdAndDelivered(deliveryBoyId).orElseThrow(() -> new NotFoundException("Not Found."));
	}
	
	@Override
	public List<CollectedOrder> getAllCollectedOrdersByDelivered(String isDelivered) {
		return collectedOrderRepo.findAllByisDelivered(isDelivered).orElseThrow(() -> new NotFoundException("Not Found."));
	}

}
