package com.surya.image;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	public String uploadFile(MultipartFile file);

}
