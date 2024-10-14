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

import com.fd.entities.CollectedOrder;
import com.fd.service.CollectedOrderService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/collectedorder")
@RestController
public class CollectedOrderController {
	
	@Autowired
	private CollectedOrderService collectedOrderService;

	@PostMapping("/add")
	public ResponseEntity<Object> saveCollectedOrder(@RequestBody CollectedOrder CollectedOrder) {
		CollectedOrder colOrd = collectedOrderService.addCollectedOrder(CollectedOrder);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateCollectedOrder(@RequestBody CollectedOrder collectedOrder) {
		CollectedOrder colOrd = collectedOrderService.editCollectedOrder(collectedOrder);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeCollectedOrder(@PathVariable long id) {
		String msg = collectedOrderService.deleteCollectedOrder(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> CollectedOrderById(@PathVariable long id) {
		CollectedOrder colOrd = collectedOrderService.getCollectedOrderById(id);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> CollectedOrderAll() {
		List<CollectedOrder> colOrd = collectedOrderService.getAllCollectedOrders();
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Object> CollectedOrderByOrder(@PathVariable long orderId) {
		CollectedOrder colOrd = collectedOrderService.getAllCollectedOrderByOrderId(orderId);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/delivery/{deliveryBoyId}")
	public ResponseEntity<Object> CollectedOrderByDeliveryBoy(@PathVariable long deliveryBoyId) {
		List<CollectedOrder> colOrd = collectedOrderService.getAllCollectedOrderByDeliveryBoyId(deliveryBoyId);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/delivered/not/delivery/{deliveryBoyId}")
	public ResponseEntity<Object> CollectedOrderByDeliveryBoyAndNotDelivered(@PathVariable long deliveryBoyId) {
		CollectedOrder colOrd = collectedOrderService.getCollectedOrderByDeliveryBoyIdAndNotDelivered(deliveryBoyId);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/delivered/delivery/{deliveryBoyId}")
	public ResponseEntity<Object> CollectedOrderByDeliveryBoyAndDelivered(@PathVariable long deliveryBoyId) {
		List<CollectedOrder> colOrd = collectedOrderService.getCollectedOrderByDeliveryBoyIdAndDelivered(deliveryBoyId);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
	
	@GetMapping("/delivered/{status}")
	public ResponseEntity<Object> CollectedOrderByDelivered(@PathVariable String isDelivered) {
		List<CollectedOrder> colOrd = collectedOrderService.getAllCollectedOrdersByDelivered(isDelivered);
		return new ResponseEntity<>(colOrd, HttpStatus.OK);
	}
}
