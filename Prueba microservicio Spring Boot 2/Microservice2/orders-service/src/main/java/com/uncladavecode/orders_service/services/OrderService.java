package com.uncladavecode.orders_service.services;

import com.uncladavecode.orders_service.entities.OrderEntity;
import com.uncladavecode.orders_service.entities.OrderItem;
import com.uncladavecode.orders_service.http.requests.OrderItemRequest;
import com.uncladavecode.orders_service.http.requests.OrderRequest;
import com.uncladavecode.orders_service.http.responses.BaseResponse;
import com.uncladavecode.orders_service.http.responses.OrderItemsResponse;
import com.uncladavecode.orders_service.http.responses.OrderResponse;
import com.uncladavecode.orders_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;


    public void placeOrder(OrderRequest orderRequest) {


        //Check for inventory
        BaseResponse result = this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/inventory/in-stock")
                .bodyValue(orderRequest.getOrderItems())
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();

        if(result != null && !result.hasErrors()){

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderNumber(UUID.randomUUID().toString());
            orderEntity.setOrderItems(orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> mapOrderItemRequestToOrderItem(orderItemRequest, orderEntity))
                    .toList());
            this.orderRepository.save(orderEntity);
        }else{
            throw new IllegalArgumentException("Some of the products are not in stock");
        }

    }

    public List<OrderResponse> getAllOrders(){
        List<OrderEntity> orders = this.orderRepository.findAll();
        return orders.stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    private OrderResponse mapToOrderResponse(OrderEntity orderEntity) {
        return new OrderResponse(orderEntity.getId(),orderEntity.getOrderNumber(),
                orderEntity.getOrderItems().stream().map(this::mapToOrderItemResponse).toList()
        );
    }

    private OrderItemsResponse mapToOrderItemResponse(OrderItem orderItem) {
        return new OrderItemsResponse(orderItem.getId(),orderItem.getSku(),orderItem.getPrice(),orderItem.getQuantity());
    }


    private OrderItem mapOrderItemRequestToOrderItem(OrderItemRequest orderItemRequest, OrderEntity orderEntity) {
        return OrderItem.builder()
                .id(orderItemRequest.getId())
                .sku(orderItemRequest.getSku())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .order(orderEntity)
                .build();
    }
}
