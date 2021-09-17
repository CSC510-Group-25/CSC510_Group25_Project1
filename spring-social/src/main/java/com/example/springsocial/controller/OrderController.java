package com.example.springsocial.controller;

import com.example.springsocial.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;


}
