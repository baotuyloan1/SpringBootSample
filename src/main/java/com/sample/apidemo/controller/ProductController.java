package com.sample.apidemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.apidemo.model.Product;
import com.sample.apidemo.model.ResponseObject;
import com.sample.apidemo.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {

//	DI = Dependency Injection
	@Autowired
	private ProductRepository repository;

	@GetMapping("")
//	this request is: http://localhost:8080/api/v1/Products
	List<Product> getAllProducts() {
		return repository.findAll();
	}

//	get detailProduct
	@GetMapping("/{id}")
//	Let's return an object with: data, message, status
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
		return (foundProduct.isPresent()
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Query product successfully", foundProduct))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Cannot find product with id =" + id, ""));

	}

//	insert new Product with POST method
	ResponseEntity<ResponseObject> insertProduct(@RequestBody)
}
