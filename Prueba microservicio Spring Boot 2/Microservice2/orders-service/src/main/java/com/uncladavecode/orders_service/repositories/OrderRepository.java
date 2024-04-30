package com.uncladavecode.orders_service.repositories;

import com.uncladavecode.orders_service.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
