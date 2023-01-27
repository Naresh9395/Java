package com.dmantz.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dmantz.productservice.model.Product;

//generic argument is product, second generic argument is string

public interface ProductRepository extends MongoRepository<Product, String> {

}
