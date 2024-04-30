package com.uncladavecode.products_service.repositories;

import com.uncladavecode.products_service.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

}
