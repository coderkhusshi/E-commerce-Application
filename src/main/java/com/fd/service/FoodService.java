package com.fd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fd.entities.FoodItem;

@Service
public interface FoodService {

	FoodItem addFoodItem(FoodItem foodItem);
	
	FoodItem editFoodItem(FoodItem foodItem);
	
	String deleteFoodItem(long id);
	
	FoodItem getFoodItemById(long id);
	
	List<FoodItem> getAllFoodItem();
	
	List<FoodItem> getByRestaurant(String restaurant);
}
