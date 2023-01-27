package com.dmantz.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dmantz.productservice.dto.ProductRequest;
import com.dmantz.productservice.dto.ProductResponse;
import com.dmantz.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

//Exposing rest API
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	// inject the projectservice into product controller
	private final ProductService productService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		// we are creating a product with the body in the class ProductRequest from dto
		// package
		// to create the product we write the business logic in service layer

		// calling product service inside createProduct method
		productService.createProduct(productRequest);

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProduct() {
		return productService.getAllProducts();
	}

}
