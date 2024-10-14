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

import com.fd.entities.ImageFood;
import com.fd.service.ImageFoodService;

@RestController
@RequestMapping("/image/food")
@CrossOrigin(origins = "http://localhost:3000/")
public class ImageFoodController {

	@Autowired
	private ImageFoodService foodService;
	
	@Value("${project.food.images}")
	private String path;
	
	
	@PostMapping(value="/upload/{foodId}", consumes="multipart/form-data")
	public ResponseEntity<String> uploadFile(@RequestParam("foodImage") MultipartFile foodImage,
											@PathVariable long foodId) {

		String message = "";
		try {
			foodService.addImage(path, foodImage, foodId);
			message = "Uploaded the photo successfully: " + foodImage.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the photo: " + foodImage.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
		}
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageById(@PathVariable long id) throws IOException {
		ImageFood foodImage = foodService.getImage(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foodImage.getName() + "\"")
				.body(foodImage.getData());
	}
	
	@GetMapping(value = "/fd/{foodId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImageByFoodId(@PathVariable long foodId) throws IOException {
		ImageFood foodImage = foodService.getImageByFood(foodId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foodImage.getName() + "\"")
				.body(foodImage.getData());
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ImageFood>> getFiles() {

		List<ImageFood> files = foodService.getAllImages().map(dbPhoto -> {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/food/images/")
//					.path(dbPhoto.getId()).toUriString();

			return new ImageFood(dbPhoto.getId(), dbPhoto.getName(), dbPhoto.getType(),  dbPhoto.getFoodId(),
					dbPhoto.getData());
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	
	@PutMapping(value="/update/{foodImageId}/{foodId}", consumes="multipart/form-data")
	public ResponseEntity<String> updateFile(@RequestParam("foodImage") MultipartFile foodImage, 
												@PathVariable long foodImageId, 
												@PathVariable long foodId) {
		
		String message = "";
		try {
			foodService.updateImage(foodImage, foodImageId, foodId);
			message = "Updated the photo successfully: " + foodImage.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not update the photo: " + foodImage.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
		}
	}
	
	@DeleteMapping("/delete/{foodImageId}")
	public ResponseEntity<String> deleteFile(@PathVariable long foodImageId) {
		String msg = foodService.delteImage(foodImageId);
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@GetMapping("/imageid/{foodId}")
	public ResponseEntity<Long> getFileIdByFood(@PathVariable long foodId) {
		long id = foodService.getImageIdByFood(foodId);
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	
}
