package com.sample.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.apidemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
