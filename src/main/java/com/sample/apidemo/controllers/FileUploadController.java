package com.sample.apidemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.apidemo.models.ResponseObject;
import com.sample.apidemo.repositories.ImageStoreRepository;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {

	@Autowired
	private ImageStoreRepository imageStoreRepository;

	@PostMapping("")
	public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String generatedFileName = imageStoreRepository.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "upload file successful", generatedFileName));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Upload file failed", ""));
		}

	}
	
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
		
	}
}
