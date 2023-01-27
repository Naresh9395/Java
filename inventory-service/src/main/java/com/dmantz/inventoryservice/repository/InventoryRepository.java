package com.dmantz.inventoryservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmantz.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	//jparepository takes two args type
	
	Optional<Inventory> findBySkuCode();
 
	
	
}
