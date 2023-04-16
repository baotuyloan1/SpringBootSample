package com.sample.apidemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	ResponseEntity<ResponseObject> getAllProducts() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Query products successful", repository.findAll()));
	}

//	get detailProduct
	@GetMapping("/{id}")
//	Let's return an object with: data, message, status
	ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
		return (foundProduct.isPresent())
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("ok", "Query product successful", foundProduct))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("failed", "Cannot find product with id =" + id, ""));

	}

//	insert new Product with POST method
	@PostMapping("/insert")
	ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
//		products must not have same name!
		List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
		if (foundProducts.size() > 0) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Product name already taken", ""));
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("ok", "Insert Product successfully", repository.save(repository.save(newProduct))));
	}

//	update, upsert = update if found, otherwise insert
	@PutMapping("/{id}")
	ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		Product updatedProduct = repository.findById(id).map(product -> {
			System.out.println(product);
			product.setProductName(newProduct.getProductName());
			product.setYear(newProduct.getYear());
			product.setPrice(newProduct.getPrice());
			product.setUrl(newProduct.getUrl());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);
		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("ok", "Update Product successfully", updatedProduct));
	}

//	delete a product
	@DeleteMapping("/{id}")
	ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
		boolean exists = repository.existsById(id);
		if (exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Delete product successful", ""));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("failed", "Cannot find product to delete", ""));

	}

}
