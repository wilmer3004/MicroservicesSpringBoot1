package com.uncladavecode.inventory_service.http.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long Id;
    private String sku;
    private Double price;
    private Long quantity;
}
