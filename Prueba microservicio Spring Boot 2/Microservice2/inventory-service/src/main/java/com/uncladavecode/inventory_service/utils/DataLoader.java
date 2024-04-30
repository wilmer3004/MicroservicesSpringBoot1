package com.uncladavecode.inventory_service.utils;

import com.uncladavecode.inventory_service.entities.InventoryEntity;
import com.uncladavecode.inventory_service.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");
        if(inventoryRepository.findAll().size() == 0) {
            inventoryRepository.saveAll(
                    (Iterable<InventoryEntity>) List.of(
                            InventoryEntity.builder().sku("00001").quantity(10L).build(),
                            InventoryEntity.builder().sku("00002").quantity(20L).build(),
                            InventoryEntity.builder().sku("00003").quantity(30L).build(),
                            InventoryEntity.builder().sku("00004").quantity(0L).build()
                    )
            );

        }
        log.info("Loading data complete.");
    }

}
