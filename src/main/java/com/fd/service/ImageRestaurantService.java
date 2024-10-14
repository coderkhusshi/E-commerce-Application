package com.fd.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fd.entities.ImageRestaurant;

@Service
public interface ImageRestaurantService {

	ImageRestaurant addImage(String path, MultipartFile image, long restaurantId) throws IOException;

	ImageRestaurant updateImage(MultipartFile file, long imageRestaurantId, long restaurantId) throws IOException;
	
	String delteImage(long imageRestaurantId);
	
	ImageRestaurant getImage(long id) throws IOException;	
	
	Stream<ImageRestaurant> getAllImages();
	
	ImageRestaurant getImageByRestaurant(long restaurantId);
	
	long getImageIdByRestaurant(long restaurantId);
}
