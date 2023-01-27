package com.dmantz.productservice;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.dmantz.productservice.dto.ProductRequest;
import com.dmantz.productservice.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@EnableWebMvc
@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class ProductServiceApplicationTests {

	@Container // junit5 will understand this is mongodb container
	// define mongodb container inside test
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));


	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper; // to convert pojo object to string
	
	@Autowired
	private ProductRepository productRepository;

	// creating mongodb uri dynamically at the time of testing
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	// testing createProduct method in the controller
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequeString = objectMapper.writeValueAsString(productRequest);

		// mock mvc to make the request to productcontroller
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequeString))
				.andExpect(status().isCreated());
	Assertions.assertEquals(1, productRepository.findAll().size()); //checking the productrepository contains how many values are there
	
	
	}
	
	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iPhone")
				.description("iPhone")
				.price(BigDecimal.valueOf(12200))
				.build();
	}

}
