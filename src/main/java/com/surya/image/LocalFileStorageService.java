package com.surya.image;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalFileStorageService implements FileStorageService {

	@Value("${upload.dir}")
	private String directory;

	@Override
	public String uploadFile(MultipartFile file) {
		String fileName = getFileName(file.getOriginalFilename());
		String fullPath = directory + File.separator + fileName;

		try (FileOutputStream fos = new FileOutputStream(fullPath)) {
			fos.write(file.getBytes());
			return fullPath;
		} catch (Exception e) {
			throw new FileUploadExecption("Failed to upload file");
		}
	}

	private String getFileName(String originalFilename) {
		return UUID.randomUUID().toString() + "_" + originalFilename;
	}
}
