package com.example.springsocial.controller;

import com.example.springsocial.model.Inventory;
import com.example.springsocial.payload.InventoryRequest;
import com.example.springsocial.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@GetMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/addInventory")
    public ResponseEntity<?> addInventory(@Valid @RequestBody InventoryRequest InventoryRequest) {
        
        //Adding new item to inventory
        Inventory inventory = new Inventory();
        inventory.setRestaurantName(InventoryRequest.getRestaurantName);
        inventory.setRestaurantID(InventoryRequest.getRestaurantID);
        inventory.setItemID(InventoryRequest.getItemID);
        inventory.setItemName(InventoryRequest.getItemName);
        inventory.setBatchID(InventoryRequest.getBatchID);
        inventory.setBatchQty(InventoryRequest.getBatchQty);
        inventory.setCostPerItem(InventoryRequest.getCostPerItem);
        inventory.setDateBought(InventoryRequest.getDateBought);
        inventory.setDateExpired(InventoryRequest.getDateExpired);

        Inventory result = inventoryRepository.save(inventory);


        return inventoryRepository.findAll(inventory);    }
}
