package com.sample.apidemo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sample.apidemo.services.ImageStorageService;

@Repository
public class ImageStoreRepository {

	@Autowired
	private ImageStorageService imageStorageService;

	public String storeFile(MultipartFile file) {
		return imageStorageService.storeFile(file);
	}

}
