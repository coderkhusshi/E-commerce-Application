package com.fd.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fd.entities.ImageRestaurant;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.ImageRestaurantRepository;

@Transactional
@Service
public class ImageRestaurantServiceImpl implements ImageRestaurantService {

	@Autowired
	private ImageRestaurantRepository imageRestaurantRepo;
	
	@Override
	public ImageRestaurant addImage(String path, MultipartFile image, long restaurantId) throws IOException {

		String imageName = StringUtils.cleanPath(image.getOriginalFilename());
		
		String filePath = path + File.separator + imageName;	//full path
		
		File f = new File(path);		//create folder if not created
			if(!f.exists()) {
				f.mkdir();
			}
		
		ImageRestaurant restuarantImage = new ImageRestaurant(imageName, image.getContentType(), image.getBytes(), restaurantId);
	    
	   // Files.copy(image.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING); //throws exception
	    
		return imageRestaurantRepo.save(restuarantImage);
	}

	@Override
	public ImageRestaurant updateImage(MultipartFile file, long imageRestaurantId, long restaurantId) throws IOException {
	     if (!imageRestaurantRepo.existsById(imageRestaurantId)) {
	            throw new NotFoundException("Image Not Found.");
	        }

	        String name = StringUtils.cleanPath(file.getOriginalFilename());
	        ImageRestaurant image = new ImageRestaurant(name, file.getContentType(), file.getBytes(), restaurantId);
	        image.setId(imageRestaurantId);

	        return imageRestaurantRepo.save(image);
	}

	@Override
	public ImageRestaurant getImage(long id) throws IOException {
		return imageRestaurantRepo.findById(id).orElseThrow(() -> new NotFoundException("Image Not Found."));
	}

	@Override
	public Stream<ImageRestaurant> getAllImages() {
		return imageRestaurantRepo.findAll().stream();
	}

	@Override
	public ImageRestaurant getImageByRestaurant(long restaurantId) {
		return imageRestaurantRepo.findByRestaurantId(restaurantId).orElseThrow(() -> new NotFoundException("Image Not Found."));
	}

	@Override
	public String delteImage(long imageRestaurantId) {
		imageRestaurantRepo.deleteById(imageRestaurantId);
		return "Deleted";
	}

	@Override
	public long getImageIdByRestaurant(long restaurantId) {
		return imageRestaurantRepo.findByRestaurantId(restaurantId).orElseThrow(() -> new NotFoundException("Image Not Found.")).getId();
	}

}
