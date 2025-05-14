package com.surya.image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.surya.messages.MessageConstants;
import com.surya.user.IUser;
import com.surya.user.UserEntity;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

	private FileStorageService storage;

	private IUser userService;

	private ImageRepo imageRepo;

	private ImageMetaDataRepo metaDataRepo;

	@Override
	@Transactional
	public String uploadImages(Integer userId, String location, List<MultipartFile> files) {

		UserEntity userEntity = userService.getUserById(userId);

		Image image = new Image(userEntity, location);

		List<ImageMetaData> metaDataList = new ArrayList<>();
		for (MultipartFile multipartFile : files) {

			String filePath = storage.uploadFile(multipartFile);

			ImageMetaData metaData = new ImageMetaData();

			metaData.setDateTime(LocalDateTime.now());
			metaData.setFileName(multipartFile.getOriginalFilename());
			metaData.setFilePath(filePath);
			metaData.setImage(image);

			metaDataList.add(metaData);

		}

		image.setUser(userEntity);
		image.setMetaData(metaDataList);

		imageRepo.save(image);

		return MessageConstants.UPLOAD_SUCCESS_MESSAGE;

	}

	@Override
	public String changeAuthorization(Integer imageMetaDataId, Boolean authorization) {

		ImageMetaData imageMetaData = findByimageMetadataId(imageMetaDataId);

		imageMetaData.setIsAuthorized(authorization);

		metaDataRepo.save(imageMetaData);

		return MessageConstants.AUTHORIZATION_CHANGE_SUCCESS_MESS;
	}

	@Override
	public List<Image> getMetaData(Integer userId) {

		UserEntity userEntity = userService.getUserById(userId);
		List<Image> images = imageRepo.findByUser(userEntity);

		return images;
	}

	@Override
	public ImageMetaData findByimageMetadataId(Integer metaDataId) {

		return metaDataRepo.findById(metaDataId).orElseThrow(
				() -> new ImageMetaDataNotFoundException(ImageMetaDataNotFoundException.getMessage(metaDataId)));
	}

}
