package com.fd.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fd.entities.ImageRestaurant;
import com.fd.service.ImageRestaurantService;

@RestController
@RequestMapping("/image/restaurant")
@CrossOrigin(origins = "http://localhost:3000/")
public class ImageRestaurantController {
	
	@Autowired
	private ImageRestaurantService imageRestaurantService;  
	
	@Value("${project.restaurant.images}")
	private String path;

	@PostMapping(value="/upload/{restaurantId}", consumes="multipart/form-data")
	public ResponseEntity<String> uploadFile(@RequestParam("restaurantImage") MultipartFile restaurantImage,
			@PathVariable long restaurantId) {

		String message = "";
		try {
			imageRestaurantService.addImage(path, restaurantImage, restaurantId);
			message = "Uploaded the photo successfully: " + restaurantImage.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the photo: " + restaurantImage.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageById(@PathVariable long id) throws IOException {
		ImageRestaurant restaurantImage = imageRestaurantService.getImage(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + restaurantImage.getName() + "\"")
				.body(restaurantImage.getData());
	}
	
	@GetMapping(value = "/res/{restaurantId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageByRestaurantId(@PathVariable long restaurantId) throws IOException {
		ImageRestaurant restaurantImage = imageRestaurantService.getImageByRestaurant(restaurantId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + restaurantImage.getName() + "\"")
				.body(restaurantImage.getData());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ImageRestaurant>> getFiles() {

		List<ImageRestaurant> files = imageRestaurantService.getAllImages().map(dbPhoto -> {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/restaurant/images/")
//					.path(dbPhoto.getId()).toUriString();

			return new ImageRestaurant(dbPhoto.getId(), dbPhoto.getName(), dbPhoto.getType(),  dbPhoto.getRestaurantId(),
					dbPhoto.getData());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@PutMapping(value="/update/{restaurantImageId}/{restaurantId}", consumes="multipart/form-data")
	public ResponseEntity<String> updateFile(@RequestParam("restaurantImage") MultipartFile restaurantImage, 
												@PathVariable long restaurantImageId, 
												@PathVariable long restaurantId) {
		
		String message = "";
		try {
			imageRestaurantService.updateImage(restaurantImage, restaurantImageId, restaurantId);
			message = "Updated the photo successfully: " + restaurantImage.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not update the photo: " + restaurantImage.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/delete/{restaurantImageId}")
	public ResponseEntity<String> deleteFile(@PathVariable long restaurantImageId) {
		String msg = imageRestaurantService.delteImage(restaurantImageId);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@GetMapping("/imageid/{restaurantId}")
	public ResponseEntity<Long> getFileIdByRestaurant(@PathVariable long restaurantId) {
		long id = imageRestaurantService.getImageIdByRestaurant(restaurantId);
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
}
