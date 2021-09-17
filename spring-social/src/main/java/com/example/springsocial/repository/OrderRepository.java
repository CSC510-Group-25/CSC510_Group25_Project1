package com.example.springsocial.repository;

import com.example.springsocial.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Repository
@RequestMapping("/orders")
public interface OrderRepository extends JpaRepository<Order, Long> {

//    @PostMapping("/addOrder")
//
//    public ResponseEntity<?> addOrder(@Valid, @RequestBody OrderRequest orderRequest)

}
