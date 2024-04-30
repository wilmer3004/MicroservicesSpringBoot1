package com.uncladavecode.orders_service.http.responses;

public record OrderItemsResponse(Long id, String sku,Double price, Long quantity) {
}
