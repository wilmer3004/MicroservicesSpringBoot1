package com.uncladavecode.inventory_service.repositories;

import com.uncladavecode.inventory_service.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
    Optional<InventoryEntity> findBySku(String sku);

    List<InventoryEntity> findBySkuIn(List<String> skus);

}
