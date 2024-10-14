package com.fd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fd.entities.FoodItem;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository foodRepo;

	@Override
	public FoodItem addFoodItem(FoodItem foodItem) {
		return foodRepo.save(foodItem);
	}

	@Override
	public FoodItem editFoodItem(FoodItem foodItem) {
		foodRepo.findById(foodItem.getId()).orElseThrow(() -> new NotFoundException("Item Not Found."));
		return foodRepo.save(foodItem);
	}

	@Override
	public String deleteFoodItem(long id) {
		foodRepo.findById(id).orElseThrow(() -> new NotFoundException("Item Not Found."));
		foodRepo.deleteById(id);
		return "Deleted Successfully.";
	}

	@Override
	public FoodItem getFoodItemById(long id) {
		return foodRepo.findById(id).orElseThrow(() -> new NotFoundException("Item Not Found."));
	}

	@Override
	public List<FoodItem> getAllFoodItem() {
		return foodRepo.findAll();
	}

	@Override
	public List<FoodItem> getByRestaurant(String restaurant) {
		return foodRepo.findAllByRestaurant(restaurant).get();
	}

}
