package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.CollectedOrder;

@Service
public interface CollectedOrderService {

	CollectedOrder addCollectedOrder(CollectedOrder collectedOrder);

	CollectedOrder editCollectedOrder(CollectedOrder collectedOrder);
	
	String deleteCollectedOrder(long id);
	
	CollectedOrder getCollectedOrderById(long id);
	
	List<CollectedOrder> getAllCollectedOrders();
	
	CollectedOrder getAllCollectedOrderByOrderId(long orderId);
	
	List<CollectedOrder> getAllCollectedOrderByDeliveryBoyId(long deliveryBoyId);

	CollectedOrder getCollectedOrderByDeliveryBoyIdAndNotDelivered(long deliveryBoyId);
	
	List<CollectedOrder> getCollectedOrderByDeliveryBoyIdAndDelivered(long deliveryBoyId);
	
	List<CollectedOrder> getAllCollectedOrdersByDelivered(String isDelivered);	
}
