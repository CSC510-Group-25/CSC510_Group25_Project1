package com.example.springsocial.controller;

import com.example.springsocial.model.Inventory;
import com.example.springsocial.model.Order;
import com.example.springsocial.payload.InventoryRequest;
import com.example.springsocial.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/addInventory")
    public String addInventory(@Valid @RequestBody InventoryRequest InventoryRequest) {

        //Adding new item to inventory
        Inventory inventory = new Inventory();
        inventory.setRestaurantName(InventoryRequest.getRestaurantName());
        inventory.setRestaurantID(InventoryRequest.getRestaurantID());
        inventory.setItemName(InventoryRequest.getItemName());
        inventory.setBatchID(InventoryRequest.getBatchID());
        inventory.setBatchQty(InventoryRequest.getBatchQty());
        inventory.setCostPerItem(InventoryRequest.getCostPerItem());
        inventory.setDateBought(InventoryRequest.getDateBought());
        inventory.setDateExpired(InventoryRequest.getDateExpired());

        Inventory result = inventoryRepository.save(inventory);


//        return inventoryRepository.findAll(inventory);
        return "{Inventory Added Successfully}";

    }

    @GetMapping("/getAllInventory")
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
}