package com.example.springsocial.controller;

import com.example.springsocial.model.Inventory;
import com.example.springsocial.payload.LowInventoryRequest;
import com.example.springsocial.payload.NotificationAboutToExpireRequest;
import com.example.springsocial.payload.NotificationExpiredRequest;
import com.example.springsocial.repository.InventoryRepository;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Validated
public class NotificationController {

    private Logger logger = Logger.getLogger(AuthController.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/fetch-notifications")
    public String fetchNotifications(@RequestParam("restaurant_id") String restaurant_id) {

        // Fetch all inventory items that are about to expire

        List<Inventory> inventoryItems = inventoryRepository.findInventoryByRestaurantID(restaurant_id);

        String inventoryJson = new Gson().toJson(inventoryItems);

        return inventoryJson;
    }

    @PostMapping("/findExpiredInventory")
    public String fetchExpiredInventoryItems(@Valid @RequestBody NotificationExpiredRequest notificationExpiredRequest) {
        List<Inventory> expiredInventoryItems = inventoryRepository.findInventoryByRestaurantNameAndDateExpiredLessThan(notificationExpiredRequest.restaurant_name, new Date());

        String inventoryItemsExpired = new Gson().toJson(expiredInventoryItems);

        return inventoryItemsExpired;

    }

    @PostMapping("/findAboutToExpireInventory")
    public String fetchExpiredInventoryItems(@Valid @RequestBody NotificationAboutToExpireRequest notificationAboutToExpireRequest) {

        DateTime dt = new DateTime();
        dt = dt.plusDays(notificationAboutToExpireRequest.expires_in_days);
        Date dateAfterNDays = dt.toDate();

        logger.info("After date: " + dateAfterNDays.toString());

        List<Inventory> aboutToExpireInventoryItems = inventoryRepository.findInventoryByRestaurantNameAndDateExpiredBetween(notificationAboutToExpireRequest.restaurant_name, new Date(), dateAfterNDays);

        String aboutToExpireInventoryItemsJSON = new Gson().toJson(aboutToExpireInventoryItems);

        return aboutToExpireInventoryItemsJSON;

    }

    @PostMapping("/findLowQuantityInventoryItems")
    public String fetchLowQuantityInventoryItems(@Valid @RequestBody LowInventoryRequest lowInventoryRequest) {

        List<Inventory> lowQuantityItems = inventoryRepository.findInventoryByRestaurantNameAndBatchQtyLessThanEqual(lowInventoryRequest.restaurant_name, lowInventoryRequest.max_qty);

        String lowQuantityItemsJson = new Gson().toJson(lowQuantityItems);

        return lowQuantityItemsJson;

    }

}
