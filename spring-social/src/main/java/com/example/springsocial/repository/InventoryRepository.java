package com.example.springsocial.repository;

import com.example.springsocial.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
/**
* The inventory repository is a mechanism for encapsulating storage, retrieval, and search behavior which emulates
* a collection of objects.
*/
public interface InventoryRepository extends JpaRepository<Inventory, String>{

    List<Inventory> findAll();

}//    Optional<Inventory> findByRestaurant(String restaurantID);

