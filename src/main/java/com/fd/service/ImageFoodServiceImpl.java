package com.fd.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fd.entities.ImageFood;
import com.fd.exceptions.NotFoundException;
import com.fd.repository.ImageFoodRepository;

@Transactional
@Service
public class ImageFoodServiceImpl implements ImageFoodService {

	@Autowired
	private ImageFoodRepository imageFoodRepo;

	@Override
	public ImageFood addImage(String path, MultipartFile image, long foodId) throws IOException {
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());
		
		String filePath = path + File.separator + imageName;	//full path
		
		File f = new File(path);		//create folder if not created
			if(!f.exists()) {
				f.mkdir();
			}
		
		ImageFood foodImage = new ImageFood(imageName, image.getContentType(), image.getBytes(), foodId);
	    
	   // Files.copy(image.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING); //throws exception
	    
		return imageFoodRepo.save(foodImage);
	}

	@Override
	public ImageFood updateImage(MultipartFile file, long imageFoodId, long foodId) throws IOException {
	     if (!imageFoodRepo.existsById(imageFoodId)) {
	            throw new NotFoundException("Image Not Found.");
	        }

	        String name = StringUtils.cleanPath(file.getOriginalFilename());
	        ImageFood image = new ImageFood(name, file.getContentType(), file.getBytes(),foodId);
	        image.setId(imageFoodId);

	        return imageFoodRepo.save(image);
	}

	@Override
	public String delteImage(long imageFoodId) {
		imageFoodRepo.deleteById(imageFoodId);
		return "Deleted";
	}

	@Override
	public ImageFood getImage(long id) throws IOException {
		return imageFoodRepo.findById(id).orElseThrow(() -> new NotFoundException("Image Not Found."));
	}

	@Override
	public Stream<ImageFood> getAllImages() {
		return imageFoodRepo.findAll().stream();
	}

	@Override
	public ImageFood getImageByFood(long foodId) {
		return imageFoodRepo.findByFoodId(foodId).orElseThrow(() -> new NotFoundException("Image Not Found."));
	}

	@Override
	public long getImageIdByFood(long foodId) {
		return imageFoodRepo.findByFoodId(foodId).orElseThrow(() -> new NotFoundException("Image Not Found.")).getId();
	}
	
	
}
