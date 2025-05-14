package com.surya.image;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageOperationsController {

	private final ImageService imageService;

	@PostMapping(name = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadImages(@RequestParam Integer userId, @RequestParam String location,
			@RequestParam List<MultipartFile> files) {

		String message = imageService.uploadImages(userId, location, files);

		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@PatchMapping("/update/authorization/{imageMetaId}/{status}")
	public ResponseEntity<String> updateEmail(@PathVariable Integer imageMetaId, Boolean status) {

		String message = imageService.changeAuthorization(imageMetaId, status);

		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@GetMapping("/retrive/images/{userId}")
	public ResponseEntity<List<Image>> getImages(@PathVariable Integer userId) {

		List<Image> images = imageService.getMetaData(userId);
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@GetMapping("/retrive/{imageMetaId}")
	public ResponseEntity<ImageMetaData> getImage(@PathVariable Integer imageMetaId) {

		ImageMetaData image = imageService.findByimageMetadataId(imageMetaId);
		return new ResponseEntity<>(image, HttpStatus.OK);
	}
}
