package com.sample.apidemo.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	public String storeFile(MultipartFile file);

	public Stream<Path> loadAll();

	public byte[] readFileContent(String fileName);

	public void deleteAllFiles();
}
