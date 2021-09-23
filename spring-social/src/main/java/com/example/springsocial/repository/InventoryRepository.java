package com.example.springsocial.repository;

import com.example.springsocial.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String>{
    List<Order> findAll();
//    Optional<Inventory> findByRestaurant(String restaurantID);

//    ResponseEntity<?> findAll(Inventory inventory);
}
