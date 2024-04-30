package com.uncladavecode.inventory_service.services;

import com.uncladavecode.inventory_service.entities.InventoryEntity;
import com.uncladavecode.inventory_service.http.requests.OrderItemRequest;
import com.uncladavecode.inventory_service.http.responses.BaseResponse;
import com.uncladavecode.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    public boolean isInStock(String sku) {
        var inventory = inventoryRepository.findBySku(sku);

        return inventory.filter(value -> value.getQuantity() > 0).isPresent();
    }

    public BaseResponse areInStock(List<OrderItemRequest> orderItems) {

        var  errorList = new ArrayList<String>();

        List<String> skus = orderItems.stream()
                .map(OrderItemRequest::getSku)
                .toList();

        List<InventoryEntity> inventoryList = inventoryRepository.findBySkuIn(skus);

        orderItems.forEach(orderItem -> {
            var invetory = inventoryList.stream().filter(value -> value.getSku().equals(orderItem.getSku())).findFirst();
            if (invetory.isEmpty()) {
                errorList.add("Product with SKU " + orderItem.getSku() + " not found");
            } else if (invetory.get().getQuantity() < orderItem.getQuantity()) {
                errorList.add("Product with SKU " + orderItem.getSku() + " is out of stock");
            }
        });

        return errorList.size() > 0 ? new BaseResponse(errorList.toArray(new String[0])) : new BaseResponse(null);
    }
}
