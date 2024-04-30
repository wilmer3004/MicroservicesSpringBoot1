package com.uncladavecode.products_service.http.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String sku;
    private String name;
    private String description;
    private Double price;
    private Boolean status;
}
