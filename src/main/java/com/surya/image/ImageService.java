package com.surya.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	public String uploadImages(Integer userId, String location, List<MultipartFile> files);

	public String changeAuthorization(Integer imageMetaId, Boolean authorization);

	public List<Image> getMetaData(Integer userId);

	public ImageMetaData findByimageMetadataId(Integer metaDataId);

}
