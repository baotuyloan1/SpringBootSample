package com.sample.apidemo.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

@Service
public class ImageStorageService implements IStorageService {

	private final Path storageFolder = Paths.get("uploads");

	private boolean isImageFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "png", "jpg", "jpeg", "bmp" }).contains(fileExtension.trim().toLowerCase());
	}

	public ImageStorageService() {
		try {
			Files.createDirectories(storageFolder);
		} catch (IOException e) {
			throw new RuntimeException("Cannot initialize storage", e);
		}
	}

	@Override
	public String storeFile(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new RuntimeException("Failed to store empty file");
			}

//			Check file is image
			if (!isImageFile(file)) {
				throw new RuntimeException("You can only upload image file");
			}

//			file must be <= 5Mb
			float fileSizeInMegabytes = file.getSize() / 1_000_000;
			if (fileSizeInMegabytes > 5.0f) {
				throw new RuntimeException("File must be <= 5Mb");
			}

//			rename filw
			String fileExtention = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedFileName = UUID.randomUUID().toString().replace("-", "");
			generatedFileName = generatedFileName + "." + fileExtention;
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize()
					.toAbsolutePath();
			if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
				throw new RuntimeException("Cannot store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);

			}
			return generatedFileName;

		} catch (Exception e) {
			throw new RuntimeException("Failed to store file.", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] readFileContent(String fileName) {
//		Path file = storageFolder.resolve(fileName);
//		try {
//			Resource resource = (Resource) new UrlResource(file.toString());
////			if(resource.ex)
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return null;

	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub

	}

}
