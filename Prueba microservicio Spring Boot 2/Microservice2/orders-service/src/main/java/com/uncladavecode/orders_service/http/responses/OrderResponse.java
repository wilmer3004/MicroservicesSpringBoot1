package com.uncladavecode.orders_service.http.responses;


import java.util.List;


public record OrderResponse(Long id, String orderNumber, List<OrderItemsResponse> orderItems) {
}
