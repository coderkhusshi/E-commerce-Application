package com.fd.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fd.entities.ImageFood;

@Service
public interface ImageFoodService {

	ImageFood addImage(String path, MultipartFile image, long foodId) throws IOException;

	ImageFood updateImage(MultipartFile file, long imageFoodId, long foodId) throws IOException;
	
	String delteImage(long imageFoodId);
	
	ImageFood getImage(long id) throws IOException;	
	
	Stream<ImageFood> getAllImages();
	
	ImageFood getImageByFood(long foodId);
	
	long getImageIdByFood(long foodId);
}
