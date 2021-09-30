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
/**
 * What: The Inventory Controller is responsible for processing incoming REST API's requests for Inventory based operations.
 *
 * How: The common use cases of this class is to allow the user to add/edit/remove inventory from the database by
 * processing the user request from the frontend.
 */
@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @PostMapping("/addInventory")
    /**
    * This method is called when the user requests to add a new item into the inventory database.
    * It does this by taking the request and then setting the parameters given into their respective variables of the
    * database and then saving all the information into the database.
    */
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

    /**
    * This method is used to call all the items in the inventory.
    */
    public List<Inventory> getAllInventory(){
        return inventoryRepository.findAll();
    }
}