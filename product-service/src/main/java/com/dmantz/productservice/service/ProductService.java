package com.dmantz.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.dmantz.productservice.dto.ProductRequest;
import com.dmantz.productservice.dto.ProductResponse;
import com.dmantz.productservice.model.Product;
import com.dmantz.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

	// inject product repository into service class
	private final ProductRepository productRepository;

	// constructor
//	public ProductService(ProductRepository productRepository) {
//		this.productRepository = productRepository;
//	}

	public void createProduct(ProductRequest productRequest) {

		// map the product request to product model
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();				
		// create the object of type product
		// instance of the product object

		// save the product object into database
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());

	}

	public List<ProductResponse> getAllProducts() {
	
	List<Product> products = productRepository.findAll(); //read all the products inside the db ans store it in variable products

	//map product class into product response class 
	
	return products.stream().map(this::mapToProductResponse).toList(); //map each product into product response object
	}

	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
		
		//object of product response created and returing all back to getAllproducts method

	}

}
