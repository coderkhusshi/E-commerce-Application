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

import com.fd.entities.DeliveryBoy;
import com.fd.model.AuthRequest;
import com.fd.service.DeliveryBoyService;

@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/deliveryboy")
@RestController
public class DeliveryBoyController {

	@Autowired
	private DeliveryBoyService deliveryboyService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> saveDeliveryBoy(@RequestBody DeliveryBoy deliveryboy) {
		DeliveryBoy cust = deliveryboyService.addDeliveryBoy(deliveryboy);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateDeliveryBoy(@RequestBody DeliveryBoy deliveryboy) {
		DeliveryBoy cust = deliveryboyService.editDeliveryBoy(deliveryboy);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removeDeliveryBoy(@PathVariable long id) {
		String msg = deliveryboyService.deleteDeliveryBoy(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> deliveryBoyById(@PathVariable long id) {
		DeliveryBoy cust = deliveryboyService.getDeliveryBoyById(id);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Object> DeliveryBoyAll() {
		List<DeliveryBoy> cust = deliveryboyService.getAllDeliveryBoys();
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<Object> deliveryBoyAllByCity(@PathVariable String city) {
		List<DeliveryBoy> cust = deliveryboyService.getAllDeliveryBoysByCity(city);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}	
	
	@GetMapping("/pincode/{pincode}")
	public ResponseEntity<Object> deliveryBoyAllByPincode(@PathVariable String pincode) {
		List<DeliveryBoy> cust = deliveryboyService.getAllDeliveryBoysByPincode(pincode);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<Object> deliveryBoyLogin(@PathVariable String username, @PathVariable String password) {
		DeliveryBoy cust = deliveryboyService.login(username, password);
		return new ResponseEntity<>(cust, HttpStatus.OK);
	}
}
